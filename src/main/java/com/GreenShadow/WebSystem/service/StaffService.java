package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaffMem(StaffDTO staffDTO);
    void updateStaffMem(String id,StaffDTO staffDTO);
    void deleteStaffMem( String id );
    StaffResponse getSelectedStaffMem(String id );
    List<StaffDTO> getAllStaffMem();
    String  genenrateNewStaffId();
}
