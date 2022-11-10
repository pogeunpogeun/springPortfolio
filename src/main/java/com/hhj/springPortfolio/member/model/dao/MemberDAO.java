package com.hhj.springPortfolio.member.model.dao;

import java.util.List;
import java.util.Map;

import com.hhj.springPortfolio._common.Criteria;
import com.hhj.springPortfolio.member.model.dto.MemberDTO;

public interface MemberDAO {
	public List<MemberDTO> getSelectAll(MemberDTO paramDto);
	public MemberDTO getSelectOne(MemberDTO paramDto);
	public int setInsert(MemberDTO paramDto);
	public int setUpdate(MemberDTO paramDto);
	public int setDelete(MemberDTO paramDto);
	public MemberDTO getLogin(MemberDTO paramDto);
	public int getTotalRecord(MemberDTO paramDto);
	public int idChk(String id);
}
