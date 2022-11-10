package com.hhj.springPortfolio.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hhj.springPortfolio.member.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> getSelectAll(MemberDTO paramDto) {		
		return sqlSession.selectList("member.getSelectAll", paramDto);
	}

	@Override
	public MemberDTO getSelectOne(MemberDTO paramDto) {
		return sqlSession.selectOne("member.getSelectOne",paramDto);
	}

	@Override
	public int setInsert(MemberDTO paramDto) {
		return sqlSession.insert("member.setInsert",paramDto);
	}

	@Override
	public int setUpdate(MemberDTO paramDto) {
		return sqlSession.update("member.setUpdate",paramDto);
	}

	@Override
	public int setDelete(MemberDTO paramDto) {
		return sqlSession.delete("member.setDelete",paramDto);
	}

	@Override
	public MemberDTO getLogin(MemberDTO paramDto) {
		MemberDTO resultDto = new MemberDTO();
		if(sqlSession.selectOne("member.getLogin", paramDto) != null) {
			resultDto = sqlSession.selectOne("member.getLogin", paramDto);
		} 
		return resultDto;
	}

	@Override
	public int getTotalRecord(MemberDTO paramDto) {
		return sqlSession.selectOne("member.getTotalRecord",paramDto);
	}

	@Override
	public int idChk(String id) {
		int result = sqlSession.selectOne("member.idChk",id);
		return result;
	}

}
