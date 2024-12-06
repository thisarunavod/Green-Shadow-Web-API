package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
    UserEntity getUserEntitiesByUserId(String id);
    Optional<UserEntity> findByEmail(String email);

}
