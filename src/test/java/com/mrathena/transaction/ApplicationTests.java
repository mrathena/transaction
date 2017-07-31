package com.mrathena.transaction;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrathena.transaction.service.TicketService;
import com.mrathena.transaction.thread.售票Runner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TicketService ticketService;
	
	@Test
	public void 测试并发售票() {
		// 创建的子线程数
		int count = 2;
		CountDownLatch latch = new CountDownLatch(count);
		
		new Thread(new 售票Runner(ticketService, latch), "售票线程-1").start();
		new Thread(new 售票Runner(ticketService, latch), "售票线程-2").start();
		
		// 主线程等待
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
