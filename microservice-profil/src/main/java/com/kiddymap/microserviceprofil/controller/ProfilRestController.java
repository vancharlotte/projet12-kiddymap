package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    @PostMapping("/profil")
    public Profil createProfil(@RequestBody Profil profil) {
        return profilService.saveProfil(profil);
    }


    /**
     * Read - Get one profil
     * @param id The id of the profil
     * @return A profil object full filled
     */
    @GetMapping("/profil/get/{id}")
    public ProfilDTO getProfil(@PathVariable("id") final UUID id) {
        Optional<Profil> profil = profilService.getProfil(id);
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
    @PutMapping("/profil/{id}")
    public Profil updateProfil(@PathVariable("id") final UUID id, @RequestBody Profil profil) {
        Optional<Profil> e = profilService.getProfil(id);
        if (e.isPresent()) {
            Profil currentProfil = e.get();

            String username = profil.getUsername();
            if (username != null) {
                currentProfil.setUsername(username);
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
    @DeleteMapping("/profil/{id}")
    public void deleteProfil(@PathVariable("id") final UUID id) {
        profilService.deleteProfil(id);
    }




}
