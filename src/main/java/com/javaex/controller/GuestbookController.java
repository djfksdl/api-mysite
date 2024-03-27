package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	//전체 리스트
	@GetMapping("/api/guests")
	public JsonResult list() {
		System.out.println("GuestbookController.list");
		
		List<GuestbookVo> guestList = guestbookService.exeList();
//		System.out.println(guestList);
		
		return JsonResult.success(guestList);
	}
	
	//등록하기
	@PostMapping("/api/guests")
	public JsonResult write(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.write");
		
		GuestbookVo guestVo = guestbookService.exeWrite(guestbookVo);
		return JsonResult.success(guestVo);
		
	}
	
	//삭제하기
	@DeleteMapping("/api/guests/{no}")
	public JsonResult remove(@PathVariable(value="no")int no
							,@RequestBody GuestbookVo guestbookVo) {//RequestBody는 modelAttribute처럼 작동한다. vue에서 객체로 넣으면 알아서 gs처럼 작동해서 넣어줌.내가 직접 new해서 묶지말고 디스패쳐한테서 만들어달라고하면 됨!
		System.out.println("GuestbookController.remove");
		
		guestbookVo.setNo(no);//주소로 넣은건 안들어감.
//		guestbookVo.setPassword(password); 이건 이미 setter로 작동되서 들어가있다.
//		System.out.println(guestbookVo);
		
		int count = guestbookService.exeRemove(guestbookVo);
		
		return null;
	}
}
