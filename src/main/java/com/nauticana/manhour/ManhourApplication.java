package com.nauticana.manhour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.HSQLServerFactory;
import com.nauticana.manhour.utils.Icons;
//import com.nauticana.manhour.utils.Utils;

@SpringBootApplication
public class ManhourApplication {

	public static final String hsqldbname = "GAMADB";
	public static final short  hsqlport = 9006;
	public static final String nauticanaDDL    = "D:\\WORK\\GAMA\\SQL\\nauticana_hsqldb.sql";
	public static final String manhourDDL      = "D:\\WORK\\GAMA\\SQL\\manhour_hsqldb.sql";
	public static final String categorySQL     = "D:\\WORK\\GAMA\\SQL\\category.sql";
	public static final String translationsSQL = "D:\\WORK\\GAMA\\SQL\\texts.sql";

	public static void main(String[] args) {
    	if (!HSQLServerFactory.started(hsqldbname)) {
    		HSQLServerFactory.startdb(hsqldbname, hsqlport);
//			try {
//				Utils.runSQLFile(nauticanaDDL,    HSQLServerFactory.getConnection(hsqldbname,"SA",""), null);// "UTF8");
//				Utils.runSQLFile(manhourDDL,      HSQLServerFactory.getConnection(hsqldbname,"SA",""), null);// "UTF8");
//				Utils.runSQLFile(translationsSQL, HSQLServerFactory.getConnection(hsqldbname,"SA",""), null);// "UTF8");
//				Utils.runSQLFile(categorySQL,     HSQLServerFactory.getConnection(hsqldbname,"SA",""), null);// "UTF8");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
    	}
		SpringApplication.run(ManhourApplication.class, args);
		DataCache.getInstance();
		Icons.getInstance();
	}
}
