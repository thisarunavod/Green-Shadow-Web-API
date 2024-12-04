package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.EquipmentErrorResponse;
import com.GreenShadow.WebSystem.customObj.EquipmentResponse;
import com.GreenShadow.WebSystem.customObj.VehicleErrorResponse;
import com.GreenShadow.WebSystem.dao.EquipmentDao;
import com.GreenShadow.WebSystem.dao.FieldDao;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dto.impl.EquipmentDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.*;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.VehicleNotFoundException;
import com.GreenShadow.WebSystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    Mapping mapping;

    @Autowired
    EquipmentDao equipmentDao;

    @Autowired
    StaffDao staffDao;

    @Autowired
    FieldDao fieldDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        if (equipmentDao.existsById(equipmentDTO.getEquipmentId()))  throw new DataPersistFailedException("Cannot Equipment Saved");
        equipmentDao.save(mapping.convertToEquipmentEntity(equipmentDTO));
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> tmpEquipmentEntity = equipmentDao.findById(equipmentId);
        if (tmpEquipmentEntity.isEmpty()) throw new VehicleNotFoundException("Equipment Not Found");
        tmpEquipmentEntity.get().setName(equipmentDTO.getName());
        tmpEquipmentEntity.get().setType(equipmentDTO.getType());
        tmpEquipmentEntity.get().setStatus(equipmentDTO.getStatus());

        if (equipmentDTO.getStaffId() != null) {
            tmpEquipmentEntity.get().setStaff(
                    staffDao.getStaffEntityById(equipmentDTO.getStaffId())
            );
        }
        if (equipmentDTO.getFieldCode() != null) {
            tmpEquipmentEntity.get().setField(
                    fieldDao.getFieldEntityByFieldCode(equipmentDTO.getFieldCode())
            );
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> tmpEquipmentEntity = equipmentDao.findById(equipmentId);
        if (tmpEquipmentEntity.isEmpty()) throw new VehicleNotFoundException("Equipment Not Found");
        equipmentDao.deleteById(equipmentId);
    }

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {

        if (equipmentDao.existsById(equipmentId)) {
            return mapping.convertToEquipmentDTO(equipmentDao.getEquipmentEntityByEquipmentId(equipmentId));
        }
        return new EquipmentErrorResponse(0,"Equipment Not Found");
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapping.convertToEquipmentDTOList(equipmentDao.findAll());
    }


    @Override
    public String generateNewEquipmentId() {
        try {
            List<String> equipmentIds = equipmentDao.findAllEquipmentIds();
            ArrayList<Integer> numberList = new ArrayList<>();
            for (String equipmentId: equipmentIds) {
                numberList.add(Integer.parseInt(equipmentId.replace("EQUIP_", "")));
            }
            return "EQUIP_"+ (Collections.max(numberList)+1);
        } catch (Exception e) {
            return "EQUIP_1";
        }
    }
    @Override
    public List<StaffDTO> getAvailableStaffMembers() {
        List<StaffDTO> availableLabors = new ArrayList<>();
        List<String> assignedIds = new ArrayList<>();
        for (EquipmentDTO equipmentDTO : getAllEquipment()) {
            if( equipmentDTO.getStaffId() != null) assignedIds.add(equipmentDTO.getStaffId());
        }

        List<StaffDTO> memberList = mapping.convertToStaffDTOList(staffDao.findAll()/*staffDao.findAllByDesignationContaining(Designation.Labors)*/);
L1:     for (StaffDTO member : memberList) {
            if (member.getDesignation() == Designation.Labors){
                for (String id: assignedIds){
                    if (id == member.getId() ) continue L1;
                }
                availableLabors.add(member);
            }

        }

        return availableLabors;

    }
}
