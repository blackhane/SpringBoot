package kr.co.board.config;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import kr.co.board.vo.FileVO;

@Configuration
public class FileConfig {

	//파일 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
		
	public FileVO fileUpload(MultipartFile file, FileVO vo) {
	
		String path = new File(uploadPath).getAbsolutePath();
		
		String oriName = file.getOriginalFilename();
		String ext = oriName.substring(oriName.lastIndexOf("."));
		
		String newName = UUID.randomUUID().toString() + ext;
		
		vo.setOriName(oriName);
		vo.setNewName(newName);
		
		try {
			file.transferTo(new File(path, newName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException {
		//String path = new File(uploadPath).getAbsolutePath()+"/"+vo.getNewName();
		Path path = Paths.get(uploadPath+"/"+vo.getNewName());
		
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentDisposition(ContentDisposition
				.builder("attachment")
				.filename(vo.getOriName(), StandardCharsets.UTF_8).build());
		
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
	
	public void fileDelete(String newName) {
		//파일 경로 지정
		Path path = Paths.get(uploadPath);
				
		//현재 게시판에 존재하는 파일객체를 만듬
		File file = new File(path + "/" + newName);
				
		if(file.exists()) { // 파일이 존재하면
			file.delete(); // 파일 삭제	
		}
	}
	
}
