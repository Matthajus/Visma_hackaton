package com.visma.hackaton.controllers;

import com.visma.hackaton.domain.dto.ToDoListBaseDto;
import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.services.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todolists")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @GetMapping()
    public List<ToDoListDto> getAllToDoLists() {
        return toDoListService.getAllToDoLists();
    }

    @PostMapping()
    public ResponseEntity<ToDoListDto> saveToDoList(@RequestBody ToDoListDto toDoListDto) {
        return new ResponseEntity<>(toDoListService.saveToDoList(toDoListDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}/items")
    public ResponseEntity<ToDoListDto> getToDoListById(@PathVariable("id") long id) {
        return new ResponseEntity<>(toDoListService.getToDoListById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoListDto> updateToDoList(@PathVariable("id") long id, @RequestBody ToDoListDto toDoListDto) {
        return new ResponseEntity<>(toDoListService.updateToDoList(toDoListDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDoList(@PathVariable("id") long id) {
        toDoListService.deleteToDoList(id);
        return new ResponseEntity<>("ToDoList deleted successfully!", HttpStatus.NO_CONTENT);
    }

}
