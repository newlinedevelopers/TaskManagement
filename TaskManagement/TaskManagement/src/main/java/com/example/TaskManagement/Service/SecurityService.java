package com.example.TaskManagement.Service;

import com.example.TaskManagement.Configurations.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public Boolean isLoggedUser(Long userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        if(myUserDetails.getUserId() != userId){
            return false;
        }
        return true;
    }
}
