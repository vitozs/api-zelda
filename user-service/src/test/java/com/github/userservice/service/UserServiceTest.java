package com.github.userservice.service;

import com.github.userservice.models.UserModel;
import com.github.userservice.models.recordClasses.UserDetalingData;
import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import com.github.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    void updateUser_deveLancarExcecaoQuandoUserNaoExiste() {
        // Arrange
        Long userId = 1L;
        UserUpdateData userUpdateData = new UserUpdateData(userId, "NovoNome", 25L);

        Mockito.when(userRepository.getReferenceById(userId))
                .thenThrow(EntityNotFoundException.class);

        // Act & Assert
        Assertions.assertThatThrownBy(() -> userService.updateUser(userUpdateData))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void updateUser_deveAlterarOsDadosDoUserQuandoEleExiste() {
        // Arrange
        Long userId = 1L;
        UserUpdateData userUpdateData = new UserUpdateData(userId, "NovoNome", 25L);
        UserModel existingUser = new UserModel(userId, "NomeAntigo", 20L);

        Mockito.when(userRepository.getReferenceById(userId)).thenReturn(existingUser);

        // Act
        UserDetalingData resultUserDetails = userService.updateUser(userUpdateData);

        // Assert
        Assertions.assertThat(resultUserDetails.id()).isEqualTo(userId);
        Assertions.assertThat(resultUserDetails.name()).isEqualTo("NovoNome");
        Assertions.assertThat(resultUserDetails.age()).isEqualTo(25L);
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

    @Test
    void getProfileUser_deveLancarExcecaoQuandoUserNaoExiste() {
        // Arrange
        Long userId = 1L;

        Mockito.when(userRepository.getReferenceById(userId))
                .thenThrow(EntityNotFoundException.class);


        // Act & Assert
        Assertions.assertThatThrownBy(() -> userService.getProfileUser(userId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void deleteUser_deveDeletarOUserQuandoEleExiste() {
        // Arrange
        Long userId = 1L;

        Mockito.when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        userService.deleteUser(userId);

        // Assert
        Mockito.verify(userRepository).deleteById(userId);
    }

    @Test
    void deleteUser_deveLancarExcecaoQuandoEleNaoExiste() {
        // Arrange
        Long userId = 1L;

        Mockito.when(userRepository.existsById(userId)).thenReturn(false);


        // Act & Assert
        Assertions.assertThatThrownBy(() -> userService.deleteUser(userId))
                .isInstanceOf(EntityNotFoundException.class);
    }
}