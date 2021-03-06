
package com.system.server;

import java.util.List;
import java.util.Map;

import com.system.dao.MrDao;
import com.system.model.MrReportModel;
import com.system.model.params.ReportParamsModel;
import com.system.util.StringUtil;

public class MrServer
{
	//为上市公司打造的
	public Map<String, Object> getWjMrData(ReportParamsModel params)
	{
		Map<String, Object> result = new MrDao().getWjMrAnalyData(params);
		
		//如果数据类型，只能重新处理标题
		if(params.getShowType() ==15)
		{
			String[] titles = {"实时","隔天","IVR","第三方支付"};
			
			@SuppressWarnings("unchecked")
			List<MrReportModel> list = (List<MrReportModel>)result.get("list");
			if(list!=null)
			for(MrReportModel model : list)
			{
				model.setTitle1(titles[StringUtil.getInteger(model.getTitle1(),0)]);
			}
		}
		
		return result;
	}
	
	//将参数集中起来
	public Map<String, Object> getMrData(ReportParamsModel params)
	{
		Map<String, Object> result = new MrDao().getMrAnalyData(params);
		
		//如果数据类型，只能重新处理标题
		if(params.getShowType() ==15)
		{
			String[] titles = {"实时","隔天","IVR","第三方支付"};
			
			@SuppressWarnings("unchecked")
			List<MrReportModel> list = (List<MrReportModel>)result.get("list");
			if(list!=null)
			for(MrReportModel model : list)
			{
				model.setTitle1(titles[StringUtil.getInteger(model.getTitle1(),0)]);
			}
		}
		
		return result;
	}
	
	//为上市公司打造的方法
	public Map<String, Object> getWjMrAnalyData(ReportParamsModel params)
	{
		Map<String, Object> result = new MrDao().getWjMrAnalyData(params);
		
		//如果数据类型，只能重新处理标题
		if(params.getShowType() ==15)
		{
			String[] titles = {"实时","隔天","IVR","第三方支付"};
			
			@SuppressWarnings("unchecked")
			List<MrReportModel> list = (List<MrReportModel>)result.get("list");
			if(list!=null)
			for(MrReportModel model : list)
			{
				model.setTitle1(titles[StringUtil.getInteger(model.getTitle1(),0)]);
			}
		}
		
		return result;
	}
	
	
	
	public Map<String, Object> getMrData(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId,int operatorId,int dataType,String spCommerceUserId,String cpCommerceUserId,int isUnHoldData,int sortType)
	{
		
		Map<String, Object> result = new MrDao().getMrAnalyData(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId,operatorId,dataType,spCommerceUserId,cpCommerceUserId,isUnHoldData,sortType);
		
		//如果数据类型，只能重新处理标题
		if(sortType==15)
		{
			String[] titles = {"实时","隔天","IVR","第三方支付"};
			
			@SuppressWarnings("unchecked")
			List<MrReportModel> list = (List<MrReportModel>)result.get("list");
			if(list!=null)
			for(MrReportModel model : list)
			{
				model.setTitle1(titles[StringUtil.getInteger(model.getTitle1(),0)]);
			}
		}
		
		return result;
	}
	
	public Map<String, Object> getThirdPayMrData(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId,int operatorId,int dataType,String spCommerceUserId,String cpCommerceUserId,int sortType)
	{
		
		Map<String, Object> result = new MrDao().getThirdPayMrData(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId,operatorId,dataType,spCommerceUserId,cpCommerceUserId,sortType);
		
		//如果数据类型，只能重新处理标题
		if(sortType==15)
		{
			String[] titles = {"实时","隔天","IVR","第三方支付"};
			
			@SuppressWarnings("unchecked")
			List<MrReportModel> list = (List<MrReportModel>)result.get("list");
			if(list!=null)
			for(MrReportModel model : list)
			{
				model.setTitle1(titles[StringUtil.getInteger(model.getTitle1(),0)]);
			}
		}
		
		return result;
	}
	
