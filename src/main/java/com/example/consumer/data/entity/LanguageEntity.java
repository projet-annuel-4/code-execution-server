package com.example.consumer.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "language_template")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_seq")
    private Long id;
    private String language;

    public LanguageEntity() {

    }

    public LanguageEntity(Long id, String language) {
        this.id = id;
        this.language = language;
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
