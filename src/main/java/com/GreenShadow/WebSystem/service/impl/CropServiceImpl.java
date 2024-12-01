package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.CropErrorResponse;
import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.customObj.FieldErrorResponse;
import com.GreenShadow.WebSystem.dao.CropDao;
import com.GreenShadow.WebSystem.dao.FieldDao;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.entity.CropEntity;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO,String fieldCode) {
        if (cropDao.existsById(cropDTO.getCropCode()))  throw new DataPersistFailedException("Cannot Crop Saved");
        cropDTO.setField(mapping.convertToFieldDTO(fieldDao.getFieldEntityByFieldCode(fieldCode)));
        CropEntity cropEntity = cropDao.save(mapping.convertToCropEntity(cropDTO));
        if (cropEntity.getCropCode() == null)  throw  new DataPersistFailedException("Cannot Crop Saved ");
    }

    @Override
    public void updateCrop(String cropCode,String fieldCode, CropDTO cropDTO) {

        Optional<CropEntity> tmpCropEntityById = cropDao.findById(cropCode);
        if (!tmpCropEntityById.isPresent()) throw new FieldNotFoundExeption("Crop Not Found");

        cropDTO.setField(mapping.convertToFieldDTO(fieldDao.getFieldEntityByFieldCode(fieldCode)));

        tmpCropEntityById.get().setCropCommonName(cropDTO.getCropCommonName());
        tmpCropEntityById.get().setCropCategory(cropDTO.getCropCategory());
        tmpCropEntityById.get().setCropImage(cropDTO.getCropImage());
        tmpCropEntityById.get().setField(mapping.convertToFieldEntity(cropDTO.getField()));
        tmpCropEntityById.get().setCropScientificName(cropDTO.getCropScientificName());
        tmpCropEntityById.get().setCropSeason(cropDTO.getCropSeason());
        tmpCropEntityById.get().setCropLogDetails(cropDTO.getCropLogDetails());

    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> cropEntitybyId = cropDao.findById(cropCode);
        if (cropEntitybyId.isEmpty()) throw new FieldNotFoundExeption("Crop Not Found");
        cropDao.deleteById(cropCode);
    }

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if (cropDao.existsById(cropCode)) {
            return mapping.convertToCropDTO(cropDao.getCropEntityByCropCode(cropCode));
        }
        return new CropErrorResponse(0,"Crop Not Found");
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.convertToCropDTOList(cropDao.findAll());
    }

    @Override
    public List<CropDTO> getAllCropsByFieldCode(String fieldCode) {
        return mapping.convertToCropDTOList(cropDao.findAllByField_FieldCode(fieldCode));
    }


}
