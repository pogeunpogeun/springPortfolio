package com.hhj.springPortfolio._common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/*
 * Servers > server.xml
 * <Context docBase="C:/HHJ/attach" path="/springStudy/attach" reloadable="true"/>
 */

public class MultipartUpload {
	String attachPath = Constants.ATTACH_PATH;
	int maxUpload = Constants.MAX_UPLOAD;

	
	private int createDirectory(String uploadPath) {
		java.io.File isDir = new java.io.File(uploadPath);
		if(!isDir.isDirectory()) {
			isDir.mkdirs();
		}
		int result = 0;
		if(isDir.exists()) {
			result++;
		}
		return result;
	}
	// 파일 이름이 중복되지 않도록 처리
	private String attachFileReName(String originalFileName, byte[] fileData, String uploadPath) {
		String ext = originalFileName.substring(originalFileName.lastIndexOf('.')).toLowerCase();
		
		// UUID 생성 (Universal Unique Identifier, 범용 고유 식별자) 
		UUID uuid = UUID.randomUUID();
		
		String newFileName = uuid.toString() + ext;
		// 임시 디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		// FileCopyUtils.copy(바이트배열, 파일객체)
		java.io.File f1 = new java.io.File(uploadPath, newFileName);
		
		try {
			FileCopyUtils.copy(fileData, f1);
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return newFileName;
	}
	public List<String> attachProc(List<MultipartFile> multiFileList, String savePath) {
		String uploadPath = attachPath + savePath;
		
		int createDirResult = createDirectory(uploadPath);
		if(createDirResult <= 0) {
			System.out.println("Directory X");
		}
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < multiFileList.size(); i++) {
			MultipartFile file = multiFileList.get(i);
			
			String originalFileName = "";
			String newFileName = "";
			long fileSize = 0;
			String contentType = "";
			String mimeType = "";
			
			int failCounter = 0;
			try {
				originalFileName = file.getOriginalFilename();
				newFileName = attachFileReName(originalFileName, file.getBytes(), uploadPath);
				fileSize = file.getSize();
				contentType = file.getContentType();
				
				InputStream inputStream;
			 	inputStream = file.getInputStream();
				Tika tika = new Tika();
				mimeType = tika.detect(inputStream);
			} catch(Exception e) {
				//e.printStackTrace();
				failCounter++;
			}
			if(contentType.equals(mimeType) && (failCounter == 0)) {
				String msg = "";
				msg += originalFileName + ",";
				msg += newFileName + ",";
				msg += fileSize + ",";
				msg += contentType + ",";
				msg += mimeType;
				list.add(msg);
			} else {
				list.add("-");
			}
		}				
		return list;
	}
	public void attachDownload(
		HttpServletResponse response,
		String originalName,
		String saveName,
		String savePath
		) {
		try {
			String uploadPath = attachPath + savePath + "/" + saveName;
			
			File file = new File(uploadPath);
			response.setHeader("Content-Disposition", "attachment;filename=" + originalName);
			
			FileInputStream fileInputStream = new FileInputStream(uploadPath);
			OutputStream out = response.getOutputStream();
			
			int read = 0;
			byte[] buffer = new byte[1024];
			while((read = fileInputStream.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
		} catch(Exception e) {
			System.out.println("download Error");
		}
	}
}
