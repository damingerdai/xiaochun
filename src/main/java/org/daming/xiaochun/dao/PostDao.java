package org.daming.xiaochun.dao;

import java.util.List;

import org.daming.xiaochun.pojo.Post;

public interface PostDao {
	
	List<Post> listByTopicId(int topicId, int startIndex, int times);
	
	long countByTopicId(int topicId);
	
	void deleteTopicPosts(int topicId);
	
	void save(Post post);
	
	void delete(int id);
	
	Post get(int id);
}
