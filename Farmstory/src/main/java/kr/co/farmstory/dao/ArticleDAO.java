package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDAO {

    //게시물
    public void insertArticle(ArticleVO vo);
    public ArticleVO selectArticle(int no);
    public List<ArticleVO> selectArticles(String arg0, int arg1);
    public int countArticles(String cate);
    public void updateArticle(ArticleVO vo);
    public void deleteArticle(int no);

    //파일
    public int insertFile(FileVO vo);
    public FileVO selectFile(int fno);
    public void updateFileDownload(int fno);
    public void deleteFile(int no);

    //댓글
    public int insetComment(ArticleVO vo);
    public List<ArticleVO> selectComment(int no);
    public int updateComment(ArticleVO vo);
    public int deleteComment(int no);
    public void updateArticleHitUp(int no);
    public void updateArticleCommentUp(int no);
    public void updateArticleCommentDown(int no);

    public List<ArticleVO> selectIndexStory();
    public List<ArticleVO> selectIndexGrow();
    public List<ArticleVO> selectIndexSchool();

    public List<ArticleVO> selectIndexTab(String cate);

}
