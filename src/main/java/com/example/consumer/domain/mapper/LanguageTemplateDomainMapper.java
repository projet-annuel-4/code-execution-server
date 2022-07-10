package com.example.consumer.domain.mapper;

import com.example.consumer.data.entity.LanguageTemplateEntity;
import com.example.consumer.domain.model.LanguageTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageTemplateDomainMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public LanguageTemplateDomainMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LanguageTemplateEntity fromModelToEntity(LanguageTemplate languageTemplate){
        return modelMapper.map(languageTemplate, LanguageTemplateEntity.class);
    }

    public LanguageTemplate fromEntityToModel(LanguageTemplateEntity languageTemplateEntity){
        return modelMapper.map(languageTemplateEntity, LanguageTemplate.class);
    }

}
