package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zj.util.DbHelper;
import com.zj.vo.TypeVO;

public class TypeService {

	/***
	 * 
	 * 获取类比列表
	 * */
	@SuppressWarnings("unchecked")
	public  List<TypeVO> getTypes(){
		List<TypeVO> list=new ArrayList<TypeVO>();
		String sql=" select * from ch_type order by number asc";
		try {
			list=DbHelper.queryMultiBean(new TypeVO(), sql);
			return list;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TypeService ss=new TypeService();
		System.out.println(ss.getTypes().size());

	}

}
