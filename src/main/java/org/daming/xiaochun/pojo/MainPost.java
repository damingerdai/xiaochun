package org.daming.xiaochun.pojo;

/**
 * Desc: MainPost
 *
 * @author daming
 * @version 2018-08-22 21:12
 */
public class MainPost extends Post {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4210892026489863033L;
	
	private int postType = 1;

	public int getPostType() {
		return postType;
	}

	public MainPost setPostType(int postType) {
		this.postType = postType;
		return this;
	}
	
	
}
