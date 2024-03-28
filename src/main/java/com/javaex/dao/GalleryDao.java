package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체리스트
	public List<GalleryVo> getList() {
		System.out.println("GalleryDao.getList");
		
		List<GalleryVo> galleryList= sqlSession.selectList("gallery.galleryList");
		
		return galleryList;
		
	}
	
	//파일 업로드
	public void saveFile(GalleryVo galleryVo) {
		System.out.println("GalleryDao.saveFile");
		
		sqlSession.insert("gallery.uploadFile",galleryVo);
	}
	
	//이미지 1개 가져오기
	public GalleryVo selectOne(int no) {
		System.out.println("GalleryDao.selectOne");
		
		GalleryVo galleryVo= sqlSession.selectOne("gallery.selectOne", no);
		return galleryVo;
	}
	
	//삭제하기
	public int delete(int no) {
		System.out.println("GalleryDao.delete");
		
		int count = sqlSession.delete("gallery.delete", no);
		
		return count;
	}
}
