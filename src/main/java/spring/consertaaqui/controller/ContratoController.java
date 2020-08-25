package spring.consertaaqui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.consertaaqui.repository.ContratoRepository;
import spring.consertaaqui.repository.UsuarioRepository;

@Controller
public class ContratoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ContratoRepository contratoRepository;

	
}
