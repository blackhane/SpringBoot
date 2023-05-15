package kr.co.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.board.service.ArticleService;
import kr.co.board.vo.ArticleVO;

@Controller
public class MainController {
	
	@Autowired
	private ArticleService service;
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<ArticleVO> articles = service.selectArticles();
		model.addAttribute("articles", articles);
		
		return "list";
	}
	
	@GetMapping("write")
	public String write() {
		return "write";
	}
	
	@PostMapping("write")
	public String write(ArticleVO vo, HttpServletRequest req) {
		
		vo.setRegip(req.getRemoteAddr());
		service.insertArticle(vo);
		
		return "redirect:/list";
	}

	@GetMapping("view")
	public String view(Model model, int no) {
		
		ArticleVO vo = service.selectArticle(no);
		model.addAttribute("vo",vo);
		
		return "view";
	}
	
	@GetMapping("modify")
	public String modify(Model model, int no) {
		
		ArticleVO vo = service.selectArticle(no);
		model.addAttribute("vo",vo);
		
		return "modify";
	}
	
	@PostMapping("modify")
	public String modify(ArticleVO vo) {
		
		service.updateArticle(vo);
		
		return "redirect:/view?no=" + vo.getNo();
	}
	
	@GetMapping("delete")
	public String delete(int no) {
		
		service.deleteArticle(no);
		
		return "redirect:/list";
	}
	
}
