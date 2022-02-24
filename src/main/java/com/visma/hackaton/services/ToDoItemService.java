package com.visma.hackaton.services;

import com.visma.hackaton.domain.dto.ToDoItemDto;
import com.visma.hackaton.services.interfaces.IToDoItemService;

import java.util.List;

public class ToDoItemService implements IToDoItemService {


    @Override
    public List<ToDoItemDto> getAllToDoItems() {
        return null;
    }

    @Override
    public ToDoItemDto saveToDoItem(ToDoItemDto itemToSave) {
        return null;
    }

    @Override
    public ToDoItemDto getToDoItemById(long id) {
        return null;
    }

    @Override
    public void deleteToDoItem(long id) {

    }

    @Override
    public ToDoItemDto updateToDoItem(ToDoItemDto toDoItemDto, long id) {
        return null;
    }

    @Override
    public ToDoItemDto markAsDone(ToDoItemDto toDoItemDto, long id) {
        return null;
    }
}
