package com.hhj.springPortfolio.memo.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhj.springPortfolio.memo.model.dao.MemoDAO;
import com.hhj.springPortfolio.memo.model.dto.MemoDTO;

@Controller
@RequestMapping("/memo")
public class MemoController {
	@Inject
	MemoDAO memoDao;
	String folderName = "memo";
	// list -------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model	
		) {
		List<MemoDTO> list = memoDao.getSelectAll();
		String title = "Memo List";
		
		model.addAttribute("title",title);
		model.addAttribute("list",list);
		model.addAttribute("fileName", "list");
		model.addAttribute("folderName", folderName);
		
		return "main" + "/main";		
	}
	// chuga -------------------------------------------
	// sujung -------------------------------------------
	// sakje -------------------------------------------
	// chugaProc -------------------------------------------
	// sujungProc -------------------------------------------
	// sakjeProc -------------------------------------------
}
