package kr.co.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.ArticleDAO;
import kr.co.board.vo.ArticleVO;

@Service
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	public ArticleVO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleVO> selectArticles(){
		return dao.selectArticles();
	}
	public void insertArticle(ArticleVO vo) {
		int result = 0;
		
		vo.setFile(result);
		
		dao.insertArticle(vo);
	}
	public void updateArticle(ArticleVO vo) {
		dao.updateArticle(vo);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	
}
