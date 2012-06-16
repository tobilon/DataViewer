package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.tools.Log;

public class CustomerPhoneImp implements CustomerPhoneDAO {

	
	public Long queryCustomerPhone(CustomerProfile customerProfile,ArrayList<String> list) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneQuery");
		/* protected */
		list.clear();
		if (null == state) {
			return 0L;
		}
		try {
			Long[] id = new Long[4];
			id[0] = 0L;
			id[1] = 0L;
			id[2] = 0L;
			id[3] = 0L;
			ResultSet set = null;
			if (customerProfile.getMobile1() != null
					&& customerProfile.getMobile1().length() == 11) {
				state.setString(1, customerProfile.getMobile1());
				set = state.executeQuery();
				if (set.next()) {
					id[0] = set.getLong(1);
				}else{
					list.add(customerProfile.getMobile1());
				}

			}

			if (customerProfile.getMobile2() != null
					&& customerProfile.getMobile2().length() == 11) {
				state.setString(1, customerProfile.getMobile2());
				set = state.executeQuery();
				if (set.next()) {
					id[1] = set.getLong(1);
				}else{
					list.add(customerProfile.getMobile2());
				}

			}

			if (customerProfile.getMobile3() != null
					&& customerProfile.getMobile3().length() == 11) {
				state.setString(1, customerProfile.getMobile3());
				set = state.executeQuery();
				if (set.next()) {
					id[2] = set.getLong(1);
				}else{
					list.add(customerProfile.getMobile3());
				}

				
			}

			if (customerProfile.getMobile4() != null
					&& customerProfile.getMobile4().length() == 11) {
				state.setString(1, customerProfile.getMobile4());
				set = state.executeQuery();
				if (set.next()) {
					id[3] = set.getLong(1);
			    }else{
					list.add(customerProfile.getMobile4());
				}
	
			}
			
			Long index = 0L;
			
			for(int i = 0; i < 4; i++){
				if(id[i].longValue() != 0L){
					index = id[i];
					break;
				}
			}
			
			for(int i = 0; i < 4; i++){
				if(index.longValue() != id[i].longValue() && 
				  id[i].longValue() != 0){
					return -1L;
				}
			}
			
			return index;
			

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryCustomerPhone failed");
			e.printStackTrace();
		}

		return 0L;
	}

	public boolean insertCustomerPhone(CustomerPhone customerPhone) throws SQLException{
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneInsert");

		if (null == state) {
			Log.error("insertCustom erPhone failed");
			return false;
		}

		state.setLong(1, customerPhone.getId());
		state.setString(2, customerPhone.getMobilePhone());
			return state.execute();
		
	}

	public boolean deleteCustomerPhone(CustomerProfile customerProfile) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneDelete");

		if (null == state) {
			Log.error("deleteCustomerPhone failed");
			return false;
		}

		try {
			if (customerProfile.getMobile1() != null
					&& customerProfile.getMobile1().length() == 11) {
				state.setString(1, customerProfile.getMobile1());
				state.execute();
			}
			if (customerProfile.getMobile2() != null
					&& customerProfile.getMobile2().length() == 11) {
				state.setString(1, customerProfile.getMobile2());
				state.execute();
			}
			if (customerProfile.getMobile3() != null
					&& customerProfile.getMobile3().length() == 11) {
				state.setString(1, customerProfile.getMobile3());
				state.execute();
			}
			if (customerProfile.getMobile4() != null
					&& customerProfile.getMobile4().length() == 11) {
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
