package com.example.shoppingList.repository;

import com.example.shoppingList.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
