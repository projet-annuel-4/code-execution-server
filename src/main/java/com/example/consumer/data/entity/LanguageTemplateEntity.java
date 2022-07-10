package com.example.consumer.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "language_template")
public class LanguageTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_template_id_seq")
    private Long id;
    @Lob
    private byte[] data;
    @ManyToOne
    private LanguageEntity languageEntity;
    private boolean isActive;

    public LanguageTemplateEntity() {
    }

    public LanguageTemplateEntity(Long id, byte[] data, LanguageEntity languageEntity, boolean isActive) {
        this.id = id;
        this.data = data;
        this.languageEntity = languageEntity;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setLanguageEntity(LanguageEntity languageEntity) {
        this.languageEntity = languageEntity;
    }

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public LanguageEntity getLanguageEntity() {
        return languageEntity;
    }
}
