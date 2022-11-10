package com.hhj.springPortfolio.boardChk.model.dao;

import java.util.List;

import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;

public interface BoardChkDAO {
	public List<BoardChkDTO> getSelectAll();
	public BoardChkDTO getSelectOne(BoardChkDTO paramDto);
	public int setInsert(BoardChkDTO paramDto);
	public int setUpdate(BoardChkDTO paramDto);
	public int setDelete(BoardChkDTO paramDto);
}
