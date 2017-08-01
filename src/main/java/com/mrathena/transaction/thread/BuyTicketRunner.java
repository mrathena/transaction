package com.mrathena.transaction.thread;

import java.util.concurrent.CountDownLatch;

import com.mrathena.transaction.service.TicketService;

public class BuyTicketRunner implements Runnable {

	private TicketService service;
	private CountDownLatch latch;

	public BuyTicketRunner(TicketService service, CountDownLatch latch) {
		this.service = service;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			service.buyTicket(1);
			latch.countDown();// 执行完毕, 计数器减1
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}