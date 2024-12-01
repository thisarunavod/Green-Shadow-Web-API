package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.Util.AppUtil;
import com.GreenShadow.WebSystem.customObj.CropResponse;
import com.GreenShadow.WebSystem.customObj.FieldResponse;
import com.GreenShadow.WebSystem.dto.impl.CropDTO;
import com.GreenShadow.WebSystem.dto.impl.FieldDTO;
import com.GreenShadow.WebSystem.exeption.CropNotFoundExeption;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldNotFoundExeption;
import com.GreenShadow.WebSystem.service.CropService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {


    @Autowired
    private CropService cropService;

    @GetMapping
    public String healthCheck(){ return "Crop Controller is running Successfully";}

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropCategory") String cropCategory,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("filedCode") String filedCode,
            @RequestPart("cropImage") MultipartFile cropImage ){


        try {
            // Handle profile picture
            byte[] imageByteCollection = cropImage.getBytes();
            String base64CropImage = AppUtil.toBase64ProfilePic(imageByteCollection); /* <--- converting to base64 format*/

            // build the Crop

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropCommonName(cropCommonName);
            cropDTO.setCropScientificName(cropScientificName);
            cropDTO.setCropCategory(cropCategory);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setCropImage(base64CropImage);

            //Send to service layer
            cropService.saveCrop(cropDTO,filedCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{cropCode}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCrop(
            @PathVariable("cropCode") String cropCode,
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropCategory") String cropCategory,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("filedCode") String filedCode,
            @RequestPart("cropImage") MultipartFile cropImage  ){

        try {
            // Handle updated profile picture
            byte[] imageByteCollection = cropImage.getBytes();
            String base64CropImage = AppUtil.toBase64ProfilePic(imageByteCollection); /* <--- converting to base64 format*/

            // build the Crop

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropCommonName(cropCommonName);
            cropDTO.setCropScientificName(cropScientificName);
            cropDTO.setCropCategory(cropCategory);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setCropImage(base64CropImage);


            //Send to service layer
            cropService.updateCrop(cropCode,filedCode,cropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch ( CropNotFoundExeption e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CropResponse getSelectedCrop(@PathVariable("cropCode") String cropCode) {
        return cropService.getSelectedCrop(cropCode);
    }

    @DeleteMapping("/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode){
        try {
            if ( cropCode == null || cropCode.isEmpty()) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundExeption e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "getAllCrops",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping(value = "getAllCropsByField/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCropsByField(@PathVariable ("fieldCode") String fieldCode){
        return cropService.getAllCropsByFieldCode(fieldCode);
    }


}
