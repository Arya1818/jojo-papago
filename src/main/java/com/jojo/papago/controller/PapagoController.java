package com.jojo.papago.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jojo.papago.service.PapagoService;
import com.jojo.papago.vo.Message;
import com.jojo.papago.vo.PapagoInfoVO;
import com.jojo.papago.vo.TransVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PapagoController {
	
	@Resource
	private PapagoService ps;
	
	@PostMapping("/papago")
	public Message doTranslate(@RequestBody TransVO tvo) {
		log.info("tvo=>{}", tvo);
		return ps.doTranslate(tvo);
	}
	@GetMapping("/papago")
	public List<PapagoInfoVO> getPapagoInfoList(@ModelAttribute PapagoInfoVO pvo){
		log.info("tvo=>{}", pvo);
		return ps.getPapagoInfoList(pvo);
	}
}
