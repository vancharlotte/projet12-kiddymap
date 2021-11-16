package com.kiddymap.microserviceprofil.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(generator = "UUID2")
    @GenericGenerator( name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToMany(mappedBy = "favoriteLocations")
    List<Profil> profils;




}
