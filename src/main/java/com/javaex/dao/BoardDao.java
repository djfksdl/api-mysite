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
	public BoardVo selectByNo(int no) {
		System.out.println("BoardDao.boardSelectByNo");
		
		BoardVo bVo = sqlSession.selectOne("board.selectByNo", no);
		
		return bVo;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("BoardDao.delete");
		
		int count = sqlSession.delete("board.delete", no);
		
		return count;
	}
	
	//글쓰기
	public int insert(BoardVo boardVo) {
		System.out.println("BoardDao.insert");
		
		int count = sqlSession.insert("board.insert", boardVo);
		
		return count;
	}
	
	//수정
	public int update(BoardVo boardVo) {
		System.out.println("BoardDao.update");
		
		int count = sqlSession.update("board.update", boardVo);
		
		return count;
	}
}
