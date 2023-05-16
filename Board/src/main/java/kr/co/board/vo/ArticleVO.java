package kr.co.board.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ArticleVO {

	private int no;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private String content;
	private MultipartFile fname;
	private int file;
	private int hit;
	private String uid;
	private String regip;
	private String rdate;
	private String updatedDate;
	private String nick;
	
	private FileVO fileVo;
	
	public String getRdate() {
		return rdate.substring(2,10);
	}
	
}
