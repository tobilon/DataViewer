package fhcp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}
	
	public Map getMultiFilter(HttpServletRequest request) throws UnsupportedEncodingException
	{
		Map cellMap = new HashMap();
		
		if(request.getParameter("cnamevalue")!=""){
			
			cellMap.put("name", URLDecoder.decode(request.getParameter("cnamevalue"),"utf-8"));
		}
			
		if(request.getParameter("csexvalue") != ""){
			cellMap.put("sex", URLDecoder.decode(request.getParameter("csexvalue"),"utf-8"));
		}
		
		if(request.getParameter("cage1value") != ""){
			cellMap.put("born1",request.getParameter("cage1value"));
		}
		
		if(request.getParameter("cage2value") != ""){
			cellMap.put("born2", request.getParameter("cage2value"));
		}
		
		if(request.getParameter("ctitlevalue") != "")
		{
			cellMap.put("actor", URLDecoder.decode(request.getParameter("ctitlevalue"),"utf-8"));
		}
		
		if(request.getParameter("cprovincevalue") != "")
		{
			cellMap.put("province", URLDecoder.decode(request.getParameter("cprovincevalue"),"utf-8"));
		}
		
		if(request.getParameter("ccityvalue") != "")
		{
			cellMap.put("city", URLDecoder.decode(request.getParameter("ccityvalue"),"utf-8"));
		}

		if(request.getParameter("cpostvalue") != "")
		{
			cellMap.put("post", request.getParameter("cpostvalue"));
		}
		
		if(request.getParameter("cmobilevalue") != "")
		{
			cellMap.put("mobilephone1", request.getParameter("cmobilevalue"));
		}
		
		if(request.getParameter("cfixvalue") != "")
		{
			cellMap.put("homephone1", request.getParameter("cfixvalue"));
		}
		
		if(request.getParameter("cmailvalue") != "")
		{
			cellMap.put("mail", request.getParameter("cmailvalue"));
		}
		
		if(request.getParameter("cidvalue") != "")
		{
			cellMap.put("id", request.getParameter("cidvalue"));
		}
		
		if(request.getParameter("caddrvalue") != "")
		{
			cellMap.put("address", URLDecoder.decode(request.getParameter("caddrvalue"),"utf-8"));
		}

		if(request.getParameter("ccompanyvalue") != "")
		{
			cellMap.put("company", URLDecoder.decode(request.getParameter("ccompanyvalue"),"utf-8"));
		}
		
		if(request.getParameter("ctypevalue")!="0")
		{
			cellMap.put("usertype", request.getParameter("ctypevalue"));
		}
		
		if(request.getParameter("csourcevalue") != "")
		{
			cellMap.put("source", request.getParameter("csourcevalue"));
		}
		
		if(request.getParameter("cothervalue") != "")
		{
			cellMap.put("extra", URLDecoder.decode(request.getParameter("cothervalue"),"utf-8"));
		}
		
		if(request.getParameter("ctypelogic") != "")
		{
			cellMap.put("logic", request.getParameter("ctypelogic"));
		}
		if(request.getParameter("cservicestate") != "")
		{
			cellMap.put("servstate", request.getParameter("cservicestate"));
		}
		
		return cellMap;
	}
	
	public String getJsonString(List list, Map pageInfo) {
		List mapList = new ArrayList();
		CustomerControl customerCtrl = new CustomerControl();
		String strSrc;
		String strClass;
		String id;
		String strServState;
		for(int i = 0; i < list.size(); i++) {   
		Map cellMap = new HashMap();   
		cellMap.put("id", ((Map)list.get(i)).get("id")); 
		id = (String)(((Map)list.get(i)).get("source"));
		strSrc = customerCtrl.getDataSource(Integer.parseInt(id));
		id = (String)(((Map)list.get(i)).get("usertype"));
		strClass = customerCtrl.getUserType(Integer.parseInt(id));
		if(((String)((Map)list.get(i)).get("servstate")).equals("0"))
		{
			strServState="未处理";
		}
		else if(((String)((Map)list.get(i)).get("servstate")).equals("1"))
		{
			strServState="待定";
		}
		else if(((String)((Map)list.get(i)).get("servstate")).equals("2"))
		{
			strServState="已失败";
		}
		else if(((String)((Map)list.get(i)).get("servstate")).equals("3"))
		{
			strServState="已回应";
		}
		else
		{
			strServState="已订购";
		}
		cellMap.put("cell", new Object[] {
				((Map)list.get(i)).get("id"),
				((Map)list.get(i)).get("name"), 
				((Map)list.get(i)).get("sex"),
				((Map)list.get(i)).get("born"),
				((Map)list.get(i)).get("company"),
				((Map)list.get(i)).get("actor"),
				((Map)list.get(i)).get("province"),
				((Map)list.get(i)).get("city"),
				((Map)list.get(i)).get("post"),
				((Map)list.get(i)).get("address"),
				((Map)list.get(i)).get("mobilephone1"),
				((Map)list.get(i)).get("mobilephone2"),
				((Map)list.get(i)).get("mobilephone3"),
				((Map)list.get(i)).get("mobilephone4"),
				((Map)list.get(i)).get("homephone1"),
				((Map)list.get(i)).get("homephone2"),
				((Map)list.get(i)).get("mail"),				
				strClass,
				strSrc,
				((Map)list.get(i)).get("extra"),
				strServState}); 
		mapList.add(cellMap);
		}	
		pageInfo.put("rows", mapList);   
		JSONObject object = new JSONObject(pageInfo);   
		return object.toString();   
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost start");		
		
		Map cellMap = new HashMap();
		response.setCharacterEncoding("utf-8");		
		String action = request.getParameter("action");
		System.out.println("action:"+action);	
		
		if("add".equals(action))
		{
			
		}
		else if("modify".equals(action))
		{
			CustomerControl customerCtrl = new CustomerControl();
			customerCtrl.id=Long.parseLong(new String(request.getParameter("customer_id").getBytes("ISO8859-1"), "UTF-8"));
			customerCtrl.name = new String(request.getParameter("customer_name").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.sex = new String(request.getParameter("customer_sex").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.born=new String(request.getParameter("customer_age").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.title = new String(request.getParameter("customer_job").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.province = new String(request.getParameter("customer_province").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.city = new String(request.getParameter("customer_city").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.post = new String(request.getParameter("customer_post").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.mobile1 = new String(request.getParameter("customer_mobile").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.fixPhone1 = new String(request.getParameter("customer_fix").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.mail = new String(request.getParameter("customer_mail").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.usertype = Long.parseLong(new String(request.getParameter("customer_class").getBytes("ISO8859-1"), "UTF-8"));
			customerCtrl.dataSource = Long.parseLong(new String(request.getParameter("customer_source").getBytes("ISO8859-1"), "UTF-8"));
			customerCtrl.remarks = new String(request.getParameter("customer_other").getBytes("ISO8859-1"), "UTF-8");
			customerCtrl.modifyCustomer();
			return;
		}
		else if("getusertype".equals(action))
		{
			CustomerControl customerCtrl = new CustomerControl();			
			PrintWriter out = response.getWriter();			
			out.println(customerCtrl.getUserTypeString());			
			out.flush();
			out.close();
			return;
		}
		else if("getusertype2".equals(action))
		{
			CustomerControl customerCtrl = new CustomerControl();			
			PrintWriter out = response.getWriter();			
			out.println(customerCtrl.getUserTypeString2());			
			out.flush();
			out.close();
			return;
		}
		else if("getserviceid".equals(action))
		{
			String serviceDate = new String(request.getParameter("servicedate").getBytes("ISO8859-1"), "UTF-8");
			CustomerControl customerCtrl = new CustomerControl();			
			PrintWriter out = response.getWriter();			
			out.println(customerCtrl.getServiceIdString(serviceDate));			
			out.flush();
			out.close();
			return;
		}
		else if("mark".equals(action))
		{
			cellMap = getMultiFilter(request);
			Long serviceId = Long.parseLong(new String(request.getParameter("serviceid").getBytes("ISO8859-1"), "UTF-8"));
			CustomerControl customerCtrl = new CustomerControl();			
			customerCtrl.getCustomerMultiFilter(cellMap);
			try {
			customerCtrl.executeMarkList(serviceId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return;
		}
		else if("exmobile".equals(action))
		{			
			cellMap = getMultiFilter(request);
			CustomerControl customerCtrl = new CustomerControl();			
			customerCtrl.getCustomerMultiFilter(cellMap);
			try {
				response.setContentType("html/txt");
				response.setHeader("Content-Disposition","attachment;filename=\"phone.txt\"");
				PrintWriter out = response.getWriter();
				while (customerCtrl.rs.next())
				{
					if(customerCtrl.rs.getString("mobilephone1") != null)
					{
						out.println(customerCtrl.rs.getString("mobilephone1"));
					}		
					if(customerCtrl.rs.getString("mobilephone2") != null)
					{
						out.println(customerCtrl.rs.getString("mobilephone2"));
					}
					if(customerCtrl.rs.getString("mobilephone3") != null)
					{
						out.println(customerCtrl.rs.getString("mobilephone3"));
					}
					if(customerCtrl.rs.getString("mobilephone4") != null)
					{
						out.println(customerCtrl.rs.getString("mobilephone4"));
					}
				} 
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return;
		}
		else if("exmail".equals(action))
		{
			cellMap = getMultiFilter(request);
			CustomerControl customerCtrl = new CustomerControl();			
			customerCtrl.getCustomerMultiFilter(cellMap);
			try {
				response.setContentType("html/txt");
				response.setHeader("Content-Disposition","attachment;filename=\"mail.txt\"");
				PrintWriter out = response.getWriter();	
				while (customerCtrl.rs.next())
				{
					if(customerCtrl.rs.getString("mail") != null)
					{
						out.println(customerCtrl.rs.getString("mail"));
					}					
				} 
				
				out.flush();
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return;
		}		
		else if("servicequery".equals(action))
		{
			return;
		}
		else if("batchdel".equals(action))
		{
			if(request.getParameter("id") != "")
			{
				CustomerControl customerCtrl = new CustomerControl();
				customerCtrl.delUser(request.getParameter("id"));
			}
			return;
		}
				
		String searchtype=new String(request.getParameter("searchtype").getBytes("ISO8859-1"), "UTF-8");
		String query=request.getParameter("query");
		String qtype=new String(request.getParameter("qtype").getBytes("ISO8859-1"), "UTF-8");
				
		// ��õ�ǰҳ��?
		String pageIndex = request.getParameter("page");
		// ���ÿҳ��������?
		String pageSize = request.getParameter("rp");

		int count = 0;
		String sql = "";
		List list = new ArrayList();

		int intPageSize = Integer.parseInt(pageSize);
		// ȡ�ô���ʾҳ��
		int intPage = 0;

		if (pageIndex == null) {// ������QueryString��û��page��һ�������ʱ��ʾ��һҳ���
			intPage = 1;
		} else {// ���ַ�ת��������
			intPage = Integer.parseInt(pageIndex);
			if (intPage < 1)
				intPage = 1;
		}		
				
		CustomerControl customerCtrl = new CustomerControl();
		
		if(searchtype.equals("normal"))
		{
			count = customerCtrl.getCustomerFilterCount(qtype, query);			
		}
		else if(searchtype.equals("advance"))
		{
			cellMap = getMultiFilter(request);
			count = customerCtrl.getCustomerMultiFilterCount(cellMap);
		}		
		else
		{
			count = customerCtrl.getAllCustomerCount();
	    }
		
			
		
		Map map = new HashMap();
		map.put("page", pageIndex);
		map.put("total", count + "");
 
		//������ҳ�� 
		int intPageCount = (count+intPageSize-1) / intPageSize; 
		//�������ʾ��ҳ��?
		if(intPage>intPageCount) intPage = intPageCount; 
	
		if(intPageCount>0){ 
			//����¼ָ�붨λ������ʾҳ�ĵ�һ����¼�� 
			try {
				if(searchtype.equals("normal"))
				{
					customerCtrl.getCustomerFilter(qtype, query, (intPage-1) * intPageSize+1, intPage* intPageSize);
				}
				else if(searchtype.equals("advance"))
				{
					customerCtrl.getCustomerMultiFilter(cellMap, (intPage-1) * intPageSize+1, intPage* intPageSize);
				}
				else
				{
					customerCtrl.allCustomer((intPage-1) * intPageSize+1, intPage* intPageSize);
				}				
				list = customerCtrl.executeQueryList(customerCtrl.rs, intPageSize);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//��ʾ���?
		}
		
		PrintWriter out = response.getWriter();
		out.println(getJsonString(list, map));
		out.flush();
		out.close();
		
		System.out.println("doPost end");
	}
	
	

}
