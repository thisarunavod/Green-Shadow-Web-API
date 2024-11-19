package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
    VehicleEntity getVehicleEntitiesByVehicleCode(String vehicleCode);
}
