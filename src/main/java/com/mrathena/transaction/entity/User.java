package com.mrathena.transaction.entity;

/** user */
public class User {

	/** user.id */
	private Long id;
	/** user.username */
	private String username;

	/** user.id */
	public Long getId() {
		return id;
	}
	/** user.id */
	public User withId(Long id) {
		this.setId(id);
		return this;
	}
	/** user.id */
	public void setId(Long id) {
		this.id = id;
	}
	/** user.username */
	public String getUsername() {
		return username;
	}
	/** user.username */
	public User withUsername(String username) {
		this.setUsername(username);
		return this;
	}
	/** user.username */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

}