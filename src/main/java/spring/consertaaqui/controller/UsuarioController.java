package spring.consertaaqui.controller;

import java.io.IOException;
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

import spring.consertaaqui.model.Usuario;
import spring.consertaaqui.repository.UsuarioRepository;

@Controller
public class UsuarioController {
 
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public ModelAndView cadastroUsuario() {
		
		ModelAndView mv= new ModelAndView("cadastrousuario");
		mv.addObject("usuarioOB", new Usuario());	
				
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public ModelAndView salvarUsuario(Usuario u, @RequestParam("filePerfil") MultipartFile file, HttpSession session) {
		
		
		try {
			u.setImagem(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		usuarioRepository.save(u);		
		ModelAndView mv= new ModelAndView("redirect:/login");		
		session.setAttribute("usuarioLogado", usuarioRepository.findById(u.getId()));								
		return mv;
		
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView login() {
		
		ModelAndView modelAndView= new ModelAndView("login");		
		return modelAndView;		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/entrarusuario")
	public ModelAndView LoginP(@RequestParam("id") String id, @RequestParam("senha") String senha, HttpSession session) {
		
		Optional<Usuario> p= usuarioRepository.findById(id);		
		String erro= "Cpf ou senha inválidos";
		ModelAndView mv;		
		
		if(p.isPresent() && p.get().getSenha().equals(senha)) {	
			if(p.get().getTipoUsuario().equals("prestador")) {
				mv= new ModelAndView("redirect:/perfilprestador");				
				mv.addObject("prestadorOB", p.get());	
				session.setAttribute("usuarioLogado", usuarioRepository.findById(id));		
				
			}else{
				mv= new ModelAndView("redirect:/perfilcliente");				
				mv.addObject("clienteOB", p.get());	
				session.setAttribute("usuarioLogado", usuarioRepository.findById(id));
			}

		}else {
			mv= new ModelAndView("login");
			mv.addObject("erros",erro);
		}
			
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioLogado");
		return "redirect:/";
	}		
	
	@RequestMapping(method = RequestMethod.GET, value = "/imagemusuario/{idusuario}")
	@ResponseBody
	public byte[] exibirImagemUsuario(@PathVariable("idusuario") String idusuario) {	
		
		Usuario u= usuarioRepository.findById(idusuario).get();
		
		return u.getImagem();
	}
}
