package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.BoardVo;

import jakarta.servlet.http.HttpServlet;

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
		
		BoardVo bVo = boardService.exeSelectOne(no);
		
		return JsonResult.success(bVo);
	}
	
	//삭제
	@DeleteMapping("/api/boards")
	public JsonResult remove(@RequestParam(value="no")int no) {
		System.out.println("BoardController.remove");
		
		int count =boardService.exeRemove(no);
		
		return JsonResult.success(count);
	}
	
	//글쓰기
	@PostMapping("/api/boards")
	public JsonResult addWirte(@RequestBody BoardVo boardVo ) {
		System.out.println("BoardController.addWirte");
		
		int count = boardService.exeAdd(boardVo);
		
		return JsonResult.success(count);
	}
	
	
}
