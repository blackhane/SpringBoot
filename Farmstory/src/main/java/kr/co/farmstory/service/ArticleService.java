package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;
    //게시물
    public void insertArticle(ArticleVO vo){
        dao.insertArticle(vo);
    }
    public ArticleVO selectArticle(int no){
        return dao.selectArticle(no);
    }
    public List<ArticleVO> selectArticles(String cate){
        return dao.selectArticles(cate);
    }
    public void updateArticle(ArticleVO vo){
        dao.updateArticle(vo);
    }
    public void deleteArticle(int no){
        dao.deleteArticle(no);
    }

    //파일
    public void insertFile(){}
    public void deleteFile(){}

    //댓글
    public int insetComment(ArticleVO vo){
        return dao.insetComment(vo);
    }
    public List<ArticleVO> selectComment(int no){
        return dao.selectComment(no);
    }
    public int updateComment(ArticleVO vo){
        return dao.updateComment(vo);
    }
    public int deleteComment(int no){
        return dao.deleteComment(no);
    }

    public void updateArticleHitUp(int no){
        dao.updateArticleHitUp(no);
    }
    public void updateArticleCommentUp(int no){
        dao.updateArticleCommentUp(no);
    }
    public void updateArticleCommentDown(int no){
        dao.updateArticleCommentDown(no);
    }

    public List<ArticleVO> selectIndexStory(){
        return dao.selectIndexStory();
    }
    public List<ArticleVO> selectIndexGrow(){
        return dao.selectIndexGrow();
    }
    public List<ArticleVO> selectIndexSchool(){
        return dao.selectIndexSchool();
    }
    public List<ArticleVO> selectIndexTab(String cate){
        return dao.selectIndexTab(cate);
    }

}
