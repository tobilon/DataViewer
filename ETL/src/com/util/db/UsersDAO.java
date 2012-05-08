package com.util.db;

import java.util.List;

import com.util.export.Users;

public interface UsersDAO {
	public boolean insert(Users user);	//增
	public boolean delete(String id);   //单条删除
	public boolean delete(String[] userIds);  //批量删除
	public boolean update(Users user);  //修改
	public List<Users> query();     //全部查询
	public Users query(String id);  //单记录查询

}