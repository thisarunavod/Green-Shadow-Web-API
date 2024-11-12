package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "staff")
public class StaffEntity {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String designation;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date joinedDate;
    private Date dateOfBirth;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;

    private String contactNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "staff")
    private List<FieldStaffDetails> fieldStaffDetails
            = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicleList
            = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    private List<StaffLogDetails> staffLogDetails
            = new ArrayList<>();

}
