package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		System.out.println(guestList);
		
		return JsonResult.success(guestList);
	}
	
	//등록하기
	@PostMapping("/api/guests")
	public JsonResult write(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.write");
		
		GuestbookVo guestVo = guestbookService.exeWrite(guestbookVo);
		return JsonResult.success(guestVo);
		
	}
}
