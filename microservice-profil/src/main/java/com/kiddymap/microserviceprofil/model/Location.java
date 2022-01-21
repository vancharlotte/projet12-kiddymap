package com.kiddymap.microserviceprofil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID2")
    @GenericGenerator( name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "location_id", updatable = false, nullable = false)
    private UUID id;
    private int longitude;
    private int latitude;
    private String name;

    private String description;

    @ManyToMany
    List<Profil> profil;




}
