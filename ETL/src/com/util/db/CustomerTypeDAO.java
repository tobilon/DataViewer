package com.util.db;

import java.util.ArrayList;

public interface CustomerTypeDAO {
	
	public boolean  queryAll(ArrayList<CustomerType> list);
	
	public boolean insertCustomerType(CustomerType customertype);

}
