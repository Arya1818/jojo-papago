package com.jojo.papago.dao;

import java.util.List;

import com.jojo.papago.vo.PapagoInfoVO;

public interface PapagoDAO {
	
	public List<PapagoInfoVO>  selectPapagoInfoList(PapagoInfoVO pvo);
	public PapagoInfoVO selectPapagoInfo(PapagoInfoVO pvo);
	public int insertPapagoInfo(PapagoInfoVO pvo);
	public int updatePapagoInfoForCnt(PapagoInfoVO pvo);
}
