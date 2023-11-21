package com.example.TaskManagement.Models;

import com.example.TaskManagement.Enum.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskStatusModel {
    @Schema(defaultValue = "1")
    private Long taskId;
    @Schema(defaultValue = "TESTING", description = "Status values: OPEN, ASSIGNED, IN_PROGRESS, TESTING, CLOSED")
    private Status status;
}
