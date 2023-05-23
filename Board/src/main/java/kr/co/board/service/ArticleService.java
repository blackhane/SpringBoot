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
import kr.co.board.vo.PageGroup;

@Service
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	@Autowired
	private FileConfig fileConfig;
	
	public ArticleVO selectArticle(int no) {
		dao.updateArticleHit(no);
		return dao.selectArticle(no);
	}
	
	public List<ArticleVO> selectArticles(int pg){
		pg = (pg-1) * 10; 
		return dao.selectArticles(pg);
	}
	public PageGroup selectCountArticles(int pg) {
		int count = dao.selectCountArticles();
		int pageGroupStart = 0;
		int pageGroupEnd = 0;
		int lastPageNum = 0;
		int currentPageGroup = 0;
		
		if(count % 10 == 0) {
			lastPageNum = count / 10;
		}else {
			lastPageNum = count / 10 + 1;
		}
		
		currentPageGroup = (int)Math.ceil(pg / 10.0);
		pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}

		PageGroup pageGroup = new PageGroup();
		pageGroup.setPageGroupStart(pageGroupStart);
		pageGroup.setPageGroupEnd(pageGroupEnd);
		pageGroup.setLastPageNum(lastPageNum);
		pageGroup.setCount(count - ((pg-1) * 10));
		
		return pageGroup;
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

	@Transactional
	public void updateArticle(ArticleVO vo) {
		MultipartFile file = vo.getFname();
		if(file == null || file.isEmpty()) {
			if(vo.getFile() == 1) {
				return;
			}else {
				vo.setFile(0);
			}
			dao.updateArticle(vo);
		}else {
			vo.setFile(1);
			dao.updateArticle(vo);
			
			FileVO fvo = new FileVO();
			fvo.setParent(vo.getNo());
			fvo = fileConfig.fileUpload(file, fvo);
			dao.insertFile(fvo);
		}
		
	}
	
	public void deleteArticle(int no, String newName) {
		dao.deleteArticle(no);
		fileConfig.fileDelete(newName);
	}
	
}
