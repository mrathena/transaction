package com.mrathena.transaction.entity;

/** ticket */
public class Ticket {

	/** ticket.id */
	private Integer id;
	/** ticket.count */
	private Integer count;
	/** ticket.version */
	private Long version;

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
	/** ticket.version */
	public Long getVersion() {
		return version;
	}
	/** ticket.version */
	public Ticket withVersion(Long version) {
		this.setVersion(version);
		return this;
	}
	/** ticket.version */
	public void setVersion(Long version) {
		this.version = version;
	}

}