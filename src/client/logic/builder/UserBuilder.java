package client.logic.builder;

import client.logic.User;

public class UserBuilder {
	private int id;
	private String nickname = null;
	private String profile = "VIEWER";
	
	
	public UserBuilder(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getProfile() {
		return this.profile;
	}

	public UserBuilder withNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public UserBuilder withProfile(String profile) {
		this.profile = profile;
		return this;
	}

	public User build() {
		return new User(this);
	}
	
}
