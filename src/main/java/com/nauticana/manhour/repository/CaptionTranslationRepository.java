package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.CaptionTranslationId;

public interface CaptionTranslationRepository extends JpaRepository<CaptionTranslation, CaptionTranslationId>{

}
