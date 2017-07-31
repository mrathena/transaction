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
	private TicketMapper mapper;

	@Override
	@Transactional
	public void 售票(Integer id) throws Exception {
		String name = ThreadKit.getCurrentThreadName();
		System.out.println(name + ": 开始查询余票");
		Ticket tickct = mapper.selectByPrimaryKey(id);
		// 悲观锁. 谁先查询到最后一张票,票就是谁的. 而现实是谁都能看到最后一张票,手快的抢得到(即乐观锁)
//		Tickct tickct = mapper.selectByIdForUpdate(id);
		int count = tickct.getCount();
		// 模拟查询过程花费时间, 创造并发可能性
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + ": 查询余票结束, 余票: " + count);
		if (count >= 1) {
			tickct.setCount(count - 1);
			mapper.updateByPrimaryKey(tickct);
			System.out.println(name + ": 更新票数成功, 当前余票: " + tickct.getCount());
		} else {
			System.out.println(name + ": 没票了");
		}
	}
	
}