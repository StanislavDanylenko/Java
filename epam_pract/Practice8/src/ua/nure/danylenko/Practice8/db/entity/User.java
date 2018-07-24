package ua.nure.danylenko.Practice8.db.entity;

public class User {

	private int id;

	private String login;

    public User() {
    }

	public User(String login) {
	    this.login = login;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public static User createUser(String user) {
	    User newUser = new User(user);
	    return newUser;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + "]";
	}

}
