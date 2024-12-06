package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.dto.impl.UserDTO;
import com.GreenShadow.WebSystem.jwtModels.JwtAuthResponse;
import com.GreenShadow.WebSystem.jwtModels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDTO signUp);
    JwtAuthResponse refreshToken(String accessToken);

//    void saveUser(UserDTO buildUserDTO);

}
