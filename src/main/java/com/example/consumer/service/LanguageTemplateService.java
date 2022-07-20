package com.example.consumer.service;

import com.example.consumer.data.entity.LanguageTemplateEntity;
import com.example.consumer.domain.model.Language;
import com.example.consumer.domain.model.LanguageTemplate;
import com.example.consumer.domain.mapper.LanguageMapper;
import com.example.consumer.domain.mapper.LanguageTemplateMapper;
import com.example.consumer.repository.LanguageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class LanguageTemplateService {

    private final LanguageTemplateRepository languageTemplateRepository;
    private final LanguageTemplateMapper languageTemplateMapper;
    private final LanguageMapper languageMapper;
    private final LanguageService languageService;

    @Autowired
    public LanguageTemplateService(LanguageTemplateRepository languageTemplateRepository, LanguageTemplateMapper languageTemplateMapper, LanguageMapper languageMapper, LanguageService languageService) {
        this.languageTemplateRepository = languageTemplateRepository;
        this.languageTemplateMapper = languageTemplateMapper;
        this.languageMapper = languageMapper;
        this.languageService = languageService;
    }

    public void createTemplate(LanguageTemplate languageTemplate){
        languageTemplateRepository.save(languageTemplateMapper.convertModelToEntity(languageTemplate));
    }

    @Transactional
    public LanguageTemplate getTemplateByLanguageId(Long languageId){
        Language language = languageService.getLanguageById(languageId);
        LanguageTemplateEntity languageTemplateEntity = languageTemplateRepository.getByLanguageEntity(languageMapper.convertModelToEntity(language));
        if ( languageTemplateEntity == null ){
            System.out.println("ca plante get get template");
            return null;
        }
        return languageTemplateMapper.convertEntityToModel(languageTemplateEntity);
    }

    @Transactional
    public Language modifyLanguageTemplateByLanguageId(Long languageId, MultipartFile templateFile) throws IOException {
        LanguageTemplate languageTemplate = getTemplateByLanguageId(languageId);
        byte[] newTemplate = templateFile.getBytes();
        languageTemplate.setFile(newTemplate);
        languageTemplateRepository.save(languageTemplateMapper.convertModelToEntity(languageTemplate));
        return languageTemplate.getLanguage();
    }


}
