package com.assesment.followers;

public class User {

	
	private String userId;
	private String follow;
	private int level;
	
	
	public User(String userId, String follow, int level) {
		super();
		this.userId = userId;
		this.follow = follow;
		this.level = level;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFollow() {
		return follow;
	}
	public void setFollow(String follow) {
		this.follow = follow;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
