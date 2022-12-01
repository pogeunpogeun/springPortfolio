package com.hhj.springPortfolio.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hhj.springPortfolio._common.Criteria;
import com.hhj.springPortfolio._common.MultipartUpload;
import com.hhj.springPortfolio._common.PageMaker;
import com.hhj.springPortfolio._common.Util;
import com.hhj.springPortfolio.member.model.dao.MemberDAO;
import com.hhj.springPortfolio.member.model.dto.MemberDTO;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	MemberDAO memberDao;
	String folderName = "member";
	Util util = new Util();
	
	// list ---------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model,
		HttpServletRequest request,
		@ModelAttribute MemberDTO dto
		) {		
		//---------------------------------------------------------------------------------------검색 (1)
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		// 공백체크 - 검색을 하지 않을때는 searchGubun, searchData가 null 처리 되기 때문에 공백체크 필수
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}	
		//--------------------------------------------------------------------------------------- (1)
		//---------------------------------------------------------------------------------------페이징(start)
		String pageNumber_ = request.getParameter("pageNumber");
		// 넘어오는 String pageNumber_ 숫자인지 체크 확인 필요
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_ = "1"; // pageNumber가 없을 경우 무조건 제일 첫 페이지로 보냄
		}
		int pageNumber = Integer.parseInt(pageNumber_);
		
		MemberDTO arguDto = new MemberDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
		arguDto.setSearchGubun(searchGubun);
		arguDto.setSearchData(searchData);
		
		int pageSize = 3; // 페이지당 게시글 개수
		int blockSize = 10; // 페이징 번호 개수
		int totalRecord = memberDao.getTotalRecord(arguDto) ; // DB 전체 레코드 개수(검색 포함)
		int startRecord = pageSize * (pageNumber -1) + 1;
		int lastRecord = pageSize * pageNumber;		
		if(lastRecord > totalRecord) { // lastRecord가 totalRecord보다 커졌을 경우 totalRecord로 설정
			lastRecord = totalRecord;
		}
		int dispNo = totalRecord - pageSize * (pageNumber - 1); // 화면상에 보여주기 위한 일련번호
		
		int totalPage = 0;
		int startPage = 1;
		int lastPage = 1;
		
		if(totalRecord > 0) {
			totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
			startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
			lastPage = startPage + blockSize - 1;
			
			if(lastPage > totalPage) {
				lastPage = totalPage; // pageSize만큼 레코드가 등록 되어 있지 않을때, 나머지 페이지 번호들을 보여줄 필요가 없기 때문에 totalPage로 설정
			}
			
		} else { // DB에 레코드가 하나도 없을 때
			totalPage = 1;
		}	
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("blockSize",blockSize);
		model.addAttribute("totalRecord",totalRecord);
		model.addAttribute("startRecord",startRecord);
		model.addAttribute("lastRecord",lastRecord);
		model.addAttribute("dispNo",dispNo);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("lastPage",lastPage);	
		
		// startRecord ~ lastRecord 를 getSelectAll에 보내기 위해 arguDto에 담는 과정
		arguDto.setStartRecord(startRecord);
		arguDto.setLastRecord(lastRecord);
		
		//---------------------------------------------------------------------------------------페이징(end)
		List<MemberDTO> list = memberDao.getSelectAll(arguDto);		
		model.addAttribute("list",list);
		//---------------------------------------------------------------------------------------검색 (2)
		model.addAttribute("searchGubun",searchGubun);
		model.addAttribute("searchData",searchData);
		//--------------------------------------------------------------------------------------- (2)
		//---------------------------------------------------------------------------------------검색 (3)
		String searchGubunEn = "";
		try {
			searchGubunEn = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubunEn = "";
		}
		String searchDataEn = "";
		try {
			searchDataEn = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchDataEn = "";
		}
		String searchQueryString = "searchGubun=" + searchGubunEn + "&searchData=" + searchDataEn;
		
		model.addAttribute("searchQueryString",searchQueryString);
		//--------------------------------------------------------------------------------------- (3)
		String title = "회원리스트";
		model.addAttribute("title", title);
		model.addAttribute("dto", dto);
		model.addAttribute("fileName", "list");
		model.addAttribute("folderName", folderName);
		return "main" + "/main"; // servlet_context.xml 에 접두사(WEB_INF/views), 접미사(.jsp) 등록 되어있음
	}
	@RequestMapping("/listSearch")
	public String listSearch(
		Model model,
		@ModelAttribute MemberDTO dto
		) {
		// 공백체크
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}		
		//Encoding 과정 (Decoding은 Encoding -> Decoding 으로 바꿔서 사용)		
		try {
			searchGubun = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubun = "";
		}
		try {
			searchData = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchData = "";
		}		

		// servlet_context.xml 에 접두사(WEB_INF/views), 접미사(.jsp) 등록 되어있음
		return "redirect:/member/list?searchGubun=" + searchGubun + "&searchData=" + searchData; // Redirect로 처리
	}
	// view ---------------------------------------------------
	@RequestMapping("/view")
	public String view(
		Model model,
		@ModelAttribute MemberDTO dto,
		HttpServletRequest request
		) {		
		MemberDTO returnDto = memberDao.getSelectOne(dto);
		//---------------------------------------------------------------------------------------검색 (1)
		String searchGubun = dto.getSearchGubun();
		String searchData = dto.getSearchData();
		// 공백체크 - 검색을 하지 않을때는 searchGubun, searchData가 null 처리 되기 때문에 공백체크 필수
		if(dto.getSearchGubun() == null || dto.getSearchGubun().trim().equals("")) {
			searchGubun = "";
		}
		if(dto.getSearchData() == null || dto.getSearchData().trim().equals("")) {
			searchData = "";
		}
		if(searchGubun.equals("") || searchData.equals("")) {
			searchGubun = "";
			searchData = "";
		}	
		String pageNumber_ = request.getParameter("pageNumber");
		// 넘어오는 String pageNumber_ 숫자인지 체크 확인 필요
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_ = "1"; // pageNumber가 없을 경우 무조건 제일 첫 페이지로 보냄
		}
		int pageNumber = Integer.parseInt(pageNumber_);
		
		MemberDTO arguDto = new MemberDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
		arguDto.setSearchGubun(searchGubun);
		arguDto.setSearchData(searchData);
		//--------------------------------------------------------------------------------------- (1)	
		//---------------------------------------------------------------------------------------검색 (2)
		model.addAttribute("searchGubun",searchGubun);
		model.addAttribute("searchData",searchData);
		//--------------------------------------------------------------------------------------- (2)
		//---------------------------------------------------------------------------------------검색 (3)
		String searchGubunEn = "";
		try {
			searchGubunEn = URLEncoder.encode(searchGubun,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchGubunEn = "";
		}
		String searchDataEn = "";
		try {
			searchDataEn = URLEncoder.encode(searchData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			searchDataEn = "";
		}
		String searchQueryString = "searchGubun=" + searchGubunEn + "&searchData=" + searchDataEn;
			
		model.addAttribute("searchQueryString",searchQueryString);
		//--------------------------------------------------------------------------------------- (3)					
		String title = "회원상세보기";
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		model.addAttribute("fileName", "view");
		model.addAttribute("folderName", folderName);		
		return "main" + "/main";
	}
	// chuga ---------------------------------------------------	
	@RequestMapping("/chuga")
	public String chuga(
		Model model
		) {
		String title = "회원등록";
		model.addAttribute("title", title);
		model.addAttribute("fileName", "chuga");
		model.addAttribute("folderName", folderName);
		return "main" + "/main";
	}
	// sujung ---------------------------------------------------
	@RequestMapping("/sujung")
	public String sujung(
		Model model,
		@ModelAttribute MemberDTO dto,
		HttpSession session,
		HttpServletRequest request
		) {
		MemberDTO returnDto = memberDao.getSelectOne(dto);		
		Integer no = dto.getNo();
		String title = "회원수정";	
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		model.addAttribute("fileName", "sujung");
		model.addAttribute("folderName", folderName);
		model.addAttribute("no" , no);
		return "main" + "/main";
	}
	// sakje ---------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje(
		Model model,
		@ModelAttribute MemberDTO dto
		) {
		MemberDTO returnDto = memberDao.getSelectOne(dto);
		Integer no = dto.getNo();
		String title = "회원삭제";		
		model.addAttribute("title", title);
		model.addAttribute("dto", returnDto);
		model.addAttribute("fileName", "sakje");
		model.addAttribute("folderName", folderName);
		model.addAttribute("no" , no);
		return "main" + "/main";
	}
	// sujungProc ---------------------------------------------------
		@RequestMapping("/sujungProc")
		public String sujungProc(
			Model model,
			@ModelAttribute MemberDTO dto
			) {
			dto.setPasswd(util.security(dto.getPasswd()));
			int result = memberDao.setUpdate(dto);
			
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/sujung?no=" + dto.getNo();
			}
		}
		
	// chugaAttachProc ---------------------------------------------------
		@RequestMapping("/chugaAttachProc")
		public String chugaAttachProc(
			Model model,
			HttpServletRequest request,
			@ModelAttribute MemberDTO dto,
			@RequestParam("file") List<MultipartFile> multiFileList
			) {
			int idChk = memberDao.idChk(dto.getId());
			
			if(idChk == 1) {
				return "redirect:/member/chuga";
			} else {
				
			}
			
			if(!dto.getPasswd().equals(dto.getPasswdChk())) {
				return "redirect:/member/chuga";
			}
			
			dto.setPasswd(util.security(dto.getPasswd()));
					
			String jumin = dto.getJumin1() + dto.getJumin2();
			dto.setJumin(jumin);
			
			String email = dto.getEmail1() + "@" + dto.getEmail2();
			dto.setEmail(email);
			
			
			MultipartUpload mu = new MultipartUpload();
			List<String> list = mu.attachProc(multiFileList, "/springPortfolio/member");
			
			String attachInfo = "";
			for(int i = 0; i < list.size(); i++) {
				attachInfo += "|" + list.get(i);
			}
			attachInfo = attachInfo.substring(1);
			dto.setAttachInfo(attachInfo);
			
			int result = memberDao.setInsert(dto);
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/chuga";
			}

			}
			
	// sakjeProc ---------------------------------------------------
		@RequestMapping("/sakjeProc")
		public String sakjeProc(
			Model model,
			@ModelAttribute MemberDTO dto
			) {
			dto.setPasswd(util.security(dto.getPasswd()));
			int result = memberDao.setDelete(dto);
			if(result > 0) {
				return "redirect:/member/list";
			} else {
				return "redirect:/member/sakje?no=" + dto.getNo();
			}
			
		}
	// login ---------------------------------------------------
		@RequestMapping("/login")
		public String login(
			Model model
			) {
			String title = "로그인";	
			model.addAttribute("title", title);
			model.addAttribute("fileName", "login");
			model.addAttribute("folderName", folderName);
			return "main" + "/main";
		}
	// loginProc ---------------------------------------------------	
		@RequestMapping("/loginProc")
		public String loginProc(
			Model model,
			HttpSession session,
			@ModelAttribute MemberDTO arguDto
			) throws IOException {			
			arguDto.setPasswd(util.security(arguDto.getPasswd()));
			MemberDTO resultDto = memberDao.getLogin(arguDto);		
			int sessionNo = resultDto.getNo();
			String sessionName = resultDto.getNickname();
			session.setAttribute("sessionNo", sessionNo);
			session.setAttribute("sessionName", sessionName);
			model.addAttribute("fileName", "index");
			model.addAttribute("folderName", "_home");
			
			if(sessionNo > 0) {
				  return "main" + "/main";
			} else {
				session.invalidate();
				return "main" + "/main";
			}
		}
	// logoutProc ---------------------------------------------------
		@RequestMapping("/logout")
		public String logout(
				Model model,
				HttpSession session,
				@ModelAttribute MemberDTO arguDto
				) {
				model.addAttribute("fileName", "index");
				model.addAttribute("folderName", "_home");
				session.invalidate();
				return "main" + "/main";		
		}	
	// download ---------------------------------------------------
		@RequestMapping("/download")
		public void download(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			) {
			String no_ = request.getParameter("no");
			String num_ = request.getParameter("num");
			int no = Integer.parseInt(no_);
			int num = Integer.parseInt(num_);
					
			MemberDTO arguDto = new MemberDTO();
			arguDto.setNo(no);
			
			MemberDTO returnDto = memberDao.getSelectOne(arguDto);
			String[] imsiArray = returnDto.getAttachInfo().split("[|]");
			
			for(int i = 0; i < imsiArray.length; i++) {
				if(i == num) {
					String[] tempArray = imsiArray[i].split(",");
					
					MultipartUpload mu = new MultipartUpload();
					mu.attachDownload(response, tempArray[0], tempArray[1], "/springPortfolio/member");
				}
			}					
		}
	// idChk ---------------------------------------------------
		@RequestMapping("/idChk")
		public @ResponseBody int idChk(String id) {
			int result = memberDao.idChk(id);
			return result;
		}		
}