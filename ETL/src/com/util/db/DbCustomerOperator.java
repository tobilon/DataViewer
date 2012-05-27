package com.util.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.gui.FileBrower;
import com.util.tools.Log;

public class DbCustomerOperator {

	CustomerProfileDAO prodao = new CustomerProfileImp();
	CustomerPhoneDAO phoneDao = new CustomerPhoneImp();
	CustomerMailDAO mailDAO = new CustomerMailImp();
	

	public void importOracle(CustomerProfile customer){
		Long ID = IsExistCustomer(customer);
		
		if(0 == ID){
			insertTranction(customer);
		}
		else{
			CustomerProfile oldCustomer = prodao.queryCustomerProfile(ID);
			CustomerProfile newCustomer = combineCustomerProfile(customer,oldCustomer);
			updateTranction(newCustomer,oldCustomer,customer);
		}
	}
	private boolean isExistPhone(String phone,CustomerProfile customer){
		if(phone.equals(customer.getMobile1())){
			return true;
		}
		if(phone.equals(customer.getMobile2())){
			return true;
		}
		if(phone.equals(customer.getMobile3())){
			return true;
		}
		if(phone.equals(customer.getMobile4())){
			return true;
		}
		return false;
	}
	
	private void updateTranction(CustomerProfile newCustomer,
			CustomerProfile oldCustomer, CustomerProfile customerProfile) {
		// TODO Auto-generated method stub
		Connection conn = DbConnection.getConn();
		try {
			conn.setAutoCommit(false);
			prodao.insertCustomerProfile(newCustomer);
			CustomerPhone customerPhone = new CustomerPhone();
			CustomerMail   mail = new CustomerMail();
			
			if(customerProfile.getMobile1().length() == 11 && false == isExistPhone(customerProfile.getMobile1(),oldCustomer)){
				customerPhone.setMobilePhone(customerProfile.getMobile2());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			if(customerProfile.getMobile1().length() == 11 && false == isExistPhone(customerProfile.getMobile2(),oldCustomer)){
				customerPhone.setMobilePhone(customerProfile.getMobile2());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile3().length() == 11 &&  false == isExistPhone(customerProfile.getMobile3(),oldCustomer)){
				customerPhone.setMobilePhone(customerProfile.getMobile3());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile4().length() == 11 && false == isExistPhone(customerProfile.getMobile4(),oldCustomer)){
				customerPhone.setMobilePhone(customerProfile.getMobile4());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMail().length() > 1 && oldCustomer.getMail().length() == 0){
				mail.setMail(customerProfile.getMail());
				mailDAO.insertCustomerMail(mail);
			}
			
			FileBrower.customerReport.setUserMerge(FileBrower.customerReport.getUserMerge() + 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("updateTranction database exception: "+ FileBrower.customerReport.getFilename());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private CustomerProfile combineCustomerProfile(CustomerProfile newCustomer,
			CustomerProfile oldCustomer) {
		// TODO Auto-generated method stub
		CustomerProfile cp = new CustomerProfile();
		if(newCustomer.address.length() > 0 && oldCustomer.address.length() == 0){
			cp.setAddress(newCustomer.address);
		}
		if(newCustomer.name.length() > 0 && oldCustomer.name.length() == 0){
			cp.setAddress(newCustomer.name);
		}
		
		if(newCustomer.city.length() > 0 && oldCustomer.city.length() == 0){
			cp.setAddress(newCustomer.city);
		}
		
		if(newCustomer.province.length() > 0 && oldCustomer.province.length() == 0){
			cp.setAddress(newCustomer.province);
		}
		
		if(newCustomer.company.length() > 0 && oldCustomer.company.length() == 0){
			cp.setAddress(newCustomer.company);
		}
		
		if(newCustomer.post.length() > 0 && oldCustomer.post.length() == 0){
			cp.setAddress(newCustomer.post);
		}
		
		if(newCustomer.fixPhone1.length() > 0 && oldCustomer.fixPhone1.length() == 0){
			cp.setAddress(newCustomer.fixPhone1);
		}
		
		if(newCustomer.fixPhone2.length() > 0 && oldCustomer.fixPhone2.length() == 0){
			cp.setAddress(newCustomer.fixPhone2);
		}
		
		if(newCustomer.mail.length() > 0 && oldCustomer.mail.length() == 0){
			cp.setAddress(newCustomer.mail);
		}
		
	    if(oldCustomer.getMobile1().length() == 11){
		cp.getMobilePhonelist().add(oldCustomer.getMobile1());
		}
	    
	    if(oldCustomer.getMobile2().length() == 11){
			cp.getMobilePhonelist().add(oldCustomer.getMobile2());
	    }
	    
	    if(oldCustomer.getMobile3().length() == 11){
			cp.getMobilePhonelist().add(oldCustomer.getMobile3());
			}
	    
	    if(oldCustomer.getMobile4().length() == 11){
			cp.getMobilePhonelist().add(oldCustomer.getMobile4());
			}
	    
	
		int index = cp.getMobilePhonelist().size();
		
		for(int loop = 0 ; loop < newCustomer.getMobilePhonelist().size(); loop++){
			
			if(cp.getMobilePhonelist().size() == 4){
				break;
			}
		    else if(false == isExistPhone(newCustomer.getMobilePhonelist().get(loop), cp)){
		    	cp.getMobilePhonelist().add(newCustomer.getMobilePhonelist().get(loop));
		    }
		}
		
		cp.setUsertype(newCustomer.usertype | oldCustomer.usertype);
		
		
	
		
		if(newCustomer.idCard.length() > 0 && oldCustomer.idCard.length() == 0){
			cp.setAddress(newCustomer.idCard);
		}
		
		cp.setRemarks(oldCustomer.remarks);
		cp.setDataSource(oldCustomer.getDataSource());
		cp.setUsertype(oldCustomer.getUsertype());
		
		
		return cp;
	}

	private Long IsExistCustomer(CustomerProfile customer){
		
		Long phoneID = phoneDao.queryCustomerPhone(customer);
		
		if(phoneID == 0L){
		
			Long mailID = mailDAO.queryCustomerMail(customer);
			return mailID;
		}
		else{
			return phoneID;
		}
	}
	
	
	private void insertTranction(CustomerProfile customerProfile){
		
		Connection conn = DbConnection.getConn();
		try {
			conn.setAutoCommit(false);
			prodao.insertCustomerProfile(customerProfile);
			CustomerPhone customerPhone = new CustomerPhone();
			CustomerMail   mail = new CustomerMail();
			if(customerProfile.getMobile1().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile1());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile2().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile2());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile3().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile3());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile4().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile4());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMail().length() > 1){
				mail.setMail(customerProfile.getMail());
				mailDAO.insertCustomerMail(mail);
			}
			FileBrower.customerReport.setUserImport(FileBrower.customerReport.getUserImport() + 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("updateTranction database exception: "+ FileBrower.customerReport.getFilename());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
}
