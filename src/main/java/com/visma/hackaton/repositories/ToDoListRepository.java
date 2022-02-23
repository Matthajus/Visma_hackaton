package com.visma.hackaton.repositories;

import com.visma.hackaton.domain.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
