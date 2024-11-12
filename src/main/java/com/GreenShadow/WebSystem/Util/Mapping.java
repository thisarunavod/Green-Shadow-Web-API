package com.GreenShadow.WebSystem.Util;


import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.CharsetEncoder;
import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public FieldEntity convertToFieldEntity(FieldDTO dto){
        return modelMapper.map(dto, FieldEntity.class);
    }
    public FieldDTO convertToFieldDTO(FieldEntity entity) {
        return modelMapper.map(entity, FieldDTO.class);
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
        return modelMapper.map(staffEntityList, new TypeToken<List<CropDTO>>() {}.getType());
    }




    /*public NoteDTO convertToDTO(NoteEntity noteEntity){
        return modelMapper.map(noteEntity,NoteDTO.class);  *//* meya auto karala denawa *//*
    }

    public NoteEntity convertToEntity(NoteDTO dto){
        return modelMapper.map(dto,NoteEntity.class);  *//* meya auto karala denawa *//*
    }

    public List<NoteDTO> convertToDTOList(List<NoteEntity> notes){
        return modelMapper.map(notes,new TypeToken<List<NoteDTO>>() {}.getType());  *//* meya auto karala denawa *//*
    }


    public UserDTO convertToUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity,UserDTO.class);
    }

    public UserEntity convertToUserEntity(UserDTO dto){
        return modelMapper.map(dto,UserEntity.class);
    }

    public List<UserDTO> convertToUserDTOList(List<UserEntity> users){
        return modelMapper.map(users,new TypeToken<List<UserDTO>>() {}.getType());
    }*/

}
