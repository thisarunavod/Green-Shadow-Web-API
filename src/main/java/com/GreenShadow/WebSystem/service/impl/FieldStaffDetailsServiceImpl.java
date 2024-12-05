package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.Util.Mapping;
import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.dao.FieldStaffDetailsDao;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldStaffDetailsDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.FieldStaffDetails;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.exeption.FieldStaffDetailsNotFoundException;
import com.GreenShadow.WebSystem.service.FieldStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldStaffDetailsServiceImpl implements FieldStaffDetailsService {

    @Autowired
    Mapping mapping;

    @Autowired
    FieldStaffDetailsDao fieldStaffDetailsDao;

    @Autowired
    StaffDao staffDao;


    @Override
    public void saveFieldStaffDetails(FieldStaffDetailsDTO fieldStaffDetailsDTO) {
        fieldStaffDetailsDao.save(mapping.convertToFieldStaffDetails(fieldStaffDetailsDTO));
    }

    @Override
    public void updateFieldStaffDetails(String staffId,String fieldCode) {
        try {

            Optional<FieldStaffDetails> tmpEntityByFieldStaffDetailPKId = fieldStaffDetailsDao.findByFieldStaffDetailPK_Id(staffId);
            /*System.out.println(tmpEntityByFieldStaffDetailPKId.get().getFieldStaffDetailPK());*/
            deleteFieldStaffDetails(tmpEntityByFieldStaffDetailPKId.get().getFieldStaffDetailPK());
            saveFieldStaffDetails(
                new FieldStaffDetailsDTO(new FieldStaffDetailPK(fieldCode,staffId))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK) {
        fieldStaffDetailsDao.deleteById(fieldStaffDetailPK);
    }

    @Override
    public CropResponse getSelectedFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK) {
        return null;
    }

    @Override
    public List<StaffDTO> getAllFieldStaffMembers(String fieldCode){
        List<StaffDTO> relavantList = new ArrayList<>();
        List<FieldStaffDetails> fieldStaffDetailsList = fieldStaffDetailsDao.findAllByField_FieldCode(fieldCode);
        for (FieldStaffDetails list : fieldStaffDetailsList) {
            relavantList.add(mapping.convertToStaffDTO(list.getStaff()));
        }
        return relavantList;
    }
}
