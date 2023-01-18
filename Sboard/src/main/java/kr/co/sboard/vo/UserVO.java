package kr.co.sboard.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {

	private String uid;
	private String pass;
	private String pass1;
	private String pass2;
	private String name;
	private String nick;
	private String email;
	private String hp;
	private int grade;
	private String zip;
	private String addr1;
	private String addr2;
	private String regip;
	private LocalDateTime rdate;
	private String sessId;
	private String sessLimitDate;
	private String wdate;
	
}
