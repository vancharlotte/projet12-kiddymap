package com.kiddymap.microserviceprofil.service;

import com.kiddymap.microserviceprofil.dao.LocationDao;
import com.kiddymap.microserviceprofil.model.Location;
import com.kiddymap.microserviceprofil.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private LocationDao locationDaoMock;

    private static Location location;


    @BeforeAll
    public static void setUp() {
        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location");
        location.setDescription("description");

    }

    @Test
    public void getLocationTest(){
        Mockito.when(locationDaoMock.findById(location.getId())).thenReturn(Optional.of(location));
        assertEquals(location , locationService.getLocation(location.getId()).get());


    }
}
