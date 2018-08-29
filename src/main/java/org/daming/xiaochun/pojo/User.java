package org.daming.xiaochun.pojo;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/** 
* 
* @author : aming
* @date: 2018-08-22 下午5:32:45
* @version : 1.0
*/
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 /**
     *锁定用户对应的状态值 
     */
    public static final int USER_LOCK = 1;
    /**
     * 用户解锁对应的状态值
     */
    public static final int USER_UNLOCK = 0;
    /**
     * 管理员类型
     */
    public static final int FORUM_ADMIN = 2;
    /**
     * 普通用户类型
     */
    public static final int NORMAL_USER = 1;
    
    private int userId;
    private String userName;
    private int userType = NORMAL_USER;
    private String lastIp;
    private Date lastVisit;
    private String password;
    private int locked ;
	private int credit;
	private Set<Board> manBoards = Sets.newHashSet();

    public static int getUserLock() {
        return USER_LOCK;
    }

    public static int getUserUnlock() {
        return USER_UNLOCK;
    }

    public static int getForumAdmin() {
        return FORUM_ADMIN;
    }

    public static int getNormalUser() {
        return NORMAL_USER;
    }

    public int getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getUserType() {
        return userType;
    }

    public User setUserType(int userType) {
        this.userType = userType;
        return this;
    }

    public String getLastIp() {
        return lastIp;
    }

    public User setLastIp(String lastIp) {
        this.lastIp = lastIp;
        return this;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public User setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getLocked() {
        return locked;
    }

    public User setLocked(int locked) {
        this.locked = locked;
        return this;
    }

    public int getCredit() {
        return credit;
    }

    public User setCredit(int credit) {
        this.credit = credit;
        return this;
    }

    public Set<Board> getManBoards() {
        return manBoards;
    }

    public User setManBoards(Set<Board> manBoards) {
        this.manBoards = manBoards;
        return this;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userName", userName)
                .append("userType", userType)
                .append("lastIp", lastIp)
                .append("lastVisit", lastVisit)
                .append("password", password)
                .append("locked", locked)
                .append("credit", credit)
                .append("manBoards", manBoards)
                .toString();
    }
}
