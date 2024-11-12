package com.GreenShadow.WebSystem.entity;

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

@Entity(name = "field_staff_details")
public class FieldStaffDetails {

    @EmbeddedId
    private FieldStaffDetailPK fieldStaffDetailPK;

    @ManyToOne
    @JoinColumn(name = "fieldCode",
            referencedColumnName = "fieldCode",
            insertable = false,
            updatable = false)
    private FieldEntity field;

    @ManyToOne
    @JoinColumn(name = "id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private StaffEntity staff;

}
