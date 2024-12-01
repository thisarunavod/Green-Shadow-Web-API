package com.GreenShadow.WebSystem.controller;


import com.GreenShadow.WebSystem.Util.AppUtil;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.service.FieldService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("api/v1/field")
@CrossOrigin
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateNewFieldCode(){
        return fieldService.generateNewFieldCode();
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocationX") String fieldLocationX,
            @RequestPart("fieldLocationY") String fieldLocationY,
            @RequestPart("extentSizeOfTheField") String extentSizeOfTheField,
            @RequestPart("fieldImage1" ) MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2 ){


        try {
            // Handle profile picture
            byte[] imageByteCollection1 = fieldImage1.getBytes();
            byte[] imageByteCollection2 = fieldImage2.getBytes();
            String base64FieldImage1 = AppUtil.toBase64ProfilePic(imageByteCollection1); /* <--- converting to base64 format*/
            String base64FieldImage2 = AppUtil.toBase64ProfilePic(imageByteCollection2);


            double longitude = Double.parseDouble(fieldLocationX);
            double latitude  = Double.parseDouble(fieldLocationY);
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

            // build the field
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(point);
            double landSize = Double.parseDouble(extentSizeOfTheField);
            fieldDTO.setExtentSizeOfTheField(landSize);

            fieldDTO.setFieldImage1(base64FieldImage1);
            fieldDTO.setFieldImage2(base64FieldImage2);

            //Send to service layer
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldResponse getSelectedField(@PathVariable("fieldCode") String fieldCode) {
        /*FieldResponse selectedField = fieldService.getSelectedField(fieldCode);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(selectedField);
        System.out.println("Serialized JSON: " + json);*/
        return fieldService.getSelectedField(fieldCode);
    }


    @PatchMapping(value = "/{fieldCode}" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateField(@PathVariable("fieldCode") String fieldCode,
                                              @RequestPart("fieldName") String fieldName,
                                              @RequestPart("fieldLocationX") String fieldLocationX,
                                              @RequestPart("fieldLocationY") String fieldLocationY,
                                              @RequestPart("extentSizeOfTheField") String extentSizeOfTheField,
                                              @RequestPart("fieldImage1" ) MultipartFile fieldImage1,
                                              @RequestPart("fieldImage2" ) MultipartFile fieldImage2){
        System.out.println(fieldImage1.isEmpty());
        System.out.println(fieldImage2.isEmpty());
        try {
            byte[] fieldImage1ByteCollection = null;
            byte[] fieldImage2ByteCollection = null;
            String updateBase64fieldImage1 = null;
            String updateBase64fieldImage2 = null;
            if (fieldImage1 != null){
                fieldImage1ByteCollection = fieldImage1.getBytes();
                updateBase64fieldImage1 = AppUtil.toBase64ProfilePic(fieldImage1ByteCollection);
            }
            if (fieldImage2 != null){
                fieldImage2ByteCollection = fieldImage2.getBytes();
                updateBase64fieldImage2 = AppUtil.toBase64ProfilePic(fieldImage2ByteCollection);
            }

            double longitude = Double.parseDouble(fieldLocationX);
            double latitude  = Double.parseDouble(fieldLocationY);
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

            FieldDTO fieldDTO = new FieldDTO();

            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldImage1(updateBase64fieldImage1);
            fieldDTO.setFieldImage2(updateBase64fieldImage2);
            fieldDTO.setExtentSizeOfTheField(Double.parseDouble(extentSizeOfTheField));
            fieldDTO.setFieldLocation(point);

            fieldService.updateField(fieldCode, fieldDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (FieldNotFoundExeption e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try {
            if ( fieldCode == null || fieldCode.isEmpty()) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundExeption e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getAllFields",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields(){
        return fieldService.getAllFields();
    }


}
