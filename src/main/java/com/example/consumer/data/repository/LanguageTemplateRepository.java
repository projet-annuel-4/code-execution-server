package com.example.consumer.data.repository;

import com.example.consumer.data.entity.LanguageTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageTemplateRepository extends JpaRepository<LanguageTemplateEntity,Long> {
}
