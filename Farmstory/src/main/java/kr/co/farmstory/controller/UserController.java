package kr.co.farmstory.controller;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@MapperScan("kr.co.farmstory.dao")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("user/terms")
    public String terms(Model model){
        TermsVO terms = service.selectTerms();
        model.addAttribute("terms", terms);
        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("user/register")
    public String register(UserVO vo, HttpServletRequest request){
        //사용자 IP 값
        vo.setRegip(request.getRemoteAddr());
        int result = service.insertUser(vo);
        if(result > 0) {
            //회원가입 성공
            result = 101;
        }else {
            //회원가입 실패
            result = 100;
        }
        return "redirect:/user/login?success=" + result;
    }

    @ResponseBody
    @GetMapping("user/checkUid")
    public Map<String,Integer> checkUid(String uid){
        int rs = service.checkUid(uid);
        Map<String,Integer> result = new HashMap<>();
        result.put("result", rs);
        return result;
    }

    @ResponseBody
    @GetMapping("user/checkNick")
    public Map<String,Integer> checkNick(String nick){
        int rs = service.checkNick(nick);
        Map<String,Integer> result = new HashMap<>();
        result.put("result", rs);
        return result;
    }
}
