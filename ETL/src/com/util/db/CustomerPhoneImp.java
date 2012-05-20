package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerPhoneImp {

    public Long queryCustomerPhone(CustomerProfile customerProfile)
    {
    	PreparedStatement state = DbCreateStateFactory.createStateInstance("phoneQuery");
		/* protected*/
		if(null == state){
			return 0L;
		}
		try {			
			state.setString(1, customerProfile.getMobile1());
			ResultSet set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			
			state.setString(1, customerProfile.getMobile2());
			set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			
			state.setString(1, customerProfile.getMobile3());
			set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			
			state.setString(1, customerProfile.getMobile4());
			set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
    }
	
	public boolean insertCustomerPhone(CustomerPhone customerPhone)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("phoneInsert");
	    
		if(null == state){
			return false;
		}
		
		try{
			state.setLong(1, customerPhone.getId());
			state.setString(2, customerPhone.getMobilePhone());
			return state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteCustomerPhone(CustomerProfile customerProfile)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("phoneDelete");
	    
		if(null == state){
			return false;
		}
			
		try{
			state.setLong(1, customerProfile.getId());
			return state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
