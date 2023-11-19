package com.example.TaskManagement.Controllers;

import com.example.TaskManagement.Models.TaskManagementModel;
import com.example.TaskManagement.Service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("taskManagement")
public class TaskManagementController {
    @Autowired
    private TaskManagementService taskManagementService;

    @PostMapping("/createTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> createTask(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.createTasks(taskManagementModel);
        return responseEntity;
    }
    @PutMapping("/updateTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateTask(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.checkTaskExistOrNot(taskManagementModel, "updateTask");
        return responseEntity;
    }
    @DeleteMapping("/deleteTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> deleteTask(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.checkTaskExistOrNot(taskManagementModel, "deleteTasks");
        return responseEntity;
    }
    @PutMapping("/assignTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> assignTask(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.checkTaskExistOrNot(taskManagementModel, "assignTask");
        return responseEntity;
    }
    @PutMapping("/updateDueDates")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> updateDueDates(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.checkTaskExistOrNot(taskManagementModel, "updateDueDates");
        return responseEntity;
    }
    @PutMapping("/updateTaskStatus")
    public ResponseEntity<?> updateTaskStatus(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.checkTaskExistOrNot(taskManagementModel, "updateTaskStatus");
        return responseEntity;
    }
    @GetMapping("/taskList")
    public ResponseEntity<?> taskList(@RequestBody TaskManagementModel taskManagementModel){
        ResponseEntity<?> responseEntity = taskManagementService.taskList(taskManagementModel);
        return responseEntity;
    }
}
