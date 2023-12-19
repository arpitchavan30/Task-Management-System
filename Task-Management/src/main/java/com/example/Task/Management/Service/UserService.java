package com.example.Task.Management.Service;

import com.example.Task.Management.Configration.UserDetailsCreator;
import com.example.Task.Management.DTOs.RequestDTO.UserRequest;
import com.example.Task.Management.DTOs.ResponseDTO.UserResponse;
import com.example.Task.Management.IdGenrator.UserCodeGenerator;
import com.example.Task.Management.Model.User;
import com.example.Task.Management.Repository.UserRepository;
import com.example.Task.Management.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    final UserRepository userRepository;
    final UserCodeGenerator userCodeGenerator;
    @Autowired
    public UserService(UserRepository userRepository, UserCodeGenerator userCodeGenerator) {
        this.userRepository = userRepository;
        this.userCodeGenerator = userCodeGenerator;
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // add user
    public String addUser(UserRequest userRequest) throws Exception{
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = UserTransformer.userRequestToUser(userRequest);

        String newUserId = null;

        newUserId = userCodeGenerator.generate("usr");


        user.setUserId(newUserId);
        User newUser = userRepository.save(user);
        return "user added successfully";
    }

    // add admin user
    public String addAdUser(UserRequest userRequest) throws Exception{
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = UserTransformer.userRequestToAdUser(userRequest);

        String newUserId = null;

        newUserId = userCodeGenerator.generate("adm");

        user.setUserId(newUserId);
        User newUser = userRepository.save(user);
        return newUser.getUserId();
    }
}
