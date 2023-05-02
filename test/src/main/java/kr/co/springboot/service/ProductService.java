package kr.co.springboot.service;

import kr.co.springboot.data.dto.ProductDto;
import kr.co.springboot.data.dto.ProductResponseDto;

public interface ProductService {

	ProductResponseDto getProduct(Long number);
	ProductResponseDto saveProduct(ProductDto productDto);
	ProductResponseDto changeProductName(Long number, String name) throws Exception;
	void deleteProduct(Long number) throws Exception;

}
