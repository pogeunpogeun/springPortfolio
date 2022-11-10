package com.hhj.springPortfolio.boardChk.model.dto;

import java.sql.Date;

public class BoardChkDTO {
	private int boardChkNo;
	private String tbl;
	private String tblName;
	private Date regiDate;
	public int getBoardChkNo() {
		return boardChkNo;
	}
	public void setBoardChkNo(int boardChkNo) {
		this.boardChkNo = boardChkNo;
	}
	public String getTbl() {
		return tbl;
	}
	public void setTbl(String tbl) {
		this.tbl = tbl;
	}
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	public Date getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}
	
}
