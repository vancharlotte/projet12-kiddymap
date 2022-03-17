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

	@Autowired
	private ProfilRestController profilRestController;

	@Autowired
	private FavoriteRestController favoriteRestController;

	@Autowired
	private ProfilService profilService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private AuthService authService;

	@Test
	public void contextLoads() {
		assertNotNull(profilRestController);
		assertNotNull(favoriteRestController);
		assertNotNull(profilService);
		assertNotNull(locationService);
		assertNotNull(authService);

	}
}
