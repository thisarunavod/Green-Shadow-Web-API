package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "user")
public class UserEntity {

    @Id
    private String userId;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToOne
//    @JoinColumn(name = "personalId", referencedColumnName = "id")
//    private StaffEntity s

}
