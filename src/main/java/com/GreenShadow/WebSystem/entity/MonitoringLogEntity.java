package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "monitoring_log")
public class MonitoringLogEntity {

    @Id
    private String logCode;

    private Date logDate;

    private String observation;

    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @OneToMany(mappedBy = "monitoringLog")
    private List<CropLogDetails> cropLogDetails
            = new ArrayList<>();

    @OneToMany(mappedBy = "monitoringLog")
    private List<FieldsLogDetails> fieldsLogDetails
            = new ArrayList<>();

    @OneToMany(mappedBy = "monitoringLog")
    private List<StaffLogDetails> staffLogDetails
            = new ArrayList<>();


}
