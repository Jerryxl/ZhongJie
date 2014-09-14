package com.zj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.xwork.StringUtils;

import com.zj.service.GeneralTechService;
import com.zj.service.TechTypeService;
import com.zj.service.TypeService;
import com.zj.vo.ImageVO;
import com.zj.vo.MenuItem;
import com.zj.vo.ShortGeneralVO;
import com.zj.vo.TechTypeVO;
import com.zj.vo.TypeVO;

/**
 * 通用的工具方法
 * */
/**
 * @author xueliang
 *
 */
public class CommonTools {

	/**
	 * 构造菜单列表，关键是构造树形结构，每个节点是一个MenuItem
	 * 污染物类别可以多级
	 * 这里工艺类别只处理一级情况
	 * 工艺挂在工艺类别下
	 * 
	 * */
	public static ArrayList<MenuItem> construceMenu(){
		ArrayList<MenuItem> result=new ArrayList<MenuItem>();
		HashMap<String,String> typemaps=getTypeMap();
		//污染物类别的长度
		int Maxt=4;
		ArrayList<String> typenums=new ArrayList<String>();//污染物类别列表，存储编号，水、气、声、渣。。。。。
		for(String s:typemaps.keySet()){
			typenums.add(s);
			if(s.length()>4){
				Maxt=s.length();
			}
		}
		//技术工艺
		List<TechTypeVO> telist=getTechVOs();//工艺类别，污染物列别
		List<ShortGeneralVO> gvos=getGeneVOs();
		//按照长度构造
		int loopnum=Maxt/4;
		for(int i=1;i<=loopnum;i++){
			for(String s:typenums){
				if(s.length()==i*4){
					MenuItem item=new MenuItem();
					item.setCode(s);
					item.setName(typemaps.get(s));
					item.setType("1");
					item.setLevel(i);
					ArrayList<MenuItem> itemson=new ArrayList<MenuItem>();
					item.setChild(itemson);
					//为类别下添加对应的技术类别
					for(TechTypeVO vv:telist){
						if(vv.getCh_type().equals(s)){
							MenuItem im=new MenuItem();
							im.setCode(vv.getNumber());
							im.setName(vv.getName());
							im.setType("2");
							im.setLevel(i+1);
							ArrayList<MenuItem> techson=new ArrayList<MenuItem>();
							im.setChild(techson);
							
							item.getChild().add(im);
							for(ShortGeneralVO gg:gvos){
								if(!gg.getTechtype().contains("_"))continue;
								if(gg.getTechtype().split("_")[1].equals(vv.getNumber())){
									MenuItem gin=new MenuItem();
									gin.setCode(gg.getNumber());
									gin.setName(gg.getTechname());
									gin.setType("3");
									gin.setLevel(im.getLevel()+1);
									ArrayList<MenuItem> gson=new ArrayList<MenuItem>();
									gin.setChild(gson);
									im.getChild().add(gin);
								}
							}
						
						}
					}
					if(i==1){//如果i==1那么是第一层，那么代表第一层，直接加入就好
						result.add(item);
					}else{//非第一层，那么要加入对应的上级
						for(MenuItem mi:result){
							if(s.startsWith(mi.getCode())&&(s.length()-mi.getCode().length()==4)){
								mi.getChild().add(item);
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 返回污染类别列表
	 * map-->  <number name>
	 * */
	public static HashMap<String,String> getTypeMap(){
		HashMap<String,String> resultmap=new HashMap<String,String>();
		TypeService ty=new TypeService();
		List<TypeVO> tylist=ty.getTypes();
		for(TypeVO vo:tylist){
			resultmap.put(vo.getNumber(), vo.getName());
		}
		return resultmap;
	}
	
	/**
	 * 类别的VO
	 * */
	public static List<TypeVO> getTypeVOs(){
		TypeService ty=new TypeService();
		List<TypeVO> tylist=ty.getTypes();
		return tylist;
	}

	/**
	 * 返回工艺类别列表
	 * map-->  <number name>
	 * */
	public static HashMap<String,String> getTechMap(){
		HashMap<String,String> resultmap=new HashMap<String,String>();
		TechTypeService ts=new TechTypeService();
		List<TechTypeVO> techlist=ts.getAllType();
		for(TechTypeVO vo:techlist){
			resultmap.put(vo.getNumber()+"_"+vo.getFather(), vo.getName());
		}
		return resultmap;
	}
	/**
	 * 工艺类别的VO
	 * */
	public static List<TechTypeVO> getTechVOs(){
		TechTypeService ts=new TechTypeService();
		List<TechTypeVO> techlist=ts.getAllType();
		return techlist;
	}
	
	
	/**
	 * 返回具体工艺列表
	 * map-->  <number name>
	 * */
	public static HashMap<String,String> getShortCutList(){
		HashMap<String,String> resultmap=new HashMap<String,String>();
		GeneralTechService ss=new GeneralTechService();
		List<ShortGeneralVO> slist=ss.getShortCutLists();
		for(ShortGeneralVO vo:slist){
			resultmap.put(vo.getNumber(), vo.getTechname());
		}
		return resultmap;
	}
	
	public static List<ShortGeneralVO> getGeneVOs(){
		GeneralTechService ss=new GeneralTechService();
		List<ShortGeneralVO> slist=ss.getShortCutLists();
		return slist;
	}
	/**
	 * @param str
	 */
	public static String[] SplitString(String str){
		ArrayList<String> result=new ArrayList<String>();
		String s[]=str.split("\\$\\$");
		for(String k:s){
			if(k!=""&&k!=null&&k.length()>0){
				result.add(k);
			}
		}
		return result.toArray(new String[0]);
	}
	/**
	 * 获得数据的图片
	 * @param names
	 * @param desc
	 * @return
	 */
	public static List<ImageVO> getImageList(String names,String desc){
		List<ImageVO> result=new ArrayList<ImageVO>();
		if(StringUtils.isEmpty(names)||StringUtils.isEmpty(desc)){
			return result;
		}
		String[] namelist=names.split("$$");
		String[] desclist=desc.split("$$");
		if(ArrayUtils.isEmpty(namelist)||ArrayUtils.isEmpty(desclist)){
			return result;
		}
		for(int i=0;i<namelist.length;i++){
			if(StringUtils.isNotBlank(namelist[i])&&i<desclist.length&&StringUtils.isNotBlank(desclist[i])){
				ImageVO image=new ImageVO();
				image.setImagename(namelist[i]);
				image.setDescription(desclist[i]);
				result.add(image);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String s="asdf";
		String ss[]=s.split("##");
		System.out.println(ss[0]);
		
		
	}
}
