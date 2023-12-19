package com.example.Task.Management.DTOs.RequestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRequest {

    String name;

    String email;

    String mobileNo;

    String username;

    String password;

}
