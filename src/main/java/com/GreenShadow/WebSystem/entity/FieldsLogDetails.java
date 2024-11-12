package com.GreenShadow.WebSystem.entity;


import com.GreenShadow.WebSystem.entity.embedded.FieldLogDetailsPK;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
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

@Entity(name = "field_log_details")
public class FieldsLogDetails {

    @EmbeddedId
    private FieldLogDetailsPK fieldLogDetailsPK;

    @ManyToOne
    @JoinColumn(name = "fieldCode",
            referencedColumnName = "fieldCode",
            insertable = false,
            updatable = false)
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "logCode",
            referencedColumnName = "logCode",
            insertable = false,
            updatable = false)
    private MonitoringLogEntity monitoringLog;

}
