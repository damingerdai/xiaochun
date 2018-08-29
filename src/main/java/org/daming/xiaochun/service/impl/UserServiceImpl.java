package org.daming.xiaochun.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.daming.xiaochun.dao.LoginLogDao;
import org.daming.xiaochun.dao.UserDao;
import org.daming.xiaochun.exceptions.UserExistException;
import org.daming.xiaochun.pojo.LoginLog;
import org.daming.xiaochun.pojo.User;
import org.daming.xiaochun.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private LoginLogDao loginLogDao;

	@Override
	public void register(User user) throws UserExistException {
		Assert.notNull(user, "param 'user' is required");
		Optional<User> optional = Optional.ofNullable(this.getUserByUserName(user.getUserName()));
		if (optional.isPresent()) {
			throw new UserExistException("用户名已经存在");
		} else {
			userDao.save(user.setCredit(100).setUserType(1));
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.get(userId);
	}

	@Override
	public void lockUser(String userName) {
		Optional.ofNullable(userDao.getUserByUserName(userName))
				.ifPresent(user -> userDao.update(user.setLocked(User.USER_LOCK)));
	}

	@Override
	public void unlockUser(String userName) {
		Optional.ofNullable(userDao.getUserByUserName(userName))
				.ifPresent(user -> userDao.update(user.setLocked(User.USER_UNLOCK)));
	}

	@Override
	public List<User> queryUserByUserName(String userName) {
		return userDao.queryUserByUserName(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.list();
	}

	@Override
	public void loginSuccess(User user) {
		user.setCredit(user.getCredit() + 5);
		LoginLog loginLog = new LoginLog()
				.setUser(user)
				.setIp(user.getLastIp())
				.setLoginDate(new Date());
		userDao.update(user);
		loginLogDao.save(loginLog);
	}

	public UserServiceImpl(UserDao userDao, LoginLogDao loginLogDao) {
		super();
		this.userDao = userDao;
		this.loginLogDao = loginLogDao;
	}

}
