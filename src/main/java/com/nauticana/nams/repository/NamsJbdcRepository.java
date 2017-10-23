package com.nauticana.nams.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.ExternalPerson;
import com.nauticana.manhour.model.ProjectWbsQuantityId;
import com.nauticana.manhour.model.ReportProjectWbsStatus;
import com.nauticana.manhour.model.ViewProjectCategory;
import com.nauticana.manhour.model.ViewProjectWbsMhApprove;
import com.nauticana.manhour.model.ViewProjectWbsMhApproveId;
import com.nauticana.manhour.model.ViewProjectWbsQtyApprove;
import com.nauticana.manhour.model.ViewWbsQuantity;
import com.nauticana.manhour.model.ViewWbsQuantityId;
import com.nauticana.manhour.model.ViewWbsTeamStatus;
import com.nauticana.manhour.model.ViewWbsTeamStatusId;
import com.nauticana.nams.model.UserMenu;
import com.nauticana.nams.utils.Utils;

@Repository
public class NamsJbdcRepository {

	private static final String userMenuSql       = "SELECT * FROM USER_MENU_PERMISSION WHERE USERNAME=? ORDER BY MENU_ORDER, PAGE_ORDER";

	private static final String projectWBSSql     = "SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, C.CAT_LEVEL, P.PROJECT_ID, C.PROJECT_ID AS PROJECT_FILTER FROM CATEGORY C LEFT JOIN (SELECT CATEGORY_ID, PROJECT_ID, UNIT FROM PROJECT_WBS WHERE PROJECT_ID=?) P ON C.CATEGORY_ID=P.CATEGORY_ID WHERE (C.PROJECT_ID IS NULL OR C.MAIN_FLAG='1') ORDER BY C.TREE_CODE ";
	private static final String projectWBSFilter  = "SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, C.CAT_LEVEL, P.PROJECT_ID, C.PROJECT_ID AS PROJECT_FILTER FROM CATEGORY C LEFT JOIN (SELECT CATEGORY_ID, PROJECT_ID, UNIT FROM PROJECT_WBS WHERE PROJECT_ID=?) P ON C.CATEGORY_ID=P.CATEGORY_ID WHERE (C.PROJECT_ID=P.PROJECT_ID OR C.PROJECT_ID=? OR C.MAIN_FLAG='1') ORDER BY C.TREE_CODE ";
//	private static final String projectWBSSql     = "SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, C.CAT_LEVEL, P.PROJECT_ID FROM CATEGORY C LEFT JOIN (SELECT CATEGORY_ID, PROJECT_ID, UNIT FROM PROJECT_WBS WHERE PROJECT_ID=?) P ON C.CATEGORY_ID=P.CATEGORY_ID ORDER BY C.TREE_CODE ";
	private static final String projectTeamWbs    = 
			"SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, C.CAT_LEVEL, T.PROJECT_ID, C.PROJECT_ID AS PROJECT_FILTER" +
			"  FROM CATEGORY C" +
			" INNER JOIN PROJECT_WBS W " +
			"         ON W.CATEGORY_ID = C.CATEGORY_ID " +
			"        AND W.PROJECT_ID  = ? " +
			"  LEFT JOIN PROJECT_TEAM_TEMPLATE T " +
			"         ON T.CATEGORY_ID = W.CATEGORY_ID " +
			"        AND T.PROJECT_ID  = W.PROJECT_ID " +
			"        AND T.TEAM_ID     = ? " +
			" ORDER BY C.TREE_CODE ";
//	private static final String projectTeamWbs2    =
//			"SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, C.CAT_LEVEL, T.PROJECT_ID" +
//			" FROM CATEGORY C, PROJECT_WBS P, PROJECT_TEAM_TEMPLATE T" + 
//			" WHERE C.CATEGORY_ID=P.CATEGORY_ID" + 
//			"   AND P.PROJECT_ID=?" + 
//			"   AND T.CATEGORY_ID (+) = P.CATEGORY_ID" + 
//			"   AND T.PROJECT_ID (+) = P.PROJECT_ID" + 
//			"   AND T.TEAM_ID (+) = ?" + 
//			" ORDER BY C.TREE_CODE";
	private static final String nextTeamIdSql     = "SELECT MAX(TEAM_ID) + 1 FROM PROJECT_TEAM WHERE PROJECT_ID = ?";
	private static final String localPersonSql    = "SELECT PERSON_ID, FIRST_NAME, LAST_NAME, NATIONALITY_ID, EMAIL, EMPLOYEE_NUMBER, SUPERVISOR, ORGANIZATION_ID, ORGANIZATION, POSITION, SUBCONTRACTOR FROM XXADSA_EXT_PERSON";
	private static final String localPersonOne    = "SELECT PERSON_ID, FIRST_NAME, LAST_NAME, NATIONALITY_ID, EMAIL, EMPLOYEE_NUMBER, SUPERVISOR, ORGANIZATION_ID, ORGANIZATION, POSITION, SUBCONTRACTOR FROM XXADSA_EXT_PERSON WHERE PERSON_ID=?";
	private static final String tableDomainSql    = "SELECT DISTINCT DOMAIN FROM DOMAIN_LOOKUP WHERE TABLENAME=?";
	private static final String wbsStatusPrjTeam  = "SELECT * FROM PROJECT_WBS_TEAM_STATUS WHERE PROJECT_ID=? ORDER BY TEAM_CAPTION, TREE_CODE";
	private static final String wbsStatusTeam     = "SELECT * FROM PROJECT_WBS_TEAM_STATUS WHERE PROJECT_ID=? AND TEAM_ID=? ORDER BY TREE_CODE";
	private static final String wbsStatusWbs      = "SELECT * FROM PROJECT_WBS_STATUS WHERE PROJECT_ID=? AND CATEGORY_ID=?";
	private static final String wbsStatusPrj      = "SELECT * FROM PROJECT_WBS_STATUS WHERE PROJECT_ID=? ORDER BY TREE_CODE";
	private static final String wbsStatusAll      = "SELECT * FROM PROJECT_WBS_STATUS ORDER BY PROJECT_ID, TREE_CODE";
	private static final String projectApproval   = "INSERT INTO PROJECT_APPROVAL_HISTORY (PROJECT_ID, USERNAME, APPROVAL, STATUS) VALUES (?, ?, ?, ?)";
	
