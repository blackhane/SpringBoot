package kr.co.ch09.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooksVO {

	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private BookVO[] items;
	
}
