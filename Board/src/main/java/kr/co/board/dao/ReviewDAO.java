package kr.co.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.board.vo.ArticleVO;

@Repository
@Mapper
public interface ReviewDAO {

	public List<ArticleVO> selectReviews(int no);
	public int insertReview(ArticleVO vo);
	public int modifyReview(ArticleVO vo);
	public int deleteReview(int no);
	public void updateCommentUp(ArticleVO vo);
	public void updateCommentDown(int no);
	
}
