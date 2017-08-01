package com.mrathena.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrathena.transaction.dao.TicketMapper;
import com.mrathena.transaction.entity.Ticket;
import com.mrathena.transaction.tool.ThreadKit;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;

	private void log(String message) {
		System.out.println(ThreadKit.getCurrentThreadName() + ": " + message);
	}
	
	// 多线程并发售票_未做任何处理_会有并发问题
	@Override
	public boolean buyTicket(int ticketId) throws Exception {
		log("查询剩余票数");
		Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
		ThreadKit.currentThreadSleep(1000);// 模拟查询余票时间, 创造并发问题发生的可能性
		int count = ticket.getCount();
		if (count > 0) {
			log("购前余票:" + count + ", 开始购票");
			int newCount = count - 1;
			ticket.setCount(newCount);
			int flag = ticketMapper.updateByPrimaryKeySelective(ticket);
			log(flag != 0 ? "购票成功" : "购票失败");
		} else {
			log("票已售完, 无法购票");
		}
		return true;
	}

	// 多线程并发售票_使用悲观锁_会有严重性能问题
	@Override
	@Transactional
	public boolean buyTicket2(int ticketId) throws Exception {
		log("查询剩余票数");
		Ticket ticket = ticketMapper.selectByIdForUpdate(ticketId);
		ThreadKit.currentThreadSleep(1000);// 模拟查询余票时间, 创造并发问题发生的可能性
		int count = ticket.getCount();
		if (count > 0) {
			log("购前余票:" + count + ", 开始购票");
			int newCount = count - 1;
			ticket.setCount(newCount);
			int flag = ticketMapper.updateByPrimaryKeySelective(ticket);
			log(flag != 0 ? "购票成功" : "购票失败");
		} else {
			log("票已售完, 无法购票");
		}
		return true;
	}

	// 多线程并发售票_使用乐观锁变种_效果不错_不用事务
	@Override
	public boolean buyTicket3(int ticketId) throws Exception {
		log("查询剩余票数");
		Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
		ThreadKit.currentThreadSleep(1000);// 模拟查询余票时间, 创造并发问题发生的可能性
		int count = ticket.getCount();
		if (count > 0) {
			log("购前余票:" + count + ", 开始购票");
			long newVersion = ticket.getVersion() + count;
			int flag = ticketMapper.updateByIdAndVersion(ticketId, newVersion);
			log(flag != 0 ? "购票成功" : "购票失败");
		} else {
			log("票已售完, 无法购票");
		}
		return true;
	}

}