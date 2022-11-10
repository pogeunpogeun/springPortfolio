package com.hhj.springPortfolio.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hhj.springPortfolio._common.MultipartUpload;
import com.hhj.springPortfolio._common.Util;
import com.hhj.springPortfolio.board.model.dao.BoardDAO;
import com.hhj.springPortfolio.board.model.dto.BoardCommentDTO;
import com.hhj.springPortfolio.board.model.dto.BoardDTO;
import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;
import com.hhj.springPortfolio.member.model.dto.MemberDTO;


@Controller
@RequestMapping("/board")
public class BoardController {	
	
	@Inject
	BoardDAO boardDao;

	String folderName = "board";
	Util util = new Util();
	
	// list ------------------------------------------------------------
	@RequestMapping("/list")
	public String list(
		Model model,
		HttpSession session,
		HttpServletRequest request,
		@ModelAttribute BoardDTO dto,
		@ModelAttribute MemberDTO memberDto
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
		
		BoardDTO arguDto = new BoardDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
		arguDto.setSearchGubun(searchGubun);
		arguDto.setSearchData(searchData);
		
		int pageSize = 5; // 페이지당 게시글 개수
		int blockSize = 10; // 페이징 번호 개수
		int totalRecord = boardDao.getTotalRecord(arguDto) ; // DB 전체 레코드 개수(검색 포함)
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
		List<BoardDTO> list = boardDao.getSelectAll(arguDto);		
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
		
		Object sessionNo_ = session.getAttribute("sessionNo");
		int sessionNo = 0;
		if(sessionNo_!=null) {
			sessionNo = (Integer) sessionNo_;
		}
		int no = sessionNo;
		MemberDTO imsiDto = boardDao.getSelectMember(no);
			
		//--------------------------------------------------------------------------------------- (3)
		model.addAttribute("list", list);
		model.addAttribute("imsiDto", imsiDto);
		model.addAttribute("fileName", "list");
		model.addAttribute("folderName", folderName);
		model.addAttribute("no", no);
		return "main" + "/main";
	}
	// listSearch ------------------------------------------------------------
	@RequestMapping("/listSearch")
	public String listSearch(
		Model model,
		@ModelAttribute BoardDTO dto
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
		return "redirect:/board/list?searchGubun=" + searchGubun + "&searchData=" + searchData; // Redirect로 처리
	}
	// view ------------------------------------------------------------
	@RequestMapping("/view")
	public String view(
		Model model,
		HttpSession session,
		@ModelAttribute BoardDTO dto,
		@ModelAttribute MemberDTO memberDto,
		HttpServletRequest request,
		HttpServletResponse response
		) {
		 Cookie oldCookie = null;
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("postView")) {
		                oldCookie = cookie;
		            }
		        }
		    }
		    if (oldCookie != null) {
		        if (!oldCookie.getValue().contains("[" + dto.getNo() + "]")) {
		        	boardDao.setUpdateHit(dto);
		            oldCookie.setValue(oldCookie.getValue() + "_[" + dto.getNo() + "]");
		            oldCookie.setPath("/");
		            oldCookie.setMaxAge(60 * 60 * 24);
		            response.addCookie(oldCookie);
		        }
		    } else {
		    	boardDao.setUpdateHit(dto);
		        Cookie newCookie = new Cookie("postView","[" + dto.getNo() + "]");
		        newCookie.setPath("/");
		        newCookie.setMaxAge(60 * 60 * 24);
		        response.addCookie(newCookie);
		    }
		BoardDTO returnDto = boardDao.getSelectOne(dto);
		
		String passwdChk = request.getParameter("passwdChk");
		
		if(passwdChk == null || passwdChk.trim().equals("")) {
			passwdChk = "";
		}
		
		String secretView = "viewPage";
		
		if(returnDto.getSecretGubun().equals("T")) {
			if(!returnDto.getPasswd().equals(passwdChk)) {
				secretView = "viewPasswdPage";
			}
		}
		model.addAttribute("secretView", secretView);
		//boardDao.setUpdateHit(dto);
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
		
		model.addAttribute("pageNumber", pageNumber);
		
		BoardDTO arguDto = new BoardDTO(); // 검색과 관련된 내용을 담기 위한 DTO객체		
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
		
		String nickname = (String) session.getAttribute("sessionName");
		int sessionNo = (Integer) session.getAttribute("sessionNo");
		
		int no = sessionNo;
		
		String boardNo_ = request.getParameter("no");
		int boardNo = Integer.parseInt(boardNo_);
		
		MemberDTO imsiDto = boardDao.getSelectMember(no);
		String boardWriter = imsiDto.getNickname();
		
		List<BoardCommentDTO> commentList = boardDao.getSelectCommentAll(boardNo);
					
		model.addAttribute("searchQueryString",searchQueryString);
		//--------------------------------------------------------------------------------------- (3)
		model.addAttribute("dto", returnDto);
		model.addAttribute("commentList", commentList);
		model.addAttribute("boardWriter", boardWriter);
		model.addAttribute("fileName", "view");
		model.addAttribute("folderName", folderName);
		model.addAttribute("nickname", nickname);
		return "main" + "/main";
	}
	// viewPasswdProc ------------------------------------------------------------
	@RequestMapping("/viewPasswdProc")
	public String viewPasswdProc(
		Model model,
		HttpServletRequest request,
		@ModelAttribute BoardDTO dto
		) {
		String passwdChk = request.getParameter("passwdChk");
		String no_ = request.getParameter("no");
		int no = Integer.parseInt(no_);
		int result = boardDao.viewPasswd(passwdChk);
		
		BoardDTO returnDto = boardDao.getSelectOne(dto);
		
		if(passwdChk == null || passwdChk.trim().equals("")) {
			return "redirect:/board/list";
		}
		
		if(result > 0 && returnDto.getNo() == no) {
			model.addAttribute("fileName", "view");
			model.addAttribute("folderName", folderName);
			model.addAttribute("dto", returnDto);
			return "main" + "/main";
		} else {
			return "redirect:/board/list";
		}
	}
	// commentList ------------------------------------------------------------
	@RequestMapping("/commentList")
	public String commnetList(
		Model model,
		HttpSession session,
		HttpServletRequest request
		) {
		String no_ = request.getParameter("no");
		int boardNo = Integer.parseInt(no_);
		List<BoardCommentDTO> commentList = boardDao.getSelectCommentAll(boardNo);
		String nickname = (String) session.getAttribute("sessionName");
		
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("commentList", commentList);
		model.addAttribute("fileName", "commentList");
		model.addAttribute("folderName", folderName);
		model.addAttribute("nickname", nickname);
		return "board/commentList";
	}
	// commnetInsertProc ------------------------------------------------------------
		@RequestMapping("/commentInsertProc")
		public String commentInsertProc(
			Model model,
			HttpServletRequest request,
			HttpSession session,
			@ModelAttribute BoardDTO dto
			) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			int boardNo = no;
			
			int memberNo = (Integer) session.getAttribute("sessionNo");
			
			BoardCommentDTO arguDto = new BoardCommentDTO();
			arguDto.setBoardNo(boardNo);
			arguDto.setWriter(writer);
			arguDto.setPasswd(passwd);
			arguDto.setContent(content);
			arguDto.setMemberNo(memberNo);
			
			int result = boardDao.setInsertComment(arguDto);
			
			if(result > 0) {
				return "redirect:/board/view?no=" + boardNo;
			} else {
				return "redirect:/board/list";
			}

		}
	// chuga ------------------------------------------------------------
	@RequestMapping("/chuga")
	public String chuga(
		Model model,	
		HttpSession session,
		@ModelAttribute MemberDTO memberDto,
		@ModelAttribute BoardDTO dto
		) {
		List<BoardChkDTO> list = boardDao.getSelectAllBoardChk();
		
		model.addAttribute("dto", dto);
		model.addAttribute("list",list);
		model.addAttribute("fileName", "chuga");
		model.addAttribute("folderName", folderName);
		int sessionNo = (Integer) session.getAttribute("sessionNo");
		
		int no = sessionNo;
				
		MemberDTO returnDto = boardDao.getSelectMember(no);
		String email = returnDto.getEmail();
		
		model.addAttribute("email",email);
		return "main" + "/main";
	}
	// sujung ------------------------------------------------------------
	@RequestMapping("/sujung")
	public String sujung() {
		return "main" + "/main";
	}
	// sakje ------------------------------------------------------------
	@RequestMapping("/sakje")
	public String sakje() {
		return "main" + "/main";
	}
	// chugaProc ------------------------------------------------------------
	@RequestMapping("/chugaProc")
	public String chugaProc(
		Model model,
		HttpServletRequest request,
		HttpSession session,
		@ModelAttribute BoardDTO dto,
		@ModelAttribute MemberDTO memberDto,
		@RequestParam("file") List<MultipartFile> multiFileList
		) {
		String boardNo_ = request.getParameter("no");
		int boardNo = Integer.parseInt(boardNo_);
		
		String tbl = "1";
		String attachInfo = "-";
		
		int num = boardDao.getMaxNumRefNoNoticeNo("num") + 1;
		dto.setNum(num);
		int refNo = boardDao.getMaxNumRefNoNoticeNo("refNo") + 1;
		int stepNo = 1;
		int levelNo = 1;
		int parentNo = 0;
		
		System.out.println("no : " + dto.getNo());
		System.out.println("refNo1 : " + refNo);
		System.out.println("boardNo : " + boardNo);
		
		if(boardNo > 0) {
			BoardDTO imsiReturnDto = boardDao.getSelectOne(dto);			
			boardDao.setUpdateRelevel(imsiReturnDto);
			
			refNo = imsiReturnDto.getRefNo();		
			stepNo = imsiReturnDto.getStepNo() + 1;
			levelNo = imsiReturnDto.getLevelNo() + 1;
			parentNo = imsiReturnDto.getNo();		
		}
		dto.setRefNo(refNo);
		dto.setStepNo(stepNo);
		dto.setLevelNo(levelNo);
		dto.setParentNo(parentNo);
		
		System.out.println("refNo2 : " + refNo);
		System.out.println("boardNo2 : " + boardNo);
		
		int sessionNo = (Integer) session.getAttribute("sessionNo");
		int hit = 0;
		int memberNo = sessionNo;
		
		String secretGubun = request.getParameter("secretGubun");
		if(secretGubun == null || secretGubun.equals("")) {
			secretGubun = "F";
		}
		
		
		int noticeNo = 0;
		if(dto.getNoticeNo() == 1) {
			noticeNo = boardDao.getMaxNumRefNoNoticeNo("noticeNo") + 1;
		}
		dto.setNoticeNo(noticeNo);
		
		int no = sessionNo;
		
		MemberDTO returnDto = boardDao.getSelectMember(no);		
		
		String email = returnDto.getEmail();
		String writer = returnDto.getName();
		String boardWriter = returnDto.getNickname();
				
		dto.setEmail(email);	
		dto.setNum(num);
		dto.setRefNo(refNo);
		dto.setStepNo(stepNo);
		dto.setLevelNo(levelNo);
		dto.setParentNo(parentNo);
		dto.setHit(hit);
		dto.setMemberNo(memberNo);
		dto.setWriter(writer);
		dto.setSecretGubun(secretGubun);
		dto.setBoardWriter(boardWriter);
		dto.setTbl(tbl);
		dto.setAttachInfo(attachInfo);
		int result = boardDao.setInsert(dto);
		
		if(result > 0) {
			return "redirect:/board/list";
		} else {
			return "redirect:/board/chuga";
		}
	}
	// sujungProc ------------------------------------------------------------
	@RequestMapping("/sujungProc")
	public String sujungProc() {
		return "main" + "/main";
	}
	// sakjeProc ------------------------------------------------------------
	@RequestMapping("/sakjeProc")
	public String sakjeProc() {
		return "main" + "/main";
	}
}
