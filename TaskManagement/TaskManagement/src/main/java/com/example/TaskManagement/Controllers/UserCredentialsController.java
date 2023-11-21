package com.example.TaskManagement.Controllers;

import com.example.TaskManagement.Models.CreateNewUserModel;
import com.example.TaskManagement.Models.UserLoginModel;
import com.example.TaskManagement.Service.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("taskManagement/userDetails")
@Tag(name="User Credentials",
        description = "The RESTful APIs developed for our task management system focus on user account management and authentication, offering the following functionalities:\n\n" +
                "**User Account Creation**: Facilitate the creation of new user accounts in the task management system.\n\n" +
                "**User Authentication**: Enable secure user authentication for seamless login access to the task management system.\n\n" +
                "**Role-Based Task Permissions**: Users are granted specific permissions based on their assigned roles, allowing them to perform designated tasks.\n\n" +
                "This design ensures a secure and tailored user experience, where individuals can interact with the task management system based on their roles and responsibilities.")
public class UserCredentialsController {
    @Autowired
    public UserDetailsService userDetailsService;
    @PostMapping("/createNewUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(
            summary = "**User Account Creation**: Facilitate the creation of new user accounts in the task management system.",
            description = "The Post API is designed for creating new user accounts within the task management system.\n\n" +
                    "This API is exclusively accessible to users with the 'admin' role, ensuring enhanced security measures.\n\n" +
                    "To utilize this API, users must be assigned the admin role, granting them the necessary permissions for user account creation.\n\n" +
                    "It is crucial to ensure that users requiring access to this API are appropriately assigned the admin role for effective and secure account management within the system.\n\n"+
                    "**User roles** : ADMIN, USER\n\n" +
                    "**Note** : Before accessing this API, users are required to log in to the system using the **admin credentials (username: admin, password: admin) or admin user**. Upon successful authentication, the system will generate a **JWT token**. Please use this token for subsequent authentication when interacting with the APIs for login and authorization to the task management system. The token serves as a secure authentication mechanism, ensuring controlled access to the system's features."
    )
    public ResponseEntity<?> createNewUser(@RequestBody CreateNewUserModel createNewUserModel){
        ResponseEntity<?> userCredentialsModelResponseEntity = userDetailsService.createUserCredentials(createNewUserModel);
        return userCredentialsModelResponseEntity;
    }
    @PostMapping("/login")
    @Operation(
            summary = "**User Authentication**: Enable secure user authentication for seamless login access to the task management system.",
            description = "The Post API is specifically designed for handling login authentication and authorization within the task management system.\n\n" +
                    "Users can utilize this API to securely authenticate their credentials and, upon successful authentication, gain authorized access to the task management features.\n\n" +
                    "The API ensures a robust authentication mechanism, enhancing the overall security of the task management system.\n\n"+
                     "**Note**: This API can be used by all users"
    )
    public ResponseEntity<?> authToLogin(@RequestBody UserLoginModel userLoginModel){
        ResponseEntity<?> verifyUserCredentialsResponse = userDetailsService.verifyUserCredentials(userLoginModel);
        return verifyUserCredentialsResponse;
    }
}
