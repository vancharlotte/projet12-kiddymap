package com.kiddymap.microservicelocation;

import com.kiddymap.microservicelocation.controller.EquipmentRestController;
import com.kiddymap.microservicelocation.controller.LocationRestController;
import com.kiddymap.microservicelocation.service.contrat.EquipmentService;
import com.kiddymap.microservicelocation.service.contrat.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MicroserviceLocationApplicationTests {




	@Test
	public void contextLoads() {
		assertNotNull(LocationRestController.class);
		assertNotNull(EquipmentRestController.class);
		assertNotNull(LocationService.class);
		assertNotNull(EquipmentService.class);
	}

}
