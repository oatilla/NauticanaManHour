package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.Language;

public interface LanguageRepository  extends JpaRepository<Language, String> {

}
