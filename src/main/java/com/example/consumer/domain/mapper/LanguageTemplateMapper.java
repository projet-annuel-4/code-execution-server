package com.example.consumer.domain.mapper;

import com.example.consumer.data.entity.LanguageTemplateEntity;
import com.example.consumer.domain.model.LanguageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageTemplateMapper {

    private final LanguageMapper languageMapper;

    @Autowired
    public LanguageTemplateMapper(LanguageMapper languageMapper) {
        this.languageMapper = languageMapper;
    }

    public LanguageTemplate convertEntityToModel(LanguageTemplateEntity languageTemplateEntity){
        LanguageTemplate languageTemplate = new LanguageTemplate();
        languageTemplate.setId(languageTemplateEntity.getId());
        languageTemplate.setFile(languageTemplateEntity.getData());
        languageTemplate.setLanguage(languageMapper.convertEntityToModel(languageTemplateEntity.getLanguageEntity()));
        return languageTemplate;
    }

    public LanguageTemplateEntity convertModelToEntity(LanguageTemplate languageTemplate){
        LanguageTemplateEntity languageTemplateEntity = new LanguageTemplateEntity();
        languageTemplateEntity.setId(languageTemplate.getId() == null ? null : languageTemplate.getId());
        languageTemplateEntity.setData(languageTemplate.getFile());
        languageTemplateEntity.setLanguageEntity(languageMapper.convertModelToEntity(languageTemplate.getLanguage()));
        return languageTemplateEntity;
    }
}
