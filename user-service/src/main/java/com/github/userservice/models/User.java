package com.github.userservice.models;


import com.github.userservice.models.recordClasses.UserRegisterData;
import com.github.userservice.models.recordClasses.UserUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long age;

    public User(UserRegisterData data) {
        this.name = data.name();
        this.age = data.age();
    }

    public void updateInformation(UserUpdateData data) {
        if ( data.name() != null){
            this.name = data.name();
        }
        if (data.age() != null){
            this.age = data.age();
        }
    }
}
