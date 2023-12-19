package com.example.Task.Management.Service;

import com.example.Task.Management.DTOs.RequestDTO.TaskRequest;
import com.example.Task.Management.DTOs.ResponseDTO.TaskResponse;
import com.example.Task.Management.DTOs.ResponseDTO.UserWithTasksResponse;
import com.example.Task.Management.Enums.Status;
import com.example.Task.Management.Exception.TaskNotFoundException;
import com.example.Task.Management.Exception.UserNotFoundException;
import com.example.Task.Management.IdGenrator.TaskCodeGenerator;
import com.example.Task.Management.Model.Task;
import com.example.Task.Management.Model.User;
import com.example.Task.Management.Repository.TaskRepository;
import com.example.Task.Management.Repository.UserRepository;
import com.example.Task.Management.Transformers.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    final TaskRepository taskRepository;
    final UserRepository userRepository;
    final TaskCodeGenerator taskCodeGenerator;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskCodeGenerator taskCodeGenerator) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskCodeGenerator = taskCodeGenerator;
    }

    public String addTask(TaskRequest taskRequest) throws Exception {
        Task task = TaskTransformer.TaskRequestToTask((taskRequest));
        String newTaskId = null;

        newTaskId = taskCodeGenerator.generate("tsk");

        task.setTaskId(newTaskId);

        User user = userRepository.findByUserId(taskRequest.getUserId()).get();

        user.getTasks().add(task);

        task.setUser(user);

        userRepository.save(user);

        Task savedTask = taskRepository.findByTaskId(task.getTaskId());
        return savedTask.getTaskId();
    }

    public List<TaskResponse> getTasks(String userid) {
       User user = userRepository.findByUserId(userid).get();
        if(user==null){
            throw new UserNotFoundException("Invalid user id !!!");
        }
        List<TaskResponse> ans = new ArrayList<>();
        for(Task task : user.getTasks()){
            ans.add(TaskTransformer.TaskToTaskResponse(task));
        }
        return ans;
    }

    public String updateTask(String taskid, TaskRequest updatedTaskRequest) {
        Task task = taskRepository.findByTaskId(taskid);

        if(task==null){
            throw new TaskNotFoundException("Task Not Found !!!");
        }

        if (updatedTaskRequest.getTitle() != null) {
            task.setTitle(updatedTaskRequest.getTitle());
        }

        if (updatedTaskRequest.getDescription() != null) {
            task.setDescription(updatedTaskRequest.getDescription());
        }

        if (updatedTaskRequest.getDueDate() != null) {
            task.setDueDate(updatedTaskRequest.getDueDate());
        }

        if (updatedTaskRequest.getStatus() != null) {
            task.setStatus(Status.valueOf(updatedTaskRequest.getStatus().toUpperCase()));
        }

        taskRepository.save(task);
        return "Task Updated Successfully !!!";
    }


    public void removeTask(String taskid) {
        Task task = taskRepository.findByTaskId(taskid);
        if(task==null){
            throw new TaskNotFoundException("Invalid task id !!!");
        }
        User user = task.getUser();
        taskRepository.delete(task);
        user.getTasks().remove(task);
        userRepository.save(user);
    }

    // retrive all users and there tasks for admin
    public List<UserWithTasksResponse> getAllUsersWithAssignedTasks() {
        List<User> userList = userRepository.findAll();
        List<UserWithTasksResponse> ans = new ArrayList<>();
        for(User user : userList){
            List<TaskResponse> tasklist = new ArrayList<>();
            for(Task task : user.getTasks()){
                tasklist.add(TaskTransformer.TaskToTaskResponse(task));
            }
            UserWithTasksResponse response = UserWithTasksResponse.builder()
                    .userId(user.getUserId())
                    .Name(user.getName())
                    .TaskAssigned(tasklist)
                    .build();

            ans.add(response);
        }
        return ans;

    }

    // get all tasks only for admin
    public Page<TaskResponse> getAllTasks(Pageable pageable) {

        Page<Task> tasks;

        if (pageable.getSort().equals("status")) {
            tasks = taskRepository.findAllByOrderByStatusAsc(pageable);
        } else {
            // Default to sorting by dueDate if sortBy is not recognized
            tasks = taskRepository.findAll(pageable);
        }

        return tasks.map(TaskTransformer::TaskToTaskResponse);

    }
}
