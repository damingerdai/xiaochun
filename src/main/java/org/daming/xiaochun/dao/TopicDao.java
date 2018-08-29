package org.daming.xiaochun.dao;

import java.util.List;

import org.daming.xiaochun.pojo.Topic;

public interface TopicDao {
	
	void save(Topic topic);
	
	Topic get(int id);
	
	void remove(int id);
	
	void update(Topic topic);
	
	List<Topic> listByBoardId(int boardId, int start, int count);
	
	long countByBoardId(int boardId);
	
	List<Topic> listByTitle(String title, int start, int count);
	
	long countByTitle(String title);
}
