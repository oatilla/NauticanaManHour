package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.CaptionTranslationId;

@Controller
@RequestMapping("/captionTranslation")
public class CaptionTranslationController extends AbstractController<CaptionTranslation, CaptionTranslationId> {

//	@Autowired
//	protected CaptionTranslationService modelService;

	public CaptionTranslationController() {
		super(CaptionTranslation.tableName,"captionTranslationList", "captionTranslationEdit");
	}

}
