package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EquipmentRestController {

    @Autowired
    EquipmentServiceImpl equipmentService;

    /**
     * Create - Add a new equipment
     * @param equipment An object equipment
     * @return The equipment object saved
     */
    @PostMapping("/equipment")
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }


    /**
     * Read - Get one equipment
     * @param id The id of the equipment
     * @return An equipment object full filled
     */
    @GetMapping("/equipment/{id}")
    public Equipment getEquipment(@PathVariable("id") final UUID id) {
        Optional<Equipment> equipment = equipmentService.getEquipment(id);
        if(equipment.isPresent()) {
            return equipment.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all equipments
     * @return - An Iterable object of equipment full filled
     */
    @GetMapping("/equipments")
    public Iterable<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    /**
     * Update - Update an existing equipment
     * @param id - The id of the equipment to update
     * @param equipment - The equipment object updated
     * @return
     */
    @PutMapping("/equipment/{id}")
    public Equipment updateEquipment (@PathVariable("id") final UUID id, @RequestBody Equipment equipment) {
        Optional<Equipment> e = equipmentService.getEquipment(id);
        if (e.isPresent()) {
            Equipment currentEquipment = e.get();

            String name = equipment.getName();
            if (name != null) {
                currentEquipment.setName(name);
            }

            equipmentService.saveEquipment(currentEquipment);
            return currentEquipment;

        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a equipment
     * @param id - The id of the equipment to delete
     */
    @DeleteMapping("/equipment/{id}")
    public void deleteEquipment(@PathVariable("id") final UUID id) {
        equipmentService.deleteEquipment(id);
    }



}
