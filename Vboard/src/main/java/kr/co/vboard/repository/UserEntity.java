package kr.co.vboard.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "board_users")
public class UserEntity {
	@Id
	private String uid;
	private String pass1;
	private String pass2;
	private String pass;	
	private String name;
	private String nick;
	private String email;
	private String hp;
	private int grade;
	private String zip;
	private String addr1;
	private String addr2;
	private String regip;
	private String rdate;	
}
