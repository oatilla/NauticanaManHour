package com.nauticana.manhour.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.query.AggrWbsQuantity;
import com.nauticana.manhour.query.AggrWbsQuantityId;
import com.nauticana.manhour.query.LocalPerson;
import com.nauticana.manhour.query.ProjectCategory;
import com.nauticana.manhour.query.UserMenu;

@Repository
public class JbdcRepository {

	private String userMenuSql    = "SELECT PAGE_CAPTION, PAGE_ICON, URL, MENU_CAPTION, MENU_ICON, MENU_ORDER, PAGE_ORDER FROM USER_MENU_PERMISSION WHERE USERNAME=? ORDER BY MENU_ORDER, PAGE_ORDER";
	private String allowSelectSql = "SELECT CNT FROM USER_TABLE_SELECT_PERMISSION WHERE TABLENAME = ? AND USERNAME = ?";
	private String allowInsertSql = "SELECT CNT FROM USER_TABLE_INSERT_PERMISSION WHERE TABLENAME = ? AND USERNAME = ?";
	private String allowUpdateSql = "SELECT CNT FROM USER_TABLE_UPDATE_PERMISSION WHERE TABLENAME = ? AND USERNAME = ?";
	private String allowDeleteSql = "SELECT CNT FROM USER_TABLE_DELETE_PERMISSION WHERE TABLENAME = ? AND USERNAME = ?";
	private String projectWBSSql  = "SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, P.PROJECT_ID FROM CATEGORY C LEFT JOIN (SELECT CATEGORY_ID, PROJECT_ID FROM PROJECT_WBS WHERE PROJECT_ID=?) P ON C.CATEGORY_ID=P.CATEGORY_ID ORDER BY C.TREE_CODE ";
	private String projectTeamWbs = "SELECT C.CATEGORY_ID, C.PARENT_ID, C.TREE_CODE, C.CAPTION, P.PROJECT_ID, PROJECT_TEAM_TEMPLATE.TEAM_ID FROM (CATEGORY AS C INNER JOIN (SELECT CATEGORY_ID, PROJECT_ID FROM PROJECT_WBS WHERE PROJECT_ID=?) AS P ON C.CATEGORY_ID = P.CATEGORY_ID) LEFT JOIN (SELECT CATEGORY_ID, PROJECT_ID FROM PROJECT_TEAM_TEMPLATE WHERE PROJECT_ID=? AND TEAM_ID=?) AS T ON P.CATEGORY_ID = T.CATEGORY_ID AND P.PROJECT_ID = T.PROJECT_ID ORDER BY C.TREE_CODE ";
	private String nextTeamIdSql  = "SELECT MAX(TEAM_ID) + 1 FROM PROJECT_TEAM WHERE PROJECT_ID = ?";
	private String localPersonSql = "SELECT * FROM PERSONNEL";
	private String localPersonOne = "SELECT * FROM PERSONNEL WHERE PERSON_ID=?";
	private String tableDomainSql = "SELECT DISTINCT DOMAIN FROM DOMAIN_LOOKUP WHERE TABLENAME=?";
	private String wbsStatusWbs   = "SELECT * FROM PROJECT_WBS_STATUS WHERE PROJECT_ID=? AND CATEGORY_ID=?";
	private String wbsStatusPrj   = "SELECT * FROM PROJECT_WBS_STATUS WHERE PROJECT_ID=? ORDER BY TREE_CODE";
	private String wbsStatusAll   = "SELECT * FROM PROJECT_WBS_STATUS ORDER BY PROJECT_ID, TREE_CODE";
	
