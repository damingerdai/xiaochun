package org.daming.xiaochun.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: 主题
 *
 * @author daming
 * @version 2018-08-22 21:10
 */
public class Topic implements Serializable {
	private static final long serialVersionUID = 5627133042720809415L;

	/**
	 * 精华主题帖子
	 */
	public static final int DIGEST_TOPIC = 1;
	/**
	 * 普通的主题帖子
	 */
	public static final int NOT_DIGEST_TOPIC = 0;

	private int topicId;
	private String topicTitle;
	private User user;
	private int boardId;
	private MainPost mainPost = new MainPost();
	private Date lastPost = new Date();
	private Date createTime = new Date();
	private int views;
	private int replies;
	private int digest = NOT_DIGEST_TOPIC;
	
	

	public int getTopicId() {
		return topicId;
	}

	public Topic setTopicId(int topicId) {
		this.topicId = topicId;
		return this;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public Topic setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Topic setUser(User user) {
		this.user = user;
		return this;
	}

	public int getBoardId() {
		return boardId;
	}

	public Topic setBoardId(int boardId) {
		this.boardId = boardId;
		return this;
	}

	public MainPost getMainPost() {
		return mainPost;
	}

	public Topic setMainPost(MainPost mainPost) {
		this.mainPost = mainPost;
		return this;
	}

	public Date getLastPost() {
		return lastPost;
	}

	public Topic setLastPost(Date lastPost) {
		this.lastPost = lastPost;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Topic setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public int getViews() {
		return views;
	}

	public Topic setViews(int views) {
		this.views = views;
		return this;
	}

	public int getReplies() {
		return replies;
	}

	public Topic setReplies(int replies) {
		this.replies = replies;
		return this;
	}

	public int getDigest() {
		return digest;
	}

	public Topic setDigest(int digest) {
		this.digest = digest;
		return this;
	}

	public Topic() {
		super();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("topicId", topicId)
				.append("topicTitle", topicTitle)
				.append("user", user)
				.append("boardId", boardId)
				.append("mainPost", mainPost)
				.append("lastPost", lastPost)
				.append("createTime", createTime)
				.append("views", views)
				.append("replies", replies)
				.append("digest", digest)
				.toString();
	}
}
