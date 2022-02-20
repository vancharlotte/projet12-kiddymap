package com.kiddymap.microserviceprofil.service.contrat;

import com.kiddymap.microserviceprofil.model.Favorite;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfilService {


    Optional<Profil> getProfil(UUID id);

    Profil updateProfil(Profil profil);

    void deleteProfil(UUID id);

    Profil saveProfil(Profil profil);

    Profil updateProfilFavorite(Location location, Profil profil);

    Profil deleteProfilFavorite(Location location, Profil profil);

    List<Location> getAllFavorites(UUID id);

    Optional<Profil> getProfilByAuthId(String authId);

    Optional<Favorite> existFavorite(UUID profilId, UUID locationId);




}
