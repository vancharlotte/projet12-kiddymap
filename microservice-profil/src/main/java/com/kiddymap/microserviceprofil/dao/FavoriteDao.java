package com.kiddymap.microserviceprofil.dao;

import com.kiddymap.microserviceprofil.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteDao extends JpaRepository<Favorite, UUID> {

    Optional<Favorite> findByProfilIdAndLocationId(UUID profilId, UUID locationId);

}