	public Map<String, Object> getMrLrData(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId,int operatorId,int dataType,String spCommerceUserId,String cpCommerceUserId,int isUnHoldData,int sortType)
	{
		return new MrDao().getMrAnalyLrData(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId,operatorId,dataType,spCommerceUserId,cpCommerceUserId,isUnHoldData,sortType);
	}
	
	public Map<String, Object> getMrDataQiBa(String startDate, String endDate,
			int spId,int spTroneId, int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, int sortType)
	{
		return new MrDao().getMrAnalyDataQiBa(startDate, endDate, spId, spTroneId,troneId,
				cpId, troneOrderId, provinceId, cityId, sortType);
	}
	
	public Map<String, Object> getMrTodayData(String startDate,
			int spId, int spTroneId,int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, String spCommerceUserId,String cpCommerceUserId,int sortType)
	{
		String tableName = StringUtil.getMonthFormat(startDate);
		
		String curDate = StringUtil.getDefaultDate();
		
		if(curDate.equalsIgnoreCase(startDate))
		{
			tableName = "daily";
		}
		
		return new MrDao().getMrTodayData(tableName, startDate, spId,spTroneId, troneId,
				cpId, troneOrderId, provinceId, cityId,spCommerceUserId, cpCommerceUserId,sortType);
	}
	
	public Map<String, Object> getMrTodayLrData(String startDate,
			int spId, int spTroneId,int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, String spCommerceUserId,String cpCommerceUserId,int sortType)
	{
		String tableName = StringUtil.getMonthFormat(startDate);
		return new MrDao().getMrTodayLrData(tableName, startDate, spId,spTroneId, troneId,
				cpId, troneOrderId, provinceId, cityId,spCommerceUserId,cpCommerceUserId,sortType);
	}
	
	public Map<String, Object> getMrTodayDataQiBa(
			int spId, int spTroneId,int troneId, int cpId, int troneOrderId, int provinceId,
			int cityId, int sortType)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new MrDao().getMrTodayDataQiBa(tableName, startDate, spId,spTroneId, troneId,
				cpId, troneOrderId, provinceId, cityId, sortType);
	}
	
	public Map<String,Object> getCpMrTodayShowData(int userId,int spTroneId)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();
		return new MrDao().getCpMrTodayShowData(tableName, startDate, userId,spTroneId);
	}
	
	public Map<String,Object> getCpMrShowData(String startDate,String endDate,int userId,int spTroneId,int showType)
	{
		return new MrDao().getCpMrShowData(startDate, endDate, userId,spTroneId,showType);
	}
	
	/**
	 * 更新MR汇总表里面的上游结算率
	 * @param spTroneId
	 * @param rate
	 * @param startDate
	 * @param endDate
	 */
	public void updateMrRate(int spTroneId,float rate,String startDate,String endDate)
	{
		new MrDao().updateMrRate(spTroneId, rate, startDate, endDate);
	}
	
	
	/**
	 * 更新CP MR汇总表里面的下游结算率
	 * @param cpId
	 * @param spTroneId
	 * @param rate
	 * @param startDate
	 * @param endDate
	 */
	public void updateCpMrRate(int cpId,int spTroneId,float rate,String startDate,String endDate)
	{
		new MrDao().updateCpMrRate(cpId,spTroneId, rate, startDate, endDate);
	}
	/**
	 * 当日数据添加业务和指令查询
	 * @param userId
	 * @param spTroneId
	 * @param showType
	 * @return
	 */
	public Map<String,Object> getCpMrTodayShowData(int userId,int spTroneId,int showType)
	{
		String tableName = StringUtil.getMonthFormat();
		String startDate = StringUtil.getDefaultDate();

		return new MrDao().getCpMrTodayShowData(tableName, startDate, userId,spTroneId,showType);
	}

	
}
