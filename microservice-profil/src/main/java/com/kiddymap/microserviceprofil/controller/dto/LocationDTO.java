package com.kiddymap.microserviceprofil.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationDTO {

    private UUID id;

    private String name;

    private String description;


}
