package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.zj.util.DbHelper;
import com.zj.vo.TechTypeVO;
import com.zj.vo.TypeVO;

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
	
	public  boolean addTechType(TechTypeVO vo){
		if(vo==null)return false;
		 List<TechTypeVO> all=getAllType();
		 //生成编号
		 String num=generateSingleNmu(all, vo.getFather());
		 vo.setNumber(num);
		 String sql =" insert into technique_type(number,name,memo,father,ch_type) values(?,?,?,?,?)";
		 try {
			DbHelper.insertWithReturnPrimeKey(sql, vo.getNumber(),vo.getName(),vo.getMemo(),vo.getFather(),vo.getCh_type());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 获取所有工艺类别编号
	 * */
	private List<String> getAllNumbers(List<TechTypeVO> exist){
		List<String> list=new ArrayList<String>();
		for(TechTypeVO vo:exist){
			list.add(vo.getNumber());
		}
		return list;
	}
	/**
	 * 生成指定父节点的子节点的编号
	 * @param father 父节点的编号
	 * */
	private String generateNum(String father){
		Random rand=new Random();
		int next=1000+Math.abs(rand.nextInt(9000));
		if(father.equalsIgnoreCase("00")){
			return ""+next;
		}
		return father+next;
	} 
	
	/**
	 * 生成指定父节点的子节点编号
	 * */
	private String generateSingleNmu(List<TechTypeVO> exist,String father){
		List<String> allNum=getAllNumbers(exist);
		String temp=generateNum(father);
		if(allNum.contains(temp)){
			return generateSingleNmu(exist, father);
		}
		return temp;
	}
}
