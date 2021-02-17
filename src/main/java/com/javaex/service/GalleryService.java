package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	//갤러리 리스트 
	public List<GalleryVo> galleryList(){
		System.out.println("Service galleryList");
			
		return galleryDao.galleryList();
	}
	
	
	//갤러리 등록
	public String restore(MultipartFile file , GalleryVo galleryVo) {
		System.out.println("GalleryService.restore()");
		System.out.println(file.getOriginalFilename());
		
		System.out.println("restore 전"+ galleryVo);
		
		//db에 저장할 정보수집//
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지널 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);
		
		//확장자 -->확장자만 남기기 위해 
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName : " + exName);	
		
		//서버 저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("seveName : " + saveName);		
		
		//서버 파일패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);
		
		//파일 사이즈		
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);
		
		//vo에 데이터 넣기 
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);		
		galleryVo.setOrgName(orgName);
		galleryVo.setFileSize(fileSize);
		
		//db에 저장
		galleryDao.insert(galleryVo);
		
		System.out.println("restore 후 galleryVo :"+ galleryVo);
		
		//서버 하드 디스크 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return saveName;
		
	}
	
	
	//갤러리 이미지 1개 불러오기
	public GalleryVo gallerySelectOne(int no) {
		System.out.println("service gallerySelectOne no -->" + no);
		
		return galleryDao.selectOne(no);
	}

}
