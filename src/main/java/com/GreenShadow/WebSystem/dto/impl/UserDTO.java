package com.GreenShadow.WebSystem.dto.impl;


import com.GreenShadow.WebSystem.customObj.UserResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements SuperDTO , UserResponse {

    private String userId;
    private String email;
    private String password;

    private String role;

}
