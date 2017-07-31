package com.mrathena.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrathena.transaction.dao.CustomerMapper;
import com.mrathena.transaction.dao.TicketMapper;
import com.mrathena.transaction.entity.Customer;
import com.mrathena.transaction.entity.Ticket;
import com.mrathena.transaction.tool.ThreadKit;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public boolean buyTicket(int CustomerId, int TicketId) throws Exception {
		log("购票流程开始");
		// 购票流程(假定每次购票只能购买一张)
		// 1.查询余票是否足够(获取余票和票价)
		log("查询剩余票数");
		Ticket ticket = ticketMapper.selectByPrimaryKey(TicketId);
		ThreadKit.currentThreadSleep(1000);// 模拟查询余票时间, 创造并发问题发生的可能性
		int count = ticket.getCount();
		log("剩余票数: " + count);
		if (count > 0) {
			// 2.查询客户余额时候足够(和票价对比)
			int price = ticket.getPrice();
			log("查询用户余额");
			Customer customer = customerMapper.selectByPrimaryKey(CustomerId);
			ThreadKit.currentThreadSleep(1000);// 模拟查询余额时间, 创造并发问题发生的可能性
			int balance = customer.getBalance();
			log("用户余额: " + balance);
			if (balance >= price) {
				// 3.更新余票数量(票数-1)
				log("更新余票数量");
				int flag = ticketMapper.updateByIdAndLastUpdateDate(CustomerId, ticket.getLastUpdateTime());
				if (flag != 0) {
					// 4.更新客户余额(余额-票价)
					log("更新用户余额");
					customer.setBalance(balance - price);
					flag = customerMapper.updateByPrimaryKeySelective(customer);
					if (flag != 0) {
						log("用户(" + CustomerId + ")购票成功");
					} else {
						error("更新用户余额异常");
					}
				} else {
					error("更新余票数量异常");
				}
			} else {
				error("客户(" + CustomerId + ")余额不足. 票价:" + price + ", 余额:" + balance);
			}
		} else {
			error("票已售完");
		}
		log("购票流程结束, 购票成功");
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