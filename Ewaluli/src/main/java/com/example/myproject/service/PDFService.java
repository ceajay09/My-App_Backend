package com.example.myproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;

/**
 * Service class for managing PDF file downloads. Utilizes Spring's ResourceLoader
 * to access PDF resources and facilitates their download.
 */
@Service
public class PDFService {
    private static final Logger logger = LogManager.getLogger(PDFService.class);

    private final ResourceLoader resourceLoader;

    @Autowired
    public PDFService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public ResponseEntity<Resource> downloadPDF(String fileName) throws MalformedURLException {
        Resource pdfFile = resourceLoader.getResource("classpath:assets/" + fileName);
        if(!pdfFile.exists()){
            String errorMessage = "File not found: " + fileName;
            logger.info(errorMessage);
            return ResponseEntity.notFound().build();
        }
        logger.info("Download: " + fileName);
               return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfFile);
    }

}
