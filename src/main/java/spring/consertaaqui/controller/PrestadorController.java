package spring.consertaaqui.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spring.consertaaqui.model.Contrato;
import spring.consertaaqui.model.Servico;
import spring.consertaaqui.model.Solicitacao;
import spring.consertaaqui.model.Usuario;
import spring.consertaaqui.repository.AvaliacaoRepository;
import spring.consertaaqui.repository.ContratoRepository;
import spring.consertaaqui.repository.ServicoRepository;
import spring.consertaaqui.repository.SolicitacaoRepository;
import spring.consertaaqui.repository.UsuarioRepository;

@Controller
public class PrestadorController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/perfilprestador")
	public ModelAndView perfilPrestador(HttpSession session) {		
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		ModelAndView mv= new ModelAndView("perfil");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("jan", servicoRepository.countServicosByIdJan(u.get().getId()));
		mv.addObject("fev", servicoRepository.countServicosByIdFev(u.get().getId()));
		mv.addObject("mar", servicoRepository.countServicosByIdMar(u.get().getId()));
		mv.addObject("abr", servicoRepository.countServicosByIdAbr(u.get().getId()));
		mv.addObject("mai", servicoRepository.countServicosByIdMai(u.get().getId()));
		mv.addObject("jun", servicoRepository.countServicosByIdJun(u.get().getId()));
		mv.addObject("jul", servicoRepository.countServicosByIdJul(u.get().getId()));
		mv.addObject("ago", servicoRepository.countServicosByIdAgo(u.get().getId()));
		mv.addObject("set", servicoRepository.countServicosByIdSet(u.get().getId()));
		mv.addObject("out", servicoRepository.countServicosByIdOut(u.get().getId()));
		mv.addObject("nov", servicoRepository.countServicosByIdNov(u.get().getId()));
		mv.addObject("dez", servicoRepository.countServicosByIdDez(u.get().getId()));
		mv.addObject("cinco", avaliacaoRepository.countEstrelaById5(u.get().getId()));
		mv.addObject("quatro", avaliacaoRepository.countEstrelaById4(u.get().getId()));
		mv.addObject("tres", avaliacaoRepository.countEstrelaById3(u.get().getId()));
		mv.addObject("dois", avaliacaoRepository.countEstrelaById2(u.get().getId()));
		mv.addObject("um", avaliacaoRepository.countEstrelaById1(u.get().getId()));
		mv.addObject("qtdSolicitacao", solicitacaoRepository.countSolicitacaoById(u.get().getId()));
		mv.addObject("qtdServico", servicoRepository.countServicoById(u.get().getId()));
		mv.addObject("qtdContrato", contratoRepository.countContratoById(u.get().getId()));
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/recusarsolicitacao/{idp}")
	public ModelAndView recusarSolicitacao(@PathVariable("idp") Long idp) {	
		
		Solicitacao s= solicitacaoRepository.findById(idp).get();
		s.setEstado("Negado");
		solicitacaoRepository.save(s);
		
		ModelAndView mv= new ModelAndView("redirect:/solicitacoes");
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/aceitarsolicitacao/{idp}")
	public ModelAndView aceitarSolicitacao(@PathVariable("idp") Long idp) {	
		
		Solicitacao s= solicitacaoRepository.findById(idp).get();
		
		Contrato contrato= new Contrato();
		contrato.setUsuario(s.getUsuario());
		contrato.setServico(s.getServico());
		contrato.setPreco(s.getServico().getPreco());
		contrato.setDataInicio(s.getDataInicio());
		contrato.setEstado("Em andamento");

		contratoRepository.save(contrato);
		solicitacaoRepository.delete(s);
		
		ModelAndView mv= new ModelAndView("redirect:/andamento");
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/servicofinalizado/{idp}")
	public ModelAndView finalizarServico(@PathVariable("idp") Long idp, HttpSession session) {	
		
		Contrato c= contratoRepository.findById(idp).get();
		LocalDate data= LocalDate.now();
		
		c.setEstado("Finalizado");
		c.setDataFim(data.toString());
		
		data=null;
		contratoRepository.save(c);
		
		ModelAndView modelAndView= new ModelAndView("redirect:/contratos");		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/avaliacoes")
	public ModelAndView avaliacoesPrestador(HttpSession session) {	
			
		ModelAndView modelAndView= new ModelAndView("avaliacoes");		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/desativar")
	public ModelAndView desativarPrestador(HttpSession session) {	
			
		ModelAndView modelAndView= new ModelAndView("desativar");		
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/divulgados")
	public ModelAndView divulgadosPrestador(HttpSession session) {	
			
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		ModelAndView mv= new ModelAndView("servicosdivulgados");					
		mv.addObject("servicoOB", new Servico());		
		mv.addObject("servicos", servicoRepository.getServicos(u.get().getId()));		
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		
		return mv;				
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/solicitacoes")
	public ModelAndView solicitacoesP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("solicitacoes");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());		
		mv.addObject("solicitacoes", solicitacaoRepository.getSolicitacoesAnaliseP(u.get().getId()));	
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/contratos")
	public ModelAndView contratosP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("contratos");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("contratosP", contratoRepository.getContratoFinalizadoP(u.get().getId()));;
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/andamento")
	public ModelAndView andamentoP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("andamento");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("contratos", contratoRepository.getContratoAndamentoP(u.get().getId()));	
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/meusdados**")
	public ModelAndView meusdadosP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("meusdados");								
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/alterarprestador")
	public ModelAndView salvarDadosAlteradosPrestador(Usuario u, HttpSession session, @RequestParam("filePerfil") MultipartFile file) {	
		
		Optional<Usuario> us = usuarioRepository.findById(u.getId());
	
		try {
			
			if(!(file.getBytes()==null)) {
				us.get().setImagem(file.getBytes());
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		us.get().setNome(u.getContato1());
		us.get().setNome(u.getContato2());
		us.get().setNome(u.getDataNascimento());
		us.get().setNome(u.getEmail());
		us.get().setNome(u.getGenero());
		us.get().setNome(u.getBairro());
		us.get().setNome(u.getRua());
		us.get().setNome(u.getZona());
		us.get().setNome(u.getNome());
		us.get().setDescricao(u.getDescricao());
		
		if(!u.getSenha().equals("")){
			us.get().setSenha(u.getSenha());
		}
		
		usuarioRepository.save(us.get());
			
		session.setAttribute("usuarioLogado", us);								
		ModelAndView modelAndView= new ModelAndView("redirect:/meusdados");
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/desativarconta")
	public ModelAndView desativarTelaP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("desativarconta");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());				
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/desativa")
	public ModelAndView desativarContaPrestador(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha, @RequestParam("motivo") String motivo) {
			
		usuarioRepository.deleteById(cpf);
		ModelAndView mv= new ModelAndView("redirect:/");
		return mv;
	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/prestadorperfil/{idp}")
	public ModelAndView perfilPublicoPrestador(@PathVariable("idp") String idp,HttpSession session) {
		
		Optional<Usuario> us= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		Optional<Usuario> u= usuarioRepository.findById(idp);
		
		ModelAndView mv= new ModelAndView("publicop");
		mv.addObject("prestadorOB", u.get());
		mv.addObject("clienteOB", us.get());
		mv.addObject("tipo", us.get().getTipoUsuario());
		mv.addObject("servicos", servicoRepository.getServicos(u.get().getId()));	
		mv.addObject("cinco", avaliacaoRepository.countEstrelaById5(u.get().getId()));
		mv.addObject("quatro", avaliacaoRepository.countEstrelaById4(u.get().getId()));
		mv.addObject("tres", avaliacaoRepository.countEstrelaById3(u.get().getId()));
		mv.addObject("dois", avaliacaoRepository.countEstrelaById2(u.get().getId()));
		mv.addObject("um", avaliacaoRepository.countEstrelaById1(u.get().getId()));
		return mv;
	
	}
}
