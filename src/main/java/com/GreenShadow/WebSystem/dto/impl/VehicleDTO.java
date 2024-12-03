package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.VehicleResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.FieldEntity;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.VehicleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO implements SuperDTO , VehicleResponse {

    private String vehicleCode;
    private String licensePlateNumber;
    private String category;
    private String fuelType;
    private VehicleStatus status;
    private String staffId;
    private String remarks;

}
