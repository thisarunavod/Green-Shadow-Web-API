package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {

    void saveField(FieldDTO fieldDTO);
    void updateField(String field_code,FieldDTO fieldDTO);
    void deleteField( String field_code );
    FieldResponse getSelectedField(String field_code );
    List<FieldDTO> getAllFields();
}
