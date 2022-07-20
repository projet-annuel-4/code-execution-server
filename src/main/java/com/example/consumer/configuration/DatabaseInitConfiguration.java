package com.example.consumer.configuration;

import com.example.consumer.data.entity.LanguageEntity;
import com.example.consumer.domain.model.Language;
import com.example.consumer.domain.model.LanguageAvailableDetails;
import com.example.consumer.domain.model.LanguageTemplate;
import com.example.consumer.service.LanguageService;
import com.example.consumer.service.LanguageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Configuration
public class DatabaseInitConfiguration {

    private final LanguageService languageService;
    private final LanguageTemplateService languageTemplateService;
    private final Map<String, LanguageAvailableDetails> languages = Map.ofEntries(
            entry("javascript", LanguageAvailableDetails.getJsDetails()),
            entry("c", LanguageAvailableDetails.getCDetails()),
            entry("python", LanguageAvailableDetails.getPythonDetails())
    );

    @Autowired
    public DatabaseInitConfiguration(LanguageService languageService, LanguageTemplateService languageTemplateService) {
        this.languageService = languageService;
        this.languageTemplateService = languageTemplateService;
    }

    @PostConstruct
    public void initDb() throws IOException {

        for( Map.Entry language : languages.entrySet()){
            System.out.println(language.getKey());
            if( languageService.getLanguageByName(language.getKey().toString()) == null){
                Language languageCreated = createLanguage(language.getKey().toString());
                LanguageTemplate languageTemplate = languageTemplateService.getTemplateByLanguageId(languageCreated.getId());
                String file = new String(languageTemplate.getFile(), StandardCharsets.UTF_8);

                System.out.println(file);
            }
        }
    }

    private Language createLanguage(String name) throws IOException {
        Language language = new Language();
        language.setName(name);
        language.setExtension(languages.get(name).getExtension());
        language.setStrToReplace(languages.get(name).getStrToReplace());
        Language languageCreated = languageService.createLanguage(language);
        createTemplate(languageCreated);
        return languageCreated;
    }


    private void createTemplate(Language language) throws IOException {
        String templateFileName = "template." + language.getExtension();
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(templateFileName);
        System.out.println(templateFileName);

        byte[] templateFileBytes = ioStream.readAllBytes();
        LanguageTemplate template = new LanguageTemplate();
        template.setLanguage(language);
        template.setFile(templateFileBytes);
        languageTemplateService.createTemplate(template);
    }
}
