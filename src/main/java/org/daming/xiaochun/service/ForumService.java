package org.daming.xiaochun.service;

import java.util.List;

import org.daming.xiaochun.pojo.Board;
import org.daming.xiaochun.pojo.Page;
import org.daming.xiaochun.pojo.Post;
import org.daming.xiaochun.pojo.Topic;

/**
 * 论坛管理服务类，对论坛版块、话题、帖子进行管理
 * 
 * @author daming
 *
 */
public interface ForumService {

	void addTopic(Topic topic);

	void removeTopic(int topicId);

	void addPost(Post post);

	void removePost(int postId);

	void addBoard(Board board);

	void removeBoard(int boardId);

	void makeDigestTopic(int topicId);

	List<Board> getAllBoards();

	Page<Topic> getPagedTopics(int boardId, int pageNo, int pageSize);

	Page<Post> getPagedPosts(int topicId, int pageNo, int pageSize);

	Page<Topic> queryTopicByTitle(String title, int pageNo, int pageSize);

	Board getBoardById(int boardId);

	Topic getTopicByTopicId(int topicId);

	Post getPostByPostId(int postId);

	void addBoardManager(int boardId, String userName);

	void updateTopic(Topic topic);

	void updatePost(Post post);
}
