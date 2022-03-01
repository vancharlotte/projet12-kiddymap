package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.LocationDTO;
import com.kiddymap.microserviceprofil.model.Favorite;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.LocationServiceImpl;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class FavoriteRestController {

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    ModelMapper modelMapper;

    public String getAuthId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println("get id : " + jwt.getClaims().get("sub"));
        return jwt.getClaims().get("sub").toString();
    }

    @PutMapping("/profil/favorite/add/{id}")
    public Profil addProfilFavorite(@PathVariable("id") final UUID locationId, @RequestBody Profil profil) {
        Optional<Location> optionalLocation = locationService.getLocation(locationId);
        Optional<Profil> optionalProfil = profilService.getProfil(profil.getId());

        if(!getAuthId().equals(optionalProfil.get().getAuthId())){
            System.out.println(" not validate");
            return null;
        }

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

        if(!getAuthId().equals(optionalProfil.get().getAuthId())){
            System.out.println(" not validate");
            return null;
        }


            if (optionalProfil.isPresent() && optionalLocation.isPresent()) {
                Profil currentProfil = optionalProfil.get();
                profilService.deleteProfilFavorite(optionalLocation.get(), optionalProfil.get());

                return currentProfil;

            } else {
                return null;
            }


    }


    @GetMapping("/profil/allfavorites/{id}")
    public List<LocationDTO> getProfilAllFavorite(@PathVariable("id") final UUID profilId, @RequestBody Profil profil) {
        Optional<Profil> optionalProfil = profilService.getProfil(profilId);

        if (optionalProfil.isPresent()) {
            Iterable<Location> favoritesList = profilService.getAllFavorites(optionalProfil.get().getId());
            return  modelMapper.map(favoritesList, new TypeToken<List<LocationDTO>>() {}.getType());

        } else {
            return null;
        }

    }


    @GetMapping("/profil/favorite/exist/{locationId}")
    public boolean existProfilFavorite(@PathVariable("locationId") final UUID locationId) {
        System.out.println("look if favorite exist");
        Optional<Profil> optionalProfil = profilService.getProfilByAuthId(getAuthId());

        if (optionalProfil.isPresent()) {

            Profil profil = optionalProfil.get();

            Optional<Favorite> optionalFavorite = profilService.existFavorite(profil.getId(), locationId);

            if (optionalFavorite.isPresent()) {
                System.out.println("favorite");

                return true;
            } else {
                System.out.println("not favorite");

                return false;
            }
        }
        else{
            return false;}


    }

}
