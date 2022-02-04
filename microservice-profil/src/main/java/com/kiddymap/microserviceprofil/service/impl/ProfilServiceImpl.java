package com.kiddymap.microserviceprofil.service.impl;

import com.kiddymap.microserviceprofil.dao.ProfilDao;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.contrat.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProfilServiceImpl implements ProfilService {

    @Autowired
    ProfilDao profilDao;


    @Override
    public Optional<Profil> getProfil(final UUID id) {
        return profilDao.findById(id);
    }

    @Override
    public Optional<Profil> getProfilByAuthId(String authId) {
        return profilDao.findByAuthId(authId);
    }

    @Override
    public Profil updateProfil(Profil profil) {
        return profilDao.save(profil);
    }

    @Override
    public void deleteProfil(UUID id) {
        profilDao.deleteById(id);
    }

    @Override
    public Profil saveProfil(Profil profil) {
        return profilDao.save(profil);
    }

    @Override
    public Profil updateProfilFavorite(Location location, Profil profil){
        profil.getFavoriteLocations().add(location);
        profilDao.save(profil);
        return profil;
    }

    @Override
    public Profil deleteProfilFavorite(Location location, Profil profil){
        profil.getFavoriteLocations().remove(location);
        profilDao.save(profil);
        return profil;
    }

    @Override
    public List<Location> getAllFavorites(UUID id){
        Optional<Profil> profil = profilDao.findById(id);
        if( profil.isPresent()){
            return profil.get().getFavoriteLocations();
        }
        else{
            return null;
        }
    }




}
