package com.visma.hackaton.controllers;

import com.visma.hackaton.domain.dto.ToDoItemDto;
import com.visma.hackaton.domain.dto.ToDoListDto;
import com.visma.hackaton.services.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todoitems")
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    @GetMapping()
    public List<ToDoItemDto> getAllToDoItems() {
        return toDoItemService.getAllToDoItems();
    }

    @PostMapping("{id}")
    public ResponseEntity<ToDoItemDto> saveItemToList(@PathVariable("id") long listId, @RequestBody ToDoItemDto toDoItemDto) {
        return new ResponseEntity<>(toDoItemService.saveToDoItem(toDoItemDto, listId), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoItemDto> getToDoItemById(@PathVariable("id") long id) {
        return new ResponseEntity<>(toDoItemService.getToDoItemById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToDoItemDto> updateToDoItem(@PathVariable("id") long id, @RequestBody ToDoItemDto toDoItemDto) {
        return new ResponseEntity<>(toDoItemService.updateToDoItem(toDoItemDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToDoItem(@PathVariable("id") long id) {
        toDoItemService.deleteToDoItem(id);
        return new ResponseEntity<>("ToDoList deleted successfully!", HttpStatus.NO_CONTENT);
    }

}
