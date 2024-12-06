package com.GreenShadow.WebSystem.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogMonitoringErrorResponse  implements LogMonitoringResponse, Serializable {
    private  int errorCode ;
    private String errorMessage ;

}
