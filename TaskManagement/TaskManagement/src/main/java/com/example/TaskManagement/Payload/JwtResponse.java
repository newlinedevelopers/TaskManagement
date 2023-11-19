package com.example.TaskManagement.Payload;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    String jwt;
    Long userId;
    String username;
    List<String> roles;
    public JwtResponse(String jwt, Long userId, String username, List<String> roles) {
        this.jwt = jwt;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

}
