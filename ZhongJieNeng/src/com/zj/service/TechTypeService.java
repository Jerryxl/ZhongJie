package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zj.util.DbHelper;
import com.zj.vo.TechTypeVO;

/**
 * 技术分类处理类
 * */
public class TechTypeService {

	
	@SuppressWarnings("unchecked")
	public List<TechTypeVO> getAllType(){
		List<TechTypeVO> result=new ArrayList<TechTypeVO>();
		String sql="select * from technique_type order by number asc";
		try {
			
			result=DbHelper.queryMultiBean(new TechTypeVO(), sql);
			
			return result;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
