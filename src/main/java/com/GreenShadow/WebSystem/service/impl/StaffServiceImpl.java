package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.CropErrorResponse;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.customObj.StaffErrorResponse;
import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.exeption.StaffNotFoundException;
import com.GreenShadow.WebSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    Mapping mapping;

    @Autowired
    StaffDao staffDao;

    @Override
    public void saveStaffMem(StaffDTO staffDTO) {

    }

    @Override
    public void updateStaffMem(String id, StaffDTO staffDTO) {

    }

    @Override
    public void deleteStaffMem(String id) {
        Optional<StaffEntity> staffEntitybyId = staffDao.findById(id);
        if (staffEntitybyId.isEmpty()) throw new StaffNotFoundException("Staff Member Not Found");
        staffDao.deleteById(id);
    }

    @Override
    public StaffResponse getSelectedStaffMem(String id) {
        if (staffDao.existsById(id)) {
            return mapping.convertToStaffDTO(staffDao.getStaffEntityById(id));
        }
        return new StaffErrorResponse(0,"Crop Not Found");
    }

    @Override
    public List<StaffDTO> getAllStaffMem() {
        return mapping.convertToStaffDTOList(staffDao.findAll());
    }
}
