package com.kiddymap.microserviceprofil;

import com.kiddymap.microserviceprofil.controller.FavoriteRestController;
import com.kiddymap.microserviceprofil.controller.ProfilRestController;
import com.kiddymap.microserviceprofil.service.contrat.AuthService;
import com.kiddymap.microserviceprofil.service.contrat.LocationService;
import com.kiddymap.microserviceprofil.service.contrat.ProfilService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MicroserviceProfilApplicationTests {




	@Test
	public void contextLoads() {
		assertNotNull(ProfilRestController.class);
		assertNotNull(FavoriteRestController.class);
		assertNotNull(ProfilService.class);
		assertNotNull(LocationService.class);
		assertNotNull(AuthService.class);

	}
}
