package org.daming.xiaochun.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: 帖子
 *
 * @author daming
 * @version 2018-08-22 21:09
 */
public class Post implements Serializable {
	private static final long serialVersionUID = -7767804094074598421L;

	private int postId;
	private String postTitle;
	private String postText;
	private int postType = 2;
	private int boardId;
	private Date createTime;
	private User user;
	private Topic topic;

	public int getPostId() {
		return postId;
	}

	public Post setPostId(int postId) {
		this.postId = postId;
		return this;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public Post setPostTitle(String postTitle) {
		this.postTitle = postTitle;
		return this;
	}

	public String getPostText() {
		return postText;
	}

	public Post setPostText(String postText) {
		this.postText = postText;
		return this;
	}
	
	public int getPostType() {
		return postType;
	}

	public Post setPostType(int postType) {
		this.postType = postType;
		return this;
	}


	public int getBoardId() {
		return boardId;
	}

	public Post setBoardId(int boardId) {
		this.boardId = boardId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Post setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Post setUser(User user) {
		this.user = user;
		return this;
	}

	public Topic getTopic() {
		return topic;
	}

	public Post setTopic(Topic topic) {
		this.topic = topic;
		return this;
	}

	public Post() {
		super();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("postId", postId)
				.append("postTitle", postTitle)
				.append("postText", postText)
				.append("boardId", boardId)
				.append("createTime", createTime)
				.append("user", user)
				.append("topic", topic)
				.toString();
	}
}
