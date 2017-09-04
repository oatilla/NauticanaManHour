package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.CaptionTranslation;
import com.gama.manhour.model.CaptionTranslationId;

public interface CaptionTranslationRepository extends JpaRepository<CaptionTranslation, CaptionTranslationId>{

}
