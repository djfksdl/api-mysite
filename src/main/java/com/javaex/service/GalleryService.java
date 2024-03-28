package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	//전체 리스트
	public List<GalleryVo> exeList(){
		System.out.println("GalleryService.exeList");
		
		List<GalleryVo> galleryList= galleryDao.getList();
		
		return galleryList;
	}
	
	//이미지 등록하기
	public void exeUpload(GalleryVo galleryVo) {
		System.out.println("GalleryService.exeUpload");
		
		//파일 저장 폴더
		String saveDir = "C:\\javaStudy\\upload";
		
		//1.파일 관련 정보 수집
		//오리지날 파일명
		String orgName = galleryVo.getFile().getOriginalFilename();
		System.out.println("orgName: "+ orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exeName: "+ exName);
		
		//저장파일명
		String saveName= System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : " + saveName);
		
		//파일 사이즈 
		long fileSize = galleryVo.getFile().getSize();
		System.out.println("fileSize: "+ fileSize);
		
		//파일 전체 경로
		String filePath = saveDir +"\\" + saveName;
		System.out.println("filePath: "+ filePath);
		
		//2. 파일정보를 DB에 저장
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		
		galleryDao.saveFile(galleryVo);
		
		//3.파일을 하드디스크에 저장
		try {
			byte[] fileData = galleryVo.getFile().getBytes();
			
			OutputStream os = new FileOutputStream(filePath); //이 이름으로 만들꺼다. 빨대 꽃음. filePath로 빈 파일이 생김
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();//빨대 회수
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		return saveName;
		
		
		
	}
	
	//이미지 1개 가져오기
	public GalleryVo exeSelectOne(int no) {
		System.out.println("GalleryService.exeSelectOne");
		
		GalleryVo galleryVo=galleryDao.selectOne(no);
		
		return galleryVo;
	}
	
	//삭제하기
	public int exeDelete(int no) {
		System.out.println("GalleryService.exeDelete");
		
		int count = galleryDao.delete(no);
		return count;
		
		
	}
}
