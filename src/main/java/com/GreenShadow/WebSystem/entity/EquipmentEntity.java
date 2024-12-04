package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "equipments")
public class EquipmentEntity {

    @Id
    private String equipmentId;

    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Column(columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @OneToOne
    @JoinColumn(name = "personalId", referencedColumnName = "id")
    private StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;
}