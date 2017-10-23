package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.Language;

public interface LanguageRepository extends JpaRepository<Language, String> {

}
