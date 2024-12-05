package com.GreenShadow.WebSystem.service;


import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldStaffDetailsDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.FieldStaffDetails;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;

import java.util.List;

public interface FieldStaffDetailsService {
    void saveFieldStaffDetails(FieldStaffDetailsDTO fieldStaffDetailsDTO);
    void updateFieldStaffDetails(String staffIid,String fieldCode);
    void deleteFieldStaffDetails( FieldStaffDetailPK fieldStaffDetailPK );
    CropResponse getSelectedFieldStaffDetails(FieldStaffDetailPK fieldStaffDetailPK );
    List<StaffDTO> getAllFieldStaffMembers(String fieldCode);

}