	private static final String authObjectType    = "SELECT OBJECT_TYPE FROM AUTHORITY_OBJECT ORDER BY 1";
	private static final String authObjectAct     = "SELECT ACTION FROM AUTHORITY_OBJECT_ACTION WHERE OBJECT_TYPE=?";
	
	private static final String  prjWbsMh2Appr     = "SELECT * FROM PROJECT_WBS_MH_TO_APR WHERE PROJECT_ID=? ORDER BY PROJECT_ID, TEAM_ID, ACTIVITY_DATE, TREE_CODE";
	private static final String  prjWbsQty2Appr    = "SELECT * FROM PROJECT_WBS_QTY_TO_APR WHERE PROJECT_ID=? ORDER BY PROJECT_ID, TEAM_ID, BEGDA, TREE_CODE";
	
	private static final String authorityChk      =
			"SELECT COUNT(*) FROM OBJECT_AUTHORIZATION O, USER_AUTHORIZATION U" + 
			" WHERE O.AUTHORITY_GROUP=U.AUTHORITY_GROUP" + 
			"   AND U.USERNAME=?" + 
			"   AND O.OBJECT_TYPE=?" + 
			"   AND O.ACTION=?" + 
			"   AND O.KEY_VALUE IN ('*', ?)";
	
	private static final String authorizedObjects =
			"SELECT DISTINCT KEY_VALUE" + 
			"  FROM USER_AUTHORIZATION U, AUTHORITY_GROUP G, OBJECT_AUTHORIZATION O" + 
			" WHERE U.AUTHORITY_GROUP=G.AUTHORITY_GROUP" + 
			"   AND O.AUTHORITY_GROUP=G.AUTHORITY_GROUP" + 
			"   AND U.USERNAME=?" + 
			"   AND O.OBJECT_TYPE=?" + 
			"   AND O.ACTION=?";
	
//	private static final String reportSql1 =
//			"SELECT C.CATEGORY_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, \n" + 
//			"       A.SUM_QUANTITY, B.SUM_MANHOUR, Q.TERM_QUANTITY, M.TERM_WORKFORCE \n" + 
//			"  FROM ( ( ( ( PROJECT P \n" + 
//			" INNER JOIN (CATEGORY C \n" + 
//			" INNER JOIN PROJECT_WBS W \n" + 
//			"         ON W.CATEGORY_ID     = C.CATEGORY_ID )\n" + 
//			"         ON W.PROJECT_ID      = P.PROJECT_ID \n" + 
//			"        AND P.PROJECT_ID      = ? )\n" + 
//			"  LEFT JOIN PROJECT_WBS_QUANTITY_AGGR A\n" + 
//			"         ON A.PROJECT_ID      = W.PROJECT_ID \n" + 
//			"        AND A.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
//			"  LEFT JOIN PROJECT_WBS_MANHOUR_AGGR B \n" + 
//			"         ON B.PROJECT_ID      = W.PROJECT_ID \n" + 
//			"        AND B.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
//			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, SUM(QUANTITY) AS TERM_QUANTITY FROM PROJECT_WBS_QUANTITY WHERE BEGDA >= ? AND ENDDA <= ? GROUP BY PROJECT_ID, CATEGORY_ID) Q \n" + 
//			"         ON Q.PROJECT_ID      = W.PROJECT_ID \n" + 
//			"        AND Q.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
//			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, SUM(MANHOUR) AS TERM_WORKFORCE FROM PROJECT_WBS_MANHOUR WHERE ACTIVITY_DATE BETWEEN ? AND ? GROUP BY PROJECT_ID, CATEGORY_ID) M \n" + 
//			"         ON M.PROJECT_ID      = W.PROJECT_ID \n" + 
//			"        AND M.CATEGORY_ID     = W.CATEGORY_ID \n" + 
//			" ORDER BY C.TREE_CODE";

