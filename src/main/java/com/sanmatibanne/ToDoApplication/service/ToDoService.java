package com.sanmatibanne.ToDoApplication.service;

import com.sanmatibanne.ToDoApplication.dto.ToDoDto;

import java.util.List;

public interface ToDoService {

    ToDoDto addToDo(ToDoDto toDoDto);

    ToDoDto getToDo(Long id);

    List<ToDoDto> getAllToDos();

    ToDoDto updateToDo(Long id,ToDoDto toDoDto);

    void deleteToDo(Long id);

    ToDoDto completeToDo(Long id);

    ToDoDto inCompleteToDo(Long id);
}
