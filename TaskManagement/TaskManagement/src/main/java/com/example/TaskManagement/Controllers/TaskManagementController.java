package com.example.TaskManagement.Controllers;

import com.example.TaskManagement.Entities.TaskDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Models.*;
import com.example.TaskManagement.Service.TaskManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("taskManagement")
@Tag(name="Task Management",
        description = "The RESTful APIs designed for our task management system will offer a comprehensive set of features, including:\n\n" +
                "**Task Creation**: Enable the creation of new tasks.\n\n" +
                "**Task Update and Deletion**: Allow users to update and delete existing tasks.\n\n" +
                "**Task Assignment**: Facilitate the assignment of tasks to specific users.\n\n" +
                "**Task Prioritization**: Support prioritization of tasks to manage workflow efficiently.\n\n" +
                "**Due Date Management**: Allow users to change due dates for tasks.\n\n" +
                "**Task Status Updates**: Enable users to update the status of tasks.\n\n" +
                "**Role-Based Task Listing**: Provide functionality to list tasks based on user roles.\n\n" +
                "These APIs aim to deliver a robust and flexible task management solution, empowering users to efficiently organize and track their tasks.")
public class TaskManagementController {
    @Autowired
    private TaskManagementService taskManagementService;

    @PostMapping("/createTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(
            summary = "**Task Creation**: Enable the creation of new tasks.",
            description = "The Post API is purpose-built for the creation of tasks within the task management system. \n\n" +
                    "Accessible exclusively by users with the 'ADMIN' role, this API ensures a secured and controlled environment for task creation. \n\n" +
                    "Only users with the designated administrative privileges can utilize this endpoint to efficiently add tasks to the system, maintaining an organized and well-managed task repository.\n\n"+
                    "**Priority Enum values**: LOW, MEDIUM, HIGH, CRITICAL\n\n"+
                    "**Note** : Before accessing this API, users are required to log in to the system using the **admin credentials (username: admin, password: admin) or admin user**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> createTask(@RequestBody CreateNewTaskModel createNewTaskModel){
        UserCredentials userCredentials = taskManagementService.checkUserExistOrNot(createNewTaskModel.getUserId());
        ResponseEntity<?> responseEntity = taskManagementService.createTasks(createNewTaskModel, userCredentials);
        return responseEntity;
    }
    @PutMapping("/updateTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(
            summary = "**Task Update**: Allow users to update existing tasks.",
            description = "The Post API is purpose-built for the update of existing tasks within the task management system. \n\n" +
                    "Accessible exclusively by users with the 'ADMIN' role, this API ensures a secured and controlled environment for task update. \n\n" +
                    "Only users with the designated administrative privileges can utilize this endpoint to efficiently update tasks in the system, maintaining an organized and well-managed task repository.\n\n"+
                    "**Priority Enum values**: LOW, MEDIUM, HIGH, CRITICAL\n\n"+
                    "**Note** : Before accessing this API, users are required to log in to the system using the **admin credentials (username: admin, password: admin) or admin user**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskModel updateTaskModel){
        TaskDetails taskDetails = taskManagementService.checkTaskExistOrNot(updateTaskModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        taskDetails = taskManagementService.updateTasks(updateTaskModel, taskDetails);
        if(taskDetails == null){
            return ResponseEntity.badRequest().body("Failed: Task hasn't updated");
        }
        return ResponseEntity.ok().body("Task has updated successfully");
    }
    @DeleteMapping("/deleteTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(
            summary = "**Task Deletion**: Allow users to delete existing tasks.",
            description = "The Post API is purpose-built for the deletion of existing tasks within the task management system. \n\n" +
                    "Accessible exclusively by users with the 'ADMIN' role, this API ensures a secured and controlled environment for task deletion. \n\n" +
                    "Only users with the designated administrative privileges can utilize this endpoint to efficiently delete tasks in the system, maintaining an organized and well-managed task repository.\n\n"+
                    "**Note** : Before accessing this API, users are required to log in to the system using the **admin credentials (username: admin, password: admin) or admin user**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskModel deleteTaskModel){
        TaskDetails taskDetails = taskManagementService.checkTaskExistOrNot(deleteTaskModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        taskManagementService.deleteTasks(taskDetails);
        return  ResponseEntity.ok().body("Task has deleted successfully");
    }
    @PutMapping("/assignTask")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(
            summary = "**Task Assignment**: Facilitate the assignment of tasks to specific users.",
            description = "The Post API is purpose-built for assigning users to tasks within the task management system. \n\n" +
                    "Accessible exclusively by users with the 'ADMIN' role, this API ensures a secured and controlled environment for task assignment. \n\n" +
                    "Only users with the designated administrative privileges can utilize this endpoint to efficiently assign tasks to the user, maintaining an organized and well-managed task repository.\n\n"+
                    "**Note** : Before accessing this API, users are required to log in to the system using the **admin credentials (username: admin, password: admin) or admin user**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> assignTask(@RequestBody AssignTaskModel assignTaskModel){
        TaskDetails taskDetails = taskManagementService.checkTaskExistOrNot(assignTaskModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        UserCredentials userCredentials = taskManagementService.checkUserExistOrNot(assignTaskModel.getUserId());
        if(userCredentials == null) {
            return ResponseEntity.badRequest().body("User is not exist in the system");
        }
        taskDetails = taskManagementService.assignTasksToUsers(taskDetails, userCredentials);
        if(taskDetails == null){
            return ResponseEntity.badRequest().body("Failed: Task hasn't assigned to the user");
        }
        return ResponseEntity.ok().body("Task has assigned to the user successfully");
    }
    @PutMapping("/updateDueDates")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(
            summary = "**Due Date Management**: Allow users to change due dates for tasks.",
            description = "The Post API is purpose-built for changing task's due dates(Estimation of task) within the task management system. \n\n" +
                    "Accessible exclusively by users with the 'USER' role, this API ensures a secured and controlled environment for changing the task estimation by assigned user. \n\n" +
                    "Only users with the designated administrative privileges can utilize this endpoint to efficiently change the task estimation, maintaining an organized and well-managed task repository.\n\n"+
                    "**Note** : Before accessing this API, users are required to log in to the system using the **USER credentials**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> updateDueDates(@RequestBody UpdateDueDatesModel updateDueDatesModel){
        TaskDetails taskDetails = taskManagementService.checkTaskExistOrNot(updateDueDatesModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        taskDetails = taskManagementService.updateDueDates(updateDueDatesModel, taskDetails);
        if(taskDetails == null){
            return ResponseEntity.badRequest().body("Failed: Estimation date not updated");
        }
        return ResponseEntity.ok().body("Estimation date updated successfully");
    }
    @PutMapping("/updateTaskStatus")
    @Operation(
            summary = "**Task Status Updates**: Enable users to update the status of tasks.",
            description ="The Post API is purpose-built for updating the task's status within the task management system. \n\n" +
                    "**Status values**: OPEN, ASSIGNED, IN_PROGRESS, TESTING, CLOSED\n\n"+
                    "**Note**: This API can be used by all users who's with the valid JWT Bearer token"
    )
    public ResponseEntity<?> updateTaskStatus(@RequestBody UpdateTaskStatusModel updateTaskStatusModel){
        TaskDetails taskDetails = taskManagementService.checkTaskExistOrNot(updateTaskStatusModel.getTaskId());
        if (taskDetails == null) {
            return ResponseEntity.badRequest().body("Task is not exist in the system");
        }
        taskDetails = taskManagementService.updateTaskStatus(updateTaskStatusModel, taskDetails);
        if(taskDetails == null){
            return ResponseEntity.badRequest().body("Failed: Task status hasn't updated");
        }
        return ResponseEntity.ok().body("Task status updated successfully");
    }
    @GetMapping("/taskList")
    @Operation(
            summary = "**Role-Based Task Listing**: Provide functionality to list tasks based on user roles.",
            description ="The Post API is purpose-built for listing tasks based on user roles \n\n" +
                    "**ADMIN role**: Listing all tasks in the Task Management System.\n\n"+
                    "**USER role**: Listing only tasks that associated with the user in the Task Management System.\n\n"+
                    "**Note**: This API can be used by all users who's with the valid JWT Bearer token. Please make sure you entered valid JWT token for specified user in parameter"
    )
    public ResponseEntity<?> taskList(@RequestParam(name = "userId") Long userId){
        ResponseEntity<?> responseEntity = taskManagementService.taskList(userId);
        return responseEntity;
    }
}
