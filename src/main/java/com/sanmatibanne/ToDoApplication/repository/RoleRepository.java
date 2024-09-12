package com.sanmatibanne.ToDoApplication.repository;

import com.sanmatibanne.ToDoApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
