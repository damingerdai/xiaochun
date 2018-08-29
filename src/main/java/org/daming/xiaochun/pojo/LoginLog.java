package org.daming.xiaochun.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {

	private static final long serialVersionUID = -780364116565734817L;

	private int loginLogId;
	private Date loginDate;
	private User user;
	private String ip;

	public int getLoginLogId() {
		return loginLogId;
	}

	public LoginLog setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
		return this;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public LoginLog setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
		return this;
	}

	public User getUser() {
		return user;
	}

	public LoginLog setUser(User user) {
		this.user = user;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public LoginLog setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public LoginLog() {
		super();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("loginLogId", loginLogId)
				.append("loginDate", loginDate)
				.append("user", user)
				.append("ip", ip)
				.toString();
	}
}
