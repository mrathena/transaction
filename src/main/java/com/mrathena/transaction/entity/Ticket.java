package com.mrathena.transaction.entity;

import java.util.Date;

/** ticket */
public class Ticket {

	/** ticket.id */
	private Integer id;
	/** ticket.price */
	private Integer price;
	/** ticket.count */
	private Integer count;
	/** ticket.lastUpdateTime */
	private Date lastUpdateTime;

	/** ticket.id */
	public Integer getId() {
		return id;
	}
	/** ticket.id */
	public Ticket withId(Integer id) {
		this.setId(id);
		return this;
	}
	/** ticket.id */
	public void setId(Integer id) {
		this.id = id;
	}
	/** ticket.price */
	public Integer getPrice() {
		return price;
	}
	/** ticket.price */
	public Ticket withPrice(Integer price) {
		this.setPrice(price);
		return this;
	}
	/** ticket.price */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/** ticket.count */
	public Integer getCount() {
		return count;
	}
	/** ticket.count */
	public Ticket withCount(Integer count) {
		this.setCount(count);
		return this;
	}
	/** ticket.count */
	public void setCount(Integer count) {
		this.count = count;
	}
	/** ticket.lastUpdateTime */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/** ticket.lastUpdateTime */
	public Ticket withLastUpdateTime(Date lastUpdateTime) {
		this.setLastUpdateTime(lastUpdateTime);
		return this;
	}
	/** ticket.lastUpdateTime */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}