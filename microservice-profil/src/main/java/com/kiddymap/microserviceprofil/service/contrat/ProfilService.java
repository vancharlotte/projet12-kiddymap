package com.kiddymap.microserviceprofil.service.contrat;

import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;

import java.util.Optional;
import java.util.UUID;

public interface ProfilService {

    void newProfil(Profil profil);

    Optional<Profil> getProfil(UUID id);

    void updateProfil(Profil profil);

    void deleteProfil(UUID id);

    Profil saveProfil(Profil profil);

    Profil updateProfilFavorite(Location location, Profil profil);

    Profil deleteProfilFavorite(Location location, Profil profil);
}
