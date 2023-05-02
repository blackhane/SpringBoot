package kr.co.springboot.data.dao;

import kr.co.springboot.data.entity.Product;

public interface ProductDAO {

	public Product insertProduct(Product product);
	public Product selectProduct(Long number);
	public Product updateProductName(Long number, String name) throws Exception;
	public void deleteProduct(Long number) throws Exception;
	
}
