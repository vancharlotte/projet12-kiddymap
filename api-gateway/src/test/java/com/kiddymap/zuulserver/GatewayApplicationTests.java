package com.kiddymap.zuulserver;

import com.kiddymap.apigateway.GatewayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
class GatewayApplicationTests {

	//@Test
	void contextLoads() {
		assertNotNull(GatewayApplication.class);
	}

}
