package com.GreenShadow.WebSystem.Util;


import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.dto.impl.VehicleDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.VehicleEntity;
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
        return modelMapper.map(entity, StaffDTO.class);
    }

    public List<StaffDTO> convertToStaffDTOList(List<StaffEntity> staffEntityList){
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public VehicleEntity convertToVehicleEntity(VehicleDTO dto){
        return modelMapper.map(dto, VehicleEntity.class);
    }
    public VehicleDTO convertToVehicleDTO(VehicleEntity entity) {

        return modelMapper.map(entity, VehicleDTO.class);
    }

    public List<VehicleDTO> convertToVehicleDTOList(List<VehicleEntity> vehicleEntityList){
        return modelMapper.map(vehicleEntityList, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

}
