package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.Util.AppUtil;
import com.GreenShadow.WebSystem.dto.impl.UserDTO;
import com.GreenShadow.WebSystem.entity.Role;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.jwtModels.JwtAuthResponse;
import com.GreenShadow.WebSystem.jwtModels.SignIn;
import com.GreenShadow.WebSystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2/auth")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(
            @RequestPart("userId") String userId,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role) {
        try {

            // build the user
            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setUserId(userId);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            buildUserDTO.setRole(role);

            //Send to service layer
            authenticationService.signUp(buildUserDTO);
            return ResponseEntity.ok(authenticationService.signUp(buildUserDTO));

        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){
        System.out.println("signIn");
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping(value = "refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
