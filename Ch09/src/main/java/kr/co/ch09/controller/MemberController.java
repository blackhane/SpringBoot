package kr.co.ch09.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.MemberService;
import kr.co.ch09.vo.MemberVO;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;

	//현재 시간
	LocalDateTime localDateTime = LocalDateTime.now();
	Timestamp time = Timestamp.valueOf(localDateTime);
	
	@GetMapping("")
	public List<MemberVO> list() {
		return service.selectMembers();
	}
	
	@GetMapping("/{uid}")
	public MemberVO list(@PathVariable("uid") String uid) {
		return service.selectMember(uid);
	}
	
	@PostMapping("")
	public List<MemberVO> register(MemberVO vo) {
		vo.setRdate(time);
		service.insertMember(vo);
		
		return service.selectMembers();
	}
	
	@PutMapping("")
	public List<MemberVO> modify(MemberVO vo) {
		vo.setRdate(time);
		service.updateMember(vo);
		
		return service.selectMembers();
	}
	
	@DeleteMapping("/{uid}")
	public List<MemberVO> delete(@PathVariable("uid") String uid) {
		service.deleteMember(uid);
		
		return service.selectMembers();
	}
	
}
