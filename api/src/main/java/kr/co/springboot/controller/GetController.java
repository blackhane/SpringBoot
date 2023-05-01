package kr.co.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.springboot.dto.MemberDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

	@GetMapping("/hello")
	public String getHello() {
		log.info("getHello 메서드가 호출되었습니다.");
		return "Hello World";
	}
	@GetMapping(value = "/variable1/{variable}")
	public String getVariable1(@PathVariable String variable) {
		log.info("getVariable1 메서드가 호출되었습니다.");
		return variable;
	}
	@GetMapping(value = "/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}
	@ApiOperation(value="GET 메서드 예제", notes="@requestParam을 활용한 GET Method")
	@GetMapping(value = "/request1")
	public String getRequestParam1(
			@ApiParam(value="이름", required = true) @RequestParam String name,
			@ApiParam(value="이메일", required = true) @RequestParam String email,
			@ApiParam(value="회사", required = true)@RequestParam String organization) {
		return name + " " + email + " " + organization;
	}
	@GetMapping(value = "/request3")
	public String getRequestParam1(MemberDto dto) {
		System.out.println(dto.toString());
		System.out.println(dto.getClass());
		return dto.getName() + " " + dto.getEmail()+ " " + dto.getOrganization();
	}
	
}
