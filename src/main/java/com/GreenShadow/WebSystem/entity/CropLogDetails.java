package com.GreenShadow.WebSystem.entity;

import com.GreenShadow.WebSystem.entity.embedded.CropLogDetailsPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "crop_log_details")
public class CropLogDetails {

    @EmbeddedId
    private CropLogDetailsPK cropLogDetailsPK;

    @ManyToOne
    @JoinColumn(name = "cropCode",
            referencedColumnName = "cropCode",
            insertable = false,
            updatable = false)
    private CropEntity crop;

    @ManyToOne
    @JoinColumn(name = "logCode",
            referencedColumnName = "logCode",
            insertable = false,
            updatable = false)
    private MonitoringLogEntity monitoringLog;


}
