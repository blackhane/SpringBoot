package kr.co.ch07.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User1VO {

	private String uid;
	private String name;
	private String hp;
	private int age;
	
}
