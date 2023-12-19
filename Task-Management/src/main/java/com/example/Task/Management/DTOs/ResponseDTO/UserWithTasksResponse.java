package com.example.Task.Management.DTOs.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserWithTasksResponse {

    String userId;

    String Name;

    List<TaskResponse> TaskAssigned = new ArrayList<>();
}
