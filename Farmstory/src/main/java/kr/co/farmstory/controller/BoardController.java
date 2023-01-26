package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@MapperScan("kr.co.farmstory.dao")
public class BoardController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        List<ArticleVO> articles = service.selectArticles(cate);
        log.info("게시물 : " + articles.size());
        model.addAttribute("articles", articles);
        return "board/list";
    }

    @GetMapping("board/view")
    public String view(Model model, String group, String cate, int no){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        ArticleVO vo = service.selectArticle(no);
        service.updateArticleHitUp(no);
        model.addAttribute("vo", vo);
        return "board/view";
    }

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/write";
    }

    @PostMapping("board/write")
    public String write(String group, String cate, ArticleVO vo, HttpServletRequest request){
        vo.setRegip(request.getRemoteAddr());
        service.insertArticle(vo);
        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

    @GetMapping("board/modify")
    public String modify(Model model, String group, String cate, int no){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        ArticleVO vo = service.selectArticle(no);
        model.addAttribute("vo", vo);
        return "board/modify";
    }

    @PostMapping("board/modify")
    public String modify(String group, String cate, ArticleVO vo){
        service.updateArticle(vo);
        return "redirect:/board/view?group="+group+"&cate="+cate+"&no="+vo.getNo();
    }

    @GetMapping("board/delete")
    public String delete(String group, String cate, int no){
        service.deleteArticle(no);
        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

    @ResponseBody
    @GetMapping("board/replyList/{no}")
    public List<ArticleVO> replyList(@PathVariable("no") int no){
        return service.selectComment(no);
    }

    @ResponseBody
    @PostMapping("board/replyRegister")
    public Map<String, Integer> replyRegister(ArticleVO vo, HttpServletRequest request){
        vo.setRegip(request.getRemoteAddr());
        int rs = service.insetComment(vo);
        service.updateArticleCommentUp(vo.getParent());
        Map<String, Integer> result = new HashMap<>();
        result.put("result", rs);
        return result;
    }

    @ResponseBody
    @DeleteMapping("board/replyDelete")
    public int replyDelete(ArticleVO vo){
        service.updateArticleCommentDown(vo.getParent());
        return service.deleteComment(vo.getNo());
    }

    @ResponseBody
    @PutMapping("board/replyModify")
    public int replyModify(ArticleVO vo){
        return service.updateComment(vo);
    }
}
