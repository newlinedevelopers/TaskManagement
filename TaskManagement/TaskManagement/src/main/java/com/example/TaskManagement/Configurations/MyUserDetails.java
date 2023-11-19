package com.example.TaskManagement.Configurations;

import com.example.TaskManagement.Entities.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyUserDetails implements UserDetails{
    public UserCredentials userCredentials;

    public MyUserDetails(UserCredentials userCredentials){
        this.userCredentials = userCredentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userCredentials.getUserRole().getRole());
        return Arrays.asList(simpleGrantedAuthority);
    }
    public Long getUserId() {
        return userCredentials.getId();
    }

    @Override
    public String getPassword() {
        return String.valueOf(userCredentials.getPassword());
    }

    @Override
    public String getUsername() {
        return userCredentials.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
