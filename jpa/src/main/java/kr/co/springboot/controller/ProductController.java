package kr.co.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.springboot.data.dto.ChangeProductNameDto;
import kr.co.springboot.data.dto.ProductDto;
import kr.co.springboot.data.dto.ProductResponseDto;
import kr.co.springboot.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public ResponseEntity<ProductResponseDto> getProduct(Long number){
		ProductResponseDto productResponseDto = productService.getProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@PostMapping()
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto){
		ProductResponseDto productResponseDto = productService.saveProduct(productDto);

		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@PutMapping()
	public ResponseEntity<ProductResponseDto> changeProductName(
			@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
		ProductResponseDto productResponseDto = productService.changeProductName(
				changeProductNameDto.getNumber(),
				changeProductNameDto.getName());

		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@DeleteMapping()
	public ResponseEntity<String> deleteProduct(Long number) throws Exception {
		productService.deleteProduct(number);

		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
	
}
