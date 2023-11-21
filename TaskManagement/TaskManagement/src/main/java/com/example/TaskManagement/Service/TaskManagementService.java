package com.example.TaskManagement.Service;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Models.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskManagementService {
    TaskDetails checkTaskExistOrNot(Long taskId);
    UserCredentials checkUserExistOrNot(Long userId);
    ResponseEntity<?> createTasks(CreateNewTaskModel createNewTaskModel, UserCredentials userCredentials);
    TaskDetails updateTasks(UpdateTaskModel updateTaskModel, TaskDetails taskDetails);
    void deleteTasks(TaskDetails taskDetails);
    TaskDetails assignTasksToUsers(TaskDetails taskDetails, UserCredentials userCredentials);
    TaskDetails updateDueDates(UpdateDueDatesModel updateDueDatesModel, TaskDetails taskDetails);
    TaskDetails updateTaskStatus(UpdateTaskStatusModel updateTaskStatusModel, TaskDetails taskDetails);
    ResponseEntity<?> taskList(Long id);
}
