package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.FieldStaffDetailsResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldStaffDetailsDTO implements SuperDTO , FieldStaffDetailsResponse {
    private FieldStaffDetailPK fieldStaffDetailPK;

}
