package com.github.userservice.service;

import com.github.userservice.models.User;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDetalingData creatUser(UserRegisterData data){
        User user = new User(data);
        userRepository.save(user);
       return new UserDetalingData(user);
    }

    public UserDetalingData updateUser(UserUpdateData dataUpdate) {
        User user = userRepository.getReferenceById(dataUpdate.id());

       // user.updateInformation(dataUpdate);

        return new UserDetalingData(user);
    }

    public List<UserDetalingData> list() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDetalingData(user))
                .toList();
    }

    public Optional<UserDetalingData> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDetalingData(user))
                .stream()
                .findAny();
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
