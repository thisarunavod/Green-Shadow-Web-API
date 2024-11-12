package com.GreenShadow.WebSystem.entity;


import com.GreenShadow.WebSystem.entity.embedded.StaffLogDetailsPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "staff_log_details")
public class StaffLogDetails {

    @EmbeddedId
    private StaffLogDetailsPK staffLogDetailsPK;

    @ManyToOne
    @JoinColumn(name = "personalId",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "logCode",
            referencedColumnName = "logCode",
            insertable = false,
            updatable = false)
    private MonitoringLogEntity monitoringLog;
}
