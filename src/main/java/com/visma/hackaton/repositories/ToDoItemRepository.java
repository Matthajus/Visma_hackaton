package com.visma.hackaton.repositories;

import com.visma.hackaton.domain.entities.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    @Modifying
    @Override
    @Query(value = "delete from todo_item where todo_item_id = ?1", nativeQuery = true)
    void deleteById(Long aLong);

    @Query(value = "select * from todo_item where todo_list_id =?1 order by is_done asc", nativeQuery = true)
    List<ToDoItem> findAllByToDoListId(long listId);
}
