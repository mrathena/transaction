package com.mrathena.transaction.entity;

/** customer */
public class Customer {

	/** customer.id */
	private Integer id;
	/** customer.balance */
	private Integer balance;

	/** customer.id */
	public Integer getId() {
		return id;
	}
	/** customer.id */
	public Customer withId(Integer id) {
		this.setId(id);
		return this;
	}
	/** customer.id */
	public void setId(Integer id) {
		this.id = id;
	}
	/** customer.balance */
	public Integer getBalance() {
		return balance;
	}
	/** customer.balance */
	public Customer withBalance(Integer balance) {
		this.setBalance(balance);
		return this;
	}
	/** customer.balance */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

}