package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.dto.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldDTO implements SuperDTO , FieldResponse {
    private String fieldCode;
    private String fieldName;

    @JsonSerialize(using = PointSerializer.class)
    private Point fieldLocation;

    private double extentSizeOfTheField;
    private String fieldImage1;
    private String fieldImage2;

    /*private List<CropEntity> cropList;

    private List<FieldStaffDetails> fieldStaffDetails
            = new ArrayList<>();
    private List<EquipmentEntity> equipmentList
            = new ArrayList<>();
    private List<FieldsLogDetails> fieldsLogDetails
            = new ArrayList<>();*/
}
