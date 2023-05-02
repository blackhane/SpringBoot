package kr.co.springboot.data.dao.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.springboot.data.dao.ProductDAO;
import kr.co.springboot.data.entity.Product;
import kr.co.springboot.data.repository.ProductRepository;

@Service
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product insertProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product selectProduct(Long number) {
		return productRepository.getById(number);
	}

	@Override
	public Product updateProductName(Long number, String name) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		Product updatedProduct;
		if(selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			product.setName(name);
			product.setUpdatedAt(LocalDateTime.now());
			
			updatedProduct = productRepository.save(product);
		}else {
			throw new Exception();
		}
		
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		if(selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			
			productRepository.delete(product);
		}else {
			throw new Exception();
		}
	}
	
	
	
}
