package com.kiddymap.microservicelocation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@IdClass(AttributeId.class)
@Table(name = "location_equipment")
public class Attribute {

    @Id
    @Column(name = "location_id" )
    private UUID locationId;

    @Column(name = "equipment_id")
    private UUID equipmentId;
}