package com.kiddymap.microservicelocation.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Location {

    @Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String name;


}
