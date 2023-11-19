package com.example.TaskManagement.Controllers;

import com.example.TaskManagement.Models.UserCredentialsModel;
import com.example.TaskManagement.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("taskManagement/userDetails")
public class UserCredentialsController {
    @Autowired
    public UserDetailsService userDetailsService;
    @GetMapping("/hello")
    public String hello(){
        return "hello allwin";
    }
    @PostMapping("/createNewUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> createNewUser(@RequestBody UserCredentialsModel userCredentialsModel){
        ResponseEntity<?> userCredentialsModelResponseEntity = userDetailsService.createUserCredentials(userCredentialsModel);
        return userCredentialsModelResponseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<?> authToLogin(@RequestBody UserCredentialsModel userCredentialsModel){
        ResponseEntity<?> verifyUserCredentialsResponse = userDetailsService.verifyUserCredentials(userCredentialsModel);
        return verifyUserCredentialsResponse;
    }
}
