package org.daming.xiaochun.dao.impl;

import org.daming.xiaochun.dao.UserDao;
import org.daming.xiaochun.pojo.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Desc: User对象Dao
 *
 * @author daming
 * @version 2018-08-22 21:55
 */
@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public User get(int id) {
		String sql = "select user_id, user_name, password,user_type, locked, credit, last_visit, last_ip from t_user where user_id = ?";
		return jdbcTemplate.query(sql,  new Object[] { id}, this::extractData);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		String sql = "select user_id, user_name, password,user_type, locked, credit, last_visit, last_ip from t_user where user_name like ?";
		return jdbcTemplate.query(sql, new Object[] { userName + "%" }, this::extractData);
	}

	private User extractData(ResultSet rs) throws SQLException, DataAccessException {
		Assert.notNull(rs, "params 'rs' is required");
		while (rs.next()) {
			return map(rs);
		}
		return null;
	}

	@Override
	public List<User> queryUserByUserName(String userName) {
		String sql = "select user_id, user_name, password,user_type, locked, credit, last_visit, last_ip from t_user where user_name like ?";
		return jdbcTemplate.query(sql, new Object[] { userName }, this::mapRow);
	}
	
	@Override
	public List<User> list() {
		String sql = "select user_id, user_name, password,user_type, locked, credit, last_visit, last_ip from t_user";
		return jdbcTemplate.query(sql, this::mapRow);
	}

	private User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return map(rs);
	}

	private User map(ResultSet rs) throws SQLException {
		return new User().setUserId(rs.getInt("user_id")).setUserName(rs.getString("user_name"))
				.setPassword(rs.getString("password")).setUserType(rs.getInt("user_type"))
				.setLocked(rs.getInt("locked")).setCredit(rs.getInt("credit"))
				.setLastVisit(rs.getTimestamp("last_visit")).setLastIp(rs.getString("last_ip"));
	}

	@Override
	public void save(User user) {
		Assert.notNull(user, "param 'user' is required");
		String sql = "insert into t_user set  user_name = ?, password= ?,  user_type = ?,  credit = ?, locked = ?,  last_visit= ?, last_ip = ?";
		jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getUserType(),
				user.getCredit(), user.getLocked(), user.getLastVisit(), user.getLastIp() });

	}

	@Override
	public void update(User user) {
		Assert.notNull(user, "param 'user' is required");
		String sql = "update t_user set  user_name = ?, password= ?,  user_type = ?,  credit = ?, locked = ?,  last_visit= ?, last_ip = ? where user_id = ?";
		jdbcTemplate.update(sql, new Object[] { user.getUserName(), user.getPassword(), user.getUserType(),
				user.getCredit(), user.getLocked(), user.getLastVisit(), user.getLastIp(), user.getUserId() });
	}

	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


}
