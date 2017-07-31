package com.mrathena.transaction.dao;

import com.mrathena.transaction.entity.Customer;

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