package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.dto.PointSerializer;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.CropLogDetails;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String cropCategory;
    private String cropSeason;
    private FieldDTO field;
    private List<CropLogDetails> cropLogDetails
            = new ArrayList<>();
}
