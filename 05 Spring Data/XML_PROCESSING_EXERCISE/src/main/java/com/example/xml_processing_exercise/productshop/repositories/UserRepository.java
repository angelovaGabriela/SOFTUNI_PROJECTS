package com.example.xml_processing_exercise.productshop.repositories;

import com.example.xml_processing_exercise.productshop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
