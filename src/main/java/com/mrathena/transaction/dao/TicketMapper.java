package com.mrathena.transaction.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.mrathena.transaction.entity.Ticket;

@Mapper
public interface TicketMapper {

	/** ticket */
	int deleteByPrimaryKey(Integer id);

	/** ticket */
	int insert(Ticket record);

	/** ticket */
	int insertSelective(Ticket record);

	/** ticket */
	Ticket selectByPrimaryKey(Integer id);

	/** ticket */
	int updateByPrimaryKeySelective(Ticket record);

	/** ticket */
	int updateByPrimaryKey(Ticket record);
	
	// 悲观锁
	Ticket selectByIdForUpdate(Integer id);
	
	// 乐观锁
	@Update("update ticket set count = count - 1, lastUpdateTime = now() where id = #{id} and lastUpdateTime = #{date}")
	int updateByIdAndLastUpdateDate(@Param("id") int id, @Param("date") Date date);

}