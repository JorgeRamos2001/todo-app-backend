package com.todoapp.repository;

import com.todoapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByUserId(UUID userId);
    List<Task> findByUserIdAndCompleted(UUID userId, boolean completed);

    Optional<Task> findByUserIdAndId(UUID userId, UUID id);
}
