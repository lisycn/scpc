package com.system.model;

import java.util.ArrayList;
import java.util.List;

public class CpModel
{
	private int cpId;
	private String fullName;
	private int currency;
	private int status;
	private String signKey;
	private List<String> ipList = new ArrayList<String>();
	
	public int getCpId()
	{
		return cpId;
	}
	public String getFullName()
	{
		return fullName;
	}
	public int getCurrency()
	{
		return currency;
	}
	public int getStatus()
	{
		return status;
	}
	public String getSignKey()
	{
		return signKey;
	}
	public void setCpId(int cpId)
	{
		this.cpId = cpId;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public void setCurrency(int currency)
	{
		this.currency = currency;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public void setSignKey(String signKey)
	{
		this.signKey = signKey;
	}
	public List<String> getIpList()
	{
		return ipList;
	}
	public void setIpList(List<String> ipList)
	{
		this.ipList = ipList;
	}
	
	
}