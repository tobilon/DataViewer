package com.util.db;

public interface CustomerReportDAO {

	public boolean insertCustomerReport(CustomerReport customerReport);
	
	public Long getLastId();
	
	public CustomerReport queryCustomerReport(Long id);
	
	public boolean updateCustomerReport(CustomerReport customerReport);
	
	public boolean deleteCustomerReport(Long id);
}
