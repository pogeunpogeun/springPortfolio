package com.hhj.springPortfolio.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springPortfolio._common.Util;
import com.hhj.springPortfolio.board.model.dao.BoardDAO;

@Controller
@RequestMapping("/board")
public class BoardGalleryController {
	
	@Inject
	BoardDAO boardDao;
	
	String folderName = "board";
	Util util = new Util();
	
	// listGallery ------------------------------------------------------------
	@RequestMapping("/listGallery")
	public String list(
		Model model
		) {
		model.addAttribute("fileName", "listGallery");
		model.addAttribute("folderName", folderName);
		
		return "main" + "/main";
	}
	
}
