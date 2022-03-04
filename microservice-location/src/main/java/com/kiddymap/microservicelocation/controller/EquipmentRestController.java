package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.EquipmentDTO;
import com.kiddymap.microservicelocation.exception.EquipmentNotFoundException;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EquipmentRestController {

    @Autowired
    EquipmentServiceImpl equipmentService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Create - Add a new equipment
     *
     * @param equipment An object equipment
     * @return The equipment object saved
     */
    @PostMapping("/equipment/protected/add")
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }


    /**
     * Read - Get one equipment
     *
     * @param id The id of the equipment
     * @return An equipment object full filled
     */
    @GetMapping("/equipment/public/get/{id}")
    public EquipmentDTO getEquipment(@PathVariable("id") final UUID id) {
        Optional<Equipment> equipment = equipmentService.getEquipment(id);
        if (equipment.isPresent()) {
            return modelMapper.map(equipment.get(), EquipmentDTO.class);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "equipment not Found", new EquipmentNotFoundException("equipment not found"));
        }
    }

    /**
     * Read - Get one equipment
     *
     * @param listOfId The list of id of the equipments
     * @return An equipment object full filled
     */
  /*  @GetMapping("/location/public/equipment/list/{listOfId}")
    public List<EquipmentDTO> getListEquipment(@PathVariable("listOfId") final List<UUID> listOfId) {
        List<EquipmentDTO> equipments = new ArrayList();

        for (int i = 0; i < listOfId.size(); i++) {

            Optional<Equipment> equipment = equipmentService.getEquipment(listOfId.get(i));
            if (equipment.isPresent()) {
                EquipmentDTO equipmentDTO = modelMapper.map(equipment.get(), EquipmentDTO.class);
                equipments.add(equipmentDTO);
            }
            else{
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "equipment not Found", new EquipmentNotFoundException("equipment not found"));
            }

        }
        return equipments;

    }*/


    /**
     * Read - Get all equipments
     *
     * @return - An Iterable object of equipment full filled
     */
    @GetMapping("/equipments/public/getAll")
    public Iterable<EquipmentDTO> getAllEquipments() {
        Iterable<Equipment> equipmentList = equipmentService.getAllEquipments();
        return modelMapper.map(equipmentList, new TypeToken<List<EquipmentDTO>>() {
        }.getType());

    }

    /**
     * Update - Update an existing equipment
     *
     * @param id        - The id of the equipment to update
     * @param equipment - The equipment object updated
     * @return
     */
    @PutMapping("/equipment/protected/update/{id}")
    public Equipment updateEquipment(@PathVariable("id") final UUID id, @RequestBody Equipment equipment) {
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
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "equipment not Found", new EquipmentNotFoundException("equipment not found"));
        }
    }




}
