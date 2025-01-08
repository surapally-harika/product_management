package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;

public interface ProductService {
	
	
	public Product add(ProductDto productdto);
	
	public Product Update(Integer id,ProductDto productDto);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Integer id);
	
	public void Delete(Integer id);

}
