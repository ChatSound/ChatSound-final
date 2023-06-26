package com.chatsound.chatsound_jhahn_v0.controller;

import com.chatsound.chatsound_jhahn_v0.service.FileService;
import com.chatsound.chatsound_jhahn_v0.service.TtsService;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final TtsService ttsService;
    private final FileService fileService;


    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody Map<String, String> request)
            throws IOException {
        String message = request.get("message");
        String language = request.get("language");

        byte[] ttsResponse = ttsService.convertTextToSpeech(message, language);

        // Encode the byte array to a Base64 string
        String base64Audio = Base64.getEncoder().encodeToString(ttsResponse);

        Map<String, String> response = new HashMap<>();
        response.put("sound", base64Audio); // Return the Base64 string instead of the file path

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
