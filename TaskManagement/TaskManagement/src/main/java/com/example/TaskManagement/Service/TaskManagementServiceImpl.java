package com.example.TaskManagement.Service;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Enum.Priority;
import com.example.TaskManagement.Enum.Status;
import com.example.TaskManagement.Models.TaskManagementModel;
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
    @Override
    public ResponseEntity<?> checkTaskExistOrNot(TaskManagementModel taskManagementModel, String chooseTask) {
        TaskDetails taskDetails = taskDetailsRepository.findTaskById(taskManagementModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        UserCredentials userCredentials = null;
        if(chooseTask.equals("assignTask")){
            userCredentials = userCredentialsRepository.findUserById(taskManagementModel.getUserId());
            if(userCredentials == null) {
                return ResponseEntity.badRequest().body("User is not exist in the system");
            }
        }

        ResponseEntity<?> responseEntity = null;
        switch (chooseTask){
            case "updateTask" -> {
                updateTasks(taskManagementModel, taskDetails);
                responseEntity = ResponseEntity.ok().body("Task has updated successfully");
            }
            case "deleteTasks" -> {
                deleteTasks(taskDetails);
                responseEntity = ResponseEntity.ok().body("Task has deleted successfully");
            }
            case "assignTask" -> {
                assignTasksToUsers(taskDetails, userCredentials);
                responseEntity = ResponseEntity.ok().body("Task has assigned to the user successfully");
            }
            case "updateDueDates" -> {
                updateDueDates(taskManagementModel, taskDetails);
                responseEntity = ResponseEntity.ok().body("Estimation date updated successfully");
            }
            case "updateTaskStatus" -> {
                updateTaskStatus(taskManagementModel, taskDetails);
                responseEntity = ResponseEntity.ok().body("Task status updated successfully");
            }
        }
        return responseEntity;
    }
    @Override
    public ResponseEntity<?> createTasks(TaskManagementModel taskManagementModel) {
        TaskDetails taskDetails = new TaskDetails();
        taskDetails.setTaskName(taskManagementModel.getTaskName());
        taskDetails.setTaskDescription(taskManagementModel.getTaskDescription());
        taskDetails.setPriority(Priority.valueOf(taskManagementModel.getPriority().toUpperCase()));
        taskDetails.setStatus(Status.OPEN);
        taskDetailsRepository.save(taskDetails);
        return ResponseEntity.ok().body("Task has created successfully");
    }
    @Override
    public TaskDetails updateTasks(TaskManagementModel taskManagementModel, TaskDetails taskDetails) {
        taskDetails.setTaskName(taskManagementModel.getTaskName());
        taskDetails.setTaskDescription(taskManagementModel.getTaskDescription());
        taskDetails.setPriority(Priority.valueOf(taskManagementModel.getPriority().toUpperCase()));
        taskDetails.setStatus(Status.valueOf(taskManagementModel.getStatus().toUpperCase()));
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
    public TaskDetails updateDueDates(TaskManagementModel taskManagementModel, TaskDetails taskDetails) {
        if(taskDetails.getStartDate() == null){
            taskDetails.setStartDate(LocalDate.now());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        taskDetails.setEndDate(LocalDate.parse(taskManagementModel.getEndDate(), formatter));
        if(taskDetails.getStatus() == Status.ASSIGNED){
            taskDetails.setStatus(Status.IN_PROGRESS);
        }
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public TaskDetails updateTaskStatus(TaskManagementModel taskManagementModel, TaskDetails taskDetails) {
        taskDetails.setStatus(Status.valueOf(taskManagementModel.getStatus().toUpperCase()));
        return taskDetailsRepository.save(taskDetails);
    }
    @Override
    public ResponseEntity<?> taskList(TaskManagementModel taskManagementModel) {
        UserCredentials userCredentials = userCredentialsRepository.findUserById(taskManagementModel.getUserId());
        if(userCredentials == null) {
            return ResponseEntity.badRequest().body("User is not exist in the system");
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
