
package com.util.db;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerPhoneDAO {

	public Long queryCustomerPhone(CustomerProfile customerProfile,ArrayList<String> list);
	
	public boolean insertCustomerPhone(CustomerPhone customerPhone) throws SQLException;
	
	public boolean deleteCustomerPhone(CustomerProfile customerProfile);
}