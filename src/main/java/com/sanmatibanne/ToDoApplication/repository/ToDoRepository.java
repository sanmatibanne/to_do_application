package com.sanmatibanne.ToDoApplication.repository;

import com.sanmatibanne.ToDoApplication.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {

}
