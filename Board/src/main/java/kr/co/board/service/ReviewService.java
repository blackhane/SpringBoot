package kr.co.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.ReviewDAO;
import kr.co.board.vo.ArticleVO;

@Service
public class ReviewService {

	@Autowired
	private ReviewDAO dao;
	
	public List<ArticleVO> selectReviews(int no){
		return dao.selectReviews(no);
	}
	
	@Transactional
	public int insertReview(ArticleVO vo) {
		dao.updateCommentUp(vo);
		return dao.insertReview(vo);
	}
	
	public int modifyReview(ArticleVO vo) {
		return dao.modifyReview(vo);
	}
	
	@Transactional
	public int deleteReview(ArticleVO vo) {
		System.out.println(vo.getParent());
		dao.updateCommentDown(vo.getParent());
		return dao.deleteReview(vo.getNo());
	}
	
}
