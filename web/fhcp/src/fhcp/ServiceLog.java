package fhcp;


public class ServiceLog {
	public long serviceID;
	public long userID;
	public int ackflag,dealflag,failflag;
	
	public long getServiceID()
	{
		return serviceID;
	}
	
	public void setServiceID(long id)
	{
		serviceID = id;
	}
	
	public long getUserID()
	{
		return userID;
	}
	
	public void setUserID(long id)
	{
		userID = id;
	}
	
	public void setAckFlag(int flag)
	{
		ackflag = flag;
	}
	
	public void setDealFlag(int flag)
	{
		dealflag = flag;
	}
	
	public void setFailFlag(int flag)
	{
		failflag = flag;
	}
	
	public String getServiceState()
	{
		if(failflag == 1)
		{
			return "已失败";
		}
		else if(dealflag == 1)
		{
			return "已订购";
		}
		else if(ackflag == 1)
		{
			return "已响应";
		}
		else
		{
			return "待定";
		}
	}
}
