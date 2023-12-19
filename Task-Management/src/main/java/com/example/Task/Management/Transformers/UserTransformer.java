package com.example.Task.Management.Transformers;

import com.example.Task.Management.DTOs.RequestDTO.UserRequest;
import com.example.Task.Management.Enums.Roles;
import com.example.Task.Management.Model.User;

import java.util.UUID;

public class UserTransformer {


    public static User userRequestToUser(UserRequest userRequest){

        return User.builder()
                .name(userRequest.getName())
                .role(Roles.ROLE_USER)
                .email(userRequest.getEmail())
                .mobileNo(userRequest.getMobileNo())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
    }

    public static User userRequestToAdUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .userId(UUID.randomUUID().toString())
                .role(Roles.ROLE_ADMIN)
                .email(userRequest.getEmail())
                .mobileNo(userRequest.getMobileNo())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .password(userRequest.getPassword())
                .build();
    }
}
