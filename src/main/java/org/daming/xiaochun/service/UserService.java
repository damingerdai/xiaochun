package org.daming.xiaochun.service;

import org.daming.xiaochun.exceptions.UserExistException;
import org.daming.xiaochun.pojo.User;

import java.util.List;

/**
 * Desc: 用户管理服务类，负责查询用户、注册用户、锁定用户等操作
 *
 * @author daming
 * @version 2018-08-22 21:23
 */
public interface UserService {

	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * @param user
	 * @throws UserExistException
	 */
	void register(User user) throws UserExistException;

	/**
	 * 更新用户
	 * @param user
	 */
	void update(User user);

	/**
	 * 根据用户名/密码查询 User对象
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);

	/**
	 * 根据userId加载User对象
	 * @param userId
	 * @return
	 */
	User getUserById(int userId);

	/**
	 * 将用户锁定，锁定的用户不能够登录
	 * @param userName 锁定目标用户的用户名
	 */
	void lockUser(String userName);

	/**
	 * 解除用户的锁定
	 * @param userName 解除锁定目标用户的用户名
	 */
	void unlockUser(String userName);

	/**
	 * 根据用户名为条件，执行模糊查询操作
	 * @param userName 查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	List<User> queryUserByUserName(String userName);

	/**
	 * 获取所有用户
	 * @return 所有用户
	 */
	List<User> getAllUsers();

	/**
	 * 登陆成功
	 * @param user
	 */
	void loginSuccess(User user);
}
