package com.kiddymap.microservicelocation.controller;

import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.contrat.EquipmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EquipmentRestControllerTest {

    @InjectMocks
    private EquipmentRestController equipmentRestController;

    @Mock
    private EquipmentService equipmentServiceMock;

    @BeforeAll
    public static void createListEquipments() {
        Equipment equipment1 = new Equipment();
        equipment1.setName("equipment1");

        Equipment equipment2 = new Equipment();
        equipment2.setName("equipment2");

    }

    @Test
    void createEquipmentTest(){
    }

    @Test
    void getEquipmentTest(){
    }

    @Test
    void getAllEquipmentsTest(){

    }

    @Test
    void updateEquipmentTest(){
    }

    @Test
    void deleteEquipmentTest(){
    }

    @Test
    void saveEquipmentTest(){
    }


}
