package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.StaffErrorResponse;
import com.GreenShadow.WebSystem.customObj.VehicleErrorResponse;
import com.GreenShadow.WebSystem.customObj.VehicleResponse;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dao.VehicleDao;
import com.GreenShadow.WebSystem.dto.impl.VehicleDTO;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.VehicleEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.VehicleNotFoundException;
import com.GreenShadow.WebSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;


    @Autowired
    StaffDao staffDao;


    @Autowired
    Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

        if (vehicleDao.existsById(vehicleDTO.getVehicleCode()))  throw new DataPersistFailedException("Cannot Vehicle Saved");
        VehicleEntity vehicleEntity = vehicleDao.save(mapping.convertToVehicleEntity(vehicleDTO));
        if (vehicleEntity.getVehicleCode() == null)  throw  new DataPersistFailedException("Cannot Staff Member Saved");

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {

        Optional<VehicleEntity> tmpVehicleEntity = vehicleDao.findById(vehicleCode);
        if (tmpVehicleEntity.isEmpty()) throw new VehicleNotFoundException("Vehicle Not Found");
        tmpVehicleEntity.get().setCategory(vehicleDTO.getCategory());
        tmpVehicleEntity.get().setStatus(vehicleDTO.getStatus());
        tmpVehicleEntity.get().setRemarks(vehicleDTO.getRemarks());
        tmpVehicleEntity.get().setFuelType(vehicleDTO.getFuelType());

        if (vehicleDTO.getStaffId() != null) {
            tmpVehicleEntity.get().setStaff(
                    staffDao.getStaffEntityById(vehicleDTO.getStaffId())
            );
        }

    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> tmpVehicleEntity = vehicleDao.findById(vehicleCode);
        if (tmpVehicleEntity.isEmpty()) throw new VehicleNotFoundException("Vehicle Not Found");
        vehicleDao.deleteById(vehicleCode);
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        System.out.println(vehicleCode);
        if (vehicleDao.existsById(vehicleCode)) {
            return mapping.convertToVehicleDTO(vehicleDao.getVehicleEntityByVehicleCode(vehicleCode));
        }
        return new VehicleErrorResponse(0,"Vehicle Not Found");

    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.convertToVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public String generateNewVehicleCode() {
        try {
            List<String> allVehicleCodeList = vehicleDao.findAllvehicleCodes();
            String id = allVehicleCodeList.get(allVehicleCodeList .size() - 1);
            char[] charArray = id.toCharArray();
            String newID = "";
            for (int i = 4; i <= charArray.length-1 ; i++) { newID = newID + (charArray[i]); }
            int x = Integer.parseInt(newID);
            return "VEH_"+(x+1);
        } catch (Exception e) {
            return "VEH_1";
        }
    }


}
