package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//!로그인!
	//@ResponseBody - RestController를 쓰면 기본적으로 json으로 찾기때문에 안써줘도 됨.
	@PostMapping("/api/users/login")//GetMapping을 쓰면 뒤에 method안써도 되고 남은건 value라 생략해도 된다.
	public UserVo login(@RequestBody UserVo userVo, HttpServletResponse response) {//id,pw / 응답문서
		System.out.println("UserController.login");
		
		System.out.println(userVo);
		
		//임시 - test용- 실제 보내는게 없으면 @RequestBody를 지워서 테스트하기
//		UserVo userVo = new UserVo();
//		userVo.setId("zzz");
//		userVo.setPassword("zzz");
		
		UserVo authUser= userService.exeLogin(userVo);//id,pw넘기고 no,name가져오기
		System.out.println(authUser);
		//토큰발급 헤더에 실어 보낸다.
//		JwtUtil jwtUtil= new JwtUtil();- 스테틱에 올라가있으니 이건 안해도됨: 클래스 이름으로 올라감. 처음에 우리가 정한 이름이 없으니까!!
		if(authUser !=null) {//로그인성공
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());//name은 동명이인있을 수 있으니 no만 줌			
		}
		
		
		System.out.println(authUser);
		return authUser;
	}
	
}
