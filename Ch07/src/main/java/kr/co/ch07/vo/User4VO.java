package kr.co.ch07.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user4")
public class User4VO {

	@Id
	private int seq;
	private String name;
	private int gender;
	private int age;
	private String addr;
	
}
