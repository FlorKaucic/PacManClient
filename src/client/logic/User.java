package client.logic;

public class User {
	private int id;
	private String username;
	private String nickname;
	
	public User(String username){
		this.username = username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String setUsername(String username) {
		return username;
	}

	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
