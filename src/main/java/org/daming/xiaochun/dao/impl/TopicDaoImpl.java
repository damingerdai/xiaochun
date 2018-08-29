package org.daming.xiaochun.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.daming.xiaochun.dao.TopicDao;
import org.daming.xiaochun.pojo.Topic;
import org.daming.xiaochun.pojo.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class TopicDaoImpl implements TopicDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Topic topic) {
		String sql = "insert into t_topic set board_id = ?, topic_title = ?, user_id = ?, create_time = ?, last_post = ?, topic_views = ?, topic_replies = ?, digest = ?";
		jdbcTemplate.update(sql, topic.getBoardId(), topic.getTopicTitle(), topic.getUser().getUserId(),
				topic.getCreateTime(), topic.getLastPost(), topic.getViews(), topic.getReplies(), topic.getDigest());
	}

	@Override
	public Topic get(int id) {
		String sql = "select topic_id, board_id, topic_title, user_id,create_time,last_post,topic_views, topic_replies, digest from t_topic where topic_id = ?";
		return jdbcTemplate.query(sql, new Object[] { id }, this::extractData);
	}
	
	@Override
	public void update(Topic topic) {
		String sql = "update t_topic set board_id = ?, topic_title = ?, user_id = ?, last_post = ?, topic_views = ?, topic_replies = ?, digest = ? where topic_id = ?";
		jdbcTemplate.update(sql, topic.getBoardId(), topic.getTopicTitle(), topic.getUser().getUserId(), topic.getLastPost(), topic.getViews(), topic.getReplies(), topic.getDigest(), topic.getTopicId());
	}

	private Topic extractData(ResultSet rs) throws SQLException, DataAccessException {
		Assert.notNull(rs, "params 'rs' is required");
		while (rs.next()) {
			return map(rs);
		}
		return null;
	}

	

	@Override
	public void remove(int id) {
		String sql = "delete from t_topic where topic_id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	@Override
	public List<Topic> listByBoardId(int boardId, int start, int count) {
		String sql = "select topic_id, board_id, topic_title, user_id,create_time,last_post,topic_views, topic_replies, digest from t_topic where board_id = ? and digest > 0 order by last_post desc,digest desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { boardId, start, start }, this::mapRow);
	}
	
	private Topic mapRow(ResultSet rs, int rowNums) throws SQLException {
		Assert.notNull(rs, "params 'rs' is required");
		return map(rs);
	}
	
	private Topic map(ResultSet rs) throws SQLException {
		return new Topic().setTopicId(rs.getInt("topic_id")).setBoardId(rs.getInt("board_id"))
				.setTopicTitle(rs.getString("topic_title")).setUser(new User().setUserId(rs.getInt("user_id")))
				.setCreateTime(rs.getTimestamp("create_time")).setLastPost(rs.getTimestamp("last_post"))
				.setViews(rs.getInt("topic_views")).setReplies(rs.getInt("topic_replies"))
				.setDigest(rs.getInt("digest"));
	}
	
	@Override
	public long countByBoardId(int boardId) {
		String sql = "select count(*) from t_topic where board_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { boardId }, long.class);
	}

	@Override
	public List<Topic> listByTitle(String title, int start, int count) {
		String sql = "select topic_id, board_id, topic_title, user_id,create_time,last_post,topic_views, topic_replies, digest from t_topic where topic_title like ?  order by last_post desc,digest desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { "%" + title + "%", start, start }, this::mapRow);
	}

	@Override
	public long countByTitle(String title) {
		String sql = "select count(*) from t_topic wheretopic_title like ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { "%" + title + "%" }, long.class);
	}

	public TopicDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	

	
 
}
