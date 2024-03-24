package com.gpv.gestion.domain.service;

import com.gpv.gestion.domain.Product;
import com.gpv.gestion.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
   public Optional<Product> getproduct(int productId){
        return productRepository.getProduct(productId);
   }
    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
     return getproduct(productId).map(product ->{ productRepository.delete(productId);
     return true;}).orElse(false);
    }
}
