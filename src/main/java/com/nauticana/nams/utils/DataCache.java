package com.nauticana.nams.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.model.CaptionTranslation;
import com.nauticana.nams.model.DomainName;
import com.nauticana.nams.model.DomainValue;
import com.nauticana.nams.model.Language;
import com.nauticana.nams.service.DomainNameService;
import com.nauticana.nams.service.LanguageService;

@Service
public class DataCache {

	private static HashMap<String, Domain> domains = null;
	private static HashMap<String, PortalLanguage> languages = null;

	public static final String defaultLanguage = "TR";

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private DomainNameService domainNameService;
	
	private DataCache( ) {
	}

	public void loadDomainValues(DomainName domainName) {
		Domain domain = domains.get(domainName.getId());
		for (DomainValue dv : domainName.getDomainValues()) {
			domain.addOption(dv.getId().getRefvalue(), dv.getCaption());
		}
	}
	
	public void loadDomains() {
		if (domains == null)
			domains = new HashMap<String, Domain>();
		else
			domains.clear();
		List<DomainName> dl = domainNameService.findAll();
		for(DomainName dn : dl) {
			domains.put(dn.getId(), new Domain(dn.getId(), dn.getCaption(), dn.getKeysize(), dn.getSortBy()));
			loadDomainValues(dn);
		}
	}
	
	public void loadTranslations(Language language) {
		PortalLanguage pl = languages.get(language.getId());
		for (CaptionTranslation t : language.getCaptionTranslations()) {
			String caption = t.getId().getCaption();
			pl.translations.put(caption, t.getLabellower());
			String icon = Icons.getIcon(caption + "_ICON");
			if (icon != null) {
				pl.iconText.put(caption, "<i class=\"" + icon + "\"> " + t.getLabellower() + " </i>");
				pl.icon.put(caption, "<i class=\"" + icon + "\"></i>");
			}
		}
	}
	
	public Map<String, PortalLanguage> loadLanguages() {
		if (languages == null)
			languages = new HashMap<String, PortalLanguage>();
		else
			languages.clear();
		List<Language> languageList = languageService.findAll();
		for (Language language : languageList) {
			try {
				languages.put(language.getId(), new PortalLanguage(language.getId(), language.getCaption()));
				loadTranslations(language);
				System.out.println("\n  New language added :\n     " + language.getId() + " " + language.getCaption());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return languages;
	}
	
	public Domain getDomain(String domainName) {
		if (domains == null) loadDomains();
		return domains.get(domainName);
	}
	
	public Map<String, String> getDomainOptions(String domainName) {
		if (domains == null) loadDomains();
		Domain d = domains.get(domainName);
		if (d == null) return null;
		return d.getOptions();
	}
	
	public Map<String, String> getDomainOptions(String domainName, PortalLanguage language) {
		if (domains == null) loadDomains();
		Domain d = domains.get(domainName);
		if (d == null) return null;
		return d.getOptions(language);
	}
	
	public PortalLanguage getLanguage(String language) {
		if (languages == null) {
			System.out.println("\n  Languages null, loading:\n");
			loadLanguages();
		}
		if (Utils.emptyStr(language))
			return languages.get(defaultLanguage);
		else
			return languages.get(language);
		
	}

	public Map<String, PortalLanguage> getLanguages() {
		if (languages == null) {
			System.out.println("\n  Languages null, loading:\n");
			loadLanguages();
		}
		return languages;
	}
	
}
