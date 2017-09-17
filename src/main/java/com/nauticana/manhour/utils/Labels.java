package com.nauticana.manhour.utils;

import java.text.SimpleDateFormat;

public class Labels {

	// Common used label names
	
	// Labels for actions
	public static final String NEW       = "NEW";
	public static final String EDIT      = "EDIT";
	public static final String SAVE      = "SAVE";
	public static final String LIST      = "LIST";
	public static final String SEARCH    = "SEARCH";
	public static final String FIND      = "FIND";
	public static final String DELETE    = "DELETE";
	public static final String USERNAME  = "USERNAME";
	public static final String LANGUAGE  = "LANGUAGE";
	public static final String PAGETITLE = "PAGETITLE";
	public static final String MENU      = "MENU";
	public static final String CHOOSE    = "CHOOSE";
	public static final String SELECT    = "SELECT";
	public static final String CANCEL    = "CANCEL";
	public static final String OK        = "OK";
	public static final String NEXTPAGE  = "nextpage";
	public static final String PERSONNEL = "PERSONNEL";
	public static final String SUBCONTRACTOR = "SUBCONTRACTOR";
	public static final String OVERTIME  = "OVERTIME";
	public static final String FOREIGN   = "FOREIGN";
	public static final String LOCALMH   = "LOCALMH";
	public static final String TRMH      = "TRMH";
	public static final String TOTAL     = "TOTAL";
	
	public static final String dmyDFStr = "dd-MM-yyyy";
	public static final String ymdDFStr = "yyyy-MM-dd";
	public static final SimpleDateFormat dmyDF = new SimpleDateFormat(dmyDFStr);
	public static final SimpleDateFormat ymdDF = new SimpleDateFormat(ymdDFStr);

	
//	public static final String pageCommons =
//		"<link rel=\"stylesheet\" href=\"../s/bootstrap.min.css\">" + System.lineSeparator() +
//		"<link rel=\"stylesheet\" href=\"../s/font-awesome/css/font-awesome.min.css\">" + System.lineSeparator() +
//		"<link rel=\"stylesheet\" href=\"../s/Ionicons/css/ionicons.min.css\">" + System.lineSeparator() +
//		"<link rel=\"stylesheet\" href=\"../s/dataTables.bootstrap.min.css\">" + System.lineSeparator() +
//		"<script src=\"../j/jquery.min.js\"></script>" + System.lineSeparator() +
//		"<script src=\"../j/jquery.dataTables.min.js\"></script>" + System.lineSeparator() +
//		"<script src=\"../j/bootstrap.min.js\"></script>" + System.lineSeparator() +
//		"<script src=\"../j/dataTables.bootstrap.min.js\"></script>";
	

	public static final String dataTableSetting1 =
			"<script> $(function () { $('#dataTable1').DataTable({'paging':true, 'lengthChange':false, 'searching':false, 'ordering':true, 'info':true, 'autoWidth': false }) }) </script>";
	
	public static final String dataTableSetting2 =
			"<script> $(function () { $('#dataTable2').DataTable({'paging':true, 'lengthChange':false, 'searching':false, 'ordering':true, 'info':true, 'autoWidth': false }) }) </script>";
	
	public static final String dataTableSetting3 =
			"<script> $(function () { $('#dataTable3').DataTable({'paging':true, 'lengthChange':false, 'searching':false, 'ordering':true, 'info':true, 'autoWidth': false }) }) </script>";
	
	public static final String dataTableSetting4 =
			"<script> $(function () { $('#dataTable4').DataTable({'paging':true, 'lengthChange':false, 'searching':false, 'ordering':true, 'info':true, 'autoWidth': false }) }) </script>";
	
/*	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	public static final String x = "";
	*/
	
}
