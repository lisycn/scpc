package com.system.flow.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.constant.Constant;
import com.system.database.JdbcControl;
import com.system.database.QueryCallBack;
import com.system.flow.model.SpTroneModel;
import com.system.util.SqlUtil;
import com.system.util.StringUtil;

public class SpTroneDao
{
	public SpTroneModel getSpTroneById(int id)
	{
		String sql = "SELECT a.*,b.short_name sp_name FROM daily_config.tbl_f_sp_trone a left join daily_config.tbl_f_sp b on a.sp_id = b.id WHERE a.id = " + id;
		return (SpTroneModel)new JdbcControl().query(sql, new QueryCallBack()
		{
			@Override
			public Object onCallBack(ResultSet rs) throws SQLException
			{
				if(rs.next())
				{
					SpTroneModel model = new SpTroneModel();
					
					model.setId(rs.getInt("id"));
					model.setSpTroneName(StringUtil.getString(rs.getString("name"), ""));
					model.setSpId(rs.getInt("sp_id"));
					model.setSpName(StringUtil.getString(rs.getString("sp_name"), ""));
					model.setSpApiId(rs.getInt("sp_api_id"));
					model.setPriceId(rs.getInt("price_id"));
					model.setType(rs.getInt("type"));
					model.setFlowTypeId(rs.getInt("flow_type_id"));
					model.setRatio(rs.getInt("ratio"));
					model.setSendSms(rs.getInt("send_sms"));
					model.setProNames(StringUtil.getString(rs.getString("pro_names"), ""));
					model.setRemark(StringUtil.getString(rs.getString("remark"), ""));
					model.setStatus(rs.getInt("status"));
					
					return model;
				}
				return null;
			}
		});
	}
	
	public Map<String, Object> loadSpTrone(int pageIndex, String keyWord)
	{
		String queryData = "a.id,b.id sp_id,b.short_name sp_name,a.name sp_trone_name,c.name sp_api_name,CONCAT(f.name_cn,'-',d.name,'-',d.num) price_name,e.use_rang,e.time_type,e.name flow_type_name,a.ratio,a.send_sms,a.pro_names,a.remark,a.status";
		
		String sql = " SELECT " + Constant.CONSTANT_REPLACE_STRING;
		
		sql += " FROM " + Constant.DB_DAILY_CONFIG + ".tbl_f_sp_trone a";
		sql += " LEFT JOIN " + Constant.DB_DAILY_CONFIG + ".tbl_f_sp b ON a.sp_id = b.id";
		sql += " LEFT JOIN " + Constant.DB_DAILY_CONFIG + ".tbl_f_sp_api c ON a.sp_api_id = c.id";
		sql += " LEFT JOIN " + Constant.DB_DAILY_CONFIG + ".tbl_f_basic_price d ON a.price_id = d.id";
		sql += " LEFT JOIN " + Constant.DB_DAILY_CONFIG + ".tbl_f_flow_type e ON a.flow_type_id";
		sql += " LEFT JOIN " + Constant.DB_DAILY_CONFIG + ".tbl_operator f ON d.operator = f.flag";
		sql += " WHERE 1=1 ";
		
		if(!StringUtil.isNullOrEmpty(keyWord))
		{
			sql += " AND (a.name like '%" + keyWord + "%' or b.full_name like '%" + keyWord + "%' or b.short_name like '%" + keyWord + "%')";
		}
		
		sql += " GROUP BY a.id,f.flag";

		String limit = " limit " + Constant.PAGE_SIZE * (pageIndex - 1) + "," + Constant.PAGE_SIZE;

		Map<String, Object> map = new HashMap<String, Object>();

		sql += " order by a.id desc ";

		JdbcControl control = new JdbcControl();
		
		map.put("rows", control.query(
				sql.replace(Constant.CONSTANT_REPLACE_STRING, "count(*)"),
				new QueryCallBack()
				{
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException
					{
						if (rs.next())
							return rs.getInt(1);

						return 0;
					}
				}));

		map.put("list",control.query(sql.replace(Constant.CONSTANT_REPLACE_STRING, queryData) + limit, new QueryCallBack()
				{
					@Override
					public Object onCallBack(ResultSet rs) throws SQLException
					{
						List<SpTroneModel> list = new ArrayList<SpTroneModel>();
						
						while (rs.next())
						{
							SpTroneModel model = new SpTroneModel();
							
							model.setId(rs.getInt("id"));
							model.setSpName(StringUtil.getString(rs.getString("sp_name"), ""));
							model.setSpId(rs.getInt("sp_id"));
							model.setFlowTypeName(StringUtil.getString(rs.getString("flow_type_name"), ""));
							model.setPriceName(StringUtil.getString(rs.getString("price_name"), ""));
							model.setProNames(StringUtil.getString(rs.getString("pro_names"), ""));
							model.setSpTroneName(StringUtil.getString(rs.getString("sp_trone_name"), ""));
							model.setSpApiName(StringUtil.getString(rs.getString("sp_api_name"), ""));
							model.setRemark(StringUtil.getString(rs.getString("remark"), ""));
							model.setSendSms(rs.getInt("send_sms"));
							model.setStatus(rs.getInt("status"));
							model.setRang(rs.getInt("use_rang"));
							model.setRatio(rs.getInt("ratio"));
							
							list.add(model);							
						}
						
						return list;
					}
				}));

		return map;
	}
	
	public int addSpTrone(SpTroneModel model)
	{
		String sql = "insert into " + Constant.DB_DAILY_CONFIG + ".tbl_f_sp_trone(sp_id,name,sp_api_id,price_id,flow_type_id,ratio,send_sms,pro_names,remark,status) values(?,?,?,?,?,?,?,?,?,?);";
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, model.getSpId());
		map.put(2,model.getSpTroneName());
		map.put(3, model.getSpApiId());
		map.put(4, model.getPriceId());
		map.put(5, model.getFlowTypeId());
		map.put(6, model.getRatio());
		map.put(7, model.getSendSms());
		map.put(8, model.getProNames());
		map.put(9, model.getRemark());
		map.put(10, model.getStatus());
		
		return new JdbcControl().insertWithGenKey(sql, map);
	}
	
	public void updateSpTrone(SpTroneModel model)
	{
		String sql = "UPDATE daily_config.tbl_f_sp_trone SET sp_id = ?,name = ?,sp_api_id = ?,price_id = ? ,flow_type_id = ? ,ratio = ? ,send_sms = ? ,pro_names = ?,remark = ? ,STATUS = ? WHERE id = ?";
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		
		map.put(1, model.getSpId());
		map.put(2, SqlUtil.sqlEncode(model.getSpTroneName()));
		map.put(3, model.getSpApiId());
		map.put(4, model.getPriceId());
		map.put(5, model.getFlowTypeId());
		map.put(6, model.getRatio());
		map.put(7, model.getSendSms());
		map.put(8, SqlUtil.sqlEncode(model.getProNames()));
		map.put(9, SqlUtil.sqlEncode(model.getRemark()));
		map.put(10, model.getStatus());
		map.put(11, model.getId());
		
		new JdbcControl().execute(sql,map);
	}
}