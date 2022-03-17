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



	@Autowired
	private LocationRestController locationRestController;

	@Autowired
	private EquipmentRestController equipmentRestController;

	@Autowired
	private LocationService locationService;

	@Autowired
	private EquipmentService equipmentService;

	@Test
	public void contextLoads() {
		assertNotNull(locationRestController);
		assertNotNull(equipmentRestController);
		assertNotNull(locationService);
		assertNotNull(equipmentService);
	}

}
