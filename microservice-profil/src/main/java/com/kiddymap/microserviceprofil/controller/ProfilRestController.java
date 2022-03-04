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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public Profil createProfil(@RequestBody Profil profil) {
        System.out.println("profil ?");

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
            System.out.println("save new profil");
            authService.addRoleToUser(profil.getAuthId());

            log.info("creteProfil : new profil added to bdd");
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println("get id : " + jwt.getClaims().get("sub"));

        Optional<Profil> profil = profilService.getProfil(id);

        if (profil.get().getAuthId().equals(jwt.getClaims().get("sub"))) {
            System.out.println("user verified");
            if (profil.isPresent()) {
                return modelMapper.map(profil.get(), ProfilDTO.class);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not Found", new UserNotFoundException("user not found"));
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "user not verified", new UserNotVerifiedException("principal and authId different"));
        }
    }

    /**
     * Read - Get one profil bu authId
     *
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/auth")
    public ProfilDTO getProfilByAuthId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println("sub : " + jwt.getClaims().get("sub"));

        Optional<Profil> profil = profilService.getProfilByAuthId(jwt.getClaims().get("sub").toString());
        if (profil.isPresent()) {
            return modelMapper.map(profil.get(), ProfilDTO.class);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user Not Found", new UserNotFoundException("user not found"));
        }
    }


    /**
     * Read - Get all profils
     * @return - An Iterable object of Profil full filled
     */

   /* @GetMapping("/profils")
    public Iterable<Profil> getProfils() {
        Iterable<Profil> profilList = profilService.getAllProfils();
        return modelMapper.map(profilList, new TypeToken<List<ProfilDTO>>() {}.getType());
    }*/

    /**
     * Update - Update an existing profil
     *
     * @param id     - The id of the profil to update
     * @param profil - The profil object updated
     * @return
     */
    @PutMapping("/profil/update/{id}")
    public Profil updateProfil(@PathVariable("id") final UUID id, @RequestBody Profil profil) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println("get id : " + jwt.getClaims().get("sub"));

        if (profil.getAuthId().equals(jwt.getClaims().get("sub"))) {

            System.out.println("profil : " + profil.toString());
            Optional<Profil> e = profilService.getProfil(id);

            if (e.isPresent()) {
                Profil currentProfil = e.get();
                currentProfil.setAuthId(profil.getAuthId());
                currentProfil.setUsername(profil.getUsername());
                currentProfil.setDescription(profil.getDescription());
                currentProfil.setFavoriteLocations(profil.getFavoriteLocations());

                String username = profil.getEmail();
                if (!currentProfil.getEmail().equals(profil.getEmail())) {
                    System.out.println(currentProfil.getEmail() + profil.getEmail());
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
