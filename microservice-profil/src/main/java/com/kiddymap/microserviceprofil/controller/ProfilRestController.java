package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@RestController
public class ProfilRestController {

    @Autowired
    ProfilServiceImpl profilService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Create - Add a new profil
     * @param profil An object profil
     * @return The profil object saved
     */
    @PostMapping("/profil/add")
    public Profil createProfil(@RequestBody Profil profil) {
        System.out.println("profil ?");

        Optional<Profil> thisProfil = profilService.getProfilByAuthId(profil.getAuthId());
        if (thisProfil.isPresent()) {
            System.out.println("profil already exist");
            return thisProfil.get();
        }
        else{
            Profil newProfil = new  Profil();
            newProfil.setAuthId(profil.getAuthId());
            newProfil.setUsername(profil.getUsername());
            newProfil.setDescription("à compléter");
            System.out.println("save new profil");

            return profilService.saveProfil(newProfil);}
    }




    /**
     * Read - Get one profil
     * @param id The id of the profil
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/id/{id}")
    public ProfilDTO getProfil(@PathVariable("id") final UUID id) {
        Optional<Profil> profil = profilService.getProfil(id);
        if(profil.isPresent()) {
            return modelMapper.map(profil.get(), ProfilDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Read - Get one profil
     * @param authId The id of the profil
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/auth/{authId}")
    public ProfilDTO getProfilByAuthId(@PathVariable("authId") final String authId) {
        Optional<Profil> profil = profilService.getProfilByAuthId(authId);
        if(profil.isPresent()) {
            return modelMapper.map(profil.get(), ProfilDTO.class);
        } else {
            return null;
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
     * @param id - The id of the profil to update
     * @param profil - The profil object updated
     * @return
     */
    @PutMapping("/profil/update/{id}")
    public Profil updateProfil(@PathVariable("id") final UUID id, @RequestBody Profil profil) {
        System.out.println("profil" + profil.toString());
        Optional<Profil> e = profilService.getProfil(id);
        if (e.isPresent()) {
            Profil currentProfil = e.get();
            currentProfil.setAuthId(profil.getAuthId());
            currentProfil.setUsername(profil.getUsername());
            currentProfil.setDescription(profil.getDescription());
            currentProfil.setFavoriteLocations(profil.getFavoriteLocations());

            String username = profil.getEmail();
            if (currentProfil.getEmail() != profil.getEmail()) {
                // service to change email to auth0
                currentProfil.setEmail(profil.getEmail());
            }

                profilService.saveProfil(currentProfil);
                return currentProfil;

            } else {
                return null;
            }
        }

    /**
     * Delete - Delete an profil
     * @param id - The id of the profil to delete
     */
    @DeleteMapping("/profil/delete/{id}")
    public void deleteProfil(@PathVariable("id") final UUID id) {
        profilService.deleteProfil(id);
    }





}
