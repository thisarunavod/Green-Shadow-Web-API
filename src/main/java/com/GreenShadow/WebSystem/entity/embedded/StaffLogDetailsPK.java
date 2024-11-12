package com.GreenShadow.WebSystem.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StaffLogDetailsPK implements Serializable {

    @Column(name = "personalId")
    private String field_code;
    @Column(name = "logCode")
    private String log_code;
}
