package com.hhj.springPortfolio.boardChk.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hhj.springPortfolio.boardChk.model.dto.BoardChkDTO;

@Repository
public class BoardChkDAOImpl implements BoardChkDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<BoardChkDTO> getSelectAll() {
		return sqlSession.selectList("boardChk.getSelectAll");
	}

	@Override
	public BoardChkDTO getSelectOne(BoardChkDTO paramDto) {
		return sqlSession.selectOne("boardChk.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(BoardChkDTO paramDto) {
		return sqlSession.insert("boardChk.setInsert");
	}

	@Override
	public int setUpdate(BoardChkDTO paramDto) {
		return sqlSession.update("boardChk.setUpdate", paramDto);
	}

	@Override
	public int setDelete(BoardChkDTO paramDto) {
		return sqlSession.delete("boardChk.setDelete", paramDto);
	}

}
