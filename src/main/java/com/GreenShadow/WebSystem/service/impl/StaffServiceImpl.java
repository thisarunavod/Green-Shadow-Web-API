package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.CropErrorResponse;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.customObj.StaffErrorResponse;
import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dao.VehicleDao;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.VehicleEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.exeption.StaffNotFoundException;
import com.GreenShadow.WebSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    Mapping mapping;

    @Autowired
    StaffDao staffDao;

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public void saveStaffMem(StaffDTO staffDTO) {
        if (staffDao.existsById(staffDTO.getId()))  throw new DataPersistFailedException("Cannot Staff Member Saved");
        StaffEntity staffEntity = staffDao.save(mapping.convertToStaffEntity(staffDTO));
        if (staffEntity.getId() == null)  throw  new DataPersistFailedException("Cannot Staff Member Saved");
    }

    @Override
    public void updateStaffMem(String id, StaffDTO staffDTO) {

        Optional<StaffEntity> tmpStaffEntityById = staffDao.findById(id);
        if (tmpStaffEntityById.isEmpty()) throw new StaffNotFoundException("Staff Member Not Found");

        tmpStaffEntityById.get().setFirstName(staffDTO.getFirstName());
        tmpStaffEntityById.get().setLastName(staffDTO.getLastName());
        tmpStaffEntityById.get().setDesignation(staffDTO.getDesignation());
        tmpStaffEntityById.get().setGender(staffDTO.getGender());
        tmpStaffEntityById.get().setJoinedDate(staffDTO.getJoinedDate());
        tmpStaffEntityById.get().setDateOfBirth(staffDTO.getDateOfBirth());
        tmpStaffEntityById.get().setAddressLine1(staffDTO.getAddressLine1());
        tmpStaffEntityById.get().setAddressLine2(staffDTO.getAddressLine2());
        tmpStaffEntityById.get().setAddressLine3(staffDTO.getAddressLine3());
        tmpStaffEntityById.get().setAddressLine4(staffDTO.getAddressLine4());
        tmpStaffEntityById.get().setAddressLine5(staffDTO.getAddressLine5());
        tmpStaffEntityById.get().setContactNo(staffDTO.getContactNo());
        tmpStaffEntityById.get().setEmail(staffDTO.getEmail());
        tmpStaffEntityById.get().setRole(staffDTO.getRole());
        tmpStaffEntityById.get().setFieldStaffDetails(staffDTO.getFieldStaffDetails());

        ArrayList<VehicleEntity> vehicleEntitiesList = new ArrayList<>();
        for (String vehicleCode: staffDTO.getVehicleCodeList()) {
            vehicleEntitiesList.add(
                    vehicleDao.getVehicleEntityByVehicleCode(vehicleCode)
            );
        }

        tmpStaffEntityById.get().setVehicleList(vehicleEntitiesList);
        tmpStaffEntityById.get().setStaffLogDetails(staffDTO.getStaffLogDetails());

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

    @Override
    public String genenrateNewStaffId() {
        try {
            List<String> allStaffIdList = staffDao.findAllIds();
            String id = allStaffIdList .get(allStaffIdList .size() - 1);
            char[] charArray = id.toCharArray();
            String newID = "";
            for (int i = 3; i <= charArray.length-1 ; i++) { newID = newID + (charArray[i]); }
            int x = Integer.parseInt(newID);
            return "ST_"+(x+1);
        } catch (Exception e) {
            return "ST_1";
        }
    }
}
