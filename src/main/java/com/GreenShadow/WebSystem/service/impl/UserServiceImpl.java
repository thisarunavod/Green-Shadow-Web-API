package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.UserResponse;
import com.GreenShadow.WebSystem.dao.UserDao;
import com.GreenShadow.WebSystem.dto.impl.UserDTO;
import com.GreenShadow.WebSystem.entity.Role;
import com.GreenShadow.WebSystem.entity.UserEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.UserNotFoundException;
import com.GreenShadow.WebSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    @Autowired
    private final UserDao userDao;

//    @Autowired
    private Mapping mapping;

    public void saveUser(UserDTO userDTO) {

        UserEntity saveUser = userDao.save(mapping.convertToUserEntity(userDTO));
        if (saveUser == null  &&  saveUser.getUserId() == null) throw new DataPersistFailedException( "Cannot Data Saved");

    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

        Optional<UserEntity> tmpUserEntity = userDao.findById(userId);
        if (!tmpUserEntity.isPresent()) { throw  new UserNotFoundException("User Not Found !!"); }
        else{

            tmpUserEntity.get().setEmail(userDTO.getEmail());
            tmpUserEntity.get().setPassword(userDTO.getPassword());
            tmpUserEntity.get().setRole(Role.valueOf(userDTO.getRole()));
        }

    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> tmpEntity = userDao.findById(userId);
        if (  !tmpEntity.isPresent() ) {
            throw new UserNotFoundException("User Not Found");
        } else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        return null;
        /*if (userDao.existsById(userId)){
            UserEntity userEntityByUserId = userDao.getUserEntitiesByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        } else {
            return new UserErrorResponse(0, "User not found");
        }*/

    }

    @Override
    public List<UserDTO> getAllUser() {
        return null;
        /*return mapping.convertToUserDTOList(userDao.findAll());*/
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDao.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found "));
    }
}
//@Override
//public UserDetailsService userDetailsService() {
//    return email ->
//            userRepository.findByEmail(email)
//                    .orElseThrow(()-> new UserNotFoundException("User Not Found"));
//}