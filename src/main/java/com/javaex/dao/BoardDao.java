package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체 리스트
	public List<BoardVo> boardList() {
		System.out.println("BoardDao.boardList");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
	}
	
	//한명 글쓴거 가져오기
	public List<BoardVo> selectByNo(int no) {
		System.out.println("BoardDao.boardSelectByNo");
		
		List<BoardVo> bVo = sqlSession.selectOne("board.selectByNo", no);
		
		return bVo;
	}
}
