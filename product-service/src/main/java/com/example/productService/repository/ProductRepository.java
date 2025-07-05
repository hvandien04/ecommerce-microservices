package com.example.productService.repository;

import com.example.productService.entity.Category;
import com.example.productService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Collection<Product> findAllByCategory(Category category);
}
