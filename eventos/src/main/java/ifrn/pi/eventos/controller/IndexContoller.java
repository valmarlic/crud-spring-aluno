package ifrn.pi.eventos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class IndexContoller {
	@RequestMapping("/")
	public String index() { 
		
		
		return "home";
	}
}
