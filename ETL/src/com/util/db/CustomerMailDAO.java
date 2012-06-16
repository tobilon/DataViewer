package com.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerMailDAO {

	public Long queryCustomerMail(CustomerProfile customerProfile);
	
	public boolean insertCustomerMail(CustomerMail customerMail) throws SQLException;
	
	public boolean deleteCustomerMail(CustomerProfile customerProfile);

}