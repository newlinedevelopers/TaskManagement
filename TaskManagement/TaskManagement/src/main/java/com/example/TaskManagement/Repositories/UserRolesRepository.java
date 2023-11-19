package com.example.TaskManagement.Repositories;

import com.example.TaskManagement.Entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    @Query(value = "select ur from UserRoles ur where ur.role = ?1")
    UserRoles findByRole(String user);
}
