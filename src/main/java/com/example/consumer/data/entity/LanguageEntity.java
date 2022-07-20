package com.example.consumer.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_seq")
    private Long id;
    private String language;
    private String extension;
    private String strToReplace;

    public LanguageEntity() {

    }

    public LanguageEntity(Long id, String language, String extension, String strToReplace) {
        this.id = id;
        this.language = language;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }
}
