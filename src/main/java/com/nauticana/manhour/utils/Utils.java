package com.nauticana.manhour.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
	
	public static boolean emptyStr(String s) {
		if (s == null)
			return true;
		if (s.isEmpty())
			return true;
		if (s.length() == 0)
			return true;
		return false;
	}
	
	public static boolean runSQL(String sqltext, Connection conn, PrintWriter writer) {
		Statement stmt1 = null;
		try {
			stmt1 = conn.createStatement();
			stmt1.execute(sqltext);
			stmt1.close();
			stmt1 = null;
			return true;
		} catch (Exception e) {
			System.out.println(sqltext);
			e.printStackTrace();
			if (writer != null) {
				writer.write(sqltext);
				e.printStackTrace(writer);
			}
		} finally {
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
					if (writer != null) {
						writer.write(sqltext);
						e.printStackTrace(writer);
					}
				}
				stmt1 = null;
			}
		}
		return false;
	}

	public static void runSQLFile(String filename, Connection conn) throws IOException {
		File sqlfile = new File(filename);
		File logfile = new File(filename + ".log");
		String sep = System.getProperty("line.separator");
		BufferedReader reader = null;
		PrintWriter writer = null;
		String text = null;
		String sqltext = "";

		reader = new BufferedReader(new FileReader(sqlfile));
		writer = new PrintWriter(new FileWriter(logfile));
		while ((text = reader.readLine()) != null) {
			text = text.trim();
			if (!emptyStr(text)) {
				if (text.charAt(text.length() - 1) == ';') {
					sqltext = sqltext + " " + text.substring(0, text.length() - 1);
					text = "/";
				}
				if (text.equals("/")) {
					if (!runSQL(sqltext, conn, writer)) {
						sqltext = "SQL error:" + sep + sqltext;
						writer.write(sqltext + sep);
						writer.close();
					} else {
						writer.write(sqltext + sep);
						sqltext = "";
					}
				} else
					sqltext = sqltext + " " + text;
			}
		}
		if (reader != null) {
			reader.close();
		}
		if (writer != null) {
			writer.close();
		}
	}
}
