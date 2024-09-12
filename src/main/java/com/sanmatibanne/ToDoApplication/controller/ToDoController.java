package com.sanmatibanne.ToDoApplication.controller;

import com.sanmatibanne.ToDoApplication.dto.ToDoDto;
import com.sanmatibanne.ToDoApplication.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addtodo")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody ToDoDto toDoDto){
        ToDoDto savedToDo=toDoService.addToDo(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> getToDoById(@PathVariable Long id){
        ToDoDto savedToDo=toDoService.getToDo(id);
        return ResponseEntity.ok(savedToDo);
    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<List<ToDoDto>> findAll(){
        List<ToDoDto> savedToDo=toDoService.getAllToDos();
        return ResponseEntity.ok(savedToDo);
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<ToDoDto> updateToDo(@PathVariable Long id,@RequestBody ToDoDto toDoDto){
     ToDoDto updatedToDo= toDoService.updateToDo(id,toDoDto);
      return ResponseEntity.ok(updatedToDo);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
        return ResponseEntity.ok("to do deleted successfully...");
    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/complete/{id}")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable Long id){
       return new ResponseEntity(toDoService.completeToDo(id),HttpStatus.OK) ;
    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable Long id){
        return new ResponseEntity(toDoService.inCompleteToDo(id),HttpStatus.OK) ;
    }
}
