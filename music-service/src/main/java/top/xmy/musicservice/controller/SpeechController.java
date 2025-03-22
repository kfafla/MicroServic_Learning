package top.xmy.musicservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.xmy.musicservice.service.SpeechRecognitionService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/speech")
public class SpeechController {

    @Autowired
    private SpeechRecognitionService recognitionService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("/recognize")
    public ResponseEntity<String> recognize(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to a temporary location
            File tempFile = File.createTempFile("speech_", ".wav");
            file.transferTo(tempFile);

            // Call the recognition service
            String result = recognitionService.recognize(tempFile.getAbsolutePath());

            // Delete the temporary file
            tempFile.delete();

            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error processing the file: " + e.getMessage());
        }
    }
}
