package com.example.TaskManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "userRole")
    private List<UserCredentials> userCredentials;

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", name='" + role +
                '}';
    }

}
