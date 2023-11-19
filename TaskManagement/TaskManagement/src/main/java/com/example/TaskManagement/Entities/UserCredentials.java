package com.example.TaskManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(nullable = false)
    private char[] password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRoles userRole;

    @JsonIgnore
    @OneToMany(mappedBy = "userCredentials")
    private List<TaskDetails> taskDetailsList;

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", username='" + username +
                '}';
    }
}