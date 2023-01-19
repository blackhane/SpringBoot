package kr.co.sboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sboard.Service.ArticleService;
import kr.co.sboard.vo.ArticleVO;

@MapperScan("kr.co.sboard.dao")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@GetMapping("/list")
	public String list(Model model, int pg) {
		List<ArticleVO> vo = service.selectArticles(pg);
		int count = service.countArticles();
		
		//페이지
		int pageGroupStart = 1;
		int pageGroupEnd = 1;
		
		int currentPageGroup = (int)Math.ceil(pg / 10.0);
		pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > count) {
			pageGroupEnd = count;
		}
		
		int num = (pg - 1) * 10;
		
		model.addAttribute("articles", vo);
		model.addAttribute("pageGroupStart", pageGroupStart);
		model.addAttribute("pageGroupEnd", pageGroupEnd);
		model.addAttribute("count", count);
		model.addAttribute("pg", pg);
		model.addAttribute("num", num);
		return "/list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "/write";
	}
	
	@PostMapping("/write")
	public String write(ArticleVO vo, HttpServletRequest request) {
		vo.setRegip(request.getRemoteAddr());
		service.insertArticle(vo);
		return "redirect:/list";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "/modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "/delete";
	}
	
	
}
