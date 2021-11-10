package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.dao.ProfilDao;
import com.kiddymap.microserviceprofil.service.contrat.ProfilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProfilRestControllerTest {

    @InjectMocks
    private ProfilRestController profilRestController;

    @Mock
    private ProfilService profilServiceMock;

    @Test
    void createProfilTest(){
    }

    @Test
    void getProfilTest(){

    }

    @Test
    void updateProfilTest(){
    }

    @Test
    void deleteProfilTest(){

    }
}
