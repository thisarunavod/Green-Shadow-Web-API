package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.EquipmentResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.EquipmentStatus;
import com.GreenShadow.WebSystem.entity.EquipmentType;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EquipmentDTO  implements SuperDTO,EquipmentResponse  {
    private String equipmentId;
    private String name;
    private EquipmentType type;
    private EquipmentStatus status;
    private String staffId;
    private String fieldCode;
}
