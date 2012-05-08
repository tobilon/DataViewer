package com.util.db;

import java.sql.ResultSet;
import java.util.List;

import com.util.export.Users;


public class UsersDAOImpl implements UsersDAO {

	private OptTemplate optTemplate = null;

	public UsersDAOImpl(OptTemplate optTemplate) {
		super();
		this.optTemplate = optTemplate;
	}

	public boolean delete(String id) {
		String sql = "delete from users where id=?";
		Object[] obj = { id };
		return optTemplate.update(sql, obj, false);
	}

	public boolean delete(String[] userIds) {
		StringBuffer sbStr = new StringBuffer();
		Object[] obj = userIds;
		;
		for (int i = 0; i < userIds.length; i++) {
			sbStr.append("?,");
		}
		String sql = "delete from users where id in("
				+ sbStr.substring(0, sbStr.length() - 1) + ")";
		return optTemplate.update(sql, obj, false);
	}

	public boolean insert(Users user) {
		String sql = "insert into users(id,name,age) values(?,?,?)";
		
		//
		Object[] obj = {user.getName(),user.getAddress()};
		
		return optTemplate.update(sql, obj, true);
	}

	@SuppressWarnings("unchecked")
	public List<Users> query() {
		String sql = "select * from users";
		Object[] obj = {};
		return (List<Users>) optTemplate.query(sql, obj, new UsersDAOObjectMapper());
		
	}

	public Users query(String id) {
		String sql = "select * from users";
		Object[] obj = {};
		return (Users) optTemplate.query(sql, obj, new UsersDAOObjectMapper()).get(0);
	}

	


	public boolean update(Users user) {
		String sql = "update users set name=?,age=? where id=?";
		Object[] obj = {user.getName(),user.getAge(),user.getId()};
		return optTemplate.update(sql, obj, false);
	}
}

class UsersDAOObjectMapper implements ObjectMapper{
	public Object mapping(ResultSet rs){
		Users u=new Users();	
			try{
				
				u.setId(Integer.valueOf(rs.getString("id")));
				u.setName(rs.getString("age"));
				u.setName(rs.getString("name"));

				
			}catch(Exception ex){
				ex.printStackTrace();
			}

		return u;
	}
	
}



