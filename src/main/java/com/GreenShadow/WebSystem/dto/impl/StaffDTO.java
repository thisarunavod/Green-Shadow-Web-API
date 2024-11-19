package com.GreenShadow.WebSystem.dto.impl;

import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dto.SuperDTO;
import com.GreenShadow.WebSystem.entity.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class StaffDTO implements SuperDTO, StaffResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String designation;
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
    private Role role;

    private List<FieldStaffDetails> fieldStaffDetails
            = new ArrayList<>();
    private List<String> vehicleCodeList
            = new ArrayList<>();
    private List<StaffLogDetails> staffLogDetails
            = new ArrayList<>();
}
