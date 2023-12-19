package com.example.Task.Management.Model;

import com.example.Task.Management.Enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "user")
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String userId;

    String name;

    @Enumerated(EnumType.STRING)
    Roles role;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "should be a valid email address")
    @Column(unique = true, nullable = false)
    String email;

    @Pattern(regexp="[0-9]{10}", message="Should be a valid contact number")
    String mobileNo;

    @Column(unique = true,nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Task> tasks = new ArrayList<>(); // null

}
