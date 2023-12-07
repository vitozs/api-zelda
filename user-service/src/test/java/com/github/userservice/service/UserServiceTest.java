package com.github.userservice.service;

import com.github.userservice.infra.security.SecurityFilter;
import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityFilter securityFilter;

    @Mock
    private TokenService tokenService;

    @Test
    void createUser_deveSalvarOUser() {
        // Arrange
        UserRegisterData userRegisterData = new UserRegisterData("Jonas", 19L, "jonas@gmail.com", "123456");

        // Act
        userService.creatUser(userRegisterData);

        // Assert
        Mockito.verify(userRepository).save(any());
    }

    @Test
    void createUser_deveRetornarOUserComUmId() {
        // Arrange
        UserRegisterData userRegisterData = new UserRegisterData("Jonas", 19L, "jonas@gmail.com", "123456");
        UserModel userModel = new UserModel(userRegisterData);

        Mockito.when(userRepository.save(any(UserModel.class))).thenAnswer(invocation -> {
            UserModel savedUserModel = invocation.getArgument(0);
            savedUserModel.setId(1L);
            return savedUserModel;
        });

        // Act
        UserDetalingData resultado = userService.creatUser(userRegisterData);

        // Assert
        Assertions.assertThat(resultado.id()).isEqualTo(1L);
    }


    @Test
    void updateUser_deveAlterarOsDadosDoUser() {
        // Arrange
        Long userId = 1L;
        UserUpdateData userUpdateData = new UserUpdateData("NovoNome", 25L, "NovoEmail@gmail.com");
        UserModel existingUser = new UserModel(userId, "NomeAntigo", 20L, "emailAntigo@gmail.com", "123456");

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);


        Mockito.when(userService.recoverId(any())).thenReturn(userId);


        Mockito.when(userRepository.getReferenceById(userId)).thenReturn(existingUser);

        // Act
        UserDetalingData resultUserDetails = userService.updateUser(userUpdateData, request);

        // Assert
        Assertions.assertThat(resultUserDetails.id()).isEqualTo(userId);
        Assertions.assertThat(resultUserDetails.name()).isEqualTo("NovoNome");
        Assertions.assertThat(resultUserDetails.age()).isEqualTo(25L);
    }

    @Test
    void getProfileUser_deveRetornarUserComIdSolicitado() {
        // arrange
        Long userId = 1L;
        UserModel userModel = new UserModel(userId, "Jonas", 19L, "jonas@gmail.com", "123456");

        UserDetalingData expectedUserDetails = new UserDetalingData(userModel);

        // mock do userRepository
        Mockito.when(userRepository.getReferenceById(userId)).thenReturn(userModel);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(userService.recoverId(any())).thenReturn(userId);
        //act
        UserDetalingData resultUserDetails = userService.getProfileUser(request);

        // assert
        Assertions.assertThat(resultUserDetails).isEqualTo(expectedUserDetails);
    }



    @Test
    void deleteUser_deveDeletarOUser() {
        // Arrange
        Long userId = 1L;

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(userService.recoverId(any())).thenReturn(userId);

        // Act
        userService.deleteUser(request);

        // Assert
        Mockito.verify(userRepository).deleteById(userId);
    }


}