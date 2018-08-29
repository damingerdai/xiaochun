package org.daming.xiaochun.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.daming.xiaochun.dao.PostDao;
import org.daming.xiaochun.pojo.Post;
import org.daming.xiaochun.pojo.Topic;
import org.daming.xiaochun.pojo.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class PostDaoImpl implements PostDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Post> listByTopicId(int topicId, int startIndex, int times) {
		String sql = "select a.post_id as post_id, a.board_id as board_id, a.topic_id as topic_id, c.topic_title as topic_title, a.user_id as user_id, b.user_name as user_name, a.post_type as post_type, a.post_title as post_title, a.post_text as post_text, a.create_time as create_time from t_post as a join t_user as b on a.user_id = b.user_id join t_topic as c on a.topic_id = c.topic_id and a.topic_id = ? order by a.create_time desc limit ?,?";
		return jdbcTemplate.query(sql, new Object[] { topicId, startIndex, times }, this::mapRow);
	}
	
	private Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Assert.notNull(rs, "param 'rs' is requred");
		return map(rs);
	}

	@Override
	public long countByTopicId(int topicId) {
		String sql = "select count(*) from t_post where topic_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { topicId }, long.class);
	}

	@Override
	public void deleteTopicPosts(int topicId) {
		String sql = "delete from t_post where topic_id = ?";
		jdbcTemplate.update(sql, topicId);
	}

	@Override
	public void save(Post post) {
		String sql = "insert into t_post set board_id = ?, topic_id = ?, user_id = ?, post_type = ?, post_title = ?, post_text = ?, create_time = now()";
		jdbcTemplate.update(sql, post.getBoardId(), post.getTopic().getTopicId(), post.getUser().getUserId(),
				post.getPostType(), post.getPostTitle(), post.getPostText());
	}
	
	@Override
	public void delete(int id) {
		String sql = "delete from t_post where post_id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	@Override
	public Post get(int id) {
		String sql = "select post_id, board_id, topic_id, user_id,post_type, post_title, post_text, create_time from t_post where post_id = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, this::extractData);
	}
	
	private Post extractData(ResultSet rs) throws SQLException, DataAccessException {
		Assert.notNull(rs, "param 'rs' is requred");
		while (rs.next()) {
			return map(rs);
		} 
		return null;
	}
	
	private Post map(ResultSet rs) throws SQLException {
		return new Post().setPostId(rs.getInt("post_id")).setBoardId(rs.getInt("board_id"))
				.setTopic(new Topic().setTopicId(rs.getInt("topic_id")))
				.setUser(new User().setUserId(rs.getInt("user_id"))).setPostTitle(rs.getString("post_title"))
				.setPostText(rs.getString("post_text")).setCreateTime(rs.getTimestamp("create_time"));
	}

	public PostDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	

}
