package com.chatsound.chatsound_jhahn_v0.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TtsService {
    private final RestTemplate restTemplate = new RestTemplate();

    public byte[] convertTextToSpeech(String text, String language) {
        String url = "http://117.16.17.149:8000/text-to-sound/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("country", language);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);

        return response.getBody();
    }
}
