package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.tools.Log;

public class CustomerMailImp implements CustomerMailDAO{

	public Long queryCustomerMail(CustomerProfile customerProfile)
	{
		
		PreparedStatement state = DbCreateStateFactory.createStateInstance("mailQuery");
		/* protected*/
		if(null == state){
			return 0L;
		}
		try {
			state.setString(1, customerProfile.getMail());
			ResultSet set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryCustomerMail failed");
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public boolean insertCustomerMail(CustomerMail customerMail)
	{
		PreparedStatement state = DbCreateStateFactory.createStateInstance("mailInsert");
	    
		if(null == state){
			return false;
		}
		
		try{
			state.setLong(1, customerMail.getId());
			state.setString(2, customerMail.getMail());
			return state.execute();
		} catch (SQLException e) {
			Log.error("insertCustomerMail failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteCustomerMail(CustomerProfile customerProfile)
	{
		PreparedStatement state = DbCreateStateFactory.createStateInstance("mailDelete");
	    
		if(null == state){
			return false;
		}
			
		try{
			state.setString(1, customerProfile.getMail());
			return state.execute();
		} catch (SQLException e) {
			Log.error("deleteCustomerMail failed");
			e.printStackTrace();
		}
		
		return false;
	}
}
