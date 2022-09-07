package com.example.jsonexe.productshop.repositories;

import com.example.jsonexe.productshop.entities.categoties.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
