package com.GreenShadow.WebSystem.Util;


import com.GreenShadow.WebSystem.dto.impl.*;
import com.GreenShadow.WebSystem.entity.*;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public FieldEntity convertToFieldEntity(FieldDTO dto){
        return modelMapper.map(dto, FieldEntity.class);
    }
    public FieldDTO convertToFieldDTO(FieldEntity entity) {

        List<CropEntity> cropList = entity.getCropList();
        List<String> cropCodeList = new ArrayList<>();
        for (CropEntity cropEntity : cropList ) {
            cropCodeList.add(cropEntity.getCropCode());
        }
        return new FieldDTO(
                entity.getFieldCode(),
                entity.getFieldName(),
                entity.getFieldLocation(),
                entity.getExtentSizeOfTheField(),
                entity.getFieldImage1(),
                entity.getFieldImage2(),
                cropCodeList
        );

    }

    public List<FieldDTO> convertToFieldDTOList(List<FieldEntity> fieldEntityList){
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDTO>>() {}.getType());
    }


    public CropEntity convertToCropEntity(CropDTO dto){
        return modelMapper.map(dto, CropEntity.class);
    }
    public CropDTO convertToCropDTO(CropEntity entity) {
        return modelMapper.map(entity, CropDTO.class);
    }

    public List<CropDTO> convertToCropDTOList(List<CropEntity> cropEntityList){
        return modelMapper.map(cropEntityList, new TypeToken<List<CropDTO>>() {}.getType());
    }


    public StaffEntity convertToStaffEntity(StaffDTO dto){
        return modelMapper.map(dto, StaffEntity.class);
    }
    public StaffDTO convertToStaffDTO(StaffEntity entity) {

        StaffDTO staffDTO = modelMapper.map(entity, StaffDTO.class);
        List<String> vehicleCodeList = new ArrayList<>();
        for (VehicleEntity vehicleEntity: entity.getVehicleList()) {
            vehicleCodeList.add(vehicleEntity.getVehicleCode());
        }
        staffDTO.setVehicleCodeList(vehicleCodeList);

        List<FieldStaffDetailPK> fieldStaffDetailPKList = new ArrayList<>();
        for (FieldStaffDetails  fieldStaffDetails : entity.getFieldStaffDetails()) {
            System.out.println(fieldStaffDetails.getFieldStaffDetailPK());
            fieldStaffDetailPKList.add(fieldStaffDetails.getFieldStaffDetailPK());
        }
        staffDTO.setFieldStaffDetailsPKs(fieldStaffDetailPKList);

        return staffDTO;
    }

    public List<StaffDTO> convertToStaffDTOList(List<StaffEntity> staffEntityList){
        /*Object map = modelMapper.map(staffEntityList, new TypeToken<List<StaffDTO>>() {}.getType());*/
        ArrayList<StaffDTO> staffDTOList = new ArrayList<>();
        for (StaffEntity entity: staffEntityList ) {
            staffDTOList.add(
                    convertToStaffDTO(entity)
            );
        }
        return staffDTOList;
    }

    public VehicleEntity convertToVehicleEntity(VehicleDTO dto){
        return modelMapper.map(dto, VehicleEntity.class);
    }

    public VehicleDTO convertToVehicleDTO(VehicleEntity entity) {
        VehicleDTO vehicleDTO = modelMapper.map(entity, VehicleDTO.class);
        if (entity.getStaff() != null) vehicleDTO.setStaffId(entity.getStaff().getId());
        return vehicleDTO;
    }

    public List<VehicleDTO> convertToVehicleDTOList(List<VehicleEntity> vehicleEntityList){
        return modelMapper.map(vehicleEntityList, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    public EquipmentEntity convertToEquipmentEntity(EquipmentDTO dto){
        return modelMapper.map(dto, EquipmentEntity.class);
    }
    public EquipmentDTO convertToEquipmentDTO(EquipmentEntity entity) {
        EquipmentDTO equipmentDTO = modelMapper.map(entity, EquipmentDTO.class);

        if (entity.getStaff() != null )equipmentDTO.setStaffId(entity.getStaff().getId());
        if (entity.getField() != null) equipmentDTO.setFieldCode(entity.getField().getFieldCode());
        return equipmentDTO;

    }

    public List<EquipmentDTO> convertToEquipmentDTOList(List<EquipmentEntity> equipmentEntityList){
        /*return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDTO>>() {}.getType());*/
        ArrayList<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for (EquipmentEntity entity: equipmentEntityList ) {
            equipmentDTOList.add( convertToEquipmentDTO(entity) );
        }
        return equipmentDTOList;
    }


    public FieldStaffDetails convertToFieldStaffDetails(FieldStaffDetailsDTO dto){
        return modelMapper.map(dto, FieldStaffDetails.class);
    }

}
