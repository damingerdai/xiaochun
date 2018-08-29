package org.daming.xiaochun.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.daming.xiaochun.dao.BoardDao;
import org.daming.xiaochun.pojo.Board;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class BoardDaoImpl implements BoardDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Board get(int id) {
		String sql = "select board_id, board_name, board_desc, topic_num from t_board where board_id = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, this::extractData);
	}
	
	private Board extractData(ResultSet rs) throws SQLException, DataAccessException {
		Assert.notNull(rs, "params 'rs' is required");
		while(rs.next()) {
			return map(rs);
		} 
		return null;
	}

	@Override
	public long getBoardNum() {
		String sql = "select count(*) from t_board";
		return jdbcTemplate.queryForObject(sql, long.class);
	}
	
	@Override
	public void update(Board board) {
		String sql = "update t_board set board_name = ?, board_desc = ?, topic_num = ?  where board_id = ?";
		jdbcTemplate.update(sql, board.getBoardName(), board.getBoardDesc(), board.getTopicNum(), board.getBoardId());
	}
	
	@Override
	public void save(Board board) {
		 String sql = "insert into t_board (board_name, board_desc,topic_num) values (?,?,?)";
		 jdbcTemplate.update(sql, board.getBoardName(), board.getBoardDesc(), board.getTopicNum());
	}
	
	@Override
	public void delete(int id) {
		String sql = "delete from t_board where board_id = ?";
		jdbcTemplate.update(sql, id);
		
	}
	
	@Override
	public List<Board> list() {
		String sql = "select board_id, board_name, board_desc, topic_num from t_board";
		return jdbcTemplate.query(sql, this::mapRow);
	}
	
	private Board mapRow(ResultSet rs, int rowsNum) throws SQLException {
		Assert.notNull(rs, "params 'rs' is required");
		return map(rs);
	}
	
	private Board map(ResultSet rs) throws SQLException {
		return new Board()
				.setBoardId(rs.getInt("board_id"))
				.setBoardName(rs.getString("board_name"))
				.setBoardDesc(rs.getString("board_desc"))
				.setTopicNum(rs.getInt("topic_num"));
	}


	public BoardDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
