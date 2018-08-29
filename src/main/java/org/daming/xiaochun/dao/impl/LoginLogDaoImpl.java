package org.daming.xiaochun.dao.impl;


import org.daming.xiaochun.dao.LoginLogDao;
import org.daming.xiaochun.pojo.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
 

@Repository
public class LoginLogDaoImpl implements LoginLogDao {

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(LoginLog loginLog) {
		 String sql = "insert into t_login_log set user_id = ? , ip = ?, login_datetime =?";
		 jdbcTemplate.update(sql, new Object[] { loginLog.getUser().getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
	}
	
	 

	public LoginLogDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
