package org.daming.xiaochun.dao;

import java.util.List;

import org.daming.xiaochun.pojo.Board;

public interface BoardDao {
	
	Board get(int id);
	
	long getBoardNum();
	
	void update(Board board);
	
	void save(Board board);
	
	void delete(int id);
	
	List<Board> list();
}
