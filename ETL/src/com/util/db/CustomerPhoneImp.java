package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.tools.Log;

public class CustomerPhoneImp implements CustomerPhoneDAO {

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
			Log.error("queryCustomerPhone failed");
			e.printStackTrace();
		}
		
		return 0L;
    }
	
	public boolean insertCustomerPhone(CustomerPhone customerPhone)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("phoneInsert");
	    
		if(null == state){
			Log.error("insertCustom erPhone failed"); 
			return false;
		}
		
		try{
			state.setLong(1, customerPhone.getId());
			state.setString(2, customerPhone.getMobilePhone());
			return state.execute();
		} catch (SQLException e) {
			Log.error("insertCustomerPhone failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteCustomerPhone(CustomerProfile customerProfile)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("phoneDelete");
	    
		if(null == state){
			Log.error("deleteCustomerPhone failed");
			return false;
		}
			
		try{
			if(customerProfile.getMobile1().length() == 11){
				state.setString(1, customerProfile.getMobile1());
			    state.execute();
			}
			if(customerProfile.getMobile2().length() == 11){
				state.setString(1, customerProfile.getMobile2());
			    state.execute();
			}
			if(customerProfile.getMobile3().length() == 11){
				state.setString(1, customerProfile.getMobile3());
			    state.execute();
			}
			if(customerProfile.getMobile4().length() == 11){
				state.setString(1, customerProfile.getMobile4());
			    state.execute();
			}
			return true;
		} catch (SQLException e) {
			Log.error("deleteCustomerPhone failed");
			e.printStackTrace();
		}
		
		return false;
	}
}
