package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("select u.assignedTasks from User u where u.id = :id")
    Set<Task> findAssignedTasksById(@Param("id") Long id);
}
