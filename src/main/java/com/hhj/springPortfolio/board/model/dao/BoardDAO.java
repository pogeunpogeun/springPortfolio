package com.hhj.springPortfolio.board.model.dao;


import java.util.List;

import com.hhj.springPortfolio.board.model.dto.BoardCommentDTO;
import com.hhj.springPortfolio.board.model.dto.BoardDTO;
import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;
import com.hhj.springPortfolio.member.model.dto.MemberDTO;

public interface BoardDAO {
	public List<BoardDTO> getSelectAll(BoardDTO paramDto);
	public BoardDTO getSelectOne(BoardDTO paramDto);
	public int setInsert(BoardDTO paramDto);
	public int setUpdate(BoardDTO paramDto);
	public int setDelete(BoardDTO paramDto);
	public int getTotalRecord(BoardDTO paramDto);
	public int getMaxNum();
	public int getMaxRefNo();
	public void setUpdateHit(BoardDTO paramDto);
	public void setUpdateRelevel(BoardDTO paramDto);
	public List<BoardChkDTO> getSelectAllBoardChk();
	public MemberDTO getSelectMember(int no);
	public int getMaxNumRefNoNoticeNo(String gubun);
	public int viewPasswd(String passwdChk);
	public List<BoardCommentDTO> getSelectCommentAll(int boardNo);
	public int setInsertComment(BoardCommentDTO paramDto);
	public int setDeleteComment(BoardCommentDTO paramDto);
}
