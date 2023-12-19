package com.example.Task.Management.Controller;

import com.example.Task.Management.DTOs.RequestDTO.TaskRequest;
import com.example.Task.Management.DTOs.RequestDTO.UserRequest;
import com.example.Task.Management.DTOs.ResponseDTO.TaskResponse;
import com.example.Task.Management.Service.TaskService;
import com.example.Task.Management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    // Add user
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        String userId = null;
        try {
             userId = userService.addUser(userRequest);
            return new ResponseEntity("User Added Successfully with user Id :-"+userId,HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // Assign Task
    @PostMapping("/addTask")
    public ResponseEntity addTask(@RequestBody TaskRequest taskRequest){

        String taskId = null;
        try {
            taskId = taskService.addTask(taskRequest);
            return new ResponseEntity<>("Task added successfully with task id:-"+taskId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // Retrive tasks assigned
    @GetMapping("/getTasks")
    public ResponseEntity getTasks(@RequestParam String userid){
        try{
            List<TaskResponse> list = taskService.getTasks(userid);
            return new ResponseEntity(list, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // Update particular task
    @PutMapping("/updateTask")
    public ResponseEntity updateTask(@RequestParam String taskid, @RequestBody TaskRequest updatedTaskRequest){
        try{
            String msg = taskService.updateTask(taskid, updatedTaskRequest);
            return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // Remove particular task from database
    @DeleteMapping("/removeTask/taskid/{taskid}")
    public ResponseEntity removeTask(@PathVariable("taskid") String taskid){
        try{
            taskService.removeTask(taskid);
            return new ResponseEntity("Task deleted successfully !!!",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
