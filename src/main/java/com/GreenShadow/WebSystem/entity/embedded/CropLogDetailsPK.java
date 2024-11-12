package com.GreenShadow.WebSystem.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CropLogDetailsPK implements Serializable {
    @Column(name = "cropCode")
    private String crop_code;

    @Column(name = "logCode")
    private String log_code;

}
