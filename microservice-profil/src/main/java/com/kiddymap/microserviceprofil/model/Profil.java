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
public class Profil implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "profil_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username")
    String username;

    @Column(name = "description")
    String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "profil_location_favorite",
            joinColumns ={ @JoinColumn(name = "profil_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    List<Location> favoriteLocations;




}
