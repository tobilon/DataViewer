package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.tools.Log;

public class CustomerProfileImp implements CustomerProfileDAO {

	public boolean insertCustomerProfile(CustomerProfile customerProfile)
			throws SQLException {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileInsert");

		if (null == state) {
			return false;
		}

		state.setString(1, customerProfile.getName());
		state.setString(2, customerProfile.getAddress());
		state.setString(3, customerProfile.getCompany());
		state.setString(4, customerProfile.getProvince());
		state.setString(5, customerProfile.getCity());
		state.setString(6, customerProfile.getBorn());
		state.setString(7, customerProfile.getIdCard());
		state.setString(8, customerProfile.getMobile1());
		state.setString(9, customerProfile.getMobile2());
		state.setString(10, customerProfile.getMobile3());
		state.setString(11, customerProfile.getMobile4());
		state.setString(12, customerProfile.getFixPhone1());
		state.setString(13, customerProfile.getFixPhone2());
		state.setString(14, customerProfile.getPost());
		state.setString(15, customerProfile.getMail());
		state.setString(16, customerProfile.getSex());
		state.setLong(17, customerProfile.getUsertype());
		state.setLong(18, customerProfile.getDataSource());
		state.setString(19, customerProfile.getActor());
		state.setString(20, customerProfile.getRemarks());
		return state.execute();

	}

	public boolean updateCustomerProfile(CustomerProfile customerProfile) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileUpdate");

		if (null == state) {
			return false;
		}

		try {
			state.setString(1, customerProfile.getName());
			state.setString(2, customerProfile.getAddress());
			state.setString(3, customerProfile.getCompany());
			state.setString(4, customerProfile.getProvince());
			state.setString(5, customerProfile.getCity());
			state.setString(6, customerProfile.getBorn());
			state.setString(7, customerProfile.getIdCard());
			state.setString(8, customerProfile.getMobile1());
			state.setString(9, customerProfile.getMobile2());
			state.setString(10, customerProfile.getMobile3());
			state.setString(11, customerProfile.getMobile4());
			state.setString(12, customerProfile.getFixPhone1());
			state.setString(13, customerProfile.getFixPhone2());
			state.setString(14, customerProfile.getPost());
			state.setString(15, customerProfile.getMail());
			state.setString(16, customerProfile.getSex());
			state.setLong(17, customerProfile.getUsertype());
			state.setLong(18, customerProfile.getDataSource());
			state.setString(19, customerProfile.getActor());
			state.setString(20, customerProfile.getRemarks());
			state.setLong(21, customerProfile.getId());
			return state.execute();
		} catch (SQLException e) {
			Log.error("updateCustomerProfile failed");
			e.printStackTrace();
		}

		return false;
	}

	public CustomerProfile queryCustomerProfile(Long id) {
		CustomerProfile customerProfile = new CustomerProfile();

		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileQuery");
		/* protected */
		if (null == state) {
			return null;
		}
		try {
			state.setLong(1, id);
			ResultSet set = state.executeQuery();
			if (set.next()) {
				customerProfile.setId(set.getLong(1));
				customerProfile.setName(set.getString(2));
				customerProfile.setAddress(set.getString(3));
				customerProfile.setCompany(set.getString(4));
				customerProfile.setProvince(set.getString(5));
				customerProfile.setCity(set.getString(6));
				customerProfile.setBorn(set.getString(7));
				customerProfile.setIdCard(set.getString(8));
				customerProfile.setMobile1(set.getString(9));
				customerProfile.setMobile2(set.getString(10));
				customerProfile.setMobile3(set.getString(11));
				customerProfile.setMobile4(set.getString(12));
				customerProfile.setFixPhone1(set.getString(13));
				customerProfile.setFixPhone2(set.getString(14));
				customerProfile.setPost(set.getString(15));
				customerProfile.setMail(set.getString(16));
				customerProfile.setSex(set.getString(17));
				customerProfile.setUsertype(set.getLong(18));
				customerProfile.setDataSource(set.getLong(19));
				customerProfile.setActor(set.getString(20));
				customerProfile.setRemarks(set.getString(21));
				return customerProfile;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryCustomerProfile failed");
			e.printStackTrace();
		}

		return null;
	}

	public boolean deleteCustomerProfile(Long id) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileDelete");

		if (null == state) {
			return false;
		}

		try {
			state.setLong(1, id);
			return state.execute();
		} catch (SQLException e) {
			Log.error("deleteCustomerProfile failed");
			e.printStackTrace();
		}

		return false;
	}

	public void queryCutomerProfile(ArrayList<CustomerProfile> list, Long source) {

		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileSource");

		if (null == state) {
			return;
		}

		list.clear();

		try {
			state.setLong(1, source);
			ResultSet set = state.executeQuery();
			while (set.next()) {
				CustomerProfile customerProfile = new CustomerProfile();
				customerProfile.setId(set.getLong(1));
				customerProfile.setName(set.getString(2));
				customerProfile.setAddress(set.getString(3));
				customerProfile.setCompany(set.getString(4));
				customerProfile.setProvince(set.getString(5));
				customerProfile.setCity(set.getString(6));
				customerProfile.setBorn(set.getString(7));
				customerProfile.setIdCard(set.getString(8));
				customerProfile.setMobile1(set.getString(9));
				customerProfile.setMobile2(set.getString(10));
				customerProfile.setMobile3(set.getString(11));
				customerProfile.setMobile4(set.getString(12));
				customerProfile.setFixPhone1(set.getString(13));
				customerProfile.setFixPhone2(set.getString(14));
				customerProfile.setPost(set.getString(15));
				customerProfile.setMail(set.getString(16));
				customerProfile.setSex(set.getString(17));
				customerProfile.setUsertype(set.getLong(18));
				customerProfile.setDataSource(set.getLong(19));
				customerProfile.setActor(set.getString(20));
				customerProfile.setRemarks(set.getString(21));
				list.add(customerProfile);
			}
		} catch (SQLException e) {
			Log.error("deleteCustomerProfile failed");
			e.printStackTrace();
		}

		return;
	}

	public Long getLastId() {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("profileMaxID");
		/* protected */
		if (null == state) {
			return null;
		}
		try {
			ResultSet set = state.executeQuery();
			if (set.next()) {
				return set.getLong(1);
			} else {
				return 0L;
			}
		} catch (SQLException e) {
			Log.error("getLastId failed");
			e.printStackTrace();
		}

		return null;
	}

}
