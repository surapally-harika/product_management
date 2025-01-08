package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.OPTIONS})
public class ProductController {
	
	@Autowired
	public ProductService pservice;
	
	
	@PostMapping("/addproduct")
	public ResponseEntity<Product> add(@RequestBody ProductDto productDto){
		
		System.out.println(productDto);
		Product product = pservice.add(productDto);
		
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAll(){
		
		List<Product> products = pservice.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Product> getProdcutById(@PathVariable Integer id){
		
		Product products = pservice.getProductById(id);
		
		return new ResponseEntity<Product>(products,HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Product> Update(@PathVariable Integer id,@RequestBody ProductDto productDto){
		
		Product products = pservice.Update(id, productDto);
		
		return new ResponseEntity<Product>(products,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> Delete(@PathVariable Integer id){
		  
		try {
            pservice.Delete(id);
            return ResponseEntity.ok("Product deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
	}
	
}
