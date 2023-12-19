package com.example.Task.Management.Controller;

import com.example.Task.Management.DTOs.RequestDTO.UserRequest;
import com.example.Task.Management.DTOs.ResponseDTO.TaskResponse;
import com.example.Task.Management.DTOs.ResponseDTO.UserWithTasksResponse;
import com.example.Task.Management.Service.TaskService;
import com.example.Task.Management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    final TaskService taskService;
    final UserService userService;

    @Autowired
    public AdminUserController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

   // Add admin user
    @PostMapping("/addAdmin")
    public ResponseEntity addAdUser(@RequestBody UserRequest userRequest){
        String adminId = null;
        try {
            adminId = userService.addAdUser(userRequest);
            return new ResponseEntity("User Admin added successfully with admin id:-"+adminId,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    // Retrive all users and their tasks for admin only
    @GetMapping("/getAllUsersWithAssignedTasks")
    public ResponseEntity getAllUsersWithAssignedTasks(){
        try{
            List<UserWithTasksResponse> list = taskService.getAllUsersWithAssignedTasks();
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // Retrive all tasks by applying pagination, for admin only
    @GetMapping("/getAllTasks")
    public ResponseEntity<Page<TaskResponse>> getAllTasks(Pageable pageable) {
        Page<TaskResponse> tasks = taskService.getAllTasks(pageable);
        return new ResponseEntity<>(tasks,HttpStatus.FOUND);
    }

}
