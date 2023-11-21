package com.example.TaskManagement.Service;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.Models.*;
import com.example.TaskManagement.Repositories.TaskDetailsRepository;
import com.example.TaskManagement.Repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskManagementServiceImpl implements TaskManagementService{
    @Autowired
    private TaskDetailsRepository taskDetailsRepository;
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    private SecurityService securityService;
    @Override
    public TaskDetails checkTaskExistOrNot(Long taskId) {
        return taskDetailsRepository.findTaskById(taskId);
    }
    @Override
    public UserCredentials checkUserExistOrNot(Long userId) {
        return userCredentialsRepository.findUserById(userId);
    }
    @Override
    public ResponseEntity<?> createTasks(CreateNewTaskModel createNewTaskModel, UserCredentials userCredentials) {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTaskName(createNewTaskModel.getTaskName());
        taskDetails.setTaskDescription(createNewTaskModel.getTaskDescription());
        taskDetails.setPriority(createNewTaskModel.getPriority());
        taskDetails.setUserCredentials(userCredentials);
        if(userCredentials == null){
            taskDetails.setStatus(Status.OPEN);
        }else{
            taskDetails.setStatus(Status.ASSIGNED);
        }
        taskDetailsRepository.save(taskDetails);
        return ResponseEntity.ok().body("Task has created successfully");
    }
    @Override
    public TaskDetails updateTasks(UpdateTaskModel updateTaskModel, TaskDetails taskDetails) {
        taskDetails.setTaskName(updateTaskModel.getTaskName());
        taskDetails.setTaskDescription(updateTaskModel.getTaskDescription());
        taskDetails.setPriority(updateTaskModel.getPriority());
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public void deleteTasks(TaskDetails taskDetails) {
        taskDetailsRepository.deleteById(taskDetails.getId());
    }
    @Override
    public TaskDetails assignTasksToUsers(TaskDetails taskDetails, UserCredentials userCredentials) {
        taskDetails.setUserCredentials(userCredentials);
        if(taskDetails.getStatus() == Status.OPEN){
            taskDetails.setStatus(Status.ASSIGNED);
        }
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public TaskDetails updateDueDates(UpdateDueDatesModel updateDueDatesModel, TaskDetails taskDetails) {
        if(taskDetails.getStartDate() == null){
            taskDetails.setStartDate(LocalDate.now());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        taskDetails.setEndDate(LocalDate.parse(updateDueDatesModel.getEndDate(), formatter));
        if(taskDetails.getStatus() == Status.ASSIGNED){
            taskDetails.setStatus(Status.IN_PROGRESS);
        }
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public TaskDetails updateTaskStatus(UpdateTaskStatusModel updateTaskStatusModel, TaskDetails taskDetails) {
        taskDetails.setStatus(updateTaskStatusModel.getStatus());
        if(updateTaskStatusModel.getStatus() == Status.CLOSED){
            taskDetails.setEndDate(LocalDate.now());
        }
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public ResponseEntity<?> taskList(Long userId) {
        UserCredentials userCredentials = userCredentialsRepository.findUserById(userId);
        if(userCredentials == null) {
            return ResponseEntity.badRequest().body("User is not exist in the system");
        }
        if(!securityService.isLoggedUser(userId)){
            return ResponseEntity.badRequest().body("You don't have access.");
        }
        List<TaskDetails> taskDetails;
        if(userCredentials.getUserRole().getRole().equals("ADMIN")){
            taskDetails = taskDetailsRepository.findAll();
        }else{
            taskDetails = taskDetailsRepository.findTaskByUser(userCredentials);
        }
        return ResponseEntity.ok().body(taskDetails);
    }
}
