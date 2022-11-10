package com.hhj.springPortfolio._home;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(
		Model model
	/* HttpServletRequest request */
		) {
		String fileName = "list";
		model.addAttribute("folderName","_home");
		model.addAttribute("fileName","index");
		//model.addAttribute("path","/springPortfolio");
		return "main/main";
	}
		
}
