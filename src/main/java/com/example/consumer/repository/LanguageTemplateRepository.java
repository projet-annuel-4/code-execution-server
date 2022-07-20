package com.example.consumer.repository;

import com.example.consumer.data.entity.LanguageEntity;
import com.example.consumer.data.entity.LanguageTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageTemplateRepository extends JpaRepository<LanguageTemplateEntity, Long> {

    LanguageTemplateEntity getByLanguageEntity(LanguageEntity languageEntity);
}
