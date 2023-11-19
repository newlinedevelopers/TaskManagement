package com.example.TaskManagement.Entities;

import com.example.TaskManagement.Configurations.LocalDateSerializer;
import com.example.TaskManagement.Enum.Priority;
import com.example.TaskManagement.Enum.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    private String taskDescription;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserCredentials userCredentials;

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", taskName='" + taskName +
                ", taskDescription='" + taskDescription +
                ", priority='" + priority +
                ", status='" + status +
                ", startDate='" + startDate +
                ", endDate='" + endDate +
                '}';
    }
}
