package kr.co.board.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.board.config.FileConfig;
import kr.co.board.dao.ArticleDAO;
import kr.co.board.vo.ArticleVO;
import kr.co.board.vo.FileVO;

@Service
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	@Autowired
	private FileConfig fileConfig;
	
	public ArticleVO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleVO> selectArticles(){
		return dao.selectArticles();
	}
	
	@Transactional
	public void insertArticle(ArticleVO vo) {
		MultipartFile file = vo.getFname();
		if(file.isEmpty()) {
			vo.setFile(0);
			dao.insertArticle(vo);
		}else {
			vo.setFile(1);
			dao.insertArticle(vo);
			
			FileVO fvo = new FileVO();
			fvo.setParent(vo.getNo());
			fvo = fileConfig.fileUpload(file, fvo);
			dao.insertFile(fvo);
		}
	}
	
	public FileVO selectFile(int fno) {
		return dao.selectFile(fno);
	}
	
	public ResponseEntity<Resource> download(FileVO vo) throws IOException {
		return fileConfig.fileDownload(vo);
	}
	
	public void updateArticle(ArticleVO vo) {
		dao.updateArticle(vo);
	}
	
	public void deleteArticle(int no, String newName) {
		dao.deleteArticle(no);
		fileConfig.fileDelete(newName);
	}
	
}
