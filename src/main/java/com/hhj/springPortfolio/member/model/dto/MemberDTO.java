package com.hhj.springPortfolio.member.model.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	private int no;
	private String id;
	private String passwd;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	private String jumin;
	private String juso1;
	private String juso2;
	private String juso3;
	private String juso4;
	private String grade;
	private Date regiDate;
	private String attachInfo;
	
	private String jumin1;
	private String jumin2;
	
	private String searchGubun;
	private String searchData;
	
	private int startRecord;
	private int lastRecord;
	
	private String preName;
	private String nxtName;
	private int preNo;
	private int nxtNo;
	
	private String passwdChk;
	
	private String email1;
	private String email2;
	
	private MultipartFile file;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	public String getJuso1() {
		return juso1;
	}

	public void setJuso1(String juso1) {
		this.juso1 = juso1;
	}

	public String getJuso2() {
		return juso2;
	}

	public void setJuso2(String juso2) {
		this.juso2 = juso2;
	}

	public String getJuso3() {
		return juso3;
	}

	public void setJuso3(String juso3) {
		this.juso3 = juso3;
	}

	public String getJuso4() {
		return juso4;
	}

	public void setJuso4(String juso4) {
		this.juso4 = juso4;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public String getAttachInfo() {
		return attachInfo;
	}

	public void setAttachInfo(String attachInfo) {
		this.attachInfo = attachInfo;
	}

	public String getJumin1() {
		return jumin1;
	}

	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}

	public String getJumin2() {
		return jumin2;
	}

	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
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

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getNxtName() {
		return nxtName;
	}

	public void setNxtName(String nxtName) {
		this.nxtName = nxtName;
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

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
}
