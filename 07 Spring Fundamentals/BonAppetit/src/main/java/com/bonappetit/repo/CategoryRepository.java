package com.bonappetit.repo;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryNameEnum category);
}
