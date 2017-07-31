package com.mrathena.transaction.thread;

import java.util.concurrent.CountDownLatch;

import com.mrathena.transaction.service.TicketService;

public class 售票Runner implements Runnable {

	private TicketService service;
	private CountDownLatch latch;

	public 售票Runner(TicketService ticketService, CountDownLatch latch) {
		this.service = ticketService;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			service.售票(1);
			latch.countDown();// 执行完毕, 计数器减1
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}