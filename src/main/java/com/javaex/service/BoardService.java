package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//전체리스트
	public List<BoardVo> exeList() {
		System.out.println("BoardService.exeList");
		
		List<BoardVo> boardList =boardDao.boardList();
		
		return boardList;
	}
	
	//한명 글쓴거 가져오기
	public List<BoardVo> exeSelectOne(int no) {
		System.out.println("BoardService.exeSelectOne");
		
		List<BoardVo> bVo = boardDao.selectByNo(no);
		
		return bVo;
	}
}
