package kr.co.springboot.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.springboot.data.dao.ProductDAO;
import kr.co.springboot.data.dto.ProductDto;
import kr.co.springboot.data.dto.ProductResponseDto;
import kr.co.springboot.data.entity.Product;
import kr.co.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;

	@Override
	public ProductResponseDto getProduct(Long number) {
		Product product = productDAO.selectProduct(number);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(product.getNumber());
		productResponseDto.setName(product.getName());
		productResponseDto.setPrice(product.getPrice());
		productResponseDto.setStock(product.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto saveProduct(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct = productDAO.insertProduct(product);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(savedProduct.getNumber());
		productResponseDto.setName(savedProduct.getName());
		productResponseDto.setPrice(savedProduct.getPrice());
		productResponseDto.setStock(savedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) throws Exception {
		Product changedProduct = productDAO.updateProductName(number, name);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(changedProduct.getNumber());
		productResponseDto.setName(changedProduct.getName());
		productResponseDto.setPrice(changedProduct.getPrice());
		productResponseDto.setStock(changedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productDAO.deleteProduct(number);
	}
	
	
	
	
}
