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

import spring.consertaaqui.model.Avaliacao;
import spring.consertaaqui.model.Servico;
import spring.consertaaqui.model.Solicitacao;
import spring.consertaaqui.model.Usuario;
import spring.consertaaqui.repository.AvaliacaoRepository;
import spring.consertaaqui.repository.ContratoRepository;
import spring.consertaaqui.repository.ServicoRepository;
import spring.consertaaqui.repository.SolicitacaoRepository;
import spring.consertaaqui.repository.UsuarioRepository;

@Controller
public class ClienteController {
	
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
	@RequestMapping(method = RequestMethod.GET, value = "/perfilcliente")
	public ModelAndView perfilCliente(HttpSession session) {
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		ModelAndView mv= new ModelAndView("meusdadosc");		
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/solicitarservico/{idp}")
	public ModelAndView solicitarServico(@PathVariable("idp") Long idp, HttpSession session) {	
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");

		Servico s=servicoRepository.findById(idp).get();
		Optional<Usuario> us= usuarioRepository.findById(u.get().getId());
	
		LocalDate data= LocalDate.now(); 
		
		Solicitacao so= new Solicitacao();		
		so.setServico(s);
		so.setUsuario(us.get());
		so.setDataInicio(data.toString());
		so.setEstado("Análise");
		
		solicitacaoRepository.save(so);
		
		data=null;
		ModelAndView mv= new ModelAndView("redirect:/");
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/solicitarservicos/{idp}")
	public ModelAndView solicitarServico2(@PathVariable("idp") Long idp, HttpSession session) {	
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");

		Servico s=servicoRepository.findById(idp).get();
		Optional<Usuario> us= usuarioRepository.findById(u.get().getId());
	
		LocalDate data= LocalDate.now(); 
		
		Solicitacao so= new Solicitacao();		
		so.setServico(s);
		so.setUsuario(us.get());
		so.setDataInicio(data.toString());
		so.setEstado("Análise");
		
		solicitacaoRepository.save(so);
		
		data=null;
		ModelAndView mv= new ModelAndView("redirect:/servicos");
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletarsolicitacao/{idp}")
	public ModelAndView recusarProcesso(@PathVariable("idp") Long idp) {	
		
		Solicitacao s= solicitacaoRepository.findById(idp).get();
		solicitacaoRepository.delete(s);
		
		ModelAndView modelAndView= new ModelAndView("redirect:/perfilcliente");
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/desativarc")
	public ModelAndView desativarContaPrestador(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha, @RequestParam("motivo") String motivo) {
			
		usuarioRepository.deleteById(cpf);
		ModelAndView mv= new ModelAndView("redirect:/");
		return mv;
	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/solicitacoesc")
	public ModelAndView solicitacoesC(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("solicitacoesc");
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("solicitacoes", solicitacaoRepository.getSolicitacoesCliente(u.get().getId()));	
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/contratosc")
	public ModelAndView contratosP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("contratosc");	
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("contratosF", contratoRepository.getContratoFinalizadoC(u.get().getId()));
		mv.addObject("avaliacaoOB", new Avaliacao());
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/andamentoc")
	public ModelAndView andamentoP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("andamentoc");
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("avaliacaoOB", new Avaliacao());
		mv.addObject("contratos", contratoRepository.getContratoAndamentoC(u.get().getId()));	
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/meusdadosc")
	public ModelAndView meusdadosP(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("meusdadosc");
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		return mv;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/interesse")
	public ModelAndView perfilInteresse(HttpSession session) {
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		ModelAndView mv= new ModelAndView("interesse");
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());
		mv.addObject("usuarios", usuarioRepository.getUsuarioP());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/alterarcliente")
	public ModelAndView salvarDadosAlteradosCliente(Usuario u, HttpSession session, @RequestParam("filePerfil") MultipartFile file) {	
		
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
		
		if(!u.getSenha().equals("")){
			us.get().setSenha(u.getSenha());
		}
		
		usuarioRepository.save(us.get());
			
		session.setAttribute("usuarioLogado", us);								
		ModelAndView modelAndView= new ModelAndView("redirect:/meusdadosc");
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/desativarcontac")
	public ModelAndView desativarTelaC(HttpSession session) {	

		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");		
		ModelAndView mv= new ModelAndView("desativarcontac");
		mv.addObject("clienteOB", u.get());
		mv.addObject("tipo", u.get().getTipoUsuario());				
		return mv;
		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/avaliarcontrato")
	public ModelAndView avaliar(Avaliacao av, @RequestParam(value ="servicoID",  required = true) Long servicoID, HttpSession session) {							
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		LocalDate data= LocalDate.now();
		av.setData(data.toString());	
		av.setUsuario(u.get());
		av.setServico(servicoRepository.findById(servicoID).get());
		avaliacaoRepository.save(av);
		data=null;
		
		ModelAndView modelAndView= new ModelAndView("redirect:/andamentoc");		
		return modelAndView;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/avaliacoesc")
	public ModelAndView minhasAvaliacoes(Avaliacao av,HttpSession session) {							
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		ModelAndView mv= new ModelAndView("avaliacoesc");
		mv.addObject("avaliacoes", avaliacaoRepository.getAvaliacaoC(u.get().getId()));			
		return mv;
		
	}
}
