package com.visma.hackaton.domain.converters;

import com.visma.hackaton.domain.dto.ToDoItemDto;
import com.visma.hackaton.domain.entities.ToDoItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToDoItemConverter {

    ToDoItemDto toDto(ToDoItem toDoItem);

    ToDoItem fromDto(ToDoItemDto toDoItemDto);
    
}
