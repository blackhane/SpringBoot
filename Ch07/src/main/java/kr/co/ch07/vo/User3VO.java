package kr.co.ch07.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user3")
public class User3VO {

	@Id
	private String uid;
	private String name;
	private String hp;
	private int age;

}
