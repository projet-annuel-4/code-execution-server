package com.example.consumer.dto;

public class ModifyLanguageTemplate {

    private Long languageId;
    private byte[] template;

    public ModifyLanguageTemplate(Long languageId, byte[] template) {
        this.languageId = languageId;
        this.template = template;
    }

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] template) {
        this.template = template;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
}
