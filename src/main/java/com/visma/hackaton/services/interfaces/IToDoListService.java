package com.visma.hackaton.services.interfaces;

import com.visma.hackaton.domain.dto.ToDoListDto;

import java.util.List;

public interface IToDoListService {

    List<ToDoListDto> getAllToDoLists();

    ToDoListDto saveToDoList(ToDoListDto listToSave);

    ToDoListDto getToDoListById(long id);

    void deleteToDoList(long id);

    ToDoListDto updateToDoList(ToDoListDto toDoListDto, long id);

}
