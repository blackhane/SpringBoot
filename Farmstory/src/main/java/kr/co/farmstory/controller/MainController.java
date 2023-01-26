package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Clock;
import java.util.List;

@Controller
@MapperScan("kr.co.farmstory.dao")
public class MainController {

    @Autowired
    private ArticleService service;

    @GetMapping(value = {"", "index"})
    public String index(Model model){
        List<ArticleVO> vo1 = service.selectIndexStory();
        List<ArticleVO> vo2 = service.selectIndexGrow();
        List<ArticleVO> vo3 = service.selectIndexSchool();

        model.addAttribute("vo1", vo1);
        model.addAttribute("vo2", vo2);
        model.addAttribute("vo3", vo3);
        return "index";
    }

    @ResponseBody
    @GetMapping("indexTab/{cate}")
    public List<ArticleVO> indexTab(@PathVariable("cate") String cate){
        return service.selectIndexTab(cate);
    }

}
