/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  业务信息维护
 */

package fhcp;

import java.sql.Date;
import java.sql.ResultSet;

public class ServiceControl extends Service {

	public ResultSet rs;

	public ServiceControl() {
	};
	
	/*
	 * 添加新的业务
	 */
	public void addservice() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("INSERT INTO service_content ( msginfo,timestamp ) VALUES ( ?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))");
			dbc.setString(1, new String(svrContent.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(2, svrDate);
			dbc.execute();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addservicelog(String mobile, long serviceid) {
		DBConnect dbc = null;
		long userid = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select userid from customer_phone where mobilephone=?");
			dbc.setString(1, mobile);
			rs = dbc.executeQuery();
			while (rs.next()) {
				userid = rs.getInt(1);
			}
			
			dbc.prepareStatement("insert into service_log (serviceid,userid,ackflag,dealflag)values(?,?,0,0)");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				rs.close();
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateLogAck(String mobile, long serviceid) {
		DBConnect dbc = null;
		long userid = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select userid from customer_phone where mobilephone=?");
			dbc.setString(1, mobile);
			rs = dbc.executeQuery();
			while (rs.next()) {
				userid = rs.getInt(1);
			}
			
			dbc.prepareStatement("update service_log set ackflag=1,acktime=current_date where serviceid=? and userid=?");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				rs.close();
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateLogDeal(String mobile, long serviceid) {
		DBConnect dbc = null;
		long userid = 0;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select userid from customer_phone where mobilephone=?");
			dbc.setString(1, mobile);
			rs = dbc.executeQuery();
			while (rs.next()) {
				userid = rs.getInt(1);
			}
			
			dbc.prepareStatement("update service_log set dealflag=1,dealtime=current_date where serviceid=? and userid=?");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				rs.close();
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* 统计业务发送人数  */
    public int getServiceUserCount(long serviceid) {
    	DBConnect dbc = null;
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select count(*) from service_log where serviceid = ?");
			dbc.setLong(1, serviceid);
			rs = dbc.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				rs.close();
				dbc.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}
    
    /* 统计业务响应人数  */
    public int getServiceAckCount(long serviceid) {
    	DBConnect dbc = null;
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select sum(ackflag) from service_log where serviceid = ?");
			dbc.setLong(1, serviceid);
			rs = dbc.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				rs.close();
				dbc.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}
    
    /* 统计业务订购人数  */
    public int getServiceDealCount(long serviceid) {
    	DBConnect dbc = null;
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select sum(dealflag) from service_log where serviceid = ?");
			dbc.setLong(1, serviceid);
			rs = dbc.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				rs.close();
				dbc.close();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}
}
