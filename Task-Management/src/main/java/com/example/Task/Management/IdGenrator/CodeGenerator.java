package com.example.Task.Management.IdGenrator;

import com.example.Task.Management.Exception.UserRegestraionMaximumLimitReach;

public class CodeGenerator {

    private static final String prefix=null;
    private static final Long MAX_SEQUENCE_NUMBER=Long.MAX_VALUE;

    public String generate(String prefix) throws Exception{

        long sequenceNumber = getLatestSequenceNumber();
        sequenceNumber+=1;
        if(sequenceNumber > MAX_SEQUENCE_NUMBER){
            throw new UserRegestraionMaximumLimitReach("Cannot register as maximum registration limit reached !!!");

        }
        return prefix+String.format("%04d",sequenceNumber);
    }
    public Long getLatestSequenceNumber(){
        return Long.MAX_VALUE;
    }
}
