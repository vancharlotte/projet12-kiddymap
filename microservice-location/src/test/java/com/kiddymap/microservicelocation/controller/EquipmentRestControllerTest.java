package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
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
public class EquipmentRestControllerTest {

    @InjectMocks
    private EquipmentRestController equipmentRestController;

    @Mock
    private EquipmentServiceImpl equipmentServiceMock;


    private static Equipment equipment;

    private static List<Equipment> equipments;

    @BeforeAll
    public static void createListEquipments() {
        equipment = new Equipment();
        equipment.setId(UUID.randomUUID());
        equipment.setName("equipment");


        equipments = new ArrayList<>();
        equipments.add(equipment);

    }

    @Test
    void createEquipmentTest(){
        Mockito.when(equipmentServiceMock.saveEquipment(equipment)).thenReturn(equipment);
      //  assertEquals(equipment, equipmentRestController.createEquipment(equipment));
    }

    @Test
    void getEquipmentTest(){
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.of(equipment));
        assertEquals(equipment, equipmentRestController.getEquipment(equipment.getId()));
    }

    @Test
    void getEquipmentTest_returnNull(){
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.empty());
        assertNull(equipmentRestController.getEquipment(equipment.getId()));
    }

    @Test
    void getAllEquipmentsTest(){
        Mockito.when(equipmentServiceMock.getAllEquipments()).thenReturn(equipments);
        assertEquals(equipments, equipmentRestController.getAllEquipments());
    }

    @Test
    void updateEquipmentTest(){
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.of(equipment));
        Mockito.when(equipmentServiceMock.saveEquipment(equipment)).thenReturn(equipment);
       // assertEquals(equipment, equipmentRestController.updateEquipment(equipment.getId(),equipment));
    }

    @Test
    void updateEquipmentTest_returnNull(){
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.empty());
      //  assertNull(equipmentRestController.updateEquipment(equipment.getId(),equipment));
    }





}
