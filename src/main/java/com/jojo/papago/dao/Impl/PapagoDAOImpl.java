package com.jojo.papago.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.jojo.papago.dao.PapagoDAO;
import com.jojo.papago.vo.PapagoInfoVO;

@Repository
public class PapagoDAOImpl implements PapagoDAO {
	
	@Resource
	SqlSessionFactory ssf;

	@Override
	public List<PapagoInfoVO> selectPapagoInfoList(PapagoInfoVO pvo) {
		
		SqlSession ss = ssf.openSession(false);
		try {
			return ss.selectList("com.sp.papago.dao.PapagoInfoMapper.selectPapagoInfoList");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return null;
	
	}

	@Override
	public PapagoInfoVO selectPapagoInfo(PapagoInfoVO pvo) {
		SqlSession ss = ssf.openSession(false);
		try {
			ss.selectOne("com.sp.papago.dao.PapagoInfoMapper.selectPapagoInfo");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return null;
	}

	@Override
	public int insertPapagoInfo(PapagoInfoVO pvo) {
		SqlSession ss = ssf.openSession(false);
		try {
			int cnt = ss.insert("com.sp.papago.dao.PapagoInfoMapper.insertPapagoInfo", pvo);
			ss.commit();
			return cnt;
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return 0;
	}

	@Override
	public int updatePapagoInfoForCnt(PapagoInfoVO pvo) {
		SqlSession ss = ssf.openSession(false);
		try {
			int cnt = ss.update("com.sp.papago.dao.PapagoInfoMapper.updatePapagoInfoForCnt", pvo);
			ss.commit();
			return cnt;
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return 0;
	}

}
