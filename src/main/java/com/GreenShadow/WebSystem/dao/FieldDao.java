package com.GreenShadow.WebSystem.dao;


import com.GreenShadow.WebSystem.entity.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {

    @Query("select fieldCode from field ")
    List<String> findAllIds();

    FieldEntity getFieldEntityByFieldCode(String fieldCode);
}
