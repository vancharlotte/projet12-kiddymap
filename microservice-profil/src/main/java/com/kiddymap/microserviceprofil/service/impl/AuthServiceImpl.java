package com.kiddymap.microserviceprofil.service.impl;

import com.kiddymap.microserviceprofil.service.contrat.AuthService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    RestTemplate restTemplate;



    @Override
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
           return restTemplate.exchange(url, HttpMethod.POST, httpEntity, JSONObject.class);


    }

    @Override
    public ResponseEntity<String> updateEmail(String authId, String email) {
            ResponseEntity<JSONObject> token = getTokenAuth();
            String url = "https://dev-kiddymap.eu.auth0.com/api/v2/users/" + authId;

            String body = "{\n" +
                    "  \"email\": \"" + email + " \"\n" +
                    "}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        if (token.hasBody()){
            headers.setBearerAuth(token.getBody().get("access_token").toString());}

            HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);

            return restTemplate.exchange(url, HttpMethod.PATCH, httpEntity, String.class);

    }

    @Override
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
            if (token.hasBody()){
            headers.setBearerAuth(token.getBody().get("access_token").toString());}

            HttpEntity<String> httpEntity = new HttpEntity<>(role, headers);
            return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);


    }

}
