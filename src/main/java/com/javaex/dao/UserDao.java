package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//로그인
	public UserVo userSelectByIdPw(UserVo userVo) {
		System.out.println("UserDao.userSelectByIdPw");
		
		//System.out.println(userVo); //id pw
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		//System.out.println(authUser);//no name
		
		return authUser;
		
	}
	
	//조회no(회원정보수정 폼)로 한명데이터 가져오기
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");
		
		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}
	
	//회원정보 수정하기
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userUpdate");
		
		int count = sqlSession.update("user.update", userVo);
		
		return count;
	}
	
	
}
