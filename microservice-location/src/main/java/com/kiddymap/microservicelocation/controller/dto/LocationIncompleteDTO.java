package com.kiddymap.microservicelocation.controller.dto;

import com.kiddymap.microservicelocation.model.Equipment;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class LocationIncompleteDTO {

    private float  longitude;

    private float latitude;

    private String name;

    private String description;

    private List<UUID> equipments;
}
