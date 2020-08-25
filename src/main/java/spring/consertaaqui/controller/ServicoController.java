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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import spring.consertaaqui.model.Servico;
import spring.consertaaqui.model.Usuario;
import spring.consertaaqui.repository.ContratoRepository;
import spring.consertaaqui.repository.ServicoRepository;
import spring.consertaaqui.repository.SolicitacaoRepository;

@Controller
public class ServicoController {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ContratoRepository contratoRepository;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = {RequestMethod.POST}, value = "/divulgarservico")
	public ModelAndView divulgarServico(Servico servico, @RequestParam("fileServico") MultipartFile file, HttpSession session) {	
		
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");	
				
		servico.setUsuario(u.get());														
		
		try {
			servico.setImagem(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LocalDate data= LocalDate.now();
		servico.setData(data.toString());		
		servicoRepository.save(servico);		
	    data=null;
		ModelAndView mv= new ModelAndView("redirect:/divulgados");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deletarservico/{servicoid}**")
	public ModelAndView deletarServico(@PathVariable("servicoid") Long servicoid) {	
	
		Optional<Servico> s= servicoRepository.findById(servicoid);
		
		if(s.isPresent()) {
			if(s.get().getSolicitacoes().isEmpty()){
				solicitacaoRepository.deleteAllById(s.get().getUsuario().getId());
			}
			
			if(s.get().getContratos().isEmpty()){
					contratoRepository.deleteAllById(s.get().getUsuario().getId());
			}
			servicoRepository.deleteById(servicoid);
		}		
	
		ModelAndView mv= new ModelAndView("redirect:/divulgados");
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/alterarservico/{servicoid}")
	public ModelAndView exibirServico(@PathVariable("servicoid") Long servicoid, HttpSession session) {	
	
		Optional<Usuario> u= (Optional<Usuario>) session.getAttribute("usuarioLogado");
		
		Optional<Servico> s= servicoRepository.findById(servicoid);
		
		ModelAndView mv= new ModelAndView("alterarservico");
		mv.addObject("servicoOB", s.get());
		mv.addObject("usuarioOB", u.get());		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/alterars")
	public ModelAndView alterarServico(Servico s,@RequestParam("fileServico") MultipartFile file) {	
	
		Optional<Servico> se= servicoRepository.findById(s.getId());
		
		try {
			
			if(!(file.getBytes()==null)) {
				se.get().setImagem(file.getBytes());
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		se.get().setCategoria(s.getCategoria());
		se.get().setDescricao(s.getDescricao());
		se.get().setTitulo(s.getTitulo());
		se.get().setZona(s.getZona());
		se.get().setPreco(s.getPreco());
		
		servicoRepository.save(se.get());
		
		ModelAndView mv= new ModelAndView("redirect:/divulgados");	
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/imagem/{idservico}")
	@ResponseBody
	public byte[] exibirImagemServico(@PathVariable("idservico") Long idservico) {	
		
		Servico s= servicoRepository.findById(idservico).get();
		
		return s.getImagem();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/alvenaria")
	public ModelAndView servicosAlvenaria() {
		
		ModelAndView modelAndView= new ModelAndView("telaalvenaria");		
		modelAndView.addObject("servicos", servicoRepository.getAlvenaria());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eletrica")
	public ModelAndView servicosEletrica() {
		
		ModelAndView modelAndView= new ModelAndView("telaeletrica");		
		modelAndView.addObject("servicos", servicoRepository.getEletrica());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hidraulica")
	public ModelAndView servicosHidraulica() {
		
		ModelAndView modelAndView= new ModelAndView("telahidraulica");		
		modelAndView.addObject("servicos", servicoRepository.getHidraulica());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pintura")
	public ModelAndView servicosPintura() {
		
		ModelAndView modelAndView= new ModelAndView("telapintura");		
		modelAndView.addObject("servicos", servicoRepository.getPintura());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reparos")
	public ModelAndView servicosReparos() {
		
		ModelAndView modelAndView= new ModelAndView("telareparos");		
		modelAndView.addObject("servicos", servicoRepository.getReparos());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/refrigeracao")
	public ModelAndView servicosRefrigeracao() {
		
		ModelAndView modelAndView= new ModelAndView("telarefrigeracao");		
		modelAndView.addObject("servicos", servicoRepository.getRefrigeracao());
		
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/servicos")
	public ModelAndView telaServicos(){
	
		ModelAndView modelAndView= new ModelAndView("telaservicos");
		modelAndView.addObject("servicos", servicoRepository.findAll());
		modelAndView.addObject("servicoOB", new Servico());
		return modelAndView;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView home(){
	
		ModelAndView modelAndView= new ModelAndView("index");
		modelAndView.addObject("servicos", servicoRepository.findAll());
		return modelAndView;
		
	}
}
