package kr.co.sboard.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	public int deleteArticle(int no) {
		return dao.deleteArticle(no);
	}
	
	//파일 업로드
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
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
	
}
