package kr.co.springboot.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.springboot.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
