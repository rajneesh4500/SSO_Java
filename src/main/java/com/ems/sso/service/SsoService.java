package com.ems.sso.service;


import com.ems.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SsoService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Integer> validateUser(String userId){
        Integer uuserId = 0;
        if(userId.contains("@")){
            uuserId = userRepository.validiateUserEmail(userId);
        }else{
            uuserId = userRepository.validateUserId(userId);
        }
       return new ResponseEntity<>(uuserId,HttpStatus.OK);

    }

    public ResponseEntity<String> valditePass(Integer userId, String pass) {
        String rs = userRepository.validatePass(userId,pass);
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    public ResponseEntity<Boolean> validateSession(String userid, String token) {

        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
