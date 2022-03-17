package com.kiddymap.microserviceprofil.service;

import com.kiddymap.microserviceprofil.dao.FavoriteDao;
import com.kiddymap.microserviceprofil.dao.ProfilDao;
import com.kiddymap.microserviceprofil.model.Favorite;
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

    @Mock
    private FavoriteDao favoriteDaoMock;

    private static Profil profil;

    private static Location location;

    private static Favorite favorite;


    @BeforeAll
    public static void setUp() {
        profil = new Profil();
        profil.setId(UUID.randomUUID());
        profil.setAuthId("abcd");
        profil.setUsername("profil");
        profil.setDescription("description1");

        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

        List<Location> favorites = new ArrayList<>();
        favorites.add(location);

        profil.setFavoriteLocations(favorites);

        favorite = new Favorite();
        favorite.setProfilId(profil.getId());
        favorite.setLocationId(location.getId());


    }


    @Test
    void getProfilTest(){
        Mockito.when(profilDaoMock.findById(profil.getId())).thenReturn(Optional.of(profil));
        assertEquals(profil,profilService.getProfil(profil.getId()).get());

    }

    @Test
    void getProfilByAuthIdTest(){
        Mockito.when(profilDaoMock.findByAuthId(profil.getAuthId())).thenReturn(Optional.of(profil));
        assertEquals(profil,profilService.getProfilByAuthId(profil.getAuthId()).get());

    }

    @Test
    void updateProfilTest(){
        Mockito.when(profilDaoMock.save(profil)).thenReturn(profil);
        assertEquals(profil,profilService.updateProfil(profil));

    }

    @Test
    void deleteProfilTest(){
        profilService.deleteProfil(profil.getId());
        Mockito.verify(profilDaoMock).deleteById(profil.getId());

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

    @Test
    public void getAllFavoritesTest_returnEmpty(){
        Mockito.when(profilDaoMock.findById(profil.getId())).thenReturn(Optional.empty());
        assertEquals(Collections.emptyList(), profilService.getAllFavorites(profil.getId()));

    }

    @Test
    public void existFavorite(){
        Mockito.when(favoriteDaoMock.findByProfilIdAndLocationId(profil.getId(), location.getId())).thenReturn(Optional.of(favorite));
        assertEquals(Optional.of(favorite), profilService.existFavorite(profil.getId(),location.getId()));

    }

    @Test
    public void existFavorite_returnEmpty(){
        Mockito.when(favoriteDaoMock.findByProfilIdAndLocationId(profil.getId(), location.getId())).thenReturn(Optional.empty());
        assertEquals(Optional.empty(),profilService.existFavorite(profil.getId(),location.getId()));

    }

}
