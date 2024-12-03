package com.GreenShadow.WebSystem.controller;

import com.GreenShadow.WebSystem.customObj.EquipmentResponse;
import com.GreenShadow.WebSystem.customObj.VehicleResponse;
import com.GreenShadow.WebSystem.dto.impl.EquipmentDTO;
import com.GreenShadow.WebSystem.dto.impl.StaffDTO;
import com.GreenShadow.WebSystem.dto.impl.VehicleDTO;
import com.GreenShadow.WebSystem.exeption.DataPersistFailedException;
import com.GreenShadow.WebSystem.exeption.EquipmentNotFoundException;
import com.GreenShadow.WebSystem.exeption.StaffNotFoundException;
import com.GreenShadow.WebSystem.exeption.VehicleNotFoundException;
import com.GreenShadow.WebSystem.service.EquipmentService;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;


    @GetMapping
    public String generateNewEquipmentId(){
        return equipmentService.generateNewEquipmentId();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaffMem(@RequestBody EquipmentDTO equipmentDTO){

        if (equipmentDTO == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{equipmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable("equipmentId") String equipmentId , @RequestBody EquipmentDTO equipmentDTO){
        try {

            if (equipmentDTO == null && (equipmentDTO == null || equipmentId.isEmpty())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.updateEquipment(equipmentId, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (EquipmentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{equipmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentResponse getEquipment(@PathVariable("equipmentId") String equipmentId){
        return equipmentService.getSelectedEquipment(equipmentId);
    }

    @GetMapping(value = "allEquipments" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments(){
        return equipmentService.getAllEquipment();
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable ("equipmentId") String equipmentId) {
        try {
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
