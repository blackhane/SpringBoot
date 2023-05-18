package kr.co.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.board.vo.ArticleVO;
import kr.co.board.vo.FileVO;

@Mapper
@Repository
public interface ArticleDAO {

	public ArticleVO selectArticle(int no);
	public void updateArticleHit(int no);
	public List<ArticleVO> selectArticles(int pg);
	public int selectCountArticles();
	public void insertArticle(ArticleVO vo);
	public void insertFile(FileVO vo);
	public void updateArticle(ArticleVO vo);
	public void deleteArticle(int no);

	
	public FileVO selectFile(int fno);
}
