package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exceptions.Exception;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	public ProductRepo prepo;

	@Override
	public Product add(ProductDto productdto) {
		
		Product product = new Product();
		
		product.setName(productdto.getName());
		product.setPrice(productdto.getPrice());
		product.setUrl(productdto.getUrl());
		return prepo.save(product);
	}

	@Override
	public Product Update(Integer id, ProductDto productDto) {
		
		Product product = prepo.findById(id).orElseThrow(() -> new Exception("There is no product with this id"));
		
		if(product.getPrice() != null) {
			product.setPrice(productDto.getPrice());
			
		}
		return prepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return prepo.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		
		
		return prepo.findById(id).orElseThrow(() -> new Exception("There is no product with this id"));
				
	}

	@Override
	public void Delete(Integer id) {
		if (!prepo.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " not found.");
        }
         prepo.deleteById(id);
	
		
	}
	
	
	
}
