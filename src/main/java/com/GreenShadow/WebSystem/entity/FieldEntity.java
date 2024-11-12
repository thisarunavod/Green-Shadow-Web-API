package com.GreenShadow.WebSystem.entity;

import com.GreenShadow.WebSystem.dto.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity (name = "field")
public class FieldEntity {
    @Id
    private String fieldCode;

    private String fieldName;

//    @JsonSerialize(using = PointSerializer.class)
    @Column(columnDefinition = "VARBINARY(5000)")
    private Point fieldLocation;

    private double extentSizeOfTheField;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;

    @OneToMany(mappedBy = "field")
    private List<CropEntity> cropList;

    @OneToMany(mappedBy = "field")
    private List<FieldStaffDetails> fieldStaffDetails
            = new ArrayList<>();

    @OneToMany(mappedBy = "field")
    private List<EquipmentEntity> equipmentList
            = new ArrayList<>();

    @OneToMany(mappedBy = "field")
    private List<FieldsLogDetails> fieldsLogDetails
            = new ArrayList<>();

}
