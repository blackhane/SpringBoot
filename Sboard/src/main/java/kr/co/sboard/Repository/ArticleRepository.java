package kr.co.sboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

	public List<ArticleEntity> findByParent(int no);
	
}
