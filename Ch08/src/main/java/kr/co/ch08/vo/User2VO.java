package kr.co.ch08.vo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user2")
public class User2VO {

	@Id
	private String uid;
	private String pass;
	private String name;
	private String hp;
	private int age;
	
	@CreationTimestamp
	private LocalDateTime rdate;
}
