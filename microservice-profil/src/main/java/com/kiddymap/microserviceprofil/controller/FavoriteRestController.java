package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.LocationDTO;
import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.exception.UserNotFoundException;
import com.kiddymap.microserviceprofil.exception.UserNotVerifiedException;
import com.kiddymap.microserviceprofil.model.Favorite;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.LocationServiceImpl;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@Slf4j
public class FavoriteRestController {

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    LocationServiceImpl locationService;

    @Autowired
    ModelMapper modelMapper;

    public String getAuthId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();

            return jwt.getClaims().get("sub").toString();
        } catch (NullPointerException e) {
            log.error("no authentication possible");
            return null;
        }
    }

    @PutMapping("/profil/favorite/add/{id}")
    public Profil addProfilFavorite(@PathVariable("id") final UUID locationId, @RequestBody ProfilDTO profil) {
        Optional<Location> optionalLocation = locationService.getLocation(locationId);
        Optional<Profil> optionalProfil = profilService.getProfil(profil.getId());


        if (optionalProfil.isPresent() && optionalLocation.isPresent()) {

            if (!getAuthId().equals(optionalProfil.get().getAuthId())) {
                log.error("user not authorized : principal and authId different");
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "user not verified", new UserNotVerifiedException("principal and authId different"));
            } else {
                Profil currentProfil = optionalProfil.get();
                profilService.updateProfilFavorite(optionalLocation.get(), optionalProfil.get());

                return currentProfil;
            }

        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
        }

    }


    @PutMapping("/profil/favorite/delete/{id}")
    public Profil deleteProfilFavorite(@PathVariable("id") final UUID locationId, @RequestBody ProfilDTO profil) {
        Optional<Location> optionalLocation = locationService.getLocation(locationId);
        Optional<Profil> optionalProfil = profilService.getProfil(profil.getId());


        if (optionalProfil.isPresent() && optionalLocation.isPresent()) {

            if (!getAuthId().equals(optionalProfil.get().getAuthId())) {
                log.error("user not authorized : principal and authId different");
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "user not verified", new UserNotVerifiedException("principal and authId different"));
            } else {
                Profil currentProfil = optionalProfil.get();
                profilService.deleteProfilFavorite(optionalLocation.get(), optionalProfil.get());

                return currentProfil;
            }

        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
        }

    }


    @GetMapping("/profil/favorite/allfavorites/{id}")
    public List<LocationDTO> getProfilAllFavorite(@PathVariable("id") final UUID profilId, @RequestBody ProfilDTO profil) {
        Optional<Profil> optionalProfil = profilService.getProfil(profilId);

        if (optionalProfil.isPresent()) {
            Iterable<Location> favoritesList = profilService.getAllFavorites(optionalProfil.get().getId());
            return modelMapper.map(favoritesList, new TypeToken<List<LocationDTO>>() {
            }.getType());

        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
        }

    }


    @GetMapping("/profil/favorite/exist/{locationId}")
    public boolean existProfilFavorite(@PathVariable("locationId") final UUID locationId) {
        Optional<Profil> optionalProfil = profilService.getProfilByAuthId(getAuthId());

        if (optionalProfil.isPresent()) {
            Profil profil = optionalProfil.get();
            Optional<Favorite> optionalFavorite = profilService.existFavorite(profil.getId(), locationId);

            if (optionalFavorite.isPresent()) {
                log.info("favorite exist : location is a favorite of the user");
                return true;
            } else {
                log.info("favorite doesn't exist : location is not a favorite of the user");
                return false;
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
        }
    }


}


