package kr.co.springboot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.springboot.service.impl.ProductServiceImpl;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProductServiceImpl productService;
	
	@Test
	@DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
	void getProductTest() throws Exception {
		
		
	}
	
}
