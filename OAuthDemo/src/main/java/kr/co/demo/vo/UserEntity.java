package kr.co.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "java1_api")
public class UserEntity {
	
	@Id
	private long uid;
	private String nickname;
	private String email;

}
