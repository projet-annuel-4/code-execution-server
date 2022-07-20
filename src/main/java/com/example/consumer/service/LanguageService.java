package com.example.consumer.service;

import com.example.consumer.data.entity.LanguageEntity;
import com.example.consumer.domain.model.Language;
import com.example.consumer.domain.mapper.LanguageMapper;
import com.example.consumer.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Autowired
    public LanguageService(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    public Language createLanguage(Language language){
        LanguageEntity languageEntity = languageRepository.save(languageMapper.convertModelToEntity(language));
        return languageMapper.convertEntityToModel(languageEntity);
    }

    public List<Language> getAllLanguage(){
        List<LanguageEntity> languageEntities = languageRepository.findAll();
        return languageEntities.stream().map(languageMapper::convertEntityToModel).collect(Collectors.toList());
    }

    public Language getLanguageByName(String name){
        LanguageEntity languageEntity = languageRepository.getByLanguageEquals(name);
        System.out.println(languageEntity);
        return languageMapper.convertEntityToModel(languageEntity);
    }

    public Language getLanguageById(Long languageId){
        Optional<LanguageEntity> languageEntityOptional = languageRepository.findById(languageId);
        if ( languageEntityOptional.isEmpty()){
            System.out.println("ca plante get language");
            return null;
        }
        return languageMapper.convertEntityToModel(languageEntityOptional.get());
    }
}
