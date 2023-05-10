package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    @Query("select t from Task t where t.user.id = :id")
    Set<Task> findAllByUserId(@Param("id") Long id);




}
