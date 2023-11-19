package com.example.TaskManagement.Service;

import com.example.TaskManagement.Models.UserCredentialsModel;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    ResponseEntity<?> createUserCredentials(UserCredentialsModel userCredentialsModel);
    ResponseEntity<?> verifyUserCredentials(UserCredentialsModel userCredentialsModel);
}
