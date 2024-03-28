package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GalleryVo;

@RestController
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//리스트 불러오기
	@GetMapping("/api/gallery")
	public JsonResult galleryList() {
		System.out.println("GalleryController.galleryList");
		
		List<GalleryVo> galleryList = galleryService.exeList();
		
		return JsonResult.success(galleryList);
	}
	
	//이미지 등록하기
	@PostMapping("/api/gallery")
	public JsonResult upload(@RequestParam MultipartFile file, @ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController.upload");
		System.out.println("fileName:"+file.getOriginalFilename());
		System.out.println(galleryVo);
		
		galleryVo.setFile(file);
		
		galleryService.exeUpload(galleryVo);
		
		return JsonResult.success("성공");
	}
	//이미지 1개 가져오기
	@GetMapping("/api/gallery/{no}")
	public JsonResult oneImg(@PathVariable(value = "no") int no) {
		System.out.println("GalleryController.oneImg");
		
		GalleryVo galleryVo = galleryService.exeSelectOne(no);
		
		return JsonResult.success(galleryVo) ;
	}
	
	//삭제
	@DeleteMapping("/api/gallery/{no}")
	public JsonResult delete(@PathVariable(value= "no")int no) {
		System.out.println("GalleryController.delete");
		
		int count = galleryService.exeDelete(no);
		
		return JsonResult.success(count);
	}
}
