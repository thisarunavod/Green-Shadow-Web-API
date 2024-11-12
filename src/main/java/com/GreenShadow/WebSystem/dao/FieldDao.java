package com.GreenShadow.WebSystem.dao;


import com.GreenShadow.WebSystem.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {
    FieldEntity getFieldEntityByFieldCode(String fieldCode);
}
