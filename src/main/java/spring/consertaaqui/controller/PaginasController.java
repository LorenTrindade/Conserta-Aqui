package spring.consertaaqui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaginasController {

	
	@RequestMapping(method = RequestMethod.GET, value = "/comofunciona")
	public String comofunciona() {							
		
		return "comofunciona";
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/calculos")
	public String calculos() {							
		
		return "telacalculos";
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/termosdeuso")
	public String termos() {							
		
		return "termosdeuso";
		
	}
		
}
