package com.hhj.springPortfolio.board.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hhj.springPortfolio.board.model.dto.BoardCommentDTO;
import com.hhj.springPortfolio.board.model.dto.BoardDTO;
import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;
import com.hhj.springPortfolio.member.model.dto.MemberDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> getSelectAll(BoardDTO paramDto) {
		return sqlSession.selectList("board.getSelectAll", paramDto);
	}

	@Override
	public BoardDTO getSelectOne(BoardDTO paramDto) {
		return sqlSession.selectOne("board.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(BoardDTO paramDto) {
		return sqlSession.insert("board.setInsert", paramDto);
	}

	@Override
	public int setUpdate(BoardDTO paramDto) {
		return sqlSession.update("board.setUpdate", paramDto);
	}

	@Override
	public int setDelete(BoardDTO paramDto) {
		return sqlSession.delete("board.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(BoardDTO paramDto) {
		return sqlSession.selectOne("board.getTotalRecord", paramDto);
	}

	@Override
	public int getMaxNum() {
		int result = sqlSession.selectOne("board.getMaxNum");
		return result;
	}

	@Override
	public int getMaxRefNo() {
		int result = sqlSession.selectOne("board.getMaxRefNo");
		return result;
	}

	@Override
	public void setUpdateHit(BoardDTO paramDto) {
		sqlSession.update("board.setUpdateHit", paramDto);
	}

	@Override
	public void setUpdateRelevel(BoardDTO paramDto) {
		sqlSession.update("board.setUpdateRelevel", paramDto);
	}

	@Override
	public List<BoardChkDTO> getSelectAllBoardChk() {
		return sqlSession.selectList("board.getSelectAllBoardChk");
	}

	@Override
	public MemberDTO getSelectMember(int no) {
		return sqlSession.selectOne("board.getSelectMember", no);
	}

	@Override
	public int getMaxNumRefNoNoticeNo(String gubun) {
		int result = sqlSession.selectOne("board.getMaxNumRefNoNoticeNo", gubun);
		return result;
	}

	@Override
	public int viewPasswd(String passwdChk) {
		int result = sqlSession.selectOne("board.viewPasswd", passwdChk);
		return result;
	}

	@Override
	public List<BoardCommentDTO> getSelectCommentAll(int boardNo) {
		return sqlSession.selectList("boardComment.getSelectAllComment", boardNo);
	}

	@Override
	public int setInsertComment(BoardCommentDTO paramDto) {
		int result = sqlSession.insert("boardComment.setInsertComment", paramDto);
		return result;
	}

	@Override
	public int setDeleteComment(BoardCommentDTO paramDto) {
		int result = sqlSession.delete("boardComment.setDeleteComment", paramDto);
		return result;
	}
}
