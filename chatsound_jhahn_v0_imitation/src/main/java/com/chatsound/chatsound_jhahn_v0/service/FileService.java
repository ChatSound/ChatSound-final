package com.chatsound.chatsound_jhahn_v0.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    public String saveAudioFile(byte[] audioData) throws IOException {
        String fileName = "audio-" + System.currentTimeMillis() + ".wav";
        String directory = "src/main/resources/static";
        Path dirPath = Paths.get(directory);

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        String filePath = directory + "/" + fileName;
        Files.write(Paths.get(filePath), audioData);

        return "/static/" + fileName;  // Include the relative path to the file in the response
    }
}
