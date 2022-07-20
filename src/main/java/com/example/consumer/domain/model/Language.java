package com.example.consumer.domain.model;

public class Language {

    private Long id;
    private String name;
    private String extension;
    private String strToReplace;

    public Language() {
    }

    public Language(Long id, String name, String extension, String strToReplace) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.strToReplace = strToReplace;
    }

    public String getStrToReplace() {
        return strToReplace;
    }

    public void setStrToReplace(String strToReplace) {
        this.strToReplace = strToReplace;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
