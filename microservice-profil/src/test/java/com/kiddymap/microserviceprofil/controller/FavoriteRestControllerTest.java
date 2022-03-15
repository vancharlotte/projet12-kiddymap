package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.LocationServiceImpl;
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
public class FavoriteRestControllerTest {

    @InjectMocks
    private FavoriteRestController favoriteRestController;

    @Mock
    private ProfilServiceImpl profilServiceMock;

    @Mock
    private LocationServiceImpl locationServiceMock;

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
    public void addProfilFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        Mockito.when(profilServiceMock.updateProfilFavorite(location,profil)).thenReturn(profil);

       // assertEquals(profil, favoriteRestController.addProfilFavorite(location.getId(),profil));
    }

    @Test
    public void addProfilFavoriteTest_profilReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));

       // assertNull(favoriteRestController.addProfilFavorite(location.getId(),profil));
    }

    @Test
    public void addProfilFavoriteTest_locationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

       // assertNull(favoriteRestController.addProfilFavorite(location.getId(),profil));
    }


    @Test
    public void addProfilFavoriteTest_profilAndLocationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

      //  assertNull(favoriteRestController.addProfilFavorite(location.getId(),profil));
    }

    @Test
    public void deleteProfilFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));

        Mockito.when(profilServiceMock.deleteProfilFavorite(location,profil)).thenReturn(profil);

//        assertEquals(profil, favoriteRestController.deleteProfilFavorite(location.getId(),profil));


    }

    @Test
    public void deleteProfilFavoriteTest_profilReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));

      //  assertNull(favoriteRestController.deleteProfilFavorite(location.getId(),profil));

    }

    @Test
    public void deleteProfilFavoriteTest_locationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

        // assertNull(favoriteRestController.deleteProfilFavorite(location.getId(),profil));
    }

    @Test
    public void deleteProfilFavoriteTest_profilAndLocationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

       // assertNull(favoriteRestController.deleteProfilFavorite(location.getId(),profil));
    }

    @Test
    public void getProfilAllFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.getAllFavorites(profil.getId())).thenReturn(profil.getFavoriteLocations());

     //   assertEquals(profil.getFavoriteLocations(), favoriteRestController.getProfilAllFavorite(profil.getId(),profil));

    }

    @Test
    public void getProfilAllFavoriteTest_returnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
       // assertNull(favoriteRestController.getProfilAllFavorite(profil.getId(),profil));

    }


}
