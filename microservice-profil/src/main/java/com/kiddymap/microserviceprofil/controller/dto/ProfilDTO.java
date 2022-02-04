package com.kiddymap.microserviceprofil.controller.dto;

import com.kiddymap.microserviceprofil.model.Location;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProfilDTO {

    private UUID id;

    private String authId;

    private String username;

    private String description;

    private List<Location> favoriteLocations;

}
