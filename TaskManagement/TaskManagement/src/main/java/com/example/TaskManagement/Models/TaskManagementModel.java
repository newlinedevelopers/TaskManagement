package com.example.TaskManagement.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagementModel {
    private Long taskId;
    private String taskName;
    private String taskDescription;
    private String priority;
    private String status;
    private Long userId;
    private String endDate;
}
