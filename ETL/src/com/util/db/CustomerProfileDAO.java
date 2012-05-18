package com.util.db;

public interface CustomerProfileDAO {
   
	public boolean insertCustomerProfile(CustomerProfile customerProfile);
	
	public boolean updateCustomerProfile(CustomerProfile customerProfile);
	
	public CustomerProfile queryCustomerProfile(Long id);
	
	public boolean deleteCustomerProfile(Long source);
	
}