	class UserMenuRowMapper implements RowMapper<UserMenu> {
	    @Override
	    public UserMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new UserMenu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
	    }
	}
	
	class ProjectCategoryRowMapper implements RowMapper<ProjectCategory> {
	    @Override
	    public ProjectCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new ProjectCategory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), null);
	    }
	}

	class LocalPersonRowMapper implements RowMapper<LocalPerson> {
	    @Override
	    public LocalPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new LocalPerson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	    }
	}

	class AggrWbsQuantityRowMapper implements RowMapper<AggrWbsQuantity> {
	    @Override
	    public AggrWbsQuantity mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AggrWbsQuantityId id = new AggrWbsQuantityId(rs.getInt(1), rs.getInt(2));
	    	String prjCaption;
	    	String treeCode;
	    	String catCaption;
	    	String unit;
	    	float  metric;
	    	float  quantity;
	    	float  sumQuantity;
	    	int    sumManhour;
	    	int    sumOvertime;
	    	Date   begda;
	    	Date   endda;
	    	float  lastQuantity;
	    	
	    	prjCaption        = rs.getString(3);
	    	treeCode          = rs.getString(4);
	    	catCaption        = rs.getString(5);
	    	unit              = rs.getString(6);
	    	try {metric       = rs.getFloat(7);}  catch (Exception e) {metric=0;}
	    	try {quantity     = rs.getFloat(8);}  catch (Exception e) {quantity=0;}
	    	try {sumQuantity  = rs.getFloat(9);}  catch (Exception e) {sumQuantity=0;}
	    	try {sumManhour   = rs.getInt(10);}   catch (Exception e) {sumManhour=0;}
	    	try {sumOvertime  = rs.getInt(11);}   catch (Exception e) {sumOvertime=0;}
	    	try {begda        = rs.getDate(12);}  catch (Exception e) {begda=new Date(System.currentTimeMillis());}
	    	try {endda        = rs.getDate(13);}  catch (Exception e) {endda=new Date(System.currentTimeMillis());}
	    	try {lastQuantity = rs.getFloat(15);} catch (Exception e) {lastQuantity=0;}
	    			
	    	return new AggrWbsQuantity(id, prjCaption, treeCode, catCaption, unit, metric, quantity, sumQuantity, sumManhour, sumOvertime, begda, endda, lastQuantity);
	    }
	}

	class StringRowMapper implements RowMapper<String> {
	    @Override
	    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return rs.getString(1);
	    }
	}

	@Autowired
    private JdbcTemplate j;

	@Transactional(readOnly=true)
    public List<UserMenu> userMenu(String username) {
    	return j.query(userMenuSql, new Object[]{username}, new UserMenuRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ProjectCategory> projectCategory(int projectId) {
    	return j.query(projectWBSSql, new Object[]{projectId}, new ProjectCategoryRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ProjectCategory> teamCategory(int projectId, int teamId) {
    	return j.query(projectTeamWbs, new Object[]{projectId, projectId, teamId}, new ProjectCategoryRowMapper());
    }

	@Transactional(readOnly=true)
    public List<LocalPerson> localPerson(String[] fields, String[] equality, String[] conditions) {
		String whr = "";
		if (fields != null) {
			whr = " WHERE " + fields[0] + equality[0] + "?";
			for (int i = 1; i < fields.length; i++) {
				whr = whr + " AND " + fields[i] + equality[i] + "?";
			}
		}
    	return j.query(localPersonSql + whr, new Object[]{conditions}, new LocalPersonRowMapper());
    }

	@Transactional(readOnly=true)
    public LocalPerson localPerson(int personId) {
    	LocalPerson l = j.query(localPersonOne, new Object[]{personId}, new ResultSetExtractor<LocalPerson>() {
			@Override
			public LocalPerson extractData(ResultSet rs) throws SQLException, DataAccessException {
				LocalPerson x = null;
				if (rs.next()) {
			        x = new LocalPerson(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}
				return x;
			}
    	});
    	return l;
    }

    public boolean allowed(String sql, String tablename, String username) {
		Integer k = j.query(sql, new Object[]{tablename,username}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int i = 0;
				if (rs.next()) {
					i = rs.getInt(1);
				} else {
					System.out.println(sql + " with " + tablename + " " + username + " has no result !!!");
				}
				return Integer.valueOf(i);
			}
		});
		return k.intValue() > 0;
    }
	
	
	@Transactional(readOnly=true)
    public boolean allowSelect(String tablename, String username) {
		return allowed(allowSelectSql, tablename, username);
    }
 
	@Transactional(readOnly=true)
    public boolean allowInsert(String tablename, String username) {
		return allowed(allowInsertSql, tablename, username);
    }
 
	@Transactional(readOnly=true)
    public boolean allowUpdate(String tablename, String username) {
		return allowed(allowUpdateSql, tablename, username);
    }
 
	@Transactional(readOnly=true)
    public boolean allowDelete(String tablename, String username) {
		return allowed(allowDeleteSql, tablename, username);
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
	public List<String> tableDomains(String tableName) {
    	return j.query(tableDomainSql, new Object[]{tableName}, new StringRowMapper());
	}

	@Transactional(readOnly=true)
	public List<AggrWbsQuantity> aggrProjectWbsQuantity(int projectId) {
		return j.query(wbsStatusPrj, new Object[]{projectId}, new AggrWbsQuantityRowMapper());
	}

	@Transactional(readOnly=true)
	public List<AggrWbsQuantity> allProjectWbsQuantity() {
		return j.query(wbsStatusAll, new AggrWbsQuantityRowMapper());
	}

	@Transactional(readOnly=true)
	public AggrWbsQuantity singleProjectWbsQuantity(int projectId, int categoryId) {
		return j.query(wbsStatusWbs, new Object[]{projectId, categoryId}, new ResultSetExtractor<AggrWbsQuantity>() {
			@Override
			public AggrWbsQuantity extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
			    	AggrWbsQuantityId id = new AggrWbsQuantityId(rs.getInt(1), rs.getInt(2));
			    	String prjCaption;
			    	String treeCode;
			    	String catCaption;
			    	String unit;
			    	float  metric;
			    	float  quantity;
			    	float  sumQuantity;
			    	int    sumManhour;
			    	int    sumOvertime;
			    	Date   begda;
			    	Date   endda;
			    	float  lastQuantity;
			    	
			    	prjCaption        = rs.getString(3);
			    	treeCode          = rs.getString(4);
			    	catCaption        = rs.getString(5);
			    	unit              = rs.getString(6);
			    	try {metric       = rs.getFloat(7);}  catch (Exception e) {metric=0;}
			    	try {quantity     = rs.getFloat(8);}  catch (Exception e) {quantity=0;}
			    	try {sumQuantity  = rs.getFloat(9);}  catch (Exception e) {sumQuantity=0;}
			    	try {sumManhour   = rs.getInt(10);}   catch (Exception e) {sumManhour=0;}
			    	try {sumOvertime  = rs.getInt(11);}   catch (Exception e) {sumOvertime=0;}
			    	try {begda        = rs.getDate(12);}  catch (Exception e) {begda=new Date(System.currentTimeMillis());}
			    	try {endda        = rs.getDate(13);}  catch (Exception e) {endda=new Date(System.currentTimeMillis());}
			    	try {lastQuantity = rs.getFloat(15);} catch (Exception e) {lastQuantity=0;}
			    			
			    	return new AggrWbsQuantity(id, prjCaption, treeCode, catCaption, unit, metric, quantity, sumQuantity, sumManhour, sumOvertime, begda, endda, lastQuantity);
				} else
					return null;
			}
		});
	}

}
