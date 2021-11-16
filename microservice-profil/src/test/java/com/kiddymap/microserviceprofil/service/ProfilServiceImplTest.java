package com.kiddymap.microserviceprofil.service;

import com.kiddymap.microserviceprofil.dao.ProfilDao;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfilServiceImplTest {

    @InjectMocks
    private ProfilServiceImpl profilService;

    @Mock
    private ProfilDao profilDaoMock;

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
    void getProfilTest(){
        Mockito.when(profilDaoMock.findById(profil.getId())).thenReturn(Optional.of(profil));
        assertEquals(profil,profilService.getProfil(profil.getId()).get());

    }

    @Test
    void updateProfilTest(){
        Mockito.when(profilDaoMock.save(profil)).thenReturn(profil);
        assertEquals(profil,profilService.updateProfil(profil));

    }

    @Test
    void deleteProfilTest(){
        Profil profil2 = new Profil();
        profil2.setId(UUID.randomUUID());
        profil2.setUsername("profil2");
        profil2.setDescription("description1");
        profilService.deleteProfil(profil2.getId());
        Mockito.verify(profilDaoMock).deleteById(profil2.getId());

    }

    @Test
    void saveProfilTest(){
        Mockito.when(profilDaoMock.save(profil)).thenReturn(profil);
        assertEquals(profil,profilService.saveProfil(profil));

    }

    @Test
    public void updateProfilFavorite(){
        Mockito.when(profilDaoMock.save(profil)).thenReturn(profil);
        assertEquals(profil,profilService.updateProfilFavorite(location, profil));

    }

    @Test
    public void deleteProfilFavoriteTest(){
        Mockito.when(profilDaoMock.save(profil)).thenReturn(profil);
        assertEquals(profil,profilService.deleteProfilFavorite(location, profil));
    }

    @Test
    public void getAllFavoritesTest(){
        Mockito.when(profilDaoMock.findById(profil.getId())).thenReturn(Optional.of(profil));
        assertEquals(profil.getFavoriteLocations(),profilService.getAllFavorites(profil.getId()));

    }

}
