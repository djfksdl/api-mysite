package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인
	//@ResponseBody - RestController를 쓰면 기본적으로 json으로 찾기때문에 안써줘도 됨.
	@PostMapping("/api/users/login")//@PostMapping을 쓰면 뒤에 method안써도 되고 남은건 value라 생략해도 된다.
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {//id,pw / 응답문서
		System.out.println("UserController.login");
		
		//System.out.println(userVo);
		
		//임시 - test용- 실제 보내는게 없으면 @RequestBody를 지워서 테스트하기
//		UserVo userVo = new UserVo();
//		userVo.setId("zzz");
//		userVo.setPassword("zzz");
		
		UserVo authUser= userService.exeLogin(userVo);//id,pw넘기고 no,name가져오기
		//System.out.println(authUser);
		
		//토큰발급 헤더에 실어 보낸다.-이 타이밍에 토큰발급됨
//		JwtUtil jwtUtil= new JwtUtil();- 스테틱에 올라가있으니 new 안해도됨(시작할때 이미 올라가있음): 클래스 이름으로 올라감.(처음에 우리가 정한 이름이 없으니까!!)
		if(authUser !=null) {//로그인성공
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());//name은 동명이인있을 수 있으니 no만 줌		
			
			return JsonResult.success(authUser);
		}else {//로그인실패
			return JsonResult.fail("로그인실패");
		}
		
		
//		System.out.println(authUser);
//		return authUser;
	}
	
	//회원정보 수정폼
	@GetMapping("/api/users/modify")
	public JsonResult modifyform(HttpServletRequest request) {//원래는 request header?잘 꺼내줬는데 통쨰로 꺼낼때가 필요? 디스패쳐 서블릿에게 요청해야함11:18
		System.out.println("UserController.modifyform");
		//테스트용
		//int no = 5;
		
		//토큰내놔
//		String token = JwtUtil.getTokenByHeader(request);
//		System.out.println("token="+token);
//		
//		//검증 - 혹시나 수정됐는지
//		boolean check =JwtUtil.checkToken(token);
//		System.out.println(check);
//		
//		//이상없으면 no값 추출하면됨
//		if(check ==true) {
//			System.out.println("정상");
//			
//			int no =Integer.parseInt(JwtUtil.getSubjectFromToken(token));
//			System.out.println(no);
//		}
		int no = JwtUtil.getNoFromHeader(request); //이것만 써도 되서 public으로 열어놓고 나머지 메소드는 private로 막았음.위는 과정임.
		if(no!=-1) {
			UserVo userVo = userService.exeModifyForm(no);
			System.out.println(userVo);
//			return userVo;
			return JsonResult.success(userVo);
		}else {
			//이상있으면 데이터 안주면 됨 + 안내문자
			//토큰이 없거나(로그인안함) 변조된 경우
			
//			return null;//리턴 형태를 맞춰야하는데 그나마 들어갈 수 있는게 null밖에 없다.
			return JsonResult.fail("토큰x, 로그인 안함, 변조");
		}
		
	}
	
	//회원정보 수정
	@PutMapping("/api/users/modify")
	public JsonResult modify(@RequestBody UserVo userVo, HttpServletRequest request) {
		System.out.println("UserController.modify");
		System.out.println(userVo);
		
		int no = JwtUtil.getNoFromHeader(request);
		if(no !=-1) {//정상
			//db에 수정시킨다.
			userService.exeModify(userVo);
			
			//쌤이 쓰신방법
//			return userVo.getName();//vue에서 response.dasta쓸땐 요걸로 써줘야함.
			
			return JsonResult.success(userVo.getName());
		}else {
//			return "";
			return JsonResult.fail("로그인하지 않음");
		}
		
	}
	
	
	
	
	
}//컨트롤러 끝