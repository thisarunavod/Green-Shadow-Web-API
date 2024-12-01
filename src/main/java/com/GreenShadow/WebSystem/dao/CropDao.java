package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CancellationException;

@Repository
public interface CropDao extends JpaRepository<CropEntity,String> {
    CropEntity getCropEntityByCropCode(String cropCode);

    List<CropEntity> findAllByField_FieldCode(String fieldFieldCode);
}
