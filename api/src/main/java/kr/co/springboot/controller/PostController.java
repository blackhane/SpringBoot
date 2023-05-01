package kr.co.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.springboot.dto.MemberDto;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

	@PostMapping("/member2")
	public String postMemberDto(MemberDto memberDto) {
		return memberDto.toString();
	}
}
