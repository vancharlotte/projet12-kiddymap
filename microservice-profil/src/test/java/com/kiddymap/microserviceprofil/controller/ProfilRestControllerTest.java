package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.controller.dto.ProfilDTO;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.impl.AuthServiceImpl;
import com.kiddymap.microserviceprofil.service.impl.ProfilServiceImpl;
import com.nimbusds.jwt.JWT;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProfilRestControllerTest {

    @InjectMocks
    private ProfilRestController profilRestController;

    @Mock
    private ProfilServiceImpl profilServiceMock;

    @Mock
    private ModelMapper modelMapperMock;


    private static Profil profil;

    private static ProfilDTO profilDTO;

    private static Location location;

    @BeforeAll
    public static void setUp() {
        profil = new Profil();
        profil.setId(UUID.randomUUID());
        profil.setAuthId("abcd");
        profil.setUsername("profil");
        profil.setEmail("email");
        profil.setDescription("à compléter");

        profilDTO = new ProfilDTO();
        profilDTO.setId(UUID.randomUUID());
        profilDTO.setAuthId("abcd");
        profilDTO.setUsername("profil");
        profilDTO.setEmail("email");
        profilDTO.setDescription("à compléter");

        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

        List<Location> favorites = new ArrayList<>();
        favorites.add(location);


        profil.setFavoriteLocations(favorites);

    }

/*
    @Test
    void createProfilTest_exist(){
        Mockito.when( profilServiceMock.getProfilByAuthId(profilDTO.getAuthId())).thenReturn(Optional.of(profil));
        assertEquals(profil,profilRestController.createProfil(profilDTO));
    }
*/
/*    @Test
    void createProfilTest_new(){
        Mockito.when( profilServiceMock.getProfilByAuthId(profilDTO.getAuthId())).thenReturn(Optional.empty());

        Profil profil2 = new Profil();
        profil2.setAuthId(profilDTO.getAuthId());
        profil2.setUsername(profilDTO.getUsername());
        profil2.setEmail(profilDTO.getEmail());
        profil2.setDescription(profilDTO.getDescription());

        Mockito.when(profilServiceMock.saveProfil(profil2)).thenReturn(profil);
        assertEquals(profil,profilRestController.createProfil(profilDTO));
    }*/

 /*  @Test
    void getProfilTest(){
       Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
       Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");

        Mockito.when(modelMapperMock.map(profil, ProfilDTO.class)).thenReturn(profilDTO);
        assertEquals(profilDTO,profilRestController.getProfil(profil.getId()));
    }

    @Test
    void getProfilTest_returnNotVerified(){
        assertThrows(ResponseStatusException.class, () -> {  profilRestController.getProfil(profil.getId());});
    }

    @Test
    void getProfilTest_returnNull(){
       // Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");
        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {  profilRestController.getProfil(profil.getId());});
    }

    @Test
    void getProfilByAuthIdTest(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");

        Mockito.when(profilServiceMock.getProfilByAuthId("abcd")).thenReturn(Optional.of(profil));
        Mockito.when(modelMapperMock.map(profil, ProfilDTO.class)).thenReturn(profilDTO);

        assertEquals(profilDTO,profilRestController.getProfilByAuthId());
    }


    @Test
    void getProfilByAuthIdTest_returnNotVerified(){
        assertThrows(ResponseStatusException.class, () -> {  profilRestController.getProfilByAuthId();});
    }

    @Test
    void getProfilByAuthIdTest_returnNull(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");

        Mockito.when(profilServiceMock.getProfilByAuthId("abcd")).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  profilRestController.getProfilByAuthId();});
    }



    @Test
    void updateProfilTest(){

        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");

        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.of(profil));
        Mockito.when(profilServiceMock.saveProfil(profil)).thenReturn(profil);
        assertEquals(profil,profilRestController.updateProfil(profil.getId(),profilDTO));

    }

    @Test
    void updateProfilTest_returnNull(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("abcd");

        Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  profilRestController.updateProfil(profil.getId(),profilDTO);});

    }

    @Test
    void updateProfilTest_returnNotVerified(){
        Mockito.when(profilServiceMock.getAuthIdFromToken()).thenReturn("efgh");

       // Mockito.when(profilServiceMock.getProfil(profil.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  profilRestController.updateProfil(profil.getId(), profilDTO);});

    }
*/
  /*  @Test
    void deleteProfilTest(){
        profilRestController.deleteProfil(profil.getId());
        Mockito.verify(profilServiceMock).deleteProfil(profil.getId());
    }*/
}
