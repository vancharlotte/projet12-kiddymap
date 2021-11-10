package com.kiddymap.microserviceprofil.service.impl;

import com.kiddymap.microserviceprofil.dao.ProfilDao;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.contrat.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProfilServiceImpl implements ProfilService {

    @Autowired
    ProfilDao profilDao;

    @Override
    public void newProfil(Profil profil) {
        profilDao.save(profil);
    }

    @Override
    public Optional<Profil> getProfil(final UUID id) {
        return profilDao.findById(id);
    }

    @Override
    public void updateProfil(Profil profil) {
        profilDao.save(profil);
    }

    @Override
    public void deleteProfil(UUID id) {
        profilDao.deleteById(id);
    }

    @Override
    public Profil saveProfil(Profil profil) {
        return profilDao.save(profil);
    }


}
