package kr.co.vboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vboard.Service.ArticleService;
import kr.co.vboard.vo.ArticleVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@PostMapping("write")
	public void write(@RequestBody ArticleVO vo, HttpServletRequest req) {
		vo.setRegip(req.getRemoteAddr());
		service.insertArticle(vo);
	}
	

	@GetMapping("list")
	public Map<String, Object> list(int pg) {
		//게시물 리스트
		List<ArticleVO> vo = service.selectArticles(pg);
		
		//게시물 페이징
		int count = service.countArticles();
		int lastPageNum = 0;
		if(count % 10 == 0) {
			lastPageNum = count / 10;
		}else {
			lastPageNum = count / 10 + 1;
		}
		
		int[] groups = service.currentPageGroup(pg, lastPageNum);
		int num = (pg - 1) * 10;
	
		int pageStartNum = (pg -1) * 10;
		
		Map<String, Object> resultMap  =new HashMap<>();
		resultMap.put("articles", vo);
		resultMap.put("groups", groups);
		resultMap.put("pg", pg);
		resultMap.put("count", count);
		resultMap.put("pageStartNum", pageStartNum);		
		resultMap.put("lastPageNum", lastPageNum);		
		resultMap.put("num", num);
		
		return resultMap;
	}
	
}
