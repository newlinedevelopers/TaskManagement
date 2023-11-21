package com.example.TaskManagement.Models;

import com.example.TaskManagement.Enum.Priority;
import com.example.TaskManagement.Enum.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewTaskModel {
    @Schema(defaultValue = "Add Spring Boot Feature")
    private String taskName;
    @Schema(defaultValue = "Use spring boot, spring mvc, spring data jpa, spring security")
    private String taskDescription;
    @Schema(defaultValue = "LOW", description = "Priority Enum values: LOW, MEDIUM, HIGH, CRITICAL")
    private Priority priority;
    @Schema(defaultValue = "2")
    private Long userId;
}
