package kr.co.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.board.vo.ArticleVO;

@Mapper
@Repository
public interface ArticleDAO {

	public ArticleVO selectArticle(int no);
	public List<ArticleVO> selectArticles();
	public void insertArticle(ArticleVO vo);
	public void updateArticle(ArticleVO vo);
	public void deleteArticle(int no);

}
