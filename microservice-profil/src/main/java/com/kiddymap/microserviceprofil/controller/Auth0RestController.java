package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.service.impl.AuthServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@Slf4j
public class Auth0RestController {

    @Autowired
    AuthServiceImpl authService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/profil/auth/add/signup")
    public ResponseEntity<String> accessAuth(@RequestBody JSONObject ob) {
        try {
            ob.put("client_id", "4xx8AmoU9nEEbWWWOQJpli8IRxuzr0p4");
            ob.put("connection", "Username-Password-Authentication");

            String url = "https://dev-kiddymap.eu.auth0.com/dbconnections/signup";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>(ob.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
            log.info(response.getBody());
            return response;
        } catch (Exception e) {
            throw e;
        }
    }


    @GetMapping("/profil/auth/get/allUsers")
    public ResponseEntity<String> getAllAuth() {
        try {
            ResponseEntity<JSONObject> ob = authService.getTokenAuth();
            ob.getBody().get("access_token");
            String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(ob.getBody().get("access_token").toString());

            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            log.info(response.getBody());
            return response;

        } catch (Exception e) {
            throw e;
        }

    }


}


