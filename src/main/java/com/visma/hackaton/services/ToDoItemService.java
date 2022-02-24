package com.visma.hackaton.services;

import com.visma.hackaton.domain.converters.ToDoItemConverter;
import com.visma.hackaton.domain.converters.ToDoListConverter;
import com.visma.hackaton.domain.dto.ToDoItemDto;
import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.domain.entities.ToDoItem;
import com.visma.hackaton.domain.entities.ToDoList;
import com.visma.hackaton.exceptions.ConflictException;
import com.visma.hackaton.exceptions.NotFoundException;
import com.visma.hackaton.repositories.ToDoItemRepository;
import com.visma.hackaton.repositories.ToDoListRepository;
import com.visma.hackaton.services.interfaces.IToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        // check for duplicity in list
        ToDoItem item = toDoItemRepository.findToDoItemByNameAndListId(itemToSave.getName(), listId);
        if (item != null)
            throw new ConflictException("ToDoItem with name: " + itemToSave.getName() + " already exists in ToDoList with id: " + listId);

        // check if list exist
        ToDoListDto list = listConverter.toDto(toDoListRepository.findById(listId).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + listId + " doesn't exist!")));

        item = converter.fromDto(itemToSave);
        item.list = listConverter.fromDto(list);

        return converter.toDto(toDoItemRepository.save(converter.fromDto(converter.toDto(item))));
    }

    @Override
    public ToDoItemDto getToDoItemById(long id) {
        // check if exist
        return converter.toDto(toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoItem with id: " + id + " doesn't exist!")));
    }

    @Override
    public void deleteToDoItem(long id) {
        // check if exists
        ToDoItem toDoItem = toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!"));
        toDoItemRepository.delete(toDoItem);
    }

    @Override
    public ToDoItemDto updateToDoItem(ToDoItemDto toDoItemDto, long id) {
        // check for duplicity in list
        ToDoItem item = toDoItemRepository.findToDoItemByNameAndListId(toDoItemDto.getName(), id);
        if (item != null)
            throw new ConflictException("ToDoItem with name: " + toDoItemDto.getName() + " already exists in ToDoList with id: " + id);

        // check if exist
        ToDoItem exist = toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoItem with id: " + id + " doesn't exist!"));

        exist.setName(toDoItemDto.getName());
        exist.setDescription(toDoItemDto.getDescription());
        exist.setDone(toDoItemDto.isDone);

        return converter.toDto(toDoItemRepository.save(exist));
    }

    @Override
    public void markAsDone(ToDoItemDto toDoItemDto, long id) {
        // check if exists
        ToDoItem toDoItem = toDoItemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!"));

        toDoItemRepository.updateToDoItemAsDone(true, id);
    }
}
