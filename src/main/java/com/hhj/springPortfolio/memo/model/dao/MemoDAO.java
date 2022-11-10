package com.hhj.springPortfolio.memo.model.dao;

import java.util.List;

import com.hhj.springPortfolio.memo.model.dto.MemoDTO;

public interface MemoDAO {
	public List<MemoDTO> getSelectAll();
	public MemoDTO getSelectOne(MemoDTO paramDto);
	public int setInsert(MemoDTO paramDto);
	public int setUpdate(MemoDTO paramDto);
	public int setDelete(MemoDTO paramDto);
}
