package com.kiddymap.microserviceprofil.service;

import com.kiddymap.microserviceprofil.dao.ProfilDao;
import com.kiddymap.microserviceprofil.model.Profil;
import com.kiddymap.microserviceprofil.service.contrat.ProfilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProfilServiceImplTest {

    @InjectMocks
    private ProfilService profilService;

    @Mock
    private ProfilDao profilDaoMock;

    @Test
    void newProfilTest(){
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

    @Test
    void saveProfilTest(){
    }


}