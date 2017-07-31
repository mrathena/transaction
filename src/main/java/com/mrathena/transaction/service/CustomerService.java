package com.mrathena.transaction.service;

public interface CustomerService {

	/**客户购票(票id)*/
	boolean buyTicket(int CustomerId, int TicketId) throws Exception;

}