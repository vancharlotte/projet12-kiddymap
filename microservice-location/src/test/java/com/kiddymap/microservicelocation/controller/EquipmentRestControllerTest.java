package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.controller.dto.EquipmentDTO;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EquipmentRestControllerTest {

    @InjectMocks
    private EquipmentRestController equipmentRestController;

    @Mock
    private EquipmentServiceImpl equipmentServiceMock;

    @Mock
    private ModelMapper modelMapperMock;

    private static Equipment equipment;

    private static EquipmentDTO equipmentDTO;

    private static List<Equipment> equipments;

    @BeforeAll
    public static void createListEquipments() {
        equipment = new Equipment();
        equipment.setId(UUID.randomUUID());
        equipment.setName("equipment");

        equipmentDTO = new EquipmentDTO();
        equipmentDTO.setId(UUID.randomUUID());
        equipmentDTO.setName("equipmentDTO");


        equipments = new ArrayList<>();
        equipments.add(equipment);

    }

    @Test
    void createEquipmentTest() {
        Mockito.when(modelMapperMock.map(equipmentDTO, Equipment.class)).thenReturn(equipment);
        Mockito.when(equipmentServiceMock.saveEquipment(equipment)).thenReturn(equipment);
        assertEquals(equipment, equipmentRestController.createEquipment(equipmentDTO));
    }

    @Test
    void getEquipmentTest() {
        Mockito.when(modelMapperMock.map(equipment, EquipmentDTO.class)).thenReturn(equipmentDTO);
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.of(equipment));
        assertEquals( equipmentDTO, equipmentRestController.getEquipment(equipment.getId()));
    }

    @Test
    void getEquipmentTest_returnNull() {
        Mockito.when(equipmentServiceMock.getEquipment(equipment.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            equipmentRestController.getEquipment(equipment.getId());         });
}



    @Test
    void updateEquipmentTest(){
        Mockito.when(equipmentServiceMock.getEquipment(equipmentDTO.getId())).thenReturn(Optional.of(equipment));
        Mockito.when(equipmentServiceMock.saveEquipment(equipment)).thenReturn(equipment);
        assertEquals(equipment, equipmentRestController.updateEquipment(equipmentDTO.getId(),equipmentDTO));
    }

    @Test
    void updateEquipmentTest_returnNull(){
        Mockito.when(equipmentServiceMock.getEquipment(equipmentDTO.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            equipmentRestController.updateEquipment(equipmentDTO.getId(),equipmentDTO);         });

    }





}
