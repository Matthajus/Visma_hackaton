package com.visma.hackaton.repositories;

import com.visma.hackaton.domain.entities.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    @Query("select l from #{#entityName} l where l.name = ?1")
    ToDoList findToDoListByName(String name);
}
