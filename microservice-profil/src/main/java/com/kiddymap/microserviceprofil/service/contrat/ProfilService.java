package com.kiddymap.microserviceprofil.service.contrat;

import com.kiddymap.microserviceprofil.model.Profil;

import java.util.Optional;

public interface ProfilService {

    void newProfil(Profil profil);

    Optional<Profil> getProfil(String id);

    void updateProfil(Profil profil);

    void deleteProfil(String id);

    Profil saveProfil(Profil profil);
}
