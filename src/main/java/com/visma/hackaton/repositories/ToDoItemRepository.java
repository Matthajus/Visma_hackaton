package com.visma.hackaton.repositories;

import com.visma.hackaton.domain.entities.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
