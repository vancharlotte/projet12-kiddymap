package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.LocationDTO;
import com.kiddymap.microservicelocation.controller.dto.LocationIncompleteDTO;
import com.kiddymap.microservicelocation.exception.LocationNotFoundException;
import com.kiddymap.microservicelocation.model.Location;
import com.kiddymap.microservicelocation.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocationRestControllerTest {

    @InjectMocks
    private LocationRestController locationRestController;

    @Mock
    private LocationServiceImpl locationServiceMock;

    @Mock
    private ModelMapper modelMapperMock;

    private static Location location;
    private static Location locationAdd;

    private static LocationDTO locationDTO;
    private static LocationIncompleteDTO locationIncompleteDTO;
    private static List<Location> locations;

    @BeforeAll
    public static void createListLocations() {
        location = new Location();
        location.setId(UUID.randomUUID());
        location.setName("location1");
        location.setDescription("description1");

        locations = new ArrayList<>();
        locations.add(location);

        locationAdd = new Location();
        locationAdd.setName("locationIncompleteDTO");
        locationAdd.setLatitude(1.0f);
        locationAdd.setLongitude(1.0f);
        locationAdd.setDescription("descriptionIncompleteDTO");
        locationAdd.setEquipments(Collections.emptyList());


        locationDTO = new LocationDTO();
        locationDTO.setId(UUID.randomUUID());
        locationDTO.setName("locationDTO");
        locationDTO.setDescription("descriptionDTO");

        locationIncompleteDTO = new LocationIncompleteDTO();
        locationIncompleteDTO.setName("locationIncompleteDTO");
        locationIncompleteDTO.setLatitude(1.0f);
        locationIncompleteDTO.setLongitude(1.0f);
        locationIncompleteDTO.setDescription("descriptionIncompleteDTO");


    }

    @Test
    void createLocationTest(){
        Mockito.when(locationServiceMock.saveLocation(locationAdd)).thenReturn(locationAdd);
        assertEquals(locationAdd, locationRestController.createLocation(locationIncompleteDTO));
    }

    @Test
    void getLocationTest(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.ofNullable(location));
        Mockito.when(modelMapperMock.map(location, LocationDTO.class)).thenReturn(locationDTO);
        assertEquals(locationDTO, locationRestController.getLocation(location.getId()));
    }

    @Test
    void getLocationTest_returnNull(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  locationRestController.getLocation(location.getId());      });    }


    @Test
    void updateLocationTest(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        Mockito.when(locationServiceMock.saveLocation(location)).thenReturn(location);
        assertEquals(location, locationRestController.updateLocation(location.getId(),locationIncompleteDTO));
    }

    @Test
    void updateLocationTest_returnNull(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  locationRestController.updateLocation(location.getId(),locationIncompleteDTO);      });
    }

    @Test
    void deleteLocationTest(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.of(location));
        locationRestController.deleteLocation(location.getId());
        Mockito.verify(locationServiceMock).deleteLocation(location.getId());

    }

    @Test
    void deleteLocationTest_returnNull(){
        Mockito.when(locationServiceMock.getLocation(location.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {  locationRestController.deleteLocation(location.getId());      });

    }



}
