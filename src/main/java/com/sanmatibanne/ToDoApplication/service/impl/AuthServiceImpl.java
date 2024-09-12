package com.sanmatibanne.ToDoApplication.service.impl;

import com.sanmatibanne.ToDoApplication.dto.RegisterDto;
import com.sanmatibanne.ToDoApplication.entity.Role;
import com.sanmatibanne.ToDoApplication.entity.User;
import com.sanmatibanne.ToDoApplication.exception.ToDoApiException;
import com.sanmatibanne.ToDoApplication.repository.RoleRepository;
import com.sanmatibanne.ToDoApplication.repository.UserRepository;
import com.sanmatibanne.ToDoApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public String register(RegisterDto registerDto) {

      if(userRepository.existsByUsername(registerDto.getUsername())){
          throw  new ToDoApiException(HttpStatus.BAD_REQUEST,"Username is already exists");
      }

      if(userRepository.existsByEmail(registerDto.getEmail())){
            throw  new ToDoApiException(HttpStatus.BAD_REQUEST,"Email is already exists");
      }
      User user=new User();
      user.setName(registerDto.getName());
      user.setEmail(registerDto.getEmail());
      user.setUsername(registerDto.getUsername());
      user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

      Set<Role> roles= new HashSet<>();
      Role userrole=roleRepository.findByName("ROLE_USER");
      roles.add(userrole);

      user.setRoles(roles);
      userRepository.save(user);
        return "User registered successfully";
    }


}
