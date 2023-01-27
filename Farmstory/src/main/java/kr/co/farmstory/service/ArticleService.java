package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;
    //게시물
    @Transactional
    public void insertArticle(ArticleVO vo){
        MultipartFile file = vo.getFname();
        if(file.isEmpty()) {
            //파일이 첨부x
            dao.insertArticle(vo);
        }else {
            //파일이 첨부O
            vo.setFile(1);
            dao.insertArticle(vo);
            //파일 업로드
            FileVO fvo = new FileVO();
            fvo.setParent(vo.getNo());
            fileUpload(file, fvo);
            //파일 등록(Insert)
            dao.insertFile(fvo);
        }
    }
    public ArticleVO selectArticle(int no){
        return dao.selectArticle(no);
    }

    public FileVO selectFile(int fno) {
        return dao.selectFile(fno);
    }

    public void updateFileDownload(int fno) {
        dao.updateFileDownload(fno);
    }

    public List<ArticleVO> selectArticles(String cate, int pg){
        pg = (pg-1) * 10;
        return dao.selectArticles(cate, pg);
    }
    public int countArticles(String cate){
        int count = dao.countArticles(cate);
        if(count % 10 == 0) {
            count = count / 10;
        }else {
            count = count / 10 + 1;
        }
        return count;
    }
    public void updateArticle(ArticleVO vo){
        dao.updateArticle(vo);
    }
    public void deleteArticle(int no){
        dao.deleteArticle(no);
    }

    @Transactional
    public void deleteFile(int no, String newName){
        dao.deleteFile(no);
        fileDelete(newName);
    }

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

    //파일 경로
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    //파일 다운로드
    public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException {
        //String path = new File(uploadPath).getAbsolutePath()+"/"+vo.getNewName();
        Path path = Paths.get(uploadPath+"/"+vo.getNewName());

        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename(vo.getOriName(), StandardCharsets.UTF_8).build());

        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    //파일 업로드
    public void fileUpload(MultipartFile file, FileVO fvo) {
        //파일 저장경로
        String path = new File(uploadPath).getAbsolutePath();
        //원본 이름
        String oriName = file.getOriginalFilename();
        String ext = oriName.substring(oriName.lastIndexOf("."));
        //저장될 이름
        String newName = UUID.randomUUID().toString() + ext;

        fvo.setOriName(oriName);
        fvo.setNewName(newName);

        //파일 저장
        try {
            file.transferTo(new File(path, newName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //파일 삭제
    public void fileDelete(String newName) {
        //파일 경로 지정
        Path path = Paths.get(uploadPath);

        //현재 게시판에 존재하는 파일객체를 만듬
        File file = new File(path + "/" + newName);

        if(file.exists()) { // 파일이 존재하면
            file.delete(); // 파일 삭제
        }
    }

    //리스트 페이징
    public int[] currentPageGroup(int pg, int lastPageNum) {
        int currentPageGroup = (int)Math.ceil(pg / 10.0);
        int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
        int pageGroupEnd = currentPageGroup * 10;

        if(pageGroupEnd > lastPageNum) {
            pageGroupEnd = lastPageNum;
        }

        if(pageGroupEnd < 1){
            pageGroupEnd = 1;
        }

        int[] groups = {pageGroupStart, pageGroupEnd, lastPageNum};
        return groups;
    }
}
