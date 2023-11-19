package com.example.TaskManagement.Repositories;

import com.example.TaskManagement.Entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    @Query(value = "select user from UserCredentials user where user.username = ?1")
    UserCredentials getUserByUsername(String username);
    @Query("select u from UserCredentials u where u.id = ?1")
    UserCredentials findUserById(Long id);
}

