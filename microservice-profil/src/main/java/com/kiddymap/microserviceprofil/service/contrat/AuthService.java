package com.kiddymap.microserviceprofil.service.contrat;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {


    ResponseEntity<JSONObject> getTokenAuth();

    ResponseEntity<String> updateEmail(String authId, String email);

    ResponseEntity<String> addRoleToUser(String id);
}
