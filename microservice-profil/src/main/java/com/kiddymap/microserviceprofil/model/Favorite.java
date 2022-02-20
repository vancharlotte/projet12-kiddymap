package com.kiddymap.microserviceprofil.model;

import lombok.Data;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@Data
@IdClass(FavoriteId.class)
@Table(name = "profil_location_favorite")
public class Favorite {

    @Id
    @Column(name = "profil_id" )
    private UUID profilId;

    @Column(name = "location_id")
    private UUID locationId;
}
