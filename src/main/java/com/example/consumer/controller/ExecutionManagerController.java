package com.example.consumer.controller;


import com.example.consumer.domain.model.Language;
import com.example.consumer.domain.model.LanguageTemplate;
import com.example.consumer.service.LanguageService;
import com.example.consumer.service.LanguageTemplateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("api/v1/executionManager")
public class ExecutionManagerController {

    private final LanguageService languageService;
    private final LanguageTemplateService languageTemplateService;

    public ExecutionManagerController(LanguageService languageService, LanguageTemplateService languageTemplateService) {
        this.languageService = languageService;
        this.languageTemplateService = languageTemplateService;
    }

    @GetMapping("/getLanguage")
    public List<Language> getListLanguage(){
        return languageService.getAllLanguage();
    }

    @GetMapping("/{languageId}/getLanguage")
    public LanguageTemplate getLanguageTemplate(@PathVariable Long languageId){
        LanguageTemplate test = languageTemplateService.getTemplateByLanguageId(languageId);
        String file = new String(test.getFile(), StandardCharsets.UTF_8);

        System.out.println(file);
        return test;
    }

    @PutMapping("/{languageId}/changeTemplate")
    public Language updateLanguageTemplate(@PathVariable Long languageId, @RequestPart MultipartFile newTemplate) throws IOException {
        return languageTemplateService.modifyLanguageTemplateByLanguageId(languageId, newTemplate);
    }

//    @GetMapping("/{languageId}/getCommands")
//    public List<Command> getCommandsByLanguage(@PathVariable Long languageId){
//        Language language = languageService.getLanguageById(languageId);
//        return commandService.getCommandByLanguage(language);
//    }
//
//    @PostMapping("/{languageId}/updateCommands")
//    public List<Command> updateCommandsByLanguage(@PathVariable Long languageId, @RequestBody List<Command> commands){
//        Language language = languageService.getLanguageById(languageId);
//        return commandService.updateCommandsByLanguage(commands, language);
//    }

}
