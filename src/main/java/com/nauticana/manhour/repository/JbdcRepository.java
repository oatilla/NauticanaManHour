package com.nauticana.manhour.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	private String nextTeamIdSql  = "SELECT MAX(TEAM_ID) + 1 FROM PROJECT_TEAM WHERE PROJECT_ID = ?";
	
	class UserMenuRowMapper implements RowMapper<UserMenu> {
	    @Override
	    public UserMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new UserMenu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
	    }
	}
	
	class ProjectCategoryRowMapper implements RowMapper<ProjectCategory> {
	    @Override
	    public ProjectCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new ProjectCategory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	    }
	}

	@Autowired
    private JdbcTemplate j;

	@Transactional(readOnly=true)
    public List<UserMenu> userMenu(String username) {
    	return j.query(userMenuSql, new Object[]{username}, new UserMenuRowMapper());
    }

	@Transactional(readOnly=true)
    public List<ProjectCategory> projectCategoory(int projectId) {
    	return j.query(projectWBSSql, new Object[]{projectId}, new ProjectCategoryRowMapper());
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
				int i = 0;
				if (rs.next()) {
					i = rs.getInt(1);
				} return i;
			}
		});
		return k;
    }

}
