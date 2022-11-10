package com.hhj.springPortfolio.boardChk.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springPortfolio._common.Util;
import com.hhj.springPortfolio.boardChk.model.dao.BoardChkDAO;
import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;

@Controller
@RequestMapping("/boardChk")
public class BoardChkController {
	
	@Inject
	BoardChkDAO boardChkDao;
	String folderName = "boardChk";
	Util util = new Util();
	
	//list -----------------------------------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model
		) {
		List<BoardChkDTO> list = boardChkDao.getSelectAll();
		model.addAttribute("list",list);
		model.addAttribute("fileName","list");
		model.addAttribute("folderName", folderName);
		return "main" + "/main";
	}
	//view -----------------------------------------------------------------------------
	@RequestMapping("/view")
	public String view() {
		return "main" + "/main";
	}
	//chuga -----------------------------------------------------------------------------
	@RequestMapping("/chuga")
	public String chuga() {
		return "main" + "/main";
	}
	//sujung -----------------------------------------------------------------------------
	@RequestMapping("/sujung")
	public String sujung() {
		return "main" + "/main";
	}
	//sakje -----------------------------------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje() {
		return "main" + "/main";
	}
	//chugaProc -----------------------------------------------------------------------------
	@RequestMapping("/chugaProc")
	public String chugaProc() {
		return "main" + "/main";
	}
	//sujungProc -----------------------------------------------------------------------------
	@RequestMapping("/sujungProc")
	public String sujungProc() {
		return "main" + "/main";
	}
	//sakjeProc -----------------------------------------------------------------------------
	@RequestMapping("/sakjeProc")
	public String sakjeProc() {
		return "main" + "/main";
	}
}
