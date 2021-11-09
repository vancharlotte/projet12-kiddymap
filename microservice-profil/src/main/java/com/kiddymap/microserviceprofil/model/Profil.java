package com.kiddymap.microserviceprofil.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class Profil {

    @Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;


    String username;
    String description;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
