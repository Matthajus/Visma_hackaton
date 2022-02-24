package com.visma.hackaton.services.interfaces;

import com.visma.hackaton.domain.dto.ToDoItemDto;

import java.util.List;

public interface IToDoItemService {

    List<ToDoItemDto> getAllToDoItems();

    ToDoItemDto saveToDoItem(ToDoItemDto itemToSave);

    ToDoItemDto getToDoItemById(long id);

    void deleteToDoItem(long id);

    ToDoItemDto updateToDoItem(ToDoItemDto toDoItemDto, long id);

    ToDoItemDto markAsDone(ToDoItemDto toDoItemDto, long id);
}
