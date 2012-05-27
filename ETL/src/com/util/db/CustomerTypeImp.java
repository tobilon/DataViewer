package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.tools.Log;

public class CustomerTypeImp implements CustomerTypeDAO {

	public boolean insertCustomerType(CustomerType customertype) {
		// TODO Auto-generated method stub
		PreparedStatement state = DbCreateStateFactory.createStateInstance("typeInsert");
		/* protected*/
		if(null == state){
			return false;
		}
		try {
			state.setString(1, customertype.getDisc());
			state.setLong(2, customertype.getTag());
			return state.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("insertCustomerType failed");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean  queryAll(ArrayList<CustomerType> list) {
		// TODO Auto-generated method stub
		PreparedStatement state = DbCreateStateFactory.createStateInstance("typeQuery");
		/* protected*/
		if(null == state){
			return false;
		}
		
		try {
			ResultSet set = state.executeQuery();
			return CustomerTypeWapper.wapper(set, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryAll failed");
			e.printStackTrace();
		}
		return false;
	}
}


/* °ü×°Àà*/
class CustomerTypeWapper {
	public static boolean wapper(ResultSet set,ArrayList<CustomerType> list){
		try {
			while(set.next()){
				CustomerType type = new CustomerType();
				type.setId(set.getInt(1));
				type.setDisc(set.getString(2));
				type.setTag(set.getLong(3));
				list.add(type);
				
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}



