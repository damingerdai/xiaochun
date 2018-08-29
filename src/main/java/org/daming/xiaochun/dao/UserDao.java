package org.daming.xiaochun.dao;

import org.daming.xiaochun.pojo.User;

import java.util.List;

/**
 * Desc: User对象Dao
 *
 * @author daming
 * @version 2018-08-22 21:53
 */
public interface UserDao {

	User get(int id);
	
	User getUserByUserName(String userName);

	List<User> queryUserByUserName(String userName);
	
	List<User> list();
	
	void save(User user);
	
	void update(User user);
}
