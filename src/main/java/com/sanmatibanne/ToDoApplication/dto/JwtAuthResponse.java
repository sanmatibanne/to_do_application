package com.sanmatibanne.ToDoApplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtAuthResponse {
    private String accessToken;
    private String username;
}
