package kr.co.vboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.vboard.vo.ArticleVO;
import kr.co.vboard.vo.FileVO;

@Repository
@Mapper
public interface ArticleDAO {
	//게시물
	public int insertArticle(ArticleVO vo);
	public ArticleVO selectArticle(int no);
	public List<ArticleVO> selectArticles(int pg);
	public int countArticles();
	public int updateArticle(ArticleVO vo);
	public int updateArticleHit(int no);
	public int deleteArticle(int no);
	
	//파일
	public int insertFile(FileVO vo);
	public FileVO selectFile(int fno);
	public void updateFileDownload(int fno);
	public void deleteFile(int no);
	
}
