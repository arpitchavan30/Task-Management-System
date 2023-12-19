package com.example.Task.Management.Model;

import com.example.Task.Management.Enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "task")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String taskId;

    String title;

    String description;

    LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @JoinColumn
    User user;

}
