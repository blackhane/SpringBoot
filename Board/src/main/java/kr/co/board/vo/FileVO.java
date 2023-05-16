package kr.co.board.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FileVO {

	private int fno;
	private int parent;
	private String newName;
	private String oriName;
	private int download;
	private String rdate;

}
