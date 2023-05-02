package kr.co.springboot.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.springboot.data.dto.ProductDto;
import kr.co.springboot.data.dto.ProductResponseDto;
import kr.co.springboot.data.entity.Product;
import kr.co.springboot.data.repository.ProductRepository;
import kr.co.springboot.service.ProductService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponseDto getProduct(Long number) {
		logger.info("[getProduct] input number : " + number);
		Product product = productRepository.findById(number).get();

		logger.info("[getProduct] product number : " + product.getNumber() + ", name : " + product.getName());
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(product.getNumber());
		productResponseDto.setName(product.getName());
		productResponseDto.setPrice(product.getPrice());
		productResponseDto.setStock(product.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto saveProduct(ProductDto productDto) {
		logger.info("[saveProduct] productDto : " + productDto.toString());
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct = productRepository.save(product);
		logger.info("[saveProduct] savedProduct : " + savedProduct);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(savedProduct.getNumber());
		productResponseDto.setName(savedProduct.getName());
		productResponseDto.setPrice(savedProduct.getPrice());
		productResponseDto.setStock(savedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) throws Exception {
		Product foundProduct = productRepository.findById(number).get();
		foundProduct.setName(name);
		Product changedProduct = productRepository.save(foundProduct);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(changedProduct.getNumber());
		productResponseDto.setName(changedProduct.getName());
		productResponseDto.setPrice(changedProduct.getPrice());
		productResponseDto.setStock(changedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productRepository.deleteById(number);
	}
	
	
	
	
}
