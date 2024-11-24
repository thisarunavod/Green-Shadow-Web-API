package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.EquipmentResponse;
import com.GreenShadow.WebSystem.dao.EquipmentDao;
import com.GreenShadow.WebSystem.dao.FieldDao;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dto.impl.EquipmentDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.EquipmentEntity;
import com.GreenShadow.WebSystem.entity.VehicleEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.VehicleNotFoundException;
import com.GreenShadow.WebSystem.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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

    }

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {
        return null;
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return null;
    }
}
