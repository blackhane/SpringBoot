package kr.co.sboard.Service;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {
	
	@Autowired
	private ArticleDAO dao;
	
	public int insertArticle(ArticleVO vo) {
		int result = 0;
		MultipartFile file = vo.getFname();
		if(file.isEmpty()) {
			//파일이 첨부x
			result = dao.insertArticle(vo);
		}else {
			//파일이 첨부O
			vo.setFile(1);
			result = dao.insertArticle(vo);
			//파일 업로드
			FileVO fvo = new FileVO();
			fvo.setParent(vo.getNo());
			fileUpload(file, fvo);
			//파일 등록(Insert)
			dao.insertFile(fvo);
		}
		
		return result;
	}
	
	public ArticleVO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	
	public FileVO selectFile(int fno) {
		return dao.selectFile(fno);
	}
	
	public void updateFileDownload(int fno) {
		dao.updateFileDownload(fno);
	}
	
	public List<ArticleVO> selectArticles(int pg){
		log.info("페이지 : " + pg);
		//페이지 그룹 계산
		pg = (pg-1) * 10;
		
		return dao.selectArticles(pg);
	}
	
	public int countArticles() {
		int count = dao.countArticles();
		if(count % 10 == 0) {
			count = count / 10;
		}else {
			count = count / 10 + 1;
		}
		return count;
	}
	
	public int updateArticle(ArticleVO vo) {
		return dao.updateArticle(vo);
	}
	
	public int updateArticleHit(int no) {
		return dao.updateArticleHit(no);
	}
	
	public int deleteArticle(int no) {
		return dao.deleteArticle(no);
	}
	
	public void deleteFile(int no, String newName) {
		dao.deleteFile(no);
		fileDelete(newName);
	}
	
	
	//파일 경로
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	//파일 다운로드
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

	//파일 업로드
	public void fileUpload(MultipartFile file, FileVO fvo) {
		//파일 저장경로
		String path = new File(uploadPath).getAbsolutePath();
		//원본 이름
		String oriName = file.getOriginalFilename();
		String ext = oriName.substring(oriName.lastIndexOf("."));
		//저장될 이름
		String newName = UUID.randomUUID().toString() + ext;
		
		fvo.setOriName(oriName);
		fvo.setNewName(newName);
		
		//파일 저장
		try {
			file.transferTo(new File(path, newName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//파일 삭제
	public void fileDelete(String newName) {
		//파일 경로 지정
		Path path = Paths.get(uploadPath);
				
		//현재 게시판에 존재하는 파일객체를 만듬
		File file = new File(path + "/" + newName);
				
		if(file.exists()) { // 파일이 존재하면
			file.delete(); // 파일 삭제	
		}
	}
	
	//리스트 페이징
	public int[] currentPageGroup(int pg, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(pg / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] groups = {pageGroupStart, pageGroupEnd, lastPageNum};
		return groups;
	}
	
}
