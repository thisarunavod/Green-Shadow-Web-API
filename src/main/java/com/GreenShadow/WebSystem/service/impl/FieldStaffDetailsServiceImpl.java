package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.dao.FieldStaffDetailsDao;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldStaffDetailsDTO;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import com.GreenShadow.WebSystem.service.FieldStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FieldStaffDetailsServiceImpl implements FieldStaffDetailsService {

    @Autowired
    Mapping mapping;

    @Autowired
    FieldStaffDetailsDao fieldStaffDetailsDao;


    @Override
    public void saveFieldStaffDetails(FieldStaffDetailsDTO fieldStaffDetailsDTO) {
        fieldStaffDetailsDao.save(mapping.convertToFieldStaffDetails(fieldStaffDetailsDTO));
    }

    @Override
    public void updateFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK) {

    }

    @Override
    public void deleteFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK) {

    }

    @Override
    public CropResponse getSelectedFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK) {
        return null;
    }

    @Override
    public List<CropDTO> getAllFieldStaffDetails() {
        return List.of();
    }
}
