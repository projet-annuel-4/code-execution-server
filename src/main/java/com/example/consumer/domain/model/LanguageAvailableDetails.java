package com.example.consumer.domain.model;

public class LanguageAvailableDetails {

    private String extension;
    private String strToReplace;

    public static LanguageAvailableDetails getCDetails(){
        return  new LanguageAvailableDetails(
                "c",
                "///CODE///"
        );
    }

    public static LanguageAvailableDetails getJsDetails(){
        return  new LanguageAvailableDetails(
                "js",
                "///CODE///"
        );
    }

    public static LanguageAvailableDetails getPythonDetails(){
        return  new LanguageAvailableDetails(
                "py",
                "###CODE###"
        );
    }

    public LanguageAvailableDetails(String extension, String strToReplace) {
        this.extension = extension;
        this.strToReplace = strToReplace;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getStrToReplace() {
        return strToReplace;
    }

    public void setStrToReplace(String strToReplace) {
        this.strToReplace = strToReplace;
    }
}
