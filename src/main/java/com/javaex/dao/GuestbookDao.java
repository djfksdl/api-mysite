package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트 
	public List<GuestbookVo> guestList() {
		System.out.println("GuestbookDao.guestList");
		
		List<GuestbookVo> guestList = sqlSession.selectList("guestbook.selectList");
		return guestList;
		
	}
	
	//등록하기- selectkey
	public int guestInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestInsert");
		
		int count = sqlSession.insert("guestbook.insertSelectkey",guestbookVo);
		
		return count;
	}
	
	//저장한 정보 1개 불러오기
	public GuestbookVo guestbookSelectOne(int no) {
		System.out.println("GuestbookDao.guestbookSelectOne");
		
		GuestbookVo gVo= sqlSession.selectOne("guestbook.selectOne", no);
		return gVo;
	}
	
	//삭제하기
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestDelete");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		
		return count;
	}
	
	
}
