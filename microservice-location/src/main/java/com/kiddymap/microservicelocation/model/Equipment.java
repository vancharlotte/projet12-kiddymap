package com.kiddymap.microservicelocation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Equipment {

    @Id
    @GeneratedValue(generator = "UUID2")
    @GenericGenerator( name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "equipment_id", updatable = false, nullable = false)
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipments")
    private List<Location> locations;
}
