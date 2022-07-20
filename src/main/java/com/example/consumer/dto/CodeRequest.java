package com.example.consumer.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CodeRequest implements Serializable {

    private String id;
    private String code;
    private String language;
    private boolean test;
    private CompilationMode mode;

    public CodeRequest(String id, String code, String language, boolean test, CompilationMode mode) {
        this.id = id;
        this.code = code;
        this.language = language;
        this.test = test;
        this.mode = mode;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "CodeRequest{" +
                "code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", mode=" + mode +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMode(CompilationMode mode) {
        this.mode = mode;
    }
}
