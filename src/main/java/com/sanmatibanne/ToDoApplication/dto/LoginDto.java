package com.sanmatibanne.ToDoApplication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginDto {

     private String usernameOrEmail;
     private String password;
}
