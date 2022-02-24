package com.visma.hackaton.services;

import com.visma.hackaton.domain.converters.ToDoListConverter;
import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.domain.entities.ToDoList;
import com.visma.hackaton.exceptions.ConflictException;
import com.visma.hackaton.exceptions.NotFoundException;
import com.visma.hackaton.repositories.ToDoListRepository;
import com.visma.hackaton.services.interfaces.IToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoListService implements IToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final ToDoListConverter converter;

    @Override
    @Transactional
    public List<ToDoListDto> getAllToDoLists() {
        List<ToDoList> lists = toDoListRepository.findAll();
        return lists.stream().map(converter::toDto).collect(Collectors.toList());
    }

    @Override
    public ToDoListDto saveToDoList(@Valid ToDoListDto listToSave) {
        // check for duplicity
        ToDoList exist = toDoListRepository.findToDoListByName(listToSave.getName());
        if (exist != null)
            throw new ConflictException("ToDoList with name: " + listToSave.getName() + " already exists!");

        return converter.toDto(toDoListRepository.save(converter.fromDto(listToSave)));
    }

    @Override
    public ToDoListDto getToDoListById(long id) {
        // check if exists
        return converter.toDto(toDoListRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!")));
    }

    @Override
    public void deleteToDoList(long id) {
        // check if exists
        ToDoList toDoList = toDoListRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!"));
        toDoListRepository.delete(toDoList);
    }

    @Override
    public ToDoListDto updateToDoList(ToDoListDto toDoListDto, long id) {
        // check if name exists
        ToDoList exist = toDoListRepository.findToDoListByName(toDoListDto.getName());
        if (exist != null)
            throw new ConflictException("ToDoList with name: " + toDoListDto.getName() + " already exists!");

        //check if exist
        ToDoList toDoList = toDoListRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ToDoList with id: " + id + " doesn't exist!"));

        toDoList.setName(toDoListDto.getName());
        toDoList.setDescription(toDoListDto.getDescription());
        toDoList.setImageLink(toDoListDto.getImageLink());
        toDoList.setItems(toDoListDto.getItems());
        return converter.toDto(toDoListRepository.save(toDoList));
    }
}
