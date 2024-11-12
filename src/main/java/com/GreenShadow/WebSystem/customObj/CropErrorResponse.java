package com.GreenShadow.WebSystem.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropErrorResponse implements CropResponse, Serializable {
    private  int errorCode ;
    private String errorMessage ;
}
