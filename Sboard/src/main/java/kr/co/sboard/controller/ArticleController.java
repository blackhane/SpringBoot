package kr.co.sboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.sboard.Service.ArticleService;
import kr.co.sboard.Service.ReplyService;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;

@MapperScan("kr.co.sboard.dao")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@Autowired
	private ReplyService service2;
	
	@GetMapping("list")
	public String list(Model model, int pg) {
		//게시물 리스트
		List<ArticleVO> vo = service.selectArticles(pg);
		
		//게시물 페이징
		int lastPageNum = service.countArticles();
		int[] groups = service.currentPageGroup(pg, lastPageNum);
		int num = (pg - 1) * 10;
		
		model.addAttribute("articles", vo);
		model.addAttribute("groups", groups);
		model.addAttribute("pg", pg);
		model.addAttribute("num", num);
		return "list";
	}
	
	@GetMapping("write")
	public String write() {
		return "write";
	}
	
	@PostMapping("write")
	public String write(ArticleVO vo, HttpServletRequest request) {
		vo.setRegip(request.getRemoteAddr());
		service.insertArticle(vo);
		return "redirect:/list?pg=1";
	}
	
	@GetMapping("view")
	public String view(Model model, int no, int pg) {
		ArticleVO vo = service.selectArticle(no);
		service.updateArticleHit(no);
		model.addAttribute(vo);
		model.addAttribute("pg", pg);
		return "view";
	}

	@GetMapping("download")
	public ResponseEntity<Resource> download(int fno) throws IOException {
		FileVO vo = service.selectFile(fno);
		service.updateFileDownload(fno);
		
		ResponseEntity<Resource> respEntity = service.fileDownload(vo);
		return respEntity;
	}
	
	@GetMapping("modify")
	public String modify(Model model, int no, int pg) {
		ArticleVO vo = service.selectArticle(no);
		model.addAttribute(vo);
		model.addAttribute("pg", pg);
		return "modify";
	}
	
	@PostMapping("modify")
	public String modify(ArticleVO vo) {
		service.updateArticle(vo);
		return "redirect:/list?pg=1";
	}
	
	@GetMapping("delete")
	public String delete(int no) {
		service.deleteArticle(no);
		service.deleteFile(no);
		return "redirect:/list?pg=1";
	}
	
	@ResponseBody
	@PostMapping("replyRegister")
	public Map<String, Integer> replyRegister(ArticleEntity vo) {
		int no = service2.insertComment(vo);
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("result", no);
		return result;
	}
	
	@ResponseBody
	@GetMapping("replyList/{no}")
	public List<ArticleVO> replyList(@PathVariable("no") int no) {
		return service2.selectComment(no);
	}
	
}
