package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.BoardVo;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//전체리스트
	@GetMapping("/api/boards")
	public JsonResult list() {
		System.out.println("BoardController.list");
		
		List<BoardVo> boardList= boardService.exeList();
		
		return JsonResult.success(boardList);
	}
	
	//한명 글쓴거 가져오기
	@GetMapping("/api/boards/{no}")
	public JsonResult readOne(@PathVariable(value="no")int no) {
		System.out.println("BoardController.readOne");
		
//		System.out.println(no);
		
		List<BoardVo> bVo = boardService.exeSelectOne(no);
		
		return JsonResult.success(bVo);
	}
	
	
}
