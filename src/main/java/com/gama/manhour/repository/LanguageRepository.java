package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.Language;

public interface LanguageRepository  extends JpaRepository<Language, String> {

}
