package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.FieldErrorResponse;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dao.FieldDao;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.service.FieldService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {

        if (fieldDao.existsById(fieldDTO.getFieldCode()))  throw new DataPersistFailedException("Cannot Customer Saved");
        FieldEntity savedField = fieldDao.save(mapping.convertToFieldEntity(fieldDTO));
        if (savedField.getFieldCode() == null) throw  new DataPersistFailedException("Cannot Customer Saved ");
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {

        Optional<FieldEntity> tmpFieldEntityById = fieldDao.findById(fieldCode);
        if (!tmpFieldEntityById.isPresent()) throw new FieldNotFoundExeption("Field Not Found");

        tmpFieldEntityById.get().setFieldName(fieldDTO.getFieldName());
        tmpFieldEntityById.get().setExtentSizeOfTheField(fieldDTO.getExtentSizeOfTheField());
        tmpFieldEntityById.get().setFieldLocation(fieldDTO.getFieldLocation());
        tmpFieldEntityById.get().setFieldImage1(fieldDTO.getFieldImage1());
        tmpFieldEntityById.get().setFieldImage2(fieldDTO.getFieldImage2());


    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> tmpFieldEntityById = fieldDao.findById(fieldCode);
        if (!tmpFieldEntityById.isPresent()) throw new FieldNotFoundExeption("Field Not Found");
        fieldDao.deleteById(fieldCode);
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            return mapping.convertToFieldDTO(fieldDao.getFieldEntityByFieldCode(fieldCode));
        }
        return new FieldErrorResponse(0,"Filed Not Found");
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.convertToFieldDTOList(fieldDao.findAll());
    }
}
