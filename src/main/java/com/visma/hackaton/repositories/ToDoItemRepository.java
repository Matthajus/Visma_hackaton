package com.visma.hackaton.repositories;

import com.visma.hackaton.domain.entities.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    @Modifying
    @Query(value = "update #{#entityName} i set i.is_done = ? where i.todo_item_id = ?", nativeQuery = true)
    int updateToDoItemAsDone(boolean isDone, long id);

}