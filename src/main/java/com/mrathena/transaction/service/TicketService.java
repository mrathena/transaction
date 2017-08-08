package com.mrathena.transaction.service;

public interface TicketService {

	// 普通代码, 会有并发问题
	boolean buyTicket(int ticketId) throws Exception;
	
	// 悲观锁方式, 会有性能问题
	boolean buyTicket2(int ticketId) throws Exception;
	
	// 乐观锁方式
	boolean buyTicket3(int ticketId) throws Exception;
	
}