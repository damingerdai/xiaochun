package org.daming.xiaochun.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.daming.xiaochun.dao.BoardDao;
import org.daming.xiaochun.dao.BoardManagerDao;
import org.daming.xiaochun.dao.PostDao;
import org.daming.xiaochun.dao.TopicDao;
import org.daming.xiaochun.dao.UserDao;
import org.daming.xiaochun.pojo.Board;
import org.daming.xiaochun.pojo.MainPost;
import org.daming.xiaochun.pojo.Page;
import org.daming.xiaochun.pojo.Post;
import org.daming.xiaochun.pojo.Topic;
import org.daming.xiaochun.pojo.User;
import org.daming.xiaochun.service.ForumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {

	private TopicDao topicDao;
	private UserDao userDao;
	private BoardDao boardDao;
	private PostDao postDao;
	private BoardManagerDao boardManagerDao;

	@Override
	public void addTopic(Topic topic) {
		Board board = boardDao.get(topic.getBoardId());
		board.setTopicNum(board.getTopicNum() + 1);
		topicDao.save(topic);

		topic.getMainPost().setTopic(topic);
		MainPost post = topic.getMainPost();
		post.setCreateTime(new Date());
		post.setUser(topic.getUser());
		post.setPostTitle(topic.getTopicTitle());
		post.setBoardId(topic.getBoardId());
		postDao.save(post);

		User user = topic.getUser();
		user.setCredit(user.getCredit() + 5);
		userDao.update(user);

	}

	@Override
	public void removeTopic(int topicId) {
		Optional<Topic> topicOptional = Optional.ofNullable(topicDao.get(topicId));
		topicOptional.ifPresent(topic -> {
			Board board = boardDao.get(topic.getBoardId());
			board.setTopicNum(board.getTopicNum() - 1);
			boardDao.update(board);
			User user = userDao.get(topic.getUser().getUserId());
			user.setCredit(user.getCredit() + 5);
			userDao.update(user);

			postDao.deleteTopicPosts(topicId);
			topicDao.remove(topicId);
		});

	}

	@Override
	public void addPost(Post post) {
		postDao.save(post);

		User user = userDao.get(post.getUser().getUserId());
		user.setCredit(user.getCredit() + 5);
		userDao.update(user);

		Topic topic = topicDao.get(post.getTopic().getTopicId());
		topic.setReplies(topic.getReplies() + 1);
		topic.setLastPost(new Date());
		topicDao.update(topic);

	}

	@Override
	public void removePost(int postId) {
		Post post = postDao.get(postId);
		Topic topic = topicDao.get(post.getPostId());
		topic.setReplies(topic.getReplies() - 1);
		topicDao.update(topic);
		User user = userDao.get(post.getUser().getUserId());
		user.setCredit(user.getCredit() - 1);
		userDao.update(user);
		postDao.delete(postId);
	}

	@Override
	public void addBoard(Board board) {
		boardDao.save(board);
	}

	@Override
	public void removeBoard(int boardId) {
		boardDao.delete(boardId);
	}

	@Override
	public void makeDigestTopic(int topicId) {
		Topic topic = topicDao.get(topicId);
		topic.setDigest(Topic.DIGEST_TOPIC);
		User user = userDao.get(topic.getUser().getUserId());
		user.setCredit(user.getCredit() + 5);

		topicDao.update(topic);
		userDao.update(user);
	}

	@Override
	public List<Board> getAllBoards() {
		return boardDao.list();
	}

	@Override
	public Page<Topic> getPagedTopics(int boardId, int pageNo, int pageSize) {
		int start = Page.getStartOfPage(pageNo, pageSize);
		return new Page<>(start, topicDao.countByBoardId(boardId), pageSize, topicDao.listByBoardId(boardId, start, pageSize));
	}

	@Override
	public Page<Post> getPagedPosts(int topicId, int pageNo, int pageSize) {
		int start = Page.getStartOfPage(pageNo, pageSize);
		return new Page<>(start, postDao.countByTopicId(topicId), pageSize, postDao.listByTopicId(topicId, start, pageSize));
	}

	@Override
	public Page<Topic> queryTopicByTitle(String title, int pageNo, int pageSize) {
		int start = Page.getStartOfPage(pageNo, pageSize);
		return new Page<>(start, topicDao.countByTitle(title) , pageSize, topicDao.listByTitle(title, start, pageSize));
	}

	@Override
	public Board getBoardById(int boardId) {
		return boardDao.get(boardId);
	}

	@Override
	public Topic getTopicByTopicId(int topicId) {
		return topicDao.get(topicId);
	}

	@Override
	public Post getPostByPostId(int postId) {
		return postDao.get(postId);
	}

	@Override
	public void addBoardManager(int boardId, String userName) {
		 User user = Optional.ofNullable(userDao.getUserByUserName(userName)).orElseThrow(() -> new RuntimeException("用户名为：" + userName + "的用户名不存在"));
		 boardManagerDao.save(user.getUserId(), boardId);
	}

	@Override
	public void updateTopic(Topic topic) {
		 topicDao.update(topic);
	}

	@Override
	public void updatePost(Post post) {
		
	}

	public ForumServiceImpl(TopicDao topicDao, UserDao userDao, BoardDao boardDao, PostDao postDao,
			BoardManagerDao boardManagerDao) {
		super();
		this.topicDao = topicDao;
		this.userDao = userDao;
		this.boardDao = boardDao;
		this.postDao = postDao;
		this.boardManagerDao = boardManagerDao;
	}

	 

}
