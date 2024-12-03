package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDao  extends JpaRepository<EquipmentEntity,String> {
    EquipmentEntity getEquipmentEntityByEquipmentId(String equipmentId);
    @Query("select equipmentId from equipments ")
    List<String> findAllEquipmentIds();
}
