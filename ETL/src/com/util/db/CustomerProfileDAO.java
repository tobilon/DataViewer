package com.util.db;

import java.util.ArrayList;

public interface CustomerProfileDAO {
   
	public boolean insertCustomerProfile(CustomerProfile customerProfile);
	
	public boolean updateCustomerProfile(CustomerProfile customerProfile);
	
	public CustomerProfile queryCustomerProfile(Long id);
	
	public boolean deleteCustomerProfile(Long id);
	
	public void queryCutomerProfile(ArrayList<CustomerProfile> list,Long source);
	
	public Long getLastId();
}
