
package com.util.db;

public interface CustomerPhoneDAO {

	public Long queryCustomerPhone(CustomerProfile customerProfile);
	
	public boolean insertCustomerPhone(CustomerPhone customerPhone);
	
	public boolean deleteCustomerPhone(CustomerProfile customerProfile);
}