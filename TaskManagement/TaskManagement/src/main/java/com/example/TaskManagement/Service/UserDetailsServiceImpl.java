package com.example.TaskManagement.Service;

import com.example.TaskManagement.Configurations.MyUserDetails;
import com.example.TaskManagement.Entities.UserCredentials;
import com.example.TaskManagement.Entities.UserRoles;
import com.example.TaskManagement.Models.UserCredentialsModel;
import com.example.TaskManagement.Payload.JwtResponse;
import com.example.TaskManagement.Repositories.UserCredentialsRepository;
import com.example.TaskManagement.Repositories.UserRolesRepository;
import com.example.TaskManagement.RequestExceptionHandler.DuplicateUserExceptionHandler;
import com.example.TaskManagement.RequestExceptionHandler.LoginAuthenticationException;
import com.example.TaskManagement.Security.JwtConfig.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public ResponseEntity<?> createUserCredentials(UserCredentialsModel userCredentialsModel) {
        if (userCredentialsRepository.getUserByUsername(userCredentialsModel.getUsername()) != null) {
                throw new DuplicateUserExceptionHandler("Username is already in use!");
        }

        UserRoles userRole = userRolesRepository.findByRole(userCredentialsModel.getRole().toUpperCase());
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(userCredentialsModel.getUsername());
        userCredentials.setPassword((passwordEncoder.encode(CharBuffer.wrap(userCredentialsModel.getPassword()))).toCharArray());
        userCredentials.setUserRole(userRole);
        userCredentialsRepository.save(userCredentials);
        return ResponseEntity.ok().body("User Credentials Created Successfully");
    }
    @Override
    public ResponseEntity<?> verifyUserCredentials(UserCredentialsModel userCredentialsModel) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userCredentialsModel.getUsername(),CharBuffer.wrap(userCredentialsModel.getPassword()));
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getUserId(),
                    userDetails.getUsername(),
                    roles));
        }catch (BadCredentialsException ex) {
            throw new LoginAuthenticationException("Invalid username and password!");
        }
    }
}
