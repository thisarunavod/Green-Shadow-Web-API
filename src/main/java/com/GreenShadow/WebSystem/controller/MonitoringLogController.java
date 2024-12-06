package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.Util.AppUtil;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.dto.impl.LogMonitoringDTO;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.service.FieldService;
import com.GreenShadow.WebSystem.service.LogMonitorinService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/monitoringLog")
@CrossOrigin
public class MonitoringLogController {

    @Autowired
    private LogMonitorinService logMonitorinService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateNewLogCode(){
        return logMonitorinService.generateNewLogCode();
    }


    @GetMapping(value = "allFieldCodes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllFieldCodes(){
        return logMonitorinService.getAllFieldIds();
    }

    @GetMapping(value = "allCropCodes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllCropCodes(){
        return logMonitorinService.getAllCropCodes();
    }

    @GetMapping(value = "allStaffMemberIDs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllStaffMemberIDs(){
        return logMonitorinService.getAllStaffMemberIDs();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveLogObservation(
            @RequestPart("logCode") String logCode,
            @RequestPart("logDate") String logDate,
            @RequestPart("observation") String observation,
            @RequestPart("logImage" ) MultipartFile logImage){


        try {
            // Handle profile picture

            byte[] imageByteCollection = logImage.getBytes();
            String base64FieldImage1 = AppUtil.toBase64ProfilePic(imageByteCollection); /* <--- converting to base64 format*/

            // build the field
            LogMonitoringDTO logMonitoringDTO = new LogMonitoringDTO();


            //Send to service layer
            logMonitorinService.saveMonitoringLogDetails(logMonitoringDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
