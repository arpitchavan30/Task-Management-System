package com.example.Task.Management.IdGenrator;

import com.example.Task.Management.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCodeGenerator extends CodeGenerator{


    @Autowired
    TaskRepository taskRepository;

    public String generate(String prefix) throws Exception{
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(){
        Long LatestSequenceNumber = taskRepository.findLatestTaskSequenceNumber();

        return (LatestSequenceNumber!=null) ? LatestSequenceNumber : 0;
    }
}
