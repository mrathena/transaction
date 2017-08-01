package com.mrathena.transaction.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
	@Select("select * from ticket where id = #{id} for update")
	Ticket selectByIdForUpdate(Integer id);

	// 乐观锁, 这里传入的 version = ticket.getVersion() + ticket.getCount(),
	// ticket是更新之前查出来的ticket
	@Update("update ticket set count=count-1, version=version+1 where id = 1 and version < #{version}")
	int updateByIdAndVersion(@Param("id") Integer id, @Param("version") long version);

}