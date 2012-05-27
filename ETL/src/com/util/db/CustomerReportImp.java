package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.tools.Log;

public class CustomerReportImp implements CustomerReportDAO {
	
    public boolean insertCustomerReport(CustomerReport customerReport)
    {
        PreparedStatement state = DbCreateStateFactory.createStateInstance("reportInsert");
	    
		if(null == state){
			return false;
		}
		
		try{
			state.setString(1, customerReport.getFilename());
			state.setString(2, customerReport.getUsertype());
			state.setLong(3, customerReport.getUsernum());
			state.setLong(4, customerReport.getUserImport());
			state.setLong(5, customerReport.getUserMerge());
		    return state.execute();
		}catch (SQLException e) {
			Log.error("insertCustomerReport failed");
		    e.printStackTrace();
	    }
	
	    return false;			
    }
	
	public Long getLastId()
	{
		PreparedStatement state = DbCreateStateFactory.createStateInstance("reportMaxID");
		/* protected*/
		if(null == state){
			return null;
		}
		try {
			ResultSet set = state.executeQuery();
			if(set.next())
			{
				return set.getLong(1);
			}
			else {
				return 0L;
			}
		} catch (SQLException e) {
			Log.error("getLastId failed");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CustomerReport queryCustomerReport(Long id)
	{
		CustomerReport customerReport = new CustomerReport();
		
		PreparedStatement state = DbCreateStateFactory.createStateInstance("reportQuery");
		/* protected*/
		if(null == state){
			return null;
		}
		try {
			state.setLong(1, id);
			ResultSet set = state.executeQuery();
			if(set.next())
			{
				customerReport.setId(set.getLong(1));
				customerReport.setFilename(set.getString(2));
				customerReport.setUsernum(set.getLong(3));
				customerReport.setUserImport(set.getLong(4));
				customerReport.setUserMerge(set.getLong(5));
				return customerReport;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryCustomerReport failed");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean updateCustomerReport(CustomerReport customerReport)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("reportUpdate");
	    
		if(null == state){
			return false;
		}
		
		try{
			state.setString(1, customerReport.getFilename());
			state.setString(2, customerReport.getUsertype());
			state.setLong(3, customerReport.getUsernum());
			state.setLong(4, customerReport.getUserImport());
			//state.setLong(5, customerReport.getId());
			state.setLong(5, customerReport.getUserMerge());
		    return state.execute();
		}catch (SQLException e) {
			Log.error("updateCustomerReport failed");
		    e.printStackTrace();
	    }
	
	    return false;
	}
	
	public boolean deleteCustomerReport(Long id)
	{
        PreparedStatement state = DbCreateStateFactory.createStateInstance("reportDelete");
	    
		if(null == state){
			return false;
		}
			
		try{
			state.setLong(1, id);
			return state.execute();
		} catch (SQLException e) {
			Log.error("deleteCustomerReport failed");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean  queryAll(ArrayList<CustomerReport> list) {
		// TODO Auto-generated method stub
		PreparedStatement state = DbCreateStateFactory.createStateInstance("reportQueryall");
		/* protected*/
		if(null == state){
			return false;
		}
		
		try {
			ResultSet set = state.executeQuery();
			return CustomerReportWapper.wapper(set, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryAll failed");
			e.printStackTrace();
		}
		return false;
	}
}

/* °ü×°Àà*/
class CustomerReportWapper {
	public static boolean wapper(ResultSet set,ArrayList<CustomerReport> list){
		try {
			while(set.next()){
				CustomerReport report = new CustomerReport();
				report.setId(set.getLong(1));
				report.setFilename(set.getString(2));
				report.setUsertype(set.getString(3));
				report.setUsernum(set.getLong(4));
				report.setUserImport(set.getLong(5));
				report.setUserMerge(set.getLong(6));
				report.setTime(set.getString(7));
				list.add(report);
				
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
