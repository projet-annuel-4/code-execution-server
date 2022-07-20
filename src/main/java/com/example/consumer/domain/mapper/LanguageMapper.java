package com.example.consumer.domain.mapper;

import com.example.consumer.data.entity.LanguageEntity;
import com.example.consumer.domain.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {


    public Language convertEntityToModel(LanguageEntity languageEntity){
        if( languageEntity == null){
            return null;
        }
        Language language = new Language();
        language.setId(languageEntity.getId());
        language.setName(languageEntity.getLanguage());
        language.setExtension(languageEntity.getExtension());
        language.setStrToReplace(languageEntity.getStrToReplace());
        return language;
    }

    public LanguageEntity convertModelToEntity(Language language){
        if( language == null){
            return null;
        }
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(language.getId() == null ? null : language.getId());
        languageEntity.setLanguage(language.getName());
        languageEntity.setExtension(language.getExtension());
        languageEntity.setStrToReplace(language.getStrToReplace());
        return languageEntity;
    }
}
