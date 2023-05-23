package kr.co.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.board.service.ArticleService;
import kr.co.board.vo.ArticleVO;
import kr.co.board.vo.FileVO;
import kr.co.board.vo.PageGroup;

@Controller
public class MainController {
	
	@Autowired
	private ArticleService service;
	
	@GetMapping("list")
	public String list(Model model,@RequestParam(required = false) Integer pg) {
		
		if(pg == null) {
			pg = 1;
		}
		
		List<ArticleVO> articles = service.selectArticles(pg);
		model.addAttribute("articles", articles);
		
		PageGroup pageGroup = service.selectCountArticles(pg);
		pageGroup.setPg(pg);
		model.addAttribute("pageGroup", pageGroup);
		
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
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(int fno) throws IOException {
		FileVO vo = service.selectFile(fno);
		
		ResponseEntity<Resource> respEntity = service.download(vo);
		return respEntity;
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
	public String delete(int no, String newName) {
		
		service.deleteArticle(no, newName);
		
		return "redirect:/list";
	}
	
}
