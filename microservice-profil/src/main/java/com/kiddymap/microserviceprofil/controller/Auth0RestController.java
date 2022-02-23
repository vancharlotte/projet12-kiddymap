package com.kiddymap.microserviceprofil.controller;

import com.kiddymap.microserviceprofil.service.impl.AuthServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class Auth0RestController {

    @Autowired
    AuthServiceImpl authService;

    @GetMapping("/profil/auth/add/signup")
    public ResponseEntity<String> accessAuth(@RequestBody JSONObject ob) {
       // JSONObject ob = new JSONObject();
        // ob.put("email", "test@email.com");
        //  ob.put("password", "Admin123!");

        ob.put("client_id", "4xx8AmoU9nEEbWWWOQJpli8IRxuzr0p4");
        ob.put("connection", "Username-Password-Authentication");

        String url = "https://dev-kiddymap.eu.auth0.com/dbconnections/signup";
        System.out.println(ob.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(ob.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url,httpEntity, String.class);
        System.out.println(response.getBody());
        return response;

    }

    @PostMapping("/profil/auth/add/roles")
    public ResponseEntity<String> addRoleToUser(@RequestBody String id) {

        ResponseEntity<JSONObject> token = authService.getTokenAuth();
        token.getBody().get("access_token");
        String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/" + id + "/roles";
        System.out.println(token.getBody().get("access_token"));

    String role = "{\n" +
            "  \"roles\": [\n" +
            "    \"rol_i5CMLwfMDGx1xBQG\"\n" +
            "  ]\n" +
            "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getBody().get("access_token").toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(role, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
        return response;

    }

    @GetMapping("/profil/auth/get/allUsers")
    public ResponseEntity<String> getAllAuth() {

        ResponseEntity<JSONObject> ob = authService.getTokenAuth();
        ob.getBody().get("access_token");
        String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ob.getBody().get("access_token").toString());



        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
        return response;

    }







}


