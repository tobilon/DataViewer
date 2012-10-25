/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  业锟斤拷锟斤拷息维锟斤拷
 */

package fhcp;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Vector;

public class ServiceControl extends Service {

	public ResultSet rs;

	public ServiceControl() {
	};
	
	/*
	 * 锟斤拷锟斤拷碌锟揭碉拷锟�	 */
	public void addservice() {
		DBConnect dbc = null;
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("INSERT INTO service_content ( msginfo,timestamp ) VALUES ( ?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))");
			dbc.setString(1, new String(svrContent.getBytes("iso-8859-1"),"gb2312"));
			dbc.setString(2, svrDate);
			System.out.println(svrContent);
			System.out.println(new String(svrContent.getBytes("utf-8"),"gb2312"));
			System.out.println(new String(svrContent.getBytes("iso-8859-1"),"gb2312"));
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
			
			dbc.prepareStatement("insert into service_log (serviceid,userid,ackflag,dealflag,failflag)values(?,?,0,0,0)");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
			
			dbc.prepareStatement("update customer_profile set servstate=1 where id=? and servstate<1 and (select count(*) from service_log where id=? and serviceid=? and failflag=0)>0");
			dbc.setLong(1, userid);			
			dbc.setLong(2, userid);
			dbc.setLong(3, serviceid);
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
			
			dbc.prepareStatement("update service_log set ackflag=1,acktime=current_date where serviceid=? and userid=? and failflag=0");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
			
			dbc.prepareStatement("update customer_profile set servstate=3 where id=? and servstate<3 and (select count(*) from service_log where id=? and serviceid=? and failflag=0)>0");
			dbc.setLong(1, userid);			
			dbc.setLong(2, userid);
			dbc.setLong(3, serviceid);
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
			
			dbc.prepareStatement("update service_log set dealflag=1,dealtime=current_date where serviceid=? and userid=?  and failflag=0");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
			
			dbc.prepareStatement("update customer_profile set servstate=4 where id=? and servstate<4 and (select count(*) from service_log where id=? and serviceid=? and failflag=0)>0");
			dbc.setLong(1, userid);			
			dbc.setLong(2, userid);
			dbc.setLong(3, serviceid);
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
	
	public void updateLogFail(String mobile, long serviceid) {
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
			
			dbc.prepareStatement("update service_log set failflag=1 where serviceid=? and userid=?");
			dbc.setLong(1, serviceid);
			dbc.setLong(2, userid);
			dbc.execute();
			
			dbc.prepareStatement("update customer_profile set servstate=2 where id=? and servstate<2 and (select count(*) from service_log where id=? and serviceid=? and failflag=1)>0");
			dbc.setLong(1, userid);			
			dbc.setLong(2, userid);
			dbc.setLong(3, serviceid);
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
	
	/* 统锟斤拷业锟斤拷锟斤拷锟斤拷锟斤拷  */
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
    
    public int getServiceFailCount(long serviceid) {
    	DBConnect dbc = null;
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select sum(failflag) from service_log where serviceid = ?");
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
    
    /* 统锟斤拷业锟斤拷锟斤拷应锟斤拷锟斤拷  */
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
    
    /* 统锟斤拷业锟今订癸拷锟斤拷锟斤拷  */
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
    
    public Vector serviceByUser(long userId) {
		DBConnect dbc = null;
		Vector serviceVector = new Vector();
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM service_log where userid=? order by serviceid");
			dbc.setLong(1, userId);
			rs = dbc.executeQuery();
			while (rs.next()) {
				ServiceLog servLog = new ServiceLog();
				servLog.setServiceID(rs.getInt("serviceid"));
				servLog.setFailFlag(rs.getInt("failflag"));
				servLog.setAckFlag(rs.getInt("ackflag"));
				servLog.setDealFlag(rs.getInt("dealflag"));
				serviceVector.add(servLog);
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return serviceVector;
	}
    
    /* 统锟斤拷业锟斤拷锟斤拷锟斤拷锟斤拷  */
    public Vector getServiceUserID(long serviceid, String userState) {
    	DBConnect dbc = null;
    	Vector serviceVector = new Vector();

		try {
			dbc = new DBConnect();
			System.out.println("userState:"+userState+".");
			if(userState == null || "".equals(userState))
			{
				dbc.prepareStatement("select userid from service_log where serviceid = ? order by userid");
				dbc.setLong(1, serviceid);
			}
			else
			{
				dbc.prepareStatement("select userid from service_log where serviceid = ? and userState=? order by userid");
				dbc.setLong(1, serviceid);
				dbc.setLong(2, Integer.parseInt(userState));
			}
			
			rs = dbc.executeQuery();
			while (rs.next()) {
				ServiceLog servLog = new ServiceLog();
				servLog.setUserID(rs.getInt("userid"));
				serviceVector.add(servLog);
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

		return serviceVector;
	}
}
