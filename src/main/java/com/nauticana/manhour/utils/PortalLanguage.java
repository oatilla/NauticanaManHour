package com.nauticana.manhour.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class PortalLanguage {

	private static final String sep = System.getProperty("line.separator");
	private static PrintWriter writer = null;

	public String code;
	public String caption;
	public Hashtable<String, String> translations;
	
	public PortalLanguage(String code, String caption) throws IOException {
		this.code = code;
		this.caption = caption;
		this.translations = new Hashtable<String, String>(); 
		if (writer == null)
		writer = new PrintWriter(new FileWriter(new File("MissingTranslations_" + code + ".log")));
	}
	
	public static void insertMissing(String code, String caption) {
		writer.write(code + " " + caption + sep);
		writer.flush();
	}
	
	public void loadTranslations(Connection conn) {
		String sql ="SELECT CAPTION, LABELLOWER FROM CAPTION_TRANSLATIONS WHERE LANGUAGE='" + this.code + "' ORDER BY 1";
		Statement stmt1 = null;
		ResultSet rs1 = null;
		try {
			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery(sql);
			translations.clear();

			while (rs1.next()) {
				String baslik = rs1.getString(1);
				String text   = rs1.getString(2);
				translations.put(baslik, text);
			}
			rs1.close();
			rs1 = null;
			stmt1.close();
			stmt1 = null;
		} catch (Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		} finally {
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs1 = null;
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt1 = null;
			}
		}
	}
	
	public String getText(String caption) {
		String text = translations.get(caption);
		if (Utils.emptyStr(text)) {
			insertMissing(code, caption);
			text = caption;
		}
		return text;
	}
}
