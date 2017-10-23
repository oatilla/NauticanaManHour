package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.CaptionTranslation;
import com.nauticana.nams.model.CaptionTranslationId;

public interface CaptionTranslationRepository extends JpaRepository<CaptionTranslation, CaptionTranslationId>{

}
