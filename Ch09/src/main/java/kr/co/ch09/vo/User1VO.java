package kr.co.ch09.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User1VO {

	private String uid;
	private String pass;
	private String name;
	private String hp;
	private int age;
	
}
