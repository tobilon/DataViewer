package fhcp;

import java.sql.ResultSet;
import java.util.Vector;

public class DispService extends Service{
	public ResultSet rs;

	public DispService() {
	};
	
	/*
	 * 得到所有短信
	 */
	public Vector allService() {
		DBConnect dbc = null;
		Vector allServiceVector = new Vector();
		try {
			dbc = new DBConnect();
			dbc.prepareStatement("SELECT * FROM service_content order by id");
			rs = dbc.executeQuery();
			while (rs.next()) {
				Service svr = new Service();
				svr.setID(rs.getInt("id"));
				svr.setSvrContent(rs.getString("msginfo"));
				svr.setSvrDate(rs.getString("timestamp"));
				allServiceVector.add(svr);
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
		return allServiceVector;
	}
}
