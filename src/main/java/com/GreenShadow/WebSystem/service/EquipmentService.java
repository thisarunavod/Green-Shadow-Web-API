package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.EquipmentResponse;
import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dto.impl.EquipmentDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);
    void deleteEquipment( String equipmentId );
    EquipmentResponse getSelectedEquipment(String equipmentId );
    List<EquipmentDTO> getAllEquipment();
    String generateNewEquipmentId();
}
