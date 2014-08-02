package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;

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
		String sql=" select * from ch_type order by id asc";
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
		return list;
	}
	/**
	 * 新增一个类别
	 * */
	public synchronized boolean addType(TypeVO vo){
		if(vo==null)return false;
		 List<TypeVO> all=getTypes();
		 //生成编号
		 String num=generateSingleNmu(all, vo.getFather());
		 vo.setNumber(num);
		 String sql =" insert into ch_type(number,name,memo,father) values(?,?,?,?)";
		 try {
			DbHelper.insertWithReturnPrimeKey(sql, vo.getNumber(),vo.getName(),vo.getMemo(),vo.getFather());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 生成指定父节点的子节点编号
	 * */
	private String generateSingleNmu(List<TypeVO> exist,String father){
		List<String> allNum=existNumbers(exist);
		String temp=generateNum(father);
		if(allNum.contains(temp)){
			return generateSingleNmu(exist, father);
		}
		return temp;
	}
	/**
	 * 已经存在的编号
	 * */
	private List<String> existNumbers(List<TypeVO> list){
		List<String> num=new ArrayList<String>();
		for(TypeVO vo:list){
			num.add(vo.getNumber());
		}
		return num;
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
	 * @param args
	 */
	public static void main(String[] args) {
		TypeService ss=new TypeService();
		System.out.println(ss.getTypes().size());

	}

}
