package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.UserResponse;
import com.GreenShadow.WebSystem.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);
    void updateUser(String userId,UserDTO userDTO) ;
    void deleteUser( String userId );
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUser();

    UserDetailsService userDetailsService();
}
