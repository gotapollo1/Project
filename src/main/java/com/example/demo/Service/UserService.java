package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private User user;
    private List<User> userList = new ArrayList<>();
    public List<User> getAll(){
    return userRepository.findAll();
    }

    public User turnOff(Integer check) {


            List<User> existingUser = userRepository.findByRole(2);
            for (User Data : existingUser) {
                if (check == 0) {
                    if(Data.getStatus() != 0){
                        Data.setStatus(2);
                        userRepository.save(Data);
                    }
                } else if (check == 1) {
                    if(Data.getStatus() != 0) {
                        Data.setStatus(check);
                        userRepository.save(Data);
                    }
                }
                else
                    return null;
            }
            return null;
        }

        public User updateUser(Integer id , User user){
        User existinUser = userRepository.findById(id).get();
            existinUser.setAgency(user.getAgency());
            existinUser.setName_user(user.getName_user());
            existinUser.setPhone_number(user.getPhone_number());
            existinUser.setUsername(user.getUsername());
            existinUser.setPassword(user.getPassword());
            existinUser.setStatus(user.getStatus());
            existinUser.setRole(user.getRole());
            return userRepository.save(existinUser);
        }
        public String deleteUser(Integer id){
         userRepository.deleteById(id);
         return "Remove! User" + id;
        }
        public User addUser(@RequestBody User user){
            userList.add(user);
        return userRepository.save(user);
        }

}
