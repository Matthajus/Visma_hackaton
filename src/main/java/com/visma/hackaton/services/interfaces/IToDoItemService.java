package com.visma.hackaton.services.interfaces;

import com.visma.hackaton.domain.dto.ToDoItemDto;

import java.util.List;

public interface IToDoItemService {

    List<ToDoItemDto> getAllToDoItems();

    ToDoItemDto saveToDoItem(ToDoItemDto itemToSave, long listId);

    ToDoItemDto getToDoItemById(long id);

    List<ToDoItemDto> getAllToDoItemsByListId(long id);

    void deleteToDoItem(long id);

    ToDoItemDto updateToDoItem(ToDoItemDto toDoItemDto, long id);

}
