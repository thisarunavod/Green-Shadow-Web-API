package com.GreenShadow.WebSystem.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Embeddable
public class FieldStaffDetailPK implements Serializable {

    @Column(name = "fieldCode")
    private String fieldCode;

    @Column(name = "id")
    private String id;

}
