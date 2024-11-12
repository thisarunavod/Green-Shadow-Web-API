package com.GreenShadow.WebSystem.entity.embedded;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FieldLogDetailsPK implements Serializable {

    @Column(name = "fieldCode")
    private String fieldCode;
    @Column(name = "logCode")
    private String logCode;

}
