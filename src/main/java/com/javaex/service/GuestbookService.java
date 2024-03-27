package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service	
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	//리스트
	public List<GuestbookVo> exeList() {
		System.out.println("GuestbookService.exeList");
		
		List<GuestbookVo> guestList = guestbookDao.guestList();
		
		return guestList;
		
	}
	
	//등록하기
	public GuestbookVo exeWrite(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeWrite");
		
		//등록하기
		int count = guestbookDao.guestInsert(guestbookVo);
		
		//등록한 no가져와서 1개의 정보 불러오기
		int no = guestbookVo.getNo();
		GuestbookVo gVo = guestbookDao.guestbookSelectOne(no);
		
		return gVo;
	}
}
