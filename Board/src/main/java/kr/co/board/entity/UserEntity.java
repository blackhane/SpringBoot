package kr.co.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board_user")
public class UserEntity {

	@Id
	private String uid;
	private String pass;
	private String pass1;
	private String pass2;
	private String name;
	private String nick;
	private String email;
	private String hp;
	private String zip;
	private String addr1;
	private String addr2;
	private int grade;
	private String regip;
	private String rdate;
	
}
