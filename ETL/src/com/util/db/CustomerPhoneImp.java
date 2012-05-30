package com.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.tools.Log;

public class CustomerPhoneImp implements CustomerPhoneDAO {

	public Long queryCustomerPhone(CustomerProfile customerProfile) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneQuery");
		/* protected */
		if (null == state) {
			return 0L;
		}
		try {
			Long id1 = 0L;
			Long id2 = 0L;
			Long id3 = 0L;
			Long id4 = 0L;
			ResultSet set = null;
			if (customerProfile.getMobile1() != null
					&& customerProfile.getMobile1().length() == 11) {
				state.setString(1, customerProfile.getMobile1());
				set = state.executeQuery();
				if (set.next()) {
					id1 = set.getLong(1);
				}

				if (id1.longValue() == 0) {
					return -1L;

				}
			}

			if (customerProfile.getMobile2() != null
					&& customerProfile.getMobile2().length() == 11) {
				state.setString(1, customerProfile.getMobile2());
				set = state.executeQuery();
				if (set.next()) {
					id2 = set.getLong(1);
				}

				if (id2.longValue() == 0) {
					return id1;
				} else if (id1.longValue() != id2.longValue()) {
					return -1L;
				}
			}

			if (customerProfile.getMobile3() != null
					&& customerProfile.getMobile3().length() == 11) {
				state.setString(1, customerProfile.getMobile3());
				set = state.executeQuery();
				if (set.next()) {
					id3 = set.getLong(1);
				}

				if (id3.longValue() == 0) {
					return id1;
				} else if (id3.longValue() != id1.longValue()) {
					return -1L;
				}
			}

			if (customerProfile.getMobile4() != null
					&& customerProfile.getMobile4().length() == 11) {
				state.setString(1, customerProfile.getMobile4());
				set = state.executeQuery();
				if (set.next()) {
					id4 = set.getLong(1);
				}

				if (id4.longValue() == 0) {
					return id1;
				} else if (id4.longValue() != id4.longValue()) {
					return 0L;
				}
			}

			return id1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error("queryCustomerPhone failed");
			e.printStackTrace();
		}

		return 0L;
	}

	public boolean insertCustomerPhone(CustomerPhone customerPhone) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneInsert");

		if (null == state) {
			Log.error("insertCustom erPhone failed");
			return false;
		}

		try {
			state.setLong(1, customerPhone.getId());
			state.setString(2, customerPhone.getMobilePhone());
			return state.execute();
		} catch (SQLException e) {
			Log.error("insertCustomerPhone failed");
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteCustomerPhone(CustomerProfile customerProfile) {
		PreparedStatement state = DbCreateStateFactory
				.createStateInstance("phoneDelete");

		if (null == state) {
			Log.error("deleteCustomerPhone failed");
			return false;
		}

		try {
			if (customerProfile.getMobile1() != null
					&& customerProfile.getMobile1().length() == 11) {
				state.setString(1, customerProfile.getMobile1());
				state.execute();
			}
			if (customerProfile.getMobile2() != null
					&& customerProfile.getMobile2().length() == 11) {
				state.setString(1, customerProfile.getMobile2());
				state.execute();
			}
			if (customerProfile.getMobile3() != null
					&& customerProfile.getMobile3().length() == 11) {
				state.setString(1, customerProfile.getMobile3());
				state.execute();
			}
			if (customerProfile.getMobile4() != null
					&& customerProfile.getMobile4().length() == 11) {
				state.setString(1, customerProfile.getMobile4());
				state.execute();
			}
			return true;
		} catch (SQLException e) {
			Log.error("deleteCustomerPhone failed");
			e.printStackTrace();
		}

		return false;
	}
}
