package kr.co.ch10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubController {

	@GetMapping("/sub/hello")
	public String hello() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		return "/sub/hello";
	}
	
	@GetMapping("/sub/welcome")
	public String welcome() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		return "/sub/welcome";
	}
	
	@GetMapping("/sub/greeting")
	public String greeting() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		return "/sub/greeting";
	}
}
