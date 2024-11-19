package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "vehicle")
public class VehicleEntity {

    @Id
    private String vehicleCode;

    @Column(unique = true)
    private String licensePlateNumber;

    private String category;

    private String fuelType;

    private String status;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;

    private String remarks;


}
