package com.jojo.papago.service.Impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojo.papago.dao.PapagoDAO;
import com.jojo.papago.service.PapagoService;
import com.jojo.papago.vo.Message;
import com.jojo.papago.vo.PapagoInfoVO;
import com.jojo.papago.vo.Result;
import com.jojo.papago.vo.TransVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PapagoServiceImpl implements PapagoService {
	
	@Autowired
	private PapagoDAO pdao;
	
	@Value("${client.id")
	private String id;
	
	@Value("${client.secret")
	private String secret;
	
	@Value("${client.api.url")
	private String apiUrl;
	
	private ObjectMapper om =
			new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	@Override
	public Message doTranslate(TransVO tvo) {
		try {
			PapagoInfoVO pi = new PapagoInfoVO();
			pi.setPiSource(tvo.getSource());
			pi.setPiTarget(tvo.getTarget());
			pi.setPiTarget(tvo.getText());
			pi = pdao.selectPapagoInfo(pi);
			if(pi!=null) {
				Result r = new Result();
				r.setSrcLangType(pi.getPiSource());
				r.setTarLangType(pi.getPiTarget());
				r.setTranslatedText(pi.getPiResult());
				Message m = new Message();
				m.setResult(r);
				pdao.updatePapagoInfoForCnt(pi);
				return m;
			}
			String text = URLEncoder.encode(tvo.getText(),"utf-8");
			URL url = new URL(apiUrl);
			HttpURLConnection hc = (HttpURLConnection)url.openConnection();
			hc.setRequestMethod("POST");
			hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			hc.setRequestProperty("X-Naver-Client-Id", id);
			hc.setRequestProperty("X-Naver-Client-Secret", secret);
			String param = "source=" + tvo.getSource() + "&target=" + tvo.getTarget() + "&text=" + text;
			hc.setDoOutput(true);
			DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			
			int status = hc.getResponseCode();
			
			InputStreamReader isr = new InputStreamReader(hc.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			StringBuffer res = new StringBuffer();
			String str = null;
			while((str=br.readLine())!=null) {
				res.append(str);
			}
			br.close();
			TransVO resultTvo = om.readValue(res.toString(), TransVO.class)
			
			
		}
		return null;
	}

	@Override
	public List<PapagoInfoVO> getPapagoInfoList(PapagoInfoVO pvo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
