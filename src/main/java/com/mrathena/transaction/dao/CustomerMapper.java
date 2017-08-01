package com.mrathena.transaction.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mrathena.transaction.entity.Customer;

@Mapper
public interface CustomerMapper {

	/** customer */
	int deleteByPrimaryKey(Integer id);

	/** customer */
	int insert(Customer record);

	/** customer */
	int insertSelective(Customer record);

	/** customer */
	Customer selectByPrimaryKey(Integer id);

	/** customer */
	int updateByPrimaryKeySelective(Customer record);

	/** customer */
	int updateByPrimaryKey(Customer record);

}