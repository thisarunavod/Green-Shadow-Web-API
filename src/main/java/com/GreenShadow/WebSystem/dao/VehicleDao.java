package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity,String> {

    VehicleEntity getVehicleEntityByVehicleCode(String vehicleCode);
    @Query("select vehicleCode from vehicle")
    List<String> findAllvehicleCodes();
}
