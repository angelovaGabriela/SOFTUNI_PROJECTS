package com.example.shoppingList.repository;

import com.example.shoppingList.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
