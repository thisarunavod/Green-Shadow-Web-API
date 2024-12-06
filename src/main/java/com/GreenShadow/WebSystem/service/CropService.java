package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO,String fieldCode);
    void updateCrop(String cropCode,String fieldCode,CropDTO cropDTO);
    void deleteCrop( String cropCode );
    CropResponse getSelectedCrop(String cropCode );
    List<CropDTO> getAllCrops();
    List<CropDTO> getAllCropsByFieldCode(String fieldCode);
    String generateNewCropCode();
    List<String> getAllCropCodes();
}
