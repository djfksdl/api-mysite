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
	public BoardVo exeSelectOne(int no) {
		System.out.println("BoardService.exeSelectOne");
		
		BoardVo bVo = boardDao.selectByNo(no);
		
		return bVo;
	}
	
	//삭제
	public int exeRemove(int no) {
		System.out.println("BoardService.exeRemove");
		
		int count = boardDao.delete(no);
		
		return count ;
	}
	
	//글쓰기
	public int exeAdd(BoardVo boardVo) {
		System.out.println("BoardService.exeAdd");
		
		int count = boardDao.insert(boardVo);
		return count ;
	}
	
	//수정
	public int exeUpdate(BoardVo boardVo) {
		System.out.println("BoardService.exeUpdate");
		
		int count = boardDao.update(boardVo);
		return count;
	}
}
