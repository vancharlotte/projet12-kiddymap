package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ProfilRestControllerTest {

    @InjectMocks
    private ProfilRestController profilRestController;

    @Mock
    private ProfilServiceImpl profilServiceMock;

    private static Profil profil;

    private static Location location;

    @BeforeAll
    public static void setUp() {
        profil = new Profil();
        profil.setId(UUID.randomUUID());
        profil.setUsername("profil");
        profil.setDescription("description1");

        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

        List<Location> favorites = new ArrayList<>();
        favorites.add(location);


        profil.setFavoriteLocations(favorites);

    }


    @Test
    void createProfilTest(){
        Mockito.when(profilServiceMock.saveProfil(profil)).thenReturn(profil);
        assertEquals(profil,profilRestController.createProfil(profil));
    }

  /*  @Test
    void getProfilTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        assertEquals(profil,profilRestController.getProfil(profil.getId()));
    }

    @Test
    void getProfilTest_returnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        assertNull(profilRestController.getProfil(profil.getId()));
    }*/

    @Test
    void updateProfilTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.saveProfil(profil)).thenReturn(profil);
        assertEquals(profil,profilRestController.updateProfil(profil.getId(),profil));

    }

    @Test
    void updateProfilTest_returnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        assertNull(profilRestController.updateProfil(profil.getId(),profil));

    }

    @Test
    void deleteProfilTest(){
        profilRestController.deleteProfil(profil.getId());
        Mockito.verify(profilServiceMock).deleteProfil(profil.getId());
    }
}
