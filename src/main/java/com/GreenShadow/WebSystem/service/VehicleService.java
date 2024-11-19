package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.customObj.VehicleResponse;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);
    void deleteVehicle( String vehicleCode);
    VehicleResponse getSelectedVehicle(String vehicleCode );
    List<VehicleDTO> getAllVehicles();
}
