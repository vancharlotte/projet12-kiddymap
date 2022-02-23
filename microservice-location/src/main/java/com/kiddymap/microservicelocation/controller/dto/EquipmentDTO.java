package com.kiddymap.microservicelocation.controller.dto;

import com.kiddymap.microservicelocation.model.Location;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EquipmentDTO {

    private UUID id;

    private String name;
}
