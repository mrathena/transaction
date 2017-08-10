package com.mrathena.transaction.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mrathena.transaction.entity.User;

@Mapper
public interface UserMapper {

	/** user */
	int deleteByPrimaryKey(Long id);

	/** user */
	int insert(User record);

	/** user */
	int insertSelective(User record);

	/** user */
	User selectByPrimaryKey(Long id);

	/** user */
	int updateByPrimaryKeySelective(User record);

	/** user */
	int updateByPrimaryKey(User record);
	
	int selectCountByUsername(String username);

}