package com.util.db;

public interface CustomerMailDAO {

	public Long queryCustomerMail(CustomerProfile customerProfile);
	
	public boolean insertCustomerMail(CustomerMail customerMail);
	
	public boolean deleteCustomerMail(CustomerProfile customerProfile);
}