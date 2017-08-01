package com.mrathena.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mrathena.transaction.dao.TicketMapper;
import com.mrathena.transaction.entity.Ticket;
import com.mrathena.transaction.tool.ThreadKit;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;
	
	@Override
	public int getTicketCount(int ticketId) {
		return ticketMapper.selectByPrimaryKey(ticketId).getCount();
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean buyTicket(int ticketId) throws Exception {
		log("查询剩余票数");
		Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
		ThreadKit.currentThreadSleep(1000);// 模拟查询余票时间, 创造并发问题发生的可能性
		int count = ticket.getCount();
		if (count > 0) {
			log("剩余票数:" + count + ", 开始购票");
			int newCount = count - 1;
			ticket.setCount(newCount);
			int flag = ticketMapper.updateByPrimaryKeySelective(ticket);
			if (flag != 0) {
				log("购票成功, 剩余票数:" + newCount);
			} else {
				error("购票失败");
			}
		} else {
			error("票已售完, 无法购票");
		}
		return true;
	}
	
	private void log(String message) {
		System.out.println(ThreadKit.getCurrentThreadName() + ": " + message);
	}

	private void error(String message) throws Exception {
		log(message);
		String value = ThreadKit.getCurrentThreadName() + ": " + message;
		throw new Exception(value);
	}

}