package com.kiddymap.microservicelocation.service;

import com.kiddymap.microservicelocation.dao.EquipmentDao;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.impl.EquipmentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EquipmentServiceImplTest {

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @Mock
    private EquipmentDao equipmentDaoMock;

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
/*
    @Test
    void getEquipmentTest(){
        Mockito.when(equipmentDaoMock.findById(equipment.getId())).thenReturn(Optional.of(equipment));
        assertEquals(equipment , equipmentService.getEquipment(equipment.getId()).get());
    }

    @Test
    void getAllEquipmentsTest(){
        Mockito.when(equipmentDaoMock.findAll()).thenReturn(equipments);
        assertEquals(equipments, equipmentService.getAllEquipments());
    }

    @Test
    void deleteEquipmentTest(){
        equipmentService.deleteEquipment(equipment.getId());
        Mockito.verify(equipmentDaoMock).deleteById(equipment.getId());

    }

    @Test
    void saveEquipmentTest(){
        Mockito.when(equipmentDaoMock.save(equipment)).thenReturn(equipment);
        assertEquals(equipment, equipmentService.saveEquipment(equipment));
    }*/
}
