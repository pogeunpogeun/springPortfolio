package com.hhj.springPortfolio.board.model.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private int no;
	private int num;
	private String tbl;
	private String writer;
	private String subject;
	private String content;
	private String email;
	private String passwd;
	private int refNo;
	private int stepNo;
	private int levelNo;
	private int parentNo;
	private int hit;
	private int memberNo;
	private int noticeNo;
	private String secretGubun;
	private Date regiDate;
	
	private String tblName;
	private String searchGubun;
	private String searchData;
	
	private int startRecord;
	private int lastRecord;
	
	private String preSubject;
	private String nxtSubject;
	private int preNo;
	private int nxtNo;
	
	private String passwdChk;
	private String boardWriter;
	private String attachInfo;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTbl() {
		return tbl;
	}
	public void setTbl(String tbl) {
		this.tbl = tbl;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getRefNo() {
		return refNo;
	}
	public void setRefNo(int refNo) {
		this.refNo = refNo;
	}
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getSecretGubun() {
		return secretGubun;
	}
	public void setSecretGubun(String secretGubun) {
		this.secretGubun = secretGubun;
	}
	public Date getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}
	public String getSearchData() {
		return searchData;
	}
	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}
	public int getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}
	public int getLastRecord() {
		return lastRecord;
	}
	public void setLastRecord(int lastRecord) {
		this.lastRecord = lastRecord;
	}
	public String getPreSubject() {
		return preSubject;
	}
	public void setPreSubject(String preSubject) {
		this.preSubject = preSubject;
	}
	public String getNxtSubject() {
		return nxtSubject;
	}
	public void setNxtSubject(String nxtSubject) {
		this.nxtSubject = nxtSubject;
	}
	public int getPreNo() {
		return preNo;
	}
	public void setPreNo(int preNo) {
		this.preNo = preNo;
	}
	public int getNxtNo() {
		return nxtNo;
	}
	public void setNxtNo(int nxtNo) {
		this.nxtNo = nxtNo;
	}
	public String getPasswdChk() {
		return passwdChk;
	}
	public void setPasswdChk(String passwdChk) {
		this.passwdChk = passwdChk;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getAttachInfo() {
		return attachInfo;
	}
	public void setAttachInfo(String attachInfo) {
		this.attachInfo = attachInfo;
	}
	
}
