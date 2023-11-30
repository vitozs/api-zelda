package com.github.userservice.service;

import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUser_deveSalvarOUser() {
        // Arrange
        UserRegisterData userRegisterData = new UserRegisterData("Jonas", 19L);
        UserModel userModel = new UserModel(1L, "Jonas", 19L);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userModel);

        // Act
        userService.creatUser(userRegisterData);

        // Assert
        Mockito.verify(userRepository).save(Mockito.any());
    }

    @Test
    void createUser_deveRetornarOUserComUmId() {
        // Arrange
        UserRegisterData userRegisterData = new UserRegisterData("Jonas", 19L);
        UserModel userModel = new UserModel(1L, "Jonas", 19L);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userModel);

        // Act
        UserDetalingData resultado = userService.creatUser(userRegisterData);

        // Assert
        Assertions.assertThat(resultado.id()).isEqualTo(1L);
    }

    @Test
    void getProfileUser_deveRetornarUserComIdSolicitado() {
        // arrange
        Long userId = 1L;
        UserModel userModel = new UserModel();
        UserDetalingData expectedUserDetails = new UserDetalingData(userModel);

        // mock do userRepository
        Mockito.when(userRepository.getReferenceById(userId)).thenReturn(userModel);

        //act
        UserDetalingData resultUserDetails = userService.getProfileUser(userId);

        // assert
        Assertions.assertThat(resultUserDetails).isEqualTo(expectedUserDetails);

        
    }

}