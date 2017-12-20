package com.nauticana.nams.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.nams.model.UserMenu;

@Repository
public class NamsJbdcRepository {

	private static final String userMenuSql       = "SELECT * FROM USER_MENU_PERMISSION WHERE USERNAME=? ORDER BY MENU_ORDER, PAGE_ORDER";
	private static final String tableDomainSql    = "SELECT DISTINCT DOMAIN FROM DOMAIN_LOOKUP WHERE TABLENAME=?";
	private static final String authObjectType    = "SELECT OBJECT_TYPE FROM AUTHORITY_OBJECT ORDER BY 1";
	private static final String authObjectAct     = "SELECT ACTION FROM AUTHORITY_OBJECT_ACTION WHERE OBJECT_TYPE=?";
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
	
	@Autowired
    private JdbcTemplate j;

	class UserMenuRowMapper implements RowMapper<UserMenu> {
	    @Override
	    public UserMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return new UserMenu(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
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
	public List<String> authorizedObjects(String username, String authorityObject, String action) {
    	return j.query(authorizedObjects, new Object[]{username, authorityObject, action}, new StringRowMapper());
	}

}
