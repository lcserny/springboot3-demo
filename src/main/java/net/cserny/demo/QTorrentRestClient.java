package net.cserny.demo;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
@AllArgsConstructor
public class QTorrentRestClient {

    public static final String SID_KEY = "SID";

    private final RestTemplate restTemplate;
    private final TorrentWebUiProperties properties;

    public String generateSid() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", properties.getUsername());
        map.add("password", properties.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(properties.getBaseUrl() + "/api/v2/auth/login", HttpMethod.POST, request, String.class);

        String cookies = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        return cookies.substring(4, cookies.indexOf(";"));
    }
}
