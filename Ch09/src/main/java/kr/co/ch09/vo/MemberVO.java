package kr.co.ch09.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="member")
public class MemberVO {

	@Id
	private String uid;
	
	private String pass;
	private String name;
	private String hp;
	private String pos;
	private int dep;
	private Timestamp rdate;
	
}
