package kr.co.sboard.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="board_article")
public class ArticleEntity {

	@Id
	private int no;
	private int parent;
	private int comment;
	private String title;
	private String content;
	private String uid;
	private String regip;
	private String rdate;
	
	private String nick;
	
	public String getRdate() {
		return rdate.substring(2, 10);
	}
	
}
