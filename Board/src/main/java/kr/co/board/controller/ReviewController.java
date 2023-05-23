package kr.co.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.board.service.ReviewService;
import kr.co.board.vo.ArticleVO;

@RestController
@RequestMapping(value = "review")
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	@GetMapping()
	public Map<String, List<ArticleVO>> list(int no) {
		
		List<ArticleVO> reviews =  service.selectReviews(no);
		Map<String, List<ArticleVO>> result = new HashMap<>();
		result.put("reviews", reviews);
		return result;
		
	}

	@PostMapping()
	public Map<String, Integer> write(ArticleVO vo, HttpServletRequest req) {
		
		vo.setRegip(req.getRemoteAddr());
		
		int rs = service.insertReview(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", rs);
		return result;

	}
	
	@PutMapping()
	public Map<String, Integer> modify(ArticleVO vo) {
	
		int rs = service.modifyReview(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", rs);
		return result;
		
	}
	
	@DeleteMapping()
	public Map<String, Integer> delete(ArticleVO vo) {
		
		int rs = service.deleteReview(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", rs);
		return result;
		
	}
	
}
