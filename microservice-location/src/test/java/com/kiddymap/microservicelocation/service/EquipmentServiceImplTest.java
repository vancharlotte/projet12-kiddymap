package com.kiddymap.microservicelocation.service;

import com.kiddymap.microservicelocation.dao.EquipmentDao;
import com.kiddymap.microservicelocation.model.Equipment;
import com.kiddymap.microservicelocation.service.contrat.EquipmentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceImplTest {

    @InjectMocks
    private EquipmentService equipmentService;

    @Mock
    private EquipmentDao equipmentDaoMock;

    @BeforeAll
    public static void createListEquipments() {
        Equipment equipment1 = new Equipment();
        equipment1.setName("equipment1");

        Equipment equipment2 = new Equipment();
        equipment2.setName("equipment2");

    }

    @Test
    void getEquipmentTest(){
    }

    @Test
    void getAllEquipmentsTest(){
    }

    @Test
    void deleteEquipmentTest(){
    }

    @Test
    void saveEquipmentTest(){
    }
}
