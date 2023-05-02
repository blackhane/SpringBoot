package kr.co.springboot.data.dto;

import lombok.Data;

@Data 
public class ProductResponseDto {

	public ProductResponseDto(long l, String string, int i, int j) {
		// TODO Auto-generated constructor stub
	}
	private Long number;
	private String name;
	private int price;
	private int stock;
}
