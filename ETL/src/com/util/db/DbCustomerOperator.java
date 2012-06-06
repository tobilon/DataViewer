package com.util.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.export.CusteomerExport;
import com.util.gui.FileBrower;
import com.util.tools.FileUtil;
import com.util.tools.Log;

public class DbCustomerOperator {

	CustomerProfileDAO prodao = new CustomerProfileImp();
	CustomerPhoneDAO phoneDao = new CustomerPhoneImp();
	CustomerMailDAO mailDAO = new CustomerMailImp();
	CustomerReportDAO  reportDAO = new CustomerReportImp();
	ArrayList<String> list = new ArrayList<String>();
	

	public void importOracle(CustomerProfile customer){
		
		list.clear();
		Long ID = IsExistCustomer(customer);
		
		if(0 == ID){
			insertTranction(customer);
		}
		else if(ID != -1L){
			CustomerProfile oldCustomer = prodao.queryCustomerProfile(ID);
			CustomerProfile newCustomer = combineCustomerProfile(customer,oldCustomer);
			newCustomer.setId(ID);
			updateTranction(newCustomer,oldCustomer,customer);
		}
		else{
			try {
				FileUtil.writeError(CusteomerExport.printlog(customer));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteOracle(CustomerReport report){
		ArrayList<CustomerProfile> list = new ArrayList<CustomerProfile>();
		prodao.queryCutomerProfile(list, report.getId());
		
		Connection conn = DbConnection.getConn();
		try {
			conn.setAutoCommit(false);
			for(int loop = 0; loop < list.size(); loop++){
				 phoneDao.deleteCustomerPhone(list.get(loop));
				 mailDAO.deleteCustomerMail(list.get(loop));
				 prodao.deleteCustomerProfile(list.get(loop).getId());
			}
			
			reportDAO.deleteCustomerReport(report.getId());
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
	private boolean isExistPhone(String phone,CustomerProfile customer){
		if(phone == null){
			return true;
		}
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
			prodao.updateCustomerProfile(newCustomer);
			CustomerPhone customerPhone = new CustomerPhone();
			CustomerMail   mail = new CustomerMail();
			
			if(newCustomer.getMobile1().length() == 11 && false == isExistPhone(newCustomer.getMobile1(),oldCustomer)){
				customerPhone.setMobilePhone(newCustomer.getMobile1());
				customerPhone.setId(newCustomer.getId());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			if(customerProfile.getMobile2().length() == 11 && false == isExistPhone(newCustomer.getMobile2(),oldCustomer)){
				customerPhone.setMobilePhone(newCustomer.getMobile2());
				customerPhone.setId(newCustomer.getId());
				 phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(newCustomer.getMobile3().length() == 11 &&  false == isExistPhone(newCustomer.getMobile3(),oldCustomer)){
				customerPhone.setMobilePhone(newCustomer.getMobile3());
				customerPhone.setId(newCustomer.getId());
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(newCustomer.getMobile4().length() == 11 && false == isExistPhone(newCustomer.getMobile4(),oldCustomer)){
				customerPhone.setMobilePhone(newCustomer.getMobile4());
				customerPhone.setId(newCustomer.getId());
				 phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMail().length() > 1 && oldCustomer.getMail().length() == 0){
				mail.setMail(customerProfile.getMail());
				mail.setId(newCustomer.getId());
				mailDAO.insertCustomerMail(mail);
			}
			
			ArrayList<String> phoneList = new ArrayList<String>();
			for(int loop = 0; loop < list.size(); loop++){
				int secLoop = 0;
				for(; secLoop < newCustomer.mobilePhonelist.size(); secLoop++){
					
					if(list.get(loop).equals(newCustomer.mobilePhonelist.get(secLoop))){
						break;
					}
				}
				if(secLoop == newCustomer.mobilePhonelist.size()){
					phoneList.add(list.get(loop));
				}
			}
			
			if(phoneList.size() > 0){
				customerProfile.setMobile1("");
				customerProfile.setMobile2("");
				customerProfile.setMobile3("");
				customerProfile.setMobile4("");
				customerProfile.getMobilePhonelist().clear();
				customerProfile.setMobilePhonelist(phoneList);
				customerProfile.setMail("");
				insertTranction(customerProfile);
			}
    		conn.commit();
			
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
	
		if(newCustomer.address.length() > 0 && oldCustomer.address == null){
			cp.setAddress(newCustomer.address);
		}
		else if(oldCustomer.address != null){
			cp.setAddress(oldCustomer.address);
		}
		if(newCustomer.name.length() > 0 && oldCustomer.name == null){
			cp.setName(newCustomer.name);
		}
		else if(oldCustomer.name != null){
			cp.setName(oldCustomer.name);
		}
		
		if(newCustomer.city.length() > 0 && oldCustomer.city== null){
			cp.setCity(newCustomer.city);
		}
		else if(oldCustomer.city != null){
			cp.setCity(oldCustomer.city);
		}
		
		if(newCustomer.province.length() > 0 && oldCustomer.province == null){
			cp.setProvince(newCustomer.province);
		}
		else if(null != oldCustomer.province){
			cp.setProvince(oldCustomer.province);
		}
		
		if(newCustomer.company.length() > 0 && oldCustomer.company== null){
			cp.setCompany(newCustomer.company);
		}
		else if(null != oldCustomer.company){
			cp.setCompany(oldCustomer.company);
		}
		if(newCustomer.post.length() > 0 && oldCustomer.post == null){
			cp.setPost(newCustomer.post);
		}
		else if(null != oldCustomer.post){
			cp.setPost(oldCustomer.post);
		}
		
		if(newCustomer.fixPhone1.length() > 0 && oldCustomer.fixPhone1 == null){
			cp.setFixPhone1(newCustomer.fixPhone1);
		}
		else if(null != oldCustomer.fixPhone1){
			cp.setFixPhone1(oldCustomer.fixPhone1);
		}
		
		if(newCustomer.fixPhone2.length() > 0 && oldCustomer.fixPhone2 == null){
			cp.setFixPhone2(newCustomer.fixPhone2);
		}
		else if(null != oldCustomer.fixPhone2){
		    cp.setFixPhone2(oldCustomer.fixPhone2);
		}
		
		if(newCustomer.mail.length() > 0 && oldCustomer.mail == null){
			cp.setMail(newCustomer.mail);
		}
		else if(null != oldCustomer.mail){
			cp.setMail(oldCustomer.mail);
		}
		
	    if(oldCustomer.getMobile1() != null && oldCustomer.getMobile1().length() == 11){
		cp.getMobilePhonelist().add(oldCustomer.getMobile1());
		}
	    
	    if(oldCustomer.getMobile2() != null && oldCustomer.getMobile2().length() == 11){
			cp.getMobilePhonelist().add(oldCustomer.getMobile2());
	    }
	    
	    if(oldCustomer.getMobile3() != null && oldCustomer.getMobile3().length() == 11){
			cp.getMobilePhonelist().add(oldCustomer.getMobile3());
			}
	    
	    if(oldCustomer.getMobile4() != null && oldCustomer.getMobile4().length() == 11){
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
    
		if(newCustomer.idCard.length() > 0 && oldCustomer.idCard == null){
			cp.setIdCard(newCustomer.idCard);
		}
		else if(null != oldCustomer.idCard){
			cp.setIdCard(oldCustomer.idCard);
		}
		
		if(newCustomer.born.length() > 0 && oldCustomer.born == null){
			cp.setBorn(newCustomer.born);
		}
		else if(null != oldCustomer.born){
			cp.setBorn(oldCustomer.born);
		}
		cp.setRemarks(oldCustomer.remarks);
		cp.setDataSource(oldCustomer.getDataSource());
		cp.setUsertype(oldCustomer.getUsertype());
		
		return cp;
	}

	private Long IsExistCustomer(CustomerProfile customer){
		
		Long phoneID = phoneDao.queryCustomerPhone(customer,list);
		Long mailID = mailDAO.queryCustomerMail(customer);
		
		if(phoneID.longValue() == -1){
			return -1L;
		}
		
		if((phoneID.longValue() != mailID.longValue() )&& (mailID.longValue() != 0) &&(phoneID.longValue() != 0) ){
			return -1L;
		}
		
		
		if(mailID.longValue() == 0 && phoneID.longValue() != 0){
			return phoneID;
		}
		
		if(mailID.longValue()!= 0 && phoneID.longValue() == 0){
			return mailID;
		}
		
		return 0L;
	}
	
	
	private void insertTranction(CustomerProfile customerProfile){
		
		Connection conn = DbConnection.getConn();
		try {
			conn.setAutoCommit(false);
			prodao.insertCustomerProfile(customerProfile);
			CustomerPhone customerPhone = new CustomerPhone();
			CustomerMail   mail = new CustomerMail();
			Long index = prodao.getLastId();
			
			if(customerProfile.getMobile1() != null && customerProfile.getMobile1().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile1());
				customerPhone.setId(index);
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile2() != null && customerProfile.getMobile2().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile2());
				customerPhone.setId(index);
				 phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile3() != null &&  customerProfile.getMobile3().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile3());
				customerPhone.setId(index);
				 phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMobile4() != null &&  customerProfile.getMobile4().length() == 11){
				customerPhone.setMobilePhone(customerProfile.getMobile4());
				customerPhone.setId(index);
				phoneDao.insertCustomerPhone(customerPhone);
			}
			
			if(customerProfile.getMail() != null && customerProfile.getMail().length() > 1){
				mail.setMail(customerProfile.getMail());
				mail.setId(index);
			    mailDAO.insertCustomerMail(mail);
			}
			
			conn.commit();
			
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
