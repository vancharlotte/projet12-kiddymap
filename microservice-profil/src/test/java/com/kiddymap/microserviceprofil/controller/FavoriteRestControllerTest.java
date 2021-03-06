package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.LocationDTO;
import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.model.Favorite;
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
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteRestControllerTest {

    @InjectMocks
    private FavoriteRestController favoriteRestController;

    @Mock
    private ProfilServiceImpl profilServiceMock;

    @Mock
    private LocationServiceImpl locationServiceMock;

    @Mock
    private ModelMapper modelMapperMock;

    private static Profil profil;
    private static ProfilDTO profilDTO;

    private static Location location;
    private static List<Location> favorites;

    private static Favorite favorite;



    @BeforeAll
    public static void setUp() {
        profil = new Profil();
        profil.setId(UUID.randomUUID());
        profil.setAuthId("abcd");
        profil.setUsername("profil");
        profil.setDescription("description1");

        profilDTO = new ProfilDTO();
        profilDTO.setId(UUID.randomUUID());
        profilDTO.setAuthId("abcd");
        profilDTO.setUsername("profil");
        profilDTO.setDescription("description1");

        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");


        favorites = new ArrayList<>();
        favorites.add(location);

        profil.setFavoriteLocations(favorites);

        favorite = new Favorite();
        favorite.setProfilId(profil.getId());
        favorite.setLocationId(location.getId());

    }

    @Test
    public void addProfilFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when(profilServiceMock.updateProfilFavorite(location,profil)).thenReturn(profil);

        assertEquals(profil, favoriteRestController.addProfilFavorite(location.getId(),profilDTO));
    }

    @Test
    public void addProfilFavoriteTest_profilReturnNull() {
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.addProfilFavorite(location.getId(), profilDTO);
        });
    }

    @Test
    public void addProfilFavoriteTest_locationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.addProfilFavorite(location.getId(), profilDTO);
        });
    }


    @Test
    public void addProfilFavoriteTest_profilAndLocationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.addProfilFavorite(location.getId(), profilDTO);
        });
    }

    @Test
    public void addProfilFavoriteTest_profilNotVerified(){
        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.addProfilFavorite(location.getId(),profilDTO);});
    }


    @Test
    public void deleteProfilFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when(profilServiceMock.deleteProfilFavorite(location,profil)).thenReturn(profil);

        assertEquals(profil, favoriteRestController.deleteProfilFavorite(location.getId(),profilDTO));
    }

    @Test
    public void deleteProfilFavoriteTest_profilReturnNull() {
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.deleteProfilFavorite(location.getId(), profilDTO);
        });
    }

    @Test
    public void deleteProfilFavoriteTest_locationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.of(profil));
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.deleteProfilFavorite(location.getId(), profilDTO);
        });
    }


    @Test
    public void deleteProfilFavoriteTest_profilAndLocationReturnNull(){
        Mockito.when(profilServiceMock.getProfil(profilDTO.getId())).thenReturn(Optional.empty());
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.deleteProfilFavorite(location.getId(), profilDTO);
        });
    }

    @Test
    public void deleteProfilFavoriteTest_profilNotVerified(){

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.deleteProfilFavorite(location.getId(),profilDTO);});
    }


    @Test
    public void getProfilAllFavoriteTest(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.getAllFavorites(profil.getId())).thenReturn(profil.getFavoriteLocations());
        Mockito.when(modelMapperMock.map(favorites, new TypeToken<List<LocationDTO>>() {
        }.getType())).thenReturn(favorites);

        assertEquals(favorites, favoriteRestController.getProfilAllFavorite(profil.getId(),profilDTO));

    }

    @Test
    public void getProfilAllFavoriteTest_returnNull(){
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.getProfilAllFavorite(profil.getId(),profilDTO);});
    }

    @Test
    public void existProfilFavorite(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when( profilServiceMock.getProfilByAuthId("abcd")).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.existFavorite(profil.getId(), location.getId())).thenReturn(Optional.of(favorite));

        assertTrue(favoriteRestController.existProfilFavorite(location.getId()));
    }

    @Test
    public void existProfilFavorite_FavoriteNotExist(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when( profilServiceMock.getProfilByAuthId("abcd")).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.existFavorite(profil.getId(), location.getId())).thenReturn(Optional.empty());

        assertFalse(favoriteRestController.existProfilFavorite(location.getId()));
    }

    @Test
    public void existProfilFavorite_ProfilNull(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when( profilServiceMock.getProfilByAuthId("abcd")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            favoriteRestController.existProfilFavorite(location.getId());});    }


}
