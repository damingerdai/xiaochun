package org.daming.xiaochun.exceptions;

/**
 * Desc: 用户存在异常
 *
 * @author daming
 * @version 2018-08-22 21:21
 */
public class UserExistException extends Exception {
	private static final long serialVersionUID = -2682554347096332729L;

	public UserExistException(String errorMsg) {
		super(errorMsg);
	}
}
