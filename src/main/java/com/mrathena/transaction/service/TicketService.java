package com.mrathena.transaction.service;

public interface TicketService {

	int getTicketCount(int ticketId);
	
	boolean buyTicket(int ticketId) throws Exception;
	
}