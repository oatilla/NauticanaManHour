package com.nauticana.manhour.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.DomainName;
import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.Language;
import com.nauticana.manhour.service.CaptionTranslationService;
import com.nauticana.manhour.service.DomainNameService;
import com.nauticana.manhour.service.DomainValueService;
import com.nauticana.manhour.service.LanguageService;

@Service
public class DataCache {

	private static DataCache instance = null;
	private static HashMap<String, Domain> domains = null;
	private static HashMap<String, PortalLanguage> languages = null;

//	private static PreparedStatement psc = null;
//	private static PreparedStatement psr = null;
//	private static PreparedStatement psu = null;
//	private static PreparedStatement psd = null;
//	
//	private static final String p = "SELECT COUNT(*) AS SAYI" +
//			  						"  FROM AUTHORITY_GROUPS A, TABLE_AUTHORIZATIONS T, USER_AUTHORIZATIONS U" +
//			  						" WHERE T.AUTHORITY_GROUP=A.AUTHORITY_GROUP" +
//			  						"   AND U.AUTHORITY_GROUP=A.AUTHORITY_GROUP" +
//			  						"   AND T.TABLENAME=?" +
//			  						"   AND U.USERNAME=?";
//	
//	private static final String sqlC = p + " AND T.ALLOW_INSERT='+'";
//	private static final String sqlR = p + " AND T.ALLOW_SELECT='+'";
//	private static final String sqlU = p + " AND T.ALLOW_UPDATE='+'";
//	private static final String sqlD = p + " AND T.ALLOW_DELETE='+'";
//
	
	public static final String defaultLanguage = "TR";

	private DataCache( ) {
		
	}

	public static DataCache getInstance() {
		if (instance == null) {
			instance = new DataCache();
			loadCache();
		}
		return instance;
	}
	
	public static void loadCache() {
		
		try {

			//loadDomains(conn);
			//loadLanguages();
//			psc = conn.prepareStatement(sqlC);
//			psr = conn.prepareStatement(sqlR);
//			psu = conn.prepareStatement(sqlU);
//			psd = conn.prepareStatement(sqlD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadDomains(DomainNameService ds, DomainValueService vs) {
		domains = new HashMap<String, Domain>();
		List<DomainName> dl = ds.findAll();
		for(DomainName dn : dl) {
			domains.put(dn.getId(), new Domain(dn.getId(), dn.getCaption(), dn.getKeysize()));
		}
		
		List<DomainValue> dvl = vs.findAll();
		
		for (DomainValue dv : dvl) {
			domains.get(dv.getId().getDomain()).addOption(dv.getId().getRefvalue(), dv.getCaption());
		}
		
//		
//	    String sql = "SELECT D.DOMAIN, D.KEYSIZE, V.REFVALUE, V.CAPTION FROM DOMAIN_NAMES D, DOMAIN_VALUES V WHERE D.DOMAIN=V.DOMAIN ORDER BY D.DOMAIN,V.REFVALUE";
//	    String n = "";
//	    ResultSet rs = conn.createStatement().executeQuery(sql);
//	    Domain domain = null;
//	    while (rs.next()) {
//			String domainName = rs.getString(1);
//			String keySize    = rs.getString(2);
//			String value      = rs.getString(3);
//			String caption    = rs.getString(4);
//			
//			if (!n.equals(domainName)) {
//				if (domain != null) {
//					domains.put(n, domain);
//					domain.println();
//				}
//				n = domainName;
//				domain = new Domain(domainName, domainName, Integer.parseInt(keySize));
//			}
//			domain.addOption(value, caption);
//	    }
//		if (domain != null) {
//			domains.put(n, domain);
//			domain.println();
//		}
	}
	
	public static Domain getDomain(String domainName) {
		return domains.get(domainName);
	}
	
	public static Map<String, String> getDomainOptions(String domainName) {
		if (domains == null) return null;
		Domain d = domains.get(domainName);
		if (d == null) return null;
		return d.getOptions();
	}
	
	
	public static Map<String, PortalLanguage> loadLanguages(LanguageService ls, CaptionTranslationService ts) throws IOException {
		languages = new HashMap<String, PortalLanguage>();
		List<Language> languageList = ls.findAll();
		for (Language language : languageList) {
			languages.put(language.getId(), new PortalLanguage(language.getId(), language.getCaption()));
		}
		
		List<CaptionTranslation> ct = ts.findAll();
		
		for (CaptionTranslation t : ct) {
			languages.get(t.getLanguage().getId()).translations.put(t.getId().getCaption(), t.getLabellower());
		}
		
		return languages;
//		String sql ="SELECT LANGUAGE, CAPTION FROM LANGUAGES ORDER BY 1";
//	    ResultSet rs = conn.createStatement().executeQuery(sql);
//		while (rs.next()) {
//			String dil = rs.getString(1);
//			String baslik  = rs.getString(2);
//			PortalLanguage language = new PortalLanguage(dil,  baslik);
//			language.loadTranslations(conn);
//			languages.put(dil, language);
//		}
	}
	
	public static PortalLanguage getLanguage(String language) {
		if (Utils.emptyStr(language))
			return languages.get(defaultLanguage);
		else
			return languages.get(language);
		
	}

	public static Map<String, PortalLanguage> getLanguages() {
		return languages;
	}
	
	public static synchronized boolean insertAllowed(String tableName, String userName) {
		return true;
//		try {
//			psc.setString(1, tableName);
//			psc.setString(2, userName);
//			ResultSet rs = psc.executeQuery();
//			rs.next();
//			int c = rs.getInt(1);
//			return (c>0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	public static synchronized boolean selectAllowed(String tableName, String userName) {
		return true;
//		try {
//			psr.setString(1, tableName);
//			psr.setString(2, userName);
//			ResultSet rs = psr.executeQuery();
//			rs.next();
//			int c = rs.getInt(1);
//			return (c>0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	public static synchronized boolean updateAllowed(String tableName, String userName) {
		return true;
//		try {
//			psu.setString(1, tableName);
//			psu.setString(2, userName);
//			ResultSet rs = psu.executeQuery();
//			rs.next();
//			int c = rs.getInt(1);
//			return (c>0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	public static synchronized boolean deleteAllowed(String tableName, String userName) {
		return true;
//		try {
//			psd.setString(1, tableName);
//			psd.setString(2, userName);
//			ResultSet rs = psd.executeQuery();
//			rs.next();
//			int c = rs.getInt(1);
//			return (c>0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
	}
}
