package com.example.TaskManagement.Service;

import com.example.TaskManagement.Models.CreateNewUserModel;
import com.example.TaskManagement.Models.UserLoginModel;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    ResponseEntity<?> createUserCredentials(CreateNewUserModel createNewUserModel);
    ResponseEntity<?> verifyUserCredentials(UserLoginModel userLoginModel);
}
