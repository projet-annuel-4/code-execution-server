package com.example.consumer.domain.model;

import org.springframework.web.multipart.MultipartFile;

public class LanguageTemplate {

    private Long id;
    private MultipartFile file;
    private String filename;

    public LanguageTemplate(Long id, MultipartFile file, String filename) {
        this.id = id;
        this.file = file;
        this.filename = filename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getFilename() {
        return filename;
    }
}
