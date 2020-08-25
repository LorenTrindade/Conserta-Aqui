package spring.consertaaqui.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.consertaaqui.model.Usuario;
import spring.consertaaqui.repository.SolicitacaoRepository;
import spring.consertaaqui.repository.UsuarioRepository;

@Controller
public class SolicitacaoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository; 		
	

}
