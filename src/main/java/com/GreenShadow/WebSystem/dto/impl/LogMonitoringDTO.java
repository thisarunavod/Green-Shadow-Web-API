package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.entity.CropLogDetails;
import com.GreenShadow.WebSystem.entity.FieldsLogDetails;
import com.GreenShadow.WebSystem.entity.StaffLogDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogMonitoringDTO {

    private String logCode;
    private LocalDate logDate;
    private String observation;
    private String observedImage;

    private List<CropLogDetails> cropLogDetails
            = new ArrayList<>();

    private List<FieldsLogDetails> fieldsLogDetails
            = new ArrayList<>();

    private List<StaffLogDetails> staffLogDetails
            = new ArrayList<>();

}
