package org.daming.xiaochun.pojo;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

/** 
* 
* @author: aming
* @date: 2018-08-22 下午5:28:56
* @version: 1.0
*/
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int boardId;
	private String boardName;
	private String boardDesc;
	private int topicNum ;
	private Set<User> users = Sets.newHashSet();

	public int getBoardId() {
		return boardId;
	}

	public Board setBoardId(int boardId) {
		this.boardId = boardId;
		return this;
	}

	public String getBoardName() {
		return boardName;
	}

	public Board setBoardName(String boardName) {
		this.boardName = boardName;
		return this;
	}

	public String getBoardDesc() {
		return boardDesc;
	}

	public Board setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
		return this;
	}

	public int getTopicNum() {
		return topicNum;
	}

	public Board setTopicNum(int topicNum) {
		this.topicNum = topicNum;
		return this;
	}

	public Set<User> getUsers() {
		return users;
	}

	public Board setUsers(Set<User> users) {
		this.users = users;
		return this;
	}

	public Board() {
		super();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("boardId", boardId)
				.append("boardName", boardName)
				.append("boardDesc", boardDesc)
				.append("topicNum", topicNum)
				.append("users", users)
				.toString();
	}
}
