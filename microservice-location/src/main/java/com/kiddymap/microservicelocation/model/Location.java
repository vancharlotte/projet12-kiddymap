package com.kiddymap.microservicelocation.model;

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
    @Column(name = "location_id", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "location_equipment",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> equipments;



}
