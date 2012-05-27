package com.util.db;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CustomerMailDAO {

	public Long queryCustomerMail(CustomerProfile customerProfile);
	
	public boolean insertCustomerMail(CustomerMail customerMail);
	
	public boolean deleteCustomerMail(CustomerProfile customerProfile);

}