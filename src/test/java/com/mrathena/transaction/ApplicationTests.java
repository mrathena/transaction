package com.mrathena.transaction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mrathena.transaction.service.TicketService;
import com.mrathena.transaction.tool.ThreadKit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TicketService service;
	
	@Test
	public void 测试多线程并发售票() {
		// 没有数据库参与
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
	public void 测试多线程并发售票_未做任何处理_会有并发问题() {
		
		int count = 10;// 创建的子线程数
		CountDownLatch latch = new CountDownLatch(count);
		
		long start = System.currentTimeMillis();
		
		for (int i = 1; i <= count; i++) {
			String threadName = "[" + i + "]";
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						service.buyTicket(1);
						latch.countDown();// 执行完毕, 计数器减1
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, threadName).start();
		}
		
		try {
			latch.await();// 主线程等待
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("本次测试用时: " + (end - start) + "ms");
		
	}
	
	@Test
	public void 测试多线程并发售票_使用悲观锁_会有严重性能问题() {
		// 可测试10个人买3张票的时间
		
		int count = 10;// 创建的子线程数
		CountDownLatch latch = new CountDownLatch(count);
		
		long start = System.currentTimeMillis();
		
		for (int i = 1; i <= count; i++) {
			String threadName = "[" + i + "]";
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						service.buyTicket2(1);
						latch.countDown();// 执行完毕, 计数器减1
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, threadName).start();
		}
		
		try {
			latch.await();// 主线程等待
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("本次测试用时: " + (end - start) + "ms");
	}

	@Test
	public void 测试多线程并发售票_使用乐观锁变种_不用事务_效果不错() {
		// 可测试1000个人买10张票的时间
		
		int count = 10;// 创建的子线程数
		CountDownLatch latch = new CountDownLatch(count);
		
		long start = System.currentTimeMillis();
		
		for (int i = 1; i <= count; i++) {
			String threadName = "[" + i + "]";
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						service.buyTicket3(1);
						latch.countDown();// 执行完毕, 计数器减1
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, threadName).start();
		}
		
		try {
			latch.await();// 主线程等待
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("本次测试用时: " + (end - start) + "ms");
		
	}

}
