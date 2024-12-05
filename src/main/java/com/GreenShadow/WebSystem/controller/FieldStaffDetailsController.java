package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dto.impl.FieldStaffDetailsDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.FieldStaffDetailsNotFoundException;
import com.GreenShadow.WebSystem.exeption.StaffNotFoundException;
import com.GreenShadow.WebSystem.service.FieldStaffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fieldStaffDetails")
@CrossOrigin
public class FieldStaffDetailsController {

    @Autowired
    FieldStaffDetailsService fieldStaffDetailsService;

    @PostMapping(value = "/{fieldCode}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveFieldStaffMem(
            @PathVariable("fieldCode") String fieldCode,
            @PathVariable("id") String id ) {

        if (fieldCode == null || id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        FieldStaffDetailsDTO fieldStaffDetailsDTO = new FieldStaffDetailsDTO();
        fieldStaffDetailsDTO.setFieldStaffDetailPK(
                new FieldStaffDetailPK(fieldCode, id)
        );

        try {
            fieldStaffDetailsService.saveFieldStaffDetails(fieldStaffDetailsDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{fieldCode}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> UpdateFieldStaffMem(
            @PathVariable("fieldCode") String fieldCode,
            @PathVariable("id") String id ) {
        System.out.println(fieldCode + " " + id);
        if (fieldCode == null || id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            fieldStaffDetailsService.updateFieldStaffDetails(id,fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldStaffDetailsNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getFieldStaffMember(@PathVariable("fieldCode") String fieldCode){
        return fieldStaffDetailsService.getAllFieldStaffMembers(fieldCode);

    }

}