	private static final String reportSql1 =
			"SELECT C.CATEGORY_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.CBS_CODE, G.CAPTION AS CBS_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY, \n" + 
			"       A.SUM_QUANTITY, B.SUM_MANHOUR, Q.TERM_QUANTITY, M.TERM_WORKFORCE \n" + 
			"  FROM ( ( ( ( ( PROJECT P \n" + 
			" INNER JOIN (CATEGORY C \n" + 
			" INNER JOIN PROJECT_WBS W \n" + 
			"         ON W.CATEGORY_ID     = C.CATEGORY_ID )\n" + 
			"         ON W.PROJECT_ID      = P.PROJECT_ID \n" + 
			"        AND P.PROJECT_ID      = ? )\n" + 
			"  LEFT JOIN XXADSA_CBS G\n" + 
			"         ON W.CBS_CODE        = G.CBS_CODE )\n" + 
			"  LEFT JOIN PROJECT_WBS_QUANTITY_AGGR A\n" + 
			"         ON A.PROJECT_ID      = W.PROJECT_ID \n" + 
			"        AND A.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
			"  LEFT JOIN PROJECT_WBS_MANHOUR_AGGR B \n" + 
			"         ON B.PROJECT_ID      = W.PROJECT_ID \n" + 
			"        AND B.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, SUM(QUANTITY) AS TERM_QUANTITY FROM PROJECT_WBS_QUANTITY WHERE BEGDA >= ? AND ENDDA <= ? AND STATUS='APPROVE_QUANTITY' GROUP BY PROJECT_ID, CATEGORY_ID) Q \n" + 
			"         ON Q.PROJECT_ID      = W.PROJECT_ID \n" + 
			"        AND Q.CATEGORY_ID     = W.CATEGORY_ID )\n" + 
			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, SUM(MANHOUR) AS TERM_WORKFORCE FROM PROJECT_WBS_MANHOUR WHERE ACTIVITY_DATE BETWEEN ? AND ? AND STATUS='APPROVE_MANHOUR' GROUP BY PROJECT_ID, CATEGORY_ID) M \n" + 
			"         ON M.PROJECT_ID      = W.PROJECT_ID \n" + 
			"        AND M.CATEGORY_ID     = W.CATEGORY_ID \n" + 
			" ORDER BY C.TREE_CODE";
	
	
	private static final String reportSql2 =
			"SELECT C.CATEGORY_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.CBS_CODE, G.CAPTION AS CBS_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY, \n" + 
			"       A.SUM_QUANTITY, B.SUM_MANHOUR, Q.TERM_QUANTITY, M.TERM_WORKFORCE \n" + 
			"  FROM ( ( ( ( ( PROJECT P\n" + 
			" INNER JOIN (CATEGORY C\n" + 
			" INNER JOIN PROJECT_WBS W\n" + 
			"         ON W.CATEGORY_ID     = C.CATEGORY_ID)\n" + 
			"         ON W.PROJECT_ID      = P.PROJECT_ID\n" + 
			"        AND P.PROJECT_ID      = ?)\n" + 
			"  LEFT JOIN XXADSA_CBS G\n" + 
			"         ON W.CBS_CODE        = G.CBS_CODE )\n" + 
			"  LEFT JOIN PROJECT_WBS_TEAM_MH_AGGR B\n" + 
			"         ON B.PROJECT_ID      = W.PROJECT_ID\n" + 
			"        AND B.CATEGORY_ID     = W.CATEGORY_ID\n" + 
			"        AND B.TEAM_ID         = ?)\n" + 
			"  LEFT JOIN PROJECT_WBS_TEAM_QTY_AGGR A\n" + 
			"         ON A.PROJECT_ID      = B.PROJECT_ID\n" + 
			"        AND A.CATEGORY_ID     = B.CATEGORY_ID\n" + 
			"        AND A.TEAM_ID         = B.TEAM_ID)\n" + 
			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS TERM_QUANTITY FROM PROJECT_WBS_QUANTITY WHERE TEAM_ID = ? AND BEGDA >= ? AND ENDDA <= ? AND STATUS='APPROVE_QUANTITY' GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID) Q\n" + 
			"         ON Q.PROJECT_ID      = B.PROJECT_ID\n" + 
			"        AND Q.CATEGORY_ID     = B.CATEGORY_ID\n" + 
			"        AND Q.TEAM_ID         = B.TEAM_ID)\n" + 
			"  LEFT JOIN (SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS TERM_WORKFORCE FROM PROJECT_WBS_MANHOUR WHERE TEAM_ID = ? AND ACTIVITY_DATE BETWEEN ? AND ? AND STATUS='APPROVE_MANHOUR' GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID) M \n" + 
			"         ON M.PROJECT_ID      = B.PROJECT_ID\n" + 
			"        AND M.CATEGORY_ID     = B.CATEGORY_ID\n" + 
			"        AND M.TEAM_ID         = B.TEAM_ID\n" + 
			" ORDER BY C.TREE_CODE";
	
	private static final String reportSql3 =
			"select A.CATEGORY_ID, A.TREE_CODE, A.CAPTION AS CAT_CAPTION, A.CUSTOMER_WBS_CODE, A.CUSTOMER_WBS_CAPTION, A.CBS_CODE, ' ' AS CBS_CAPTION, A.UNIT, A.METRIC, A.QUANTITY, A.PUP_METRIC, A.PUP_QUANTITY, A.PLANNED_METRIC, A.PLANNED_QUANTITY, A.SUM_QUANTITY, A.SUM_MANHOUR, SQ.TERM_QUANTITY, SM.TERM_WORKFORCE \n" +
			"  FROM PROJECT_SUBCONTRACTOR_SUMMARY A \n" +
			"  LEFT JOIN ( \n" +
			"       SELECT Q.PROJECT_ID, W.SUBCONTRACTOR_ID, Q.CATEGORY_ID, SUM(Q.QUANTITY) AS TERM_QUANTITY FROM PROJECT_WBS_QUANTITY Q, PROJECT_TEAM_PERSON P, WORKER W \n" +
			"        WHERE Q.PROJECT_ID  = P.PROJECT_ID \n" +
			"          AND Q.TEAM_ID     = P.TEAM_ID \n" +
			"          AND Q.BEGDA       >= ? \n" +
			"          AND Q.ENDDA       <= ? \n" +
			"          AND Q.STATUS      = 'APPROVE_QUANTITY' \n" +
			"          AND P.WORKER_ID   = W.WORKER_ID \n" +
			"          AND P.TEAM_LEAD   = 1 \n" +
			"        GROUP BY Q.PROJECT_ID, W.SUBCONTRACTOR_ID, Q.CATEGORY_ID) SQ \n" +
			"    ON SQ.PROJECT_ID=A.PROJECT_ID \n" +
			"   AND SQ.SUBCONTRACTOR_ID = A.SUBCONTRACTOR_ID \n" +
			"   AND SQ.CATEGORY_ID=A.CATEGORY_ID \n" +
			"  LEFT JOIN ( \n" +
			"       SELECT M.PROJECT_ID, W.SUBCONTRACTOR_ID, M.CATEGORY_ID, SUM(M.MANHOUR) AS TERM_WORKFORCE FROM PROJECT_WBS_MANHOUR M, PROJECT_TEAM_PERSON P, WORKER W \n" +
			"        WHERE M.PROJECT_ID  = P.PROJECT_ID \n" +
			"          AND M.TEAM_ID     = P.TEAM_ID \n" +
			"          AND M.ACTIVITY_DATE BETWEEN ? AND ? \n" +
			"          AND M.STATUS      = 'APPROVE_MANHOUR' \n" +
			"          AND P.WORKER_ID   = W.WORKER_ID \n" +
			"          AND P.TEAM_LEAD   = 1 \n" +
			"        GROUP BY M.PROJECT_ID, W.SUBCONTRACTOR_ID, M.CATEGORY_ID) SM \n" +
			"    ON SM.PROJECT_ID=A.PROJECT_ID \n" +
			"   AND SM.SUBCONTRACTOR_ID = A.SUBCONTRACTOR_ID \n" +
			"   AND SM.CATEGORY_ID=A.CATEGORY_ID \n" +
			" WHERE A.PROJECT_ID=? AND A.SUBCONTRACTOR_ID=? \n" +
			" ORDER BY A.TREE_CODE";
	
	private static final String projectTeamAllCats = "SELECT DISTINCT CATEGORY_ID FROM (SELECT CATEGORY_ID FROM PROJECT_WBS_MANHOUR WHERE PROJECT_ID=? AND TEAM_ID=? AND ACTIVITY_DATE=? UNION SELECT CATEGORY_ID FROM PROJECT_TEAM_TEMPLATE WHERE PROJECT_ID=? AND TEAM_ID=?)";
	
	@Autowired
    private JdbcTemplate j;

	class UserMenuRowMapper implements RowMapper<UserMenu> {
	    @Override
	    public UserMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new UserMenu(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
	    }
	}
	
	class ProjectCategoryRowMapper implements RowMapper<ViewProjectCategory> {
	    @Override
	    public ViewProjectCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new ViewProjectCategory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7));
	    }
	}

	class LocalPersonRowMapper implements RowMapper<ExternalPerson> {
	    @Override
	    public ExternalPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new ExternalPerson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
	    }
	}

	class AggrWbsQuantityRowMapper implements RowMapper<ViewWbsQuantity> {
	    @Override
	    public ViewWbsQuantity mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ViewWbsQuantityId id = new ViewWbsQuantityId(rs.getInt(1), rs.getInt(2));
	    	String prjCaption;
//	    	Date   startDate;
	    	String treeCode;
	    	String catCaption;
	    	String unit;
	    	float  metric;
	    	float  quantity;
	    	float  pupMetric;
	    	float  pupQuantity;
	    	float  sumQuantity;
	    	int    sumManhour;
//	    	Date   begda;
//	    	Date   endda;
//	    	float  lastQuantity;
	    	
	    	prjCaption        = rs.getString(3);
//	    	startDate         = rs.getDate(4);
	    	treeCode          = rs.getString(4);
	    	catCaption        = rs.getString(5);
	    	unit              = rs.getString(6);
	    	try {metric       = rs.getFloat(7);}  catch (Exception e) {metric=0;}
	    	try {quantity     = rs.getFloat(8);}  catch (Exception e) {quantity=0;}
	    	try {pupMetric    = rs.getFloat(9);}  catch (Exception e) {pupMetric=0;}
	    	try {pupQuantity  = rs.getFloat(10);}  catch (Exception e) {pupQuantity=0;}
	    	try {sumQuantity  = rs.getFloat(11);}  catch (Exception e) {sumQuantity=0;}
	    	try {sumManhour   = rs.getInt(12);}   catch (Exception e) {sumManhour=0;}
//	    	try {begda        = rs.getDate(13);}  catch (Exception e) {begda=new Date(System.currentTimeMillis());}
//	    	try {endda        = rs.getDate(14);}  catch (Exception e) {endda=new Date(System.currentTimeMillis());}
//	    	try {lastQuantity = rs.getFloat(15);} catch (Exception e) {lastQuantity=0;}
	    			
	    	return new ViewWbsQuantity(id, prjCaption, treeCode, catCaption, unit, metric, quantity, pupMetric, pupQuantity, sumQuantity, sumManhour);//, begda, endda, lastQuantity);
	    }
	}

	class AggrWbsTeamStatusRowMapper implements RowMapper<ViewWbsTeamStatus> {
	    @Override
	    public ViewWbsTeamStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ViewWbsTeamStatusId id = new ViewWbsTeamStatusId(rs.getInt(1), rs.getInt(2), rs.getInt(3));
	    	String prjCaption;
	    	String treeCode;
	    	String catCaption;
	    	String teamCaption;
	    	String unit;
	    	float  metric;
	    	float  quantity;
	    	float  sumQuantity;
	    	int    sumManhour;
	    	Date   begda;
	    	Date   endda;
	    	float  lastQuantity;
	    	
	    	prjCaption        = rs.getString(4);
	    	treeCode          = rs.getString(5);
	    	catCaption        = rs.getString(6);
	    	teamCaption       = rs.getString(7);
	    	unit              = rs.getString(8);
	    	try {metric       = rs.getFloat(9);}  catch (Exception e) {metric=0;}
	    	try {quantity     = rs.getFloat(10);} catch (Exception e) {quantity=0;}
	    	try {sumQuantity  = rs.getFloat(11);}  catch (Exception e) {sumQuantity=0;}
	    	try {sumManhour   = rs.getInt(12);}   catch (Exception e) {sumManhour=0;}
	    	try {begda        = rs.getDate(13);}  catch (Exception e) {begda=null;}
	    	try {endda        = rs.getDate(14);}  catch (Exception e) {endda=null;}
	    	try {lastQuantity = rs.getFloat(15);} catch (Exception e) {lastQuantity=0;}
	    	return new ViewWbsTeamStatus(id, prjCaption, treeCode, catCaption, teamCaption, unit, metric, quantity, sumQuantity, sumManhour, begda, endda, lastQuantity);
	    }
	}

	class ReportProjectWbsStatusRowMapper implements RowMapper<ReportProjectWbsStatus> {
	    @Override
	    public ReportProjectWbsStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int    categoryId         = rs.getInt(1);
	    	String treeCode           = rs.getString(2);
	    	String catCaption         = rs.getString(3);
	    	String customerWbsCode    = rs.getString(4);
	    	String customerWbsCaption = rs.getString(5);
	    	String cbsCode    = rs.getString(6);
	    	String cbsCaption = rs.getString(7);
	    	String unit       = rs.getString(8);
	    	float  metric;
	    	float  quantity;
	    	float  workforce;
	    	float  pupMetric;
	    	float  pupQuantity;
	    	float  pupWorkforce;
	    	float  plannedMetric;
	    	float  plannedQuantity;
	    	float  plannedWorkforce;
	    	float  sumQuantity;
	    	float  sumWorkforce;
	    	float  sumMetric;
	    	float  termQuantity;
	    	float  termWorkforce;
	    	float  termMetric;
	    	try {metric        = rs.getFloat(9);}  catch (Exception e) {metric=0;}
	    	try {quantity      = rs.getFloat(10);}  catch (Exception e) {quantity=0;}
	    	try {pupMetric     = rs.getFloat(11);}  catch (Exception e) {pupMetric=0;}
	    	try {pupQuantity   = rs.getFloat(12);}  catch (Exception e) {pupQuantity=0;}
	    	try {plannedMetric = rs.getFloat(13);}  catch (Exception e) {plannedMetric=0;}
	    	try {plannedQuantity= rs.getFloat(14);}  catch (Exception e) {plannedQuantity=0;}
	    	try {sumQuantity   = rs.getFloat(15);}  catch (Exception e) {sumQuantity=0;}
	    	try {sumWorkforce  = rs.getFloat(16);} catch (Exception e) {sumWorkforce=0;}
	    	try {termQuantity  = rs.getFloat(17);} catch (Exception e) {termQuantity=0;}
	    	try {termWorkforce = rs.getFloat(18);} catch (Exception e) {termWorkforce=0;}
	    	if (!Utils.emptyStr(customerWbsCode)) treeCode = customerWbsCode;
	    	if (!Utils.emptyStr(customerWbsCaption)) catCaption = customerWbsCaption;
	    	workforce = quantity * metric;
	    	pupWorkforce = pupQuantity * pupMetric;
	    	plannedWorkforce = plannedQuantity * plannedMetric;
	    	if (sumWorkforce != 0 && sumQuantity != 0) sumMetric = sumWorkforce/sumQuantity; else sumMetric=0;
	    	if (termWorkforce != 0 && termQuantity != 0) termMetric = termWorkforce/termQuantity; else termMetric=0;
	    	return new ReportProjectWbsStatus(categoryId, treeCode, catCaption, cbsCode, cbsCaption, unit, metric, quantity, workforce, pupMetric, pupQuantity, pupWorkforce, plannedMetric, plannedQuantity, plannedWorkforce, sumMetric, sumQuantity, sumWorkforce, termMetric, termQuantity, termWorkforce);
	    }
	}


	class ProjectWbsMhApproveRowMapper implements RowMapper<ViewProjectWbsMhApprove> {
	    @Override
	    public ViewProjectWbsMhApprove mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int projectId             = rs.getInt(1);
	    	int categoryId            = rs.getInt(2);
	    	int teamId                = rs.getInt(3);
	    	String projectCaption     = rs.getString(4);
	    	String treeCode           = rs.getString(5);
	    	String categoryCaption    = rs.getString(6);
	    	String teamCaption        = rs.getString(7);
	    	String unit               = rs.getString(8);
	    	float  metric             = rs.getFloat(9);
	    	float  quantity           = rs.getFloat(10);
	    	Float  approvedManhour    = rs.getFloat(11);
	    	Date   activityDate       = rs.getDate(12);
	    	Float  manhourToApprove   = rs.getFloat(13);
	    	ViewProjectWbsMhApproveId id  = new ViewProjectWbsMhApproveId(projectId, categoryId, teamId, activityDate);
	    	return new ViewProjectWbsMhApprove(id, projectCaption, treeCode, categoryCaption, teamCaption, unit, metric, quantity, approvedManhour, manhourToApprove);
	    }
	}

	
	class ProjectWbsQtyApproveRowMapper implements RowMapper<ViewProjectWbsQtyApprove> {
	    @Override
	    public ViewProjectWbsQtyApprove mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	int projectId             = rs.getInt(1);
	    	int categoryId            = rs.getInt(2);
	    	int teamId                = rs.getInt(3);
	    	String projectCaption     = rs.getString(4);
	    	String treeCode           = rs.getString(5);
	    	String categoryCaption    = rs.getString(6);
	    	String teamCaption        = rs.getString(7);
	    	String unit               = rs.getString(8);
	    	float  metric             = rs.getFloat(9);
	    	float  quantity           = rs.getFloat(10);
	    	Float  approvedQuantity   = rs.getFloat(11);
	    	Date   begda              = rs.getDate(12);
	    	Date   endda              = rs.getDate(13);
	    	Float  quantityToApprove  = rs.getFloat(14);
	    	ProjectWbsQuantityId id   = new ProjectWbsQuantityId(projectId, categoryId, teamId, begda);
	    	return new ViewProjectWbsQtyApprove(id, projectCaption, treeCode, categoryCaption, teamCaption, unit, metric, quantity, approvedQuantity, endda, quantityToApprove);
	    }
	}

	
	class StringRowMapper implements RowMapper<String> {
	    @Override
	    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getString(1);
	    }
	}

	@Transactional(readOnly=true)
    public List<UserMenu> userMenu(String username) {
		return j.query(userMenuSql, new Object[]{username}, new UserMenuRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ViewProjectCategory> viewProjectCategory(int projectId, int projectFilter) {
		if (projectFilter < 0)
			return j.query(projectWBSSql, new Object[]{projectId}, new ProjectCategoryRowMapper());
		else
			return j.query(projectWBSFilter, new Object[]{projectId,projectFilter}, new ProjectCategoryRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ViewProjectCategory> teamCategory(int projectId, int teamId) {
    	return j.query(projectTeamWbs, new Object[]{projectId, teamId}, new ProjectCategoryRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ExternalPerson> localPersonnel(String[] fields, String[] equality, String[] conditions) {
		String whr = "";
		if (fields != null) {
			whr = " WHERE " + fields[0] + equality[0] + "?";
			for (int i = 1; i < fields.length; i++) {
				whr = whr + " AND " + fields[i] + equality[i] + "?";
			}
		}
		System.out.println(localPersonSql);
		System.out.println(whr);
		System.out.println(conditions);
		
    	return j.query(localPersonSql + whr + " ORDER BY FIRST_NAME, LAST_NAME", (Object[])conditions, new LocalPersonRowMapper());
    }

	@Transactional(readOnly=true)
    public ExternalPerson externalPerson(int personId) {
    	ExternalPerson l = j.query(localPersonOne, new Object[]{personId}, new ResultSetExtractor<ExternalPerson>() {
			@Override
			public ExternalPerson extractData(ResultSet rs) throws SQLException, DataAccessException {
				ExternalPerson x = null;
				if (rs.next()) {
			        x = new ExternalPerson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
				}
				return x;
			}
    	});
    	return l;
    }

	@Transactional(readOnly=true)
    public boolean authorityChk(String username, String objectType, String action, String keyValue) {
		Integer k = j.query(authorityChk, new Object[]{username, objectType, action, keyValue}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int i = 0;
				if (rs.next()) {
					i = rs.getInt(1);
				} else {
					System.out.println("Authority check for " + username + " to " + action + " on " + objectType + "(" + keyValue + ") failed !!!");
				}
				return Integer.valueOf(i);
			}
		});
		return k.intValue() > 0;
    }
	
	
	@Transactional(readOnly=true)
    public int nextTeamId(int projectId) {
		int k = j.query(nextTeamIdSql, new Object[]{projectId}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int i = 1;
				if (rs.next()) {
					i = rs.getInt(1);
				} return i;
			}
		});
		return k;
    }

	@Transactional(readOnly=true)
	public List<String> tableDomainsList(String tableName) {
    	return j.query(tableDomainSql, new Object[]{tableName}, new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public String[] tableDomains(String tableName) {
		ArrayList<String> s = j.query(tableDomainSql, new Object[]{tableName}, new ResultSetExtractor<ArrayList<String>>() {
			@Override
			public ArrayList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<String> s = new ArrayList<String>();
				while (rs.next()) {
					s.add(rs.getString(1));
				}return s;
			}
		});
		String[] sl = new String[s.size()];
		for (int i = 0; i < sl.length; i++) {
			sl[i] = s.get(i);
		}
		return sl;
	}

	@Transactional(readOnly=true)
	public List<String> authorityObjectTypes() {
    	return j.query(authObjectType, new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public List<String> authorityObjectActions(String authorityObject) {
    	return j.query(authObjectAct, new Object[]{authorityObject}, new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public List<ViewWbsQuantity> aggrProjectWbsQuantity(int projectId) {
		return j.query(wbsStatusPrj, new Object[]{projectId}, new AggrWbsQuantityRowMapper());
	}

	@Transactional(readOnly=true)
	public List<ViewWbsQuantity> allProjectWbsQuantity() {
		return j.query(wbsStatusAll, new AggrWbsQuantityRowMapper());
	}

	@Transactional(readOnly=true)
	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId) {
		return j.query(wbsStatusPrjTeam, new Object[]{projectId}, new AggrWbsTeamStatusRowMapper());
	}

	@Transactional(readOnly=true)
	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId, int teamId) {
		return j.query(wbsStatusTeam, new Object[]{projectId, teamId}, new AggrWbsTeamStatusRowMapper());
	}
	
	@Transactional(readOnly=true)
	public ViewWbsQuantity singleProjectWbsQuantity(int projectId, int categoryId) {
		return j.query(wbsStatusWbs, new Object[]{projectId, categoryId}, new ResultSetExtractor<ViewWbsQuantity>() {
			@Override
			public ViewWbsQuantity extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
			    	ViewWbsQuantityId id = new ViewWbsQuantityId(rs.getInt(1), rs.getInt(2));
			    	String prjCaption;
			    	String treeCode;
			    	String catCaption;
			    	String unit;
			    	float  metric;
			    	float  quantity;
			    	float  pupMetric;
			    	float  pupQuantity;
			    	float  sumQuantity;
			    	int    sumManhour;
//			    	Date   begda;
//			    	Date   endda;
//			    	float  lastQuantity;
			    	
			    	prjCaption        = rs.getString(3);
			    	treeCode          = rs.getString(4);
			    	catCaption        = rs.getString(5);
			    	unit              = rs.getString(6);
			    	try {metric       = rs.getFloat(7);}  catch (Exception e) {metric=0;}
			    	try {quantity     = rs.getFloat(8);}  catch (Exception e) {quantity=0;}
			    	try {pupMetric    = rs.getFloat(9);}  catch (Exception e) {pupMetric=0;}
			    	try {pupQuantity  = rs.getFloat(10);}  catch (Exception e) {pupQuantity=0;}
			    	try {sumQuantity  = rs.getFloat(11);}  catch (Exception e) {sumQuantity=0;}
			    	try {sumManhour   = rs.getInt(12);}   catch (Exception e) {sumManhour=0;}
//			    	try {begda        = rs.getDate(13);}  catch (Exception e) {begda=new Date(System.currentTimeMillis());}
//			    	try {endda        = rs.getDate(14);}  catch (Exception e) {endda=new Date(System.currentTimeMillis());}
//			    	try {lastQuantity = rs.getFloat(15);} catch (Exception e) {lastQuantity=0;}
			    			
			    	return new ViewWbsQuantity(id, prjCaption, treeCode, catCaption, unit, metric, quantity, pupMetric, pupQuantity, sumQuantity, sumManhour);//, begda, endda, lastQuantity);
				} else
					return null;
			}
		});
	}

	@Transactional(readOnly=true)
	public List<String> authorizedObjects(String username, String authorityObject, String action) {
    	return j.query(authorizedObjects, new Object[]{username, authorityObject, action}, new StringRowMapper());
	}

	
	@Transactional
	public int addProjectApproval(int projectId, String username, String action) {
		return j.update(projectApproval, projectId, username, new Date(System.currentTimeMillis()), action);
	}

	
	@Transactional(readOnly=true)
	public List<ReportProjectWbsStatus> getReportProjectWbsStatus(int projectId, Date begda, Date endda) {
		return j.query(reportSql1, new Object[]{projectId, begda, endda, begda, endda}, new ReportProjectWbsStatusRowMapper());
	}

	@Transactional(readOnly=true)
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForTeam(int projectId, int teamId, Date begda, Date endda) {
		return j.query(reportSql2, new Object[]{projectId, teamId, teamId, begda, endda, teamId, begda, endda}, new ReportProjectWbsStatusRowMapper());
	}
	
	@Transactional(readOnly=true)
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForSubcontractor(int projectId, int subcontractorId, Date begda, Date endda) {
		return j.query(reportSql3, new Object[]{begda, endda, begda, endda, projectId, subcontractorId}, new ReportProjectWbsStatusRowMapper());
	}

	public List<ViewProjectWbsMhApprove> getProjectWbsMhApprove(int projectId) {
		return j.query(prjWbsMh2Appr, new Object[]{projectId}, new ProjectWbsMhApproveRowMapper());
	}

	public List<ViewProjectWbsQtyApprove> getProjectWbsQtyApprove(int projectId) {
		return j.query(prjWbsQty2Appr, new Object[]{projectId}, new ProjectWbsQtyApproveRowMapper());
	}

	public List<String> getProjectTeamTemplate(int projectId, int teamId, Date activityDate) {
		return j.query(projectTeamAllCats, new Object[]{projectId,teamId,activityDate,projectId,teamId}, new StringRowMapper());
	}

}
