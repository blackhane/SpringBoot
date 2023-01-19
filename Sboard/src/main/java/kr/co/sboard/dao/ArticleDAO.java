package kr.co.sboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;

@Repository
@Mapper
public interface ArticleDAO {
	
	public int insertArticle(ArticleVO vo);
	public ArticleVO selectArticle(int no);
	public List<ArticleVO> selectArticles(int pg);
	public int countArticles();
	public int updateArticle(ArticleVO vo);
	public int deleteArticle(int no);
	
	public int insertFile(FileVO vo);
	
}
