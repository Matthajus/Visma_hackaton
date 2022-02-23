package com.visma.hackaton.domain.converters;

import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.domain.entities.ToDoList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToDoListConverter {

    ToDoListDto toDto(ToDoList toDoList);

    ToDoList fromDto(ToDoListDto toDoListDto);

}
