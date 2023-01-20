package kr.co.sboard.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileVO {
	
	private int fno;
	private int parent;
	private String newName;
	private String oriName;
	private int download;
	private String rdate;

}
