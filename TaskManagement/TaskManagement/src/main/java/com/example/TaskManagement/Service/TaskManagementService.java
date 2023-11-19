package com.example.TaskManagement.Service;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Models.TaskManagementModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskManagementService {
    ResponseEntity<?> checkTaskExistOrNot(TaskManagementModel taskManagementModel, String chooseTask);
    ResponseEntity<?> createTasks(TaskManagementModel taskManagementModel);
    TaskDetails updateTasks(TaskManagementModel taskManagementModel, TaskDetails taskDetails);
    void deleteTasks(TaskDetails taskDetails);
    TaskDetails assignTasksToUsers(TaskDetails taskDetails, UserCredentials userCredentials);
    TaskDetails updateDueDates(TaskManagementModel taskManagementModel, TaskDetails taskDetails);
    TaskDetails updateTaskStatus(TaskManagementModel taskManagementModel, TaskDetails taskDetails);
    ResponseEntity<?> taskList(TaskManagementModel taskManagementModel);
}
