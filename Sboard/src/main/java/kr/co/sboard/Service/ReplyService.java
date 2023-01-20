package kr.co.sboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sboard.Repository.ArticleRepository;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.vo.ArticleVO;

@Service
public class ReplyService {

	@Autowired
	private ArticleRepository repo;
	
	public int insertComment(ArticleEntity vo) {
		repo.save(vo);
		return vo.getNo();
	}
	
	public List<ArticleVO> selectComment(int no) {
		return repo.findByParent(no);
	}
	
	public void updateComment() {}
	
	public void deleteComment() {}
	
}
