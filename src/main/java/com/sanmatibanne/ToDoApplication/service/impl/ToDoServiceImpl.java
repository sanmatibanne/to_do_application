package com.sanmatibanne.ToDoApplication.service.impl;

import com.sanmatibanne.ToDoApplication.dto.ToDoDto;
import com.sanmatibanne.ToDoApplication.entity.ToDo;
import com.sanmatibanne.ToDoApplication.exception.ResourceNotFoundException;
import com.sanmatibanne.ToDoApplication.repository.ToDoRepository;
import com.sanmatibanne.ToDoApplication.service.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoRepository toDoRepository;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    };

    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
        //convert todo dto into todo jpa entity
        ToDo toDo=new ToDo();

        //toDo.setId(toDoDto.getId());
        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setCompleted(toDoDto.isCompleted());

        //todo jpa entity
        ToDo savedToDO=toDoRepository.save(toDo);
        //convert saved todo entity object into todo dto
        ToDoDto savedToDoDto=new ToDoDto();
        savedToDoDto.setId(savedToDO.getId());
        savedToDoDto.setTitle(savedToDO.getTitle());
        savedToDoDto.setDescription(savedToDO.getDescription());
        savedToDoDto.setCompleted(savedToDO.isCompleted());

        return savedToDoDto;
    }

    @Override
    public ToDoDto getToDo(Long id) {

        ToDo savedToDo=toDoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ToDo not found with id "+id));
        ToDoDto toDoDto= modelMapper().map(savedToDo,ToDoDto.class);
        return toDoDto;

        //OR
//        ToDo savedToDo=toDoRepository.findById(id).get();
//        return modelMapper.map(savedToDo,ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDos() {
        List<ToDo> list=toDoRepository.findAll();
        return list.stream().map((todo)->modelMapper().map(todo,ToDoDto.class)).collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateToDo(Long id, ToDoDto toDoDto) {
        ToDo savedToDo=toDoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("ToDo not found with id "+id));
        //savedToDo.setId(toDoDto.getId());
        savedToDo.setTitle(toDoDto.getTitle());
        savedToDo.setDescription(toDoDto.getDescription());
        savedToDo.setCompleted(toDoDto.isCompleted());
        ToDo updatedToDo=toDoRepository.save(savedToDo);
        return modelMapper().map(updatedToDo,ToDoDto.class);
    }

    @Override
    public void deleteToDo(Long id) {
        ToDo savedToDo=toDoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("ToDo not found with id "+id));
        toDoRepository.delete(savedToDo);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
        ToDo savedToDo=toDoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("ToDo not found with id "+id));
        savedToDo.setCompleted(true);
        ToDo competedToDo=toDoRepository.save(savedToDo);
        return modelMapper().map(competedToDo,ToDoDto.class);
    }

    @Override
    public ToDoDto inCompleteToDo(Long id) {
        ToDo savedToDo=toDoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("ToDo not found with id "+id));
        savedToDo.setCompleted(false);
        ToDo competedToDo=toDoRepository.save(savedToDo);
        return modelMapper().map(competedToDo,ToDoDto.class);
    }
}
