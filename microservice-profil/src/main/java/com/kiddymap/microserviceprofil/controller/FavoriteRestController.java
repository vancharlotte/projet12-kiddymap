package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.LocationServiceImpl;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api
@RestController
public class FavoriteRestController {

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    LocationServiceImpl locationService;


    @PutMapping("/profil/favorite/add/{id}")
    public Profil addProfilFavorite(@PathVariable("id") final UUID locationId, @RequestBody Profil profil) {
        Optional<Location> optionalLocation = locationService.getLocation(locationId);
        Optional<Profil> optionalProfil = profilService.getProfil(profil.getId());

        if (optionalProfil.isPresent() && optionalLocation.isPresent()) {
            Profil currentProfil = optionalProfil.get();
            profilService.updateProfilFavorite(optionalLocation.get(), optionalProfil.get());

            return currentProfil ;

        } else {
            return null;
        }

    }


    @PutMapping("/profil/favorite/delete/{id}")
    public Profil deleteProfilFavorite(@PathVariable("id") final UUID locationId, @RequestBody Profil profil) {
        Optional<Location> optionalLocation = locationService.getLocation(locationId);
        Optional<Profil> optionalProfil = profilService.getProfil(profil.getId());

        if (optionalProfil.isPresent() && optionalLocation.isPresent()) {
            Profil currentProfil = optionalProfil.get();
            profilService.deleteProfilFavorite(optionalLocation.get(), optionalProfil.get());

            return currentProfil ;

        } else {
            return null;
        }

    }


    @GetMapping("/profil/allfavorites/{id}")
    public List<Location> getProfilAllFavorite(@PathVariable("id") final UUID profilId, @RequestBody Profil profil) {
        Optional<Profil> optionalProfil = profilService.getProfil(profilId);

        if (optionalProfil.isPresent()) {
            return profilService.getAllFavorites(profilId);

        } else {
            return null;
        }

    }

}
