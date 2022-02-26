package com.visma.hackaton.services;

import com.visma.hackaton.domain.converters.ToDoItemConverter;
import com.visma.hackaton.domain.converters.ToDoListConverter;
import com.visma.hackaton.domain.dto.ToDoItemDto;
import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.domain.entities.ToDoItem;
import com.visma.hackaton.exceptions.NotFoundException;
import com.visma.hackaton.repositories.ToDoItemRepository;
import com.visma.hackaton.repositories.ToDoListRepository;
import com.visma.hackaton.services.interfaces.IToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoItemService implements IToDoItemService {

    private final ToDoItemRepository toDoItemRepository;
    private final ToDoItemConverter converter;

    private final ToDoListRepository toDoListRepository;
    private final ToDoListConverter listConverter;

    @Override
    public List<ToDoItemDto> getAllToDoItems() {
        List<ToDoItem> items = toDoItemRepository.findAll();
        return items.stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public ToDoItemDto saveToDoItem(ToDoItemDto itemToSave, long listId) {
        // check if list exist
        ToDoListDto list = listConverter.toDto(toDoListRepository.findById(listId).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + listId + " doesn't exist!")));

        ToDoItem item = converter.fromDto(itemToSave);
        item.list = listConverter.fromDto(list);

        return converter.toDto(toDoItemRepository.save(item));
    }

    @Override
    public ToDoItemDto getToDoItemById(long id) {
        // check if exist
        return converter.toDto(toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoItem with id: " + id + " doesn't exist!")));
    }

    @Override
    public List<ToDoItemDto> getAllToDoItemsByListId(long id) {
        List<ToDoItem> items = toDoItemRepository.findAllByToDoListId(id);
        return items.stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteToDoItem(long id) {
        // check if exists
        ToDoItem toDoItem = toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!"));
        toDoItemRepository.deleteById(id);
    }

    @Override
    public ToDoItemDto updateToDoItem(ToDoItemDto toDoItemDto, long id) {
        // check if exist
        ToDoItem exist = toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoItem with id: " + id + " doesn't exist!"));

        exist.setName(toDoItemDto.getName());
        exist.setDescription(toDoItemDto.getDescription());
        exist.setDone(toDoItemDto.isDone());

        return converter.toDto(toDoItemRepository.save(exist));
    }

}
