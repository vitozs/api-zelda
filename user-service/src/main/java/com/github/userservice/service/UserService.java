package com.github.userservice.service;

import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDetalingData creatUser(UserRegisterData data){
        UserModel userModel = new UserModel(data);
        userRepository.save(userModel);
       return new UserDetalingData(userModel);
    }

    public UserDetalingData updateUser(UserUpdateData dataUpdate) {
        UserModel userModel = userRepository.getReferenceById(dataUpdate.id());

        userModel.updateInformation(dataUpdate);

        return new UserDetalingData(userModel);
    }

    public UserDetalingData getProfileUser(Long id) {
        UserModel userModel = userRepository.getReferenceById(id);
        return new UserDetalingData(userModel);
    }

    public Page<UserDetalingData> getUsersPages(Pageable pages) {
        return userRepository.findAll(pages).map(UserDetalingData::new);
    }

}
