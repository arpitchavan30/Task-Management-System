package com.example.Task.Management.IdGenrator;

import com.example.Task.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCodeGenerator extends CodeGenerator{

    @Autowired
    UserRepository userRepository;

    String pref = null;

    @Override
    public String generate(String prefix) throws Exception{
        pref = prefix;
        return super.generate(prefix);
    }

    @Override
    public Long getLatestSequenceNumber(){
        Long LatestSequenceNumber = 0L;
        if(pref.equals("usr")) {
            LatestSequenceNumber = userRepository.findLatestUserSequenceNumber();
        }
        else{
            LatestSequenceNumber = userRepository.findLatestAdminSequenceNumber();
        }
        return (LatestSequenceNumber!=null) ? LatestSequenceNumber : 0;
    }
}
