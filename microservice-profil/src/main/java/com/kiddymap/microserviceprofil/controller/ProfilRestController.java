package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.exception.UserNotFoundException;
import com.kiddymap.microserviceprofil.exception.UserNotVerifiedException;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.AuthServiceImpl;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;


import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@Slf4j
public class ProfilRestController {

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    AuthServiceImpl authService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtDecoder jwtDecoder;




    /**
     * Create - Add a new profil
     *
     * @param profil An object profil
     * @return The profil object saved
     */
    @PostMapping("/profil/add")
    public Profil createProfil(@RequestBody ProfilDTO profil) {
        Optional<Profil> thisProfil = profilService.getProfilByAuthId(profil.getAuthId());

        if (thisProfil.isPresent()) {
            log.info("createProfil : profil not added to bdd:  already exist");
            return thisProfil.get();
        } else {
            Profil newProfil = new Profil();
            newProfil.setAuthId(profil.getAuthId());
            newProfil.setUsername(profil.getUsername());
            newProfil.setEmail(profil.getEmail());

            newProfil.setDescription("à compléter");

            authService.addRoleToUser(profil.getAuthId());

            log.info("createProfil : new profil added to bdd");
            return profilService.saveProfil(newProfil);
        }
    }


    /**
     * Read - Get one profil
     *
     * @param id The id of the profil
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/id/{id}")
    public ProfilDTO getProfil(@PathVariable("id") final UUID id) {
        Optional<Profil> profil = profilService.getProfil(id);

        if (profil.isPresent()) {
            if (profil.get().getAuthId().equals(profilService.getAuthIdFromToken())) {
            return modelMapper.map(profil.get(), ProfilDTO.class);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "user not verified", new UserNotVerifiedException("principal and authId different"));
            }
        }

            else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
            }

    }

    /**
     * Read - Get one profil bu authId
     *
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/auth")
    public ProfilDTO getProfilByAuthId() {


        Optional<Profil> profil = profilService.getProfilByAuthId(profilService.getAuthIdFromToken());
        if (profil.isPresent()) {
            return modelMapper.map(profil.get(), ProfilDTO.class);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user Not Found", new UserNotFoundException("user not found"));
        }
    }


    /**
     * Update - Update an existing profil
     *
     * @param id     - The id of the profil to update
     * @param profil - The profil object updated
     * @return
     */
    @PutMapping("/profil/update/{id}")
    public Profil updateProfil(@PathVariable("id") final UUID id, @RequestBody ProfilDTO profil) {


        if (profil.getAuthId().equals(profilService.getAuthIdFromToken())) {

            Optional<Profil> e = profilService.getProfil(id);

            if (e.isPresent()) {
                Profil currentProfil = e.get();
                currentProfil.setAuthId(profil.getAuthId());
                currentProfil.setUsername(profil.getUsername());
                currentProfil.setDescription(profil.getDescription());

                if (!currentProfil.getEmail().equals(profil.getEmail())) {
                log.info("email updated");
                    authService.updateEmail(profil.getAuthId(), profil.getEmail());
                    currentProfil.setEmail(profil.getEmail());
                }

                profilService.saveProfil(currentProfil);
                return currentProfil;

            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user Not Found", new UserNotFoundException("user not found"));            }
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "user not verified", new UserNotVerifiedException("principal and profilId different"));
        }
    }

    /**
     * Delete - Delete an profil
     *
     * @param id - The id of the profil to delete
     */
/*    @DeleteMapping("/profil/delete/{id}")
    public void deleteProfil(@PathVariable("id") final UUID id) {
        profilService.deleteProfil(id);
    }*/


}
