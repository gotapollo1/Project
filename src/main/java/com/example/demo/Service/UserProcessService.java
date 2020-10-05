package com.example.demo.Service;


import com.example.demo.Dto.UserProcessDto;
import com.example.demo.Model.Process;
import com.example.demo.Model.User;
import com.example.demo.Model.UserProcess;
import com.example.demo.Repository.UserProcessRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProcessService {
    @Autowired
    private UserProcessRepository userProcessRepository;

    @Autowired
    private UserRepository userRepository;
    UserProcessDto userProcessDto = new UserProcessDto();

    public UserProcessDto getAllProUserID(Integer userID){
        Optional<User> userDetail = userRepository.findById(userID);
        if(!userDetail.isPresent()){
            return null;
        }
         List<UserProcess> data = userProcessRepository.findByUser(userDetail.get());

         List listProcess = new ArrayList();
         for (UserProcess Data : data) {
             listProcess.add(Data.getProcess_r());
             userProcessDto.setUser(Data.getUser());
         }
         userProcessDto.setProcess(listProcess);
         return userProcessDto;

    }


}
