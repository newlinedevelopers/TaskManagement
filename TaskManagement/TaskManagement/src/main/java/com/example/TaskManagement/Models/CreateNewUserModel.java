package com.example.TaskManagement.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserModel {
    @Schema(defaultValue = "allwin")
    private String username;
    @Schema(defaultValue = "allwin")
    private String password;
    @Schema(defaultValue = "USER")
    private String role;
}
