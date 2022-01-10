package com.kiddymap.apigateway;

import io.netty.handler.logging.LogLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;


@SpringBootApplication
@EnableEurekaClient
@Configuration
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	@Bean
	@CrossOrigin("*")
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("microservice-profil", r -> r.path("/profil/**")
						.uri("lb://microservice-profil"))
				.route("microservice-location", r -> r.path("/location/**","/equipment/**")
						.uri("lb://microservice-location"))
				.build();


	}

	@Bean
	HttpClient httpClient() {
		return HttpClient.create().wiretap("LoggingFilter", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
	}
}
