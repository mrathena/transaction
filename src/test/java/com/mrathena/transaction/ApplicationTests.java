package com.mrathena.transaction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrathena.transaction.service.TicketService;
import com.mrathena.transaction.thread.BuyTicketRunner;
import com.mrathena.transaction.tool.ThreadKit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TicketService customerService;
	
	@Test
	public void 测试多线程并发售票() {
		AtomicInteger count = new AtomicInteger(7);
		for (int i = 1; i <= 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String name = ThreadKit.getCurrentThreadName();
					int oldCount = count.getAndDecrement();
					if (oldCount > 0) {
						int newCount = oldCount - 1;
						System.out.println(name + ":购前余票:" + oldCount + ", 购后余票:" + newCount);
					} else {
						oldCount = 0;
						System.out.println(name + ":购前余票:" + oldCount + ", 票已售罄,无法购票");
						return;
					}
				}
			}, String.valueOf(i)).start();
		}
	}
	
	@Test
	public void 测试多线程并发售票2() {
		// 创建的子线程数
		int count = 2;
		CountDownLatch latch = new CountDownLatch(count);
		
		for (int i = 1; i <= count; i++) {
			String threadName = "[" + i + "]";
			new Thread(new BuyTicketRunner(customerService, latch), threadName).start();
		}
		
		// 主线程等待
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
