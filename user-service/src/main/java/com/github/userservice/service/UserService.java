package com.github.userservice.service;

import com.github.userservice.infra.security.SecurityFilter;
import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    private SecurityFilter securityFilter = new SecurityFilter();

    public UserDetalingData creatUser(UserRegisterData data){
        UserModel userModel = new UserModel(data);
        userModel.setPassword(passwordEncoder.encode(data.password()));
        userRepository.save(userModel);
       return new UserDetalingData(userModel);

    }

    public UserDetalingData updateUser(UserUpdateData dataUpdate, HttpServletRequest request) {
        Long id = recoverId(request);
        UserModel userModel = userRepository.getReferenceById(id);
        userModel.updateInformation(dataUpdate);
        return new UserDetalingData(userModel);
    }

    public UserDetalingData getProfileUser(HttpServletRequest request) {
        Long id = recoverId(request);
        UserModel userModel = userRepository.getReferenceById(id);
        return new UserDetalingData(userModel);
    }


    public void deleteUser(HttpServletRequest request) {
        Long id = recoverId(request);
        userRepository.deleteById(id);
    }

    private Long recoverId (HttpServletRequest request){
        String tokenJwt = securityFilter.recoverToken(request);

        return tokenService.getIdUser(tokenJwt);
    }

}
