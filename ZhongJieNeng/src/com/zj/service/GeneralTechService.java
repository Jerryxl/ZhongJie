package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zj.util.DbHelper;
import com.zj.vo.GeneralVO;
import com.zj.vo.ShortGeneralVO;

public class GeneralTechService {

	/**
	 * 添加一个实体
	 * */
	public boolean addGeneralVO(GeneralVO vo) {
		if (vo == null) {
			return false;
		}
		vo.setNumber("" + System.currentTimeMillis());
		String sql = " insert into generaltech(number,techname,zhtype,techtype,pulAttr,projectimages,imagesdescribe,tcflash,pathflash,shortmessage,usescope,advancedesc,appdesc,detailmessage) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			DbHelper.update(sql, vo.getNumber(), vo.getTechname(),
					vo.getZhtype(), vo.getTechtype(), vo.getPulAttr(),
					vo.getProjectimages(), vo.getImagesdescribe(),
					vo.getTcflash(), vo.getPathflash(), vo.getShortmessage(),
					vo.getUsescope(), vo.getAdvancedesc(), vo.getAppdesc(),
					vo.getDetailmessage());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean updateGeneralVO(GeneralVO vo){
		if(vo==null)return false;
		String sql=" update generaltech set techname=?,zhtype=?,techtype=?,advancedesc=?,appdesc=?,detailmessage=?,shortmessage=?,usescope=?,tcflash=?,pathflash=? where id= ? ";
		try {
			DbHelper.update(sql,vo.getTechname(),vo.getZhtype(),vo.getTechtype(),vo.getAdvancedesc(),vo.getAppdesc(),vo.getDetailmessage(),vo.getShortmessage(),vo.getUsescope(),vo.getTcflash(),vo.getPathflash(),vo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public List<GeneralVO> getVOs(){
		List<GeneralVO> list=new ArrayList<GeneralVO>();
		String sql="select * from generaltech order by id desc";
		try {
			list=DbHelper.queryMultiBean(new GeneralVO(), sql);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<GeneralVO> getByTypeAndNumber(String type,String number){
		List<GeneralVO> list=new ArrayList<GeneralVO>();
		list=getVOs();
		if(list==null){
			return new ArrayList<GeneralVO>();
		}
		List<GeneralVO> result=new ArrayList<GeneralVO>();
		if("1".equals(type)){
			for(int i=0;i<list.size();i++){
				GeneralVO vo=list.get(i);
				if(vo.getZhtype().startsWith(number)){
					result.add(vo);
				}
			}
		}else if("2".equals(type)){//按照技术体系
			for(int i=0;i<list.size();i++){
				GeneralVO vo=list.get(i);
				if(vo.getTechtype().split("_")[1].startsWith(number)){
					result.add(vo);
				}
			}
		}else{//按照工艺搜索
			for(int i=0;i<list.size();i++){
				GeneralVO vo=list.get(i);
				if(vo.getNumber().equals(number)){
					result.add(vo);
				}
			}
		}
		return result;
	}
	/**
	 * 获取技术列表，精简版，只包含编码，名称和类型
	 * */
	@SuppressWarnings("unchecked")
	public List<ShortGeneralVO> getShortCutLists(){
		List<ShortGeneralVO> result=new ArrayList<ShortGeneralVO>();
		String sql=" select number,techname,techtype from generaltech order by number asc";
		try {
			result=DbHelper.queryMultiBean(new ShortGeneralVO(), sql);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public GeneralVO getById(String tid){
		GeneralVO vo=new GeneralVO();
		String sql="select * from generaltech where id= ? ";
		try {
			GeneralVO list=(GeneralVO) DbHelper.querySingleBean(new GeneralVO(), sql,tid);
			if(list!=null)
				return list;
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean delete(String techname){
		String sql="delete from generaltech where techname = ?";
		boolean result = false;
		try {
			result = DbHelper.update(sql, techname) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * delete by id
	 * */
	public boolean deletebyId(String tid){
		String sql="delete from generaltech where id=?";
		boolean result=true;
		try {
			result=DbHelper.update(sql, tid)>0?true:false;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralTechService ss=new GeneralTechService();
		List<GeneralVO> ll=ss.getVOs();
		System.out.println(ll.size());
	}

}
