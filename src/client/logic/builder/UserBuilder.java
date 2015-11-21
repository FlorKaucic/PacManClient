package client.logic.builder;

import client.logic.User;

public class UserBuilder {
	private int id;
	private String nickname = null;
	private int profile = -1;
	
	
	public UserBuilder(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public int getProfile() {
		return this.profile;
	}

	public UserBuilder withNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public UserBuilder withProfile(int profile) {
		this.profile = profile;
		return this;
	}

	public User build() {
		return new User(this);
	}
	
}
