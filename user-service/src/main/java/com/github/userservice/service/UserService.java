package com.github.userservice.service;

import com.github.userservice.models.User;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User creatUser(UserRegisterData data){
        User user = new User(data);
       return userRepository.save(user);
    }

}
