package com.jojo.papago.service;

import java.util.List;

import com.jojo.papago.vo.Message;
import com.jojo.papago.vo.PapagoInfoVO;
import com.jojo.papago.vo.TransVO;

public interface PapagoService {
	public Message doTranslate(TransVO tvo);
	public List<PapagoInfoVO> getPapagoInfoList(PapagoInfoVO pvo);
}
