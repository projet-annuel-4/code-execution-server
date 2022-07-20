package com.example.consumer.domain.model;

public class LanguageTemplate {

    private Long id;
    private byte[] file;
    private Language language;


    public LanguageTemplate() {
    }

    public LanguageTemplate(Long id, byte[] file, Language language) {
        this.id = id;
        this.file = file;
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
