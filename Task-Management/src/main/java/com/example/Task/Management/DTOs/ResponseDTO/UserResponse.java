package com.example.Task.Management.DTOs.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    String userId;

    String name;

    String email;

    String mobileNo;
}
