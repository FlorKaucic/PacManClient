package client.logic;

import client.logic.builder.UserBuilder;

public class User {
	private int id;
	private String nickname;
	private int profile;	
	
	public User(UserBuilder builder){
		this.id = builder.getId();
		this.nickname = builder.getNickname();
		this.profile = builder.getProfile();
	}

	public int getId() {
		return id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getProfile() {
		return profile;
	}

}
