package org.daming.xiaochun.dao.impl;

import org.daming.xiaochun.dao.BoardManagerDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardManagerDaoImpl implements BoardManagerDao {

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(int userId, int boardId) {
		String sql = "insert into t_board_manager (board_id, user_id) values (?,?)";
		jdbcTemplate.update(sql, boardId, userId);
	}

	public BoardManagerDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
