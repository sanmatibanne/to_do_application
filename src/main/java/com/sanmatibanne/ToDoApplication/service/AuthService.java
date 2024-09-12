package com.sanmatibanne.ToDoApplication.service;

import com.sanmatibanne.ToDoApplication.dto.LoginDto;
import com.sanmatibanne.ToDoApplication.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

}
