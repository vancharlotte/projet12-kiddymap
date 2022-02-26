package com.kiddymap.microserviceprofil.service.impl;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class AuthServiceImpl {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<JSONObject> getTokenAuth() {
        JSONObject ob = new JSONObject();
        ob.put("grant_type", "client_credentials");
        ob.put("client_id", "U7zYUrCjRg1chznumlF1GrBnnzKkyHpD");
        ob.put("client_secret", "xKuiZiJW4YXeY_HEpdfQVfeKG0kHPhAuNqW_4cZU9Ejc_2EvYqx8KwwTqBzZWiSx");
        ob.put("audience", "https://dev-kiddymap.eu.auth0.com/api/v2/");

        String url = "https://dev-kiddymap.eu.auth0.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(ob, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, JSONObject.class);

        System.out.println(response.getBody());
        return response;
    }

    public ResponseEntity<String> updateEmail(String authId, String email) {

        ResponseEntity<JSONObject> token = getTokenAuth();
        String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/" + authId;

        System.out.println(email);
        String body = "{\n" +
                "  \"email\": \"" + email + " \"\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getBody().get("access_token").toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, httpEntity, String.class);
        System.out.println(response.getBody());
        return response;
    }

    public ResponseEntity<String> addRoleToUser(String id) {

        ResponseEntity<JSONObject> token = getTokenAuth();
        String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/" + id + "/roles";

        String role = "{\n" +
                "  \"roles\": [\n" +
                "    \"rol_i5CMLwfMDGx1xBQG\"\n" +
                "  ]\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getBody().get("access_token").toString());

        HttpEntity<String> httpEntity = new HttpEntity<>(role, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
        return response;

    }

}