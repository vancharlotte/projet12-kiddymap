package com.kiddymap.microserviceprofil.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Profil {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    String username;
    String description;

    @ManyToMany
    @JoinTable(
            name = "profil_location_favorite",
            joinColumns = @JoinColumn(name = "profil_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    List<Location> favoriteLocations;
}
