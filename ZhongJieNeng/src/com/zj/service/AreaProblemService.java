package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zj.util.DbHelper;
import com.zj.vo.AreaProblem;

/**
 * 区域问题
 * */
public class AreaProblemService {

	public boolean addAreaProblem(AreaProblem vo) {
		String sql = " insert into areaproblem(number,infotype,areaid,envtype,title,shortmessage,detailmessage) values(?,?,?,?,?,?,?)";
		vo.setNumber("" + System.currentTimeMillis());
		try {
			DbHelper.update(sql, vo.getNumber(), vo.getInfotype(),
					vo.getAreaid(), vo.getEnvtype(), vo.getTitle(),
					vo.getShortmessage(), vo.getDetailmessage());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<AreaProblem> getAllVOs(){
		List<AreaProblem> result=new ArrayList<AreaProblem>();
		String sql="select * from areaproblem where areaid!='harbin' ";
		try {
			result=DbHelper.queryMultiBean(new AreaProblem(), sql);
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
	
	public List<AreaProblem> getVOsbyCity(String number){
		List<AreaProblem> result=new ArrayList<AreaProblem>();
		String sql="select * from areaproblem where areaid=? ";
		try {
			result=DbHelper.queryMultiBean(new AreaProblem(), sql,number);
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


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AreaProblemService ser=new AreaProblemService();
		System.out.println(ser.getVOsbyCity("harbin").size());

	}

}
