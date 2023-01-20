package kr.co.sboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.vo.ArticleVO;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

	public List<ArticleVO> findByParent(int no);
	
}
