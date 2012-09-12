package fhcp;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerControl extends Customer{
	
	public DBConnect dbc = null;
	public ResultSet rs;	
	
	/* 获取所有记录数目  */
	public int getAllCustomerCount() {
		
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select count(*) from customer_profile");
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

	/* 获取所有用户记录  */
	public void allCustomer(int startRow, int endRow) {
		int count = 0;

		try {
			dbc = new DBConnect();
			dbc.prepareStatement("select * from (select e.*,ROWNUM rn from (SELECT * FROM customer_profile)e where ROWNUM<=?) where rn>=?");
			dbc.setInt(1, endRow);
			dbc.setInt(2, startRow);
			rs = dbc.executeQuery();
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCustomerFilterCount(String qtype, String qvalue) {
		int count = 0;
		
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT count(*) FROM customer_profile where "
					+ qtype + " like " + "'%" + qvalue + "%'");
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
	
	public void getCustomerFilter(String qtype, String qvalue, int startRow, int endRow) {
		try {

			dbc = new DBConnect();
			String strSQL = "SELECT * FROM customer_profile where " + qtype
					+ " like " + "'%" + qvalue + "%'";
			dbc.prepareStatement("select * from (select e.*,ROWNUM rn from ("+strSQL+")e where ROWNUM<=?) where rn>=?");
			dbc.setInt(1, endRow);
			dbc.setInt(2, startRow);

			rs = dbc.executeQuery();
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getCustomerMultiFilterCount(Map filter) {
		int count = 0;
		Set entris;
		Map.Entry entry;
		Iterator it;
		String strSQL;
		int i = 0;
		long usertype = 0L;
		String userlogic = "";
		
		try {
			//fen ye
			 dbc = new DBConnect();
		    if(filter.size()>0)
		    {
		    	strSQL = "SELECT count(*) FROM customer_profile";
		    	
		    	entris=filter.entrySet();
		    	it = entris.iterator();
		    	while(it.hasNext())
		    	{	    		
		    		entry = (Map.Entry)it.next();		    		
		    		if(entry.getKey() == "born1")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += ">='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "born2")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += "<='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "usertype")
		    		{
		    			System.out.println("usertype"+entry.getValue()+".");
		    			usertype = Long.parseLong((String)entry.getValue());
		    			continue;
		    		}
		    		else if(entry.getKey() == "logic")
		    		{
		    			userlogic = (String)entry.getValue();
		    			continue;
		    		}
		    		else
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += entry.getKey();
						strSQL += " like " + "'%";
						strSQL += entry.getValue();
						strSQL += "%'";
					}
		    		i++;
		    	}
		    	
		    	if(usertype != 0 && userlogic != null)
		    	{		 
		    		if(i == 0)
	    				strSQL += " where "; 
	    			else		    				
		    			strSQL += " and ";
		    		
		    		if("and".equals(userlogic))
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")="+usertype; 
		    		}
		    		else
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")!=0"; 
		    		}
		    	}
		    }
		    else
		    {
		    	strSQL = "SELECT count(*) FROM customer_profile";
		    }
		    System.out.println(strSQL);
			dbc.prepareStatement(strSQL);
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
	
	public void getCustomerMultiFilter(Map filter, int startRow, int endRow) {
		Set entris;
		Map.Entry entry;
		Iterator it;
		String strSQL;
		int i = 0;
		long usertype = 0L;
		String userlogic = "";
		
		try {
			//fen ye
			 dbc = new DBConnect();

		    if(filter.size()>0)
		    {
		    	strSQL = "SELECT * FROM customer_profile";
		    	
		    	entris=filter.entrySet();
		    	it = entris.iterator();
		    	while(it.hasNext())
		    	{	    		
		    		entry = (Map.Entry)it.next();		    		
		    		if(entry.getKey() == "born1")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += ">='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "born2")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += "<='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "usertype")
		    		{
		    			System.out.println("usertype"+entry.getValue()+".");
		    			usertype = Long.parseLong((String)entry.getValue());
		    			continue;
		    		}
		    		else if(entry.getKey() == "logic")
		    		{
		    			userlogic = (String)entry.getValue();
		    			continue;
		    		}
		    		else
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += entry.getKey();
						strSQL += " like " + "'%";
						strSQL += entry.getValue();
						strSQL += "%'";
					}
		    		i++;
		    	}
		    	
		    	if(usertype != 0 && userlogic != null)
		    	{		 
		    		if(i == 0)
	    				strSQL += " where "; 
	    			else		    				
		    			strSQL += " and ";
		    		
		    		if("and".equals(userlogic))
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")="+usertype; 
		    		}
		    		else
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")!=0"; 
		    		}
		    	}
		    }
		    else
		    {
		    	strSQL = "SELECT * FROM customer_profile";
		    }
		    System.out.println(strSQL);		    
		    dbc.prepareStatement("select * from (select e.*,ROWNUM rn from ("+strSQL+")e where ROWNUM<=?) where rn>=?");
			dbc.setInt(1, endRow);
			dbc.setInt(2, startRow);			
			rs = dbc.executeQuery(); 
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getCustomerMultiFilter(Map filter) {
		Set entris;
		Map.Entry entry;
		Iterator it;
		String strSQL;
		int i = 0;
		long usertype = 0L;
		String userlogic = "";
		
		try {
			//fen ye
			 dbc = new DBConnect();

		    if(filter.size()>0)
		    {
		    	strSQL = "SELECT * FROM customer_profile";
		    	
		    	entris=filter.entrySet();
		    	it = entris.iterator();
		    	while(it.hasNext())
		    	{	    		
		    		entry = (Map.Entry)it.next();		    		
		    		if(entry.getKey() == "born1")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += ">='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "born2")
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += "born";
		    			strSQL += "<='";
		    			strSQL += entry.getValue();
		    			strSQL += "'";
		    		}
		    		else if(entry.getKey() == "usertype")
		    		{
		    			System.out.println("usertype"+entry.getValue()+".");
		    			usertype = Long.parseLong((String)entry.getValue());
		    			continue;
		    		}
		    		else if(entry.getKey() == "logic")
		    		{
		    			userlogic = (String)entry.getValue();
		    			continue;
		    		}
		    		else
		    		{
		    			if(i == 0)
		    				strSQL += " where "; 
		    			else		    				
			    			strSQL += " and ";
		    			strSQL += entry.getKey();
						strSQL += " like " + "'%";
						strSQL += entry.getValue();
						strSQL += "%'";
					}
		    		i++;
		    	}
		    	
		    	if(usertype != 0 && userlogic != null)
		    	{		 
		    		if(i == 0)
	    				strSQL += " where "; 
	    			else		    				
		    			strSQL += " and ";
		    		
		    		if("and".equals(userlogic))
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")="+usertype; 
		    		}
		    		else
		    		{
		    			strSQL += "bitand(usertype,"+usertype+")!=0"; 
		    		}
		    	}
		    }
		    else
		    {
		    	strSQL = "SELECT * FROM customer_profile";
		    }
		    System.out.println(strSQL);		    
		    dbc.prepareStatement(strSQL);
		    rs = dbc.executeQuery(); 
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getUserTypeString()
	{
		String str="";
		
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM customer_type");
			rs = dbc.executeQuery();
			while (rs.next()) {		
				str+="<input type='checkbox' name='ctype' value="+rs.getLong("tag")+">"+rs.getString("disc");				
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
		
		return str;
	}
	
	public String getServiceIdString(String date)
	{
		String str="";
		
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM service_content where timestamp > to_date(?, 'yyyy-mm-dd')");
			System.out.println(date);
			dbc.setString(1, date);
			rs = dbc.executeQuery();
			while (rs.next()) {
				str+="<option value='"+rs.getLong("id")+"'>" +rs.getLong("id")+":"+rs.getString("msginfo")+ "&nbsp;&nbsp;</option>";
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
		
		return str;
	}
	
	public boolean modifyCustomer()
	{
		boolean ret = false;
		
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("UPDATE customer_profile SET name = ?,sex=?,born =?," +
					"company=?,province=?,city=?,post=?,mobilephone1=?,homephone1=?,mail=?," +
					"usertype=?,source=?,extra=? where id = ?");
			dbc.setString(1, name);
			dbc.setString(2, sex);
			dbc.setString(3, born);
			dbc.setString(4, company);
			dbc.setString(5, province);
			dbc.setString(6, city);
			dbc.setString(7, post);
			dbc.setString(8, mobile1);
			dbc.setString(9, fixPhone1);
			dbc.setString(10, mail);
			dbc.setLong(11, usertype);
			dbc.setLong(12, dataSource);
			dbc.setString(13, remarks);
			dbc.setLong(14, id);
			dbc.execute();
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public ArrayList executeQueryList(ResultSet rs,int intPageSize) throws SQLException {
		Statement st = null;
		ArrayList alResult = new ArrayList();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			System.out.println(count);
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++)
				if (rsmd.getColumnName(i + 1) != null)
					colum[i] = rsmd.getColumnName(i + 1);
				else
					colum[i] = rsmd.getColumnLabel(i + 1);

			HashMap hdRow = null;
			String fieldValue = null;
			int j = 0;
			for (j =0;rs.next() && j < intPageSize; alResult.add(hdRow)) {
				hdRow = new HashMap();
				j++;
				for (int i = 0; i < colum.length; i++) {
					int iType = rsmd.getColumnType(i + 1);
					if (iType == -1 || iType == 3) {
						if (rsmd.getScale(i + 1) == 0)
							fieldValue = String.valueOf(rs.getLong(i + 1));
						else
							fieldValue = rs.getString(i + 1);
					} else if (iType == 8)
						fieldValue = String.valueOf(rs.getDouble(i + 1));
					else if (iType == 6 || iType == 7)
						fieldValue = String.valueOf(rs.getFloat(i + 1));
					else
						fieldValue = rs.getString(i + 1);
					if (fieldValue == null)
						fieldValue = "";
					else
						fieldValue = fieldValue.trim();
					hdRow.put(colum[i].toLowerCase(), fieldValue);//.toLowerCase()
				}
			}
			
			dbc.close();
			rs.close();			
		} catch (Exception e)
		{
			System.err.println("executeQueryList error:" + e);
			e.printStackTrace();
		}
		
		return alResult;
	}
	
	public boolean executeMarkList(long servid) throws SQLException {
		Long userid = 0L;
		Statement st = null;
		
		try {
			
			
			dbc = new DBConnect();			
			dbc.prepareStatement("insert into service_log (serviceid,userid,ackflag,dealflag)values(?,?,0,0)");

			while (rs.next()){
				userid = rs.getLong("id");
				dbc.setLong(1, servid);
				dbc.setLong(2, userid);
				dbc.execute();
			}
		
		} catch (Exception e) {
			System.err.println("error:" + e);
		} finally {
			try {
				dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		return true;
	}
	
}
