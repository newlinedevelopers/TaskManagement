package com.example.TaskManagement.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginModel {
    @Schema(defaultValue = "admin")
    private String username;
    @Schema(defaultValue = "admin")
    private String password;
}
