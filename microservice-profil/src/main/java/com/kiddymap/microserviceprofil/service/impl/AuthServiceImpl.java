package com.kiddymap.microserviceprofil.service.impl;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class AuthServiceImpl {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

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

}
