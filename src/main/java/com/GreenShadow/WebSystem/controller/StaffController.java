package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.customObj.StaffResponse;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.StaffNotFoundException;
import com.GreenShadow.WebSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping
    public String healthCheck(){ return "Staff Controller is running Successfully";}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaffMem(@RequestBody StaffDTO staffDTO){

        if (staffDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            staffService.saveStaffMem(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaffMember(@PathVariable("id") String id , @RequestBody StaffDTO staffDTO){

        try {

            if (staffDTO == null && (id == null || id.isEmpty())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaffMem(id, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (StaffNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffResponse getStaffMember(@PathVariable("id") String id){
        return staffService.getSelectedStaffMem(id);
    }
    @GetMapping(value = "allStaffMembers",produces = MediaType.APPLICATION_JSON_VALUE)  /* jacksoStaffMembers object bind karanne */
    public List<StaffDTO> getAllStaffMembers(){return staffService.getAllStaffMem(); }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("id") String id) {
        try {
            staffService.deleteStaffMem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
