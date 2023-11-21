package com.example.TaskManagement.Models;

import com.example.TaskManagement.Enum.Priority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskModel {
    @Schema(defaultValue = "1")
    private Long taskId;
    @Schema(defaultValue = "Add Spring Boot Feature")
    private String taskName;
    @Schema(defaultValue = "Use spring boot, spring mvc, spring data jpa, spring security")
    private String taskDescription;
    @Schema(defaultValue = "MEDIUM", description = "Priority values: LOW, MEDIUM, HIGH, CRITICAL")
    private Priority priority;
}
