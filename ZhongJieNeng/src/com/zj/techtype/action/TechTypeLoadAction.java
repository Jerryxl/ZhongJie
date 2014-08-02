package com.zj.techtype.action;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.TechTypeService;
import com.zj.vo.TechTypeVO;

/**
 * 工艺技术分类类别加载
 * */
public class TechTypeLoadAction extends ActionSupport{
	private String zhtype;//对应的污染物类别
	private String techtype;//上级类别
	private String techName;//名称
	private String techMemo;//备注

	private static final long serialVersionUID = 4498256488006452990L;
	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}
	
	/**
	 * 添加工艺类别
	 * */
	public String addtechType(){
		TechTypeVO vo=new TechTypeVO();
		if(StringUtils.isBlank(zhtype)||StringUtils.isBlank(techName)||StringUtils.isBlank(techtype))return ERROR;
		String temp=techtype;
		if("00".equalsIgnoreCase(techtype)){
			temp="00";
		}else{
			String s[]=techtype.split("_");
			temp=s[s.length-1];
		}
		vo.setCh_type(zhtype);
		vo.setFather(temp);
		vo.setMemo(techMemo);
		vo.setName(techName);
		TechTypeService service=new TechTypeService();
		boolean flag=service.addTechType(vo);
		return flag?SUCCESS:ERROR;
	}
	@Override
	public String execute() throws Exception {
		
		JSONArray array = new JSONArray();
		jsonobject = new JSONObject();
		
		//空的
		JSONObject em=new JSONObject();
		em.put("value", "00");
		em.put("name","无");
		array.add(em);
		
		TechTypeService service=new TechTypeService();
		List<TechTypeVO> list=service.getAllType();

		HashMap<String, String> map = construct(list);
		if (map.size() < 1) {
			return SUCCESS;
		}

		for (String s : map.keySet()) {
			JSONObject obj = new JSONObject();
			obj.put("value", s);
			obj.put("name", map.get(s));
			array.add(obj);
		}
		jsonobject.put("data", array);
		System.out.println(jsonobject.toString());
		return SUCCESS;
	}
	
	/**
	 * 构造列别列表 <number name>
	 * */
	public HashMap<String, String> construct(List<TechTypeVO> ls) {
		HashMap<String, String> result = new HashMap<String, String>();
		HashMap<String, String> num_name = new HashMap<String, String>();
		for (TechTypeVO vv : ls) {
			num_name.put(vv.getNumber(), vv.getName());
		}

		for (TechTypeVO vo : ls) {
			if (vo.getNumber().length() <= 4) {
				result.put(vo.getCh_type()+"_"+vo.getNumber(), vo.getName());
			} else {
				int i = 0;
				String name = "";
				String number = "";
				for (; i < (vo.getNumber().length() - 4);) {
					number = vo.getNumber().substring(0, i + 4);
					i += 4;
					name = name + num_name.get(number) + "-->";
				}
				result.put(vo.getCh_type()+"_"+vo.getNumber(), name + vo.getName());
			}
		}
		return result;
	}
	
	
	public String getZhtype() {
		return zhtype;
	}

	public void setZhtype(String zhtype) {
		this.zhtype = zhtype;
	}

	public String getTechtype() {
		return techtype;
	}

	public void setTechtype(String techtype) {
		this.techtype = techtype;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public String getTechMemo() {
		return techMemo;
	}

	public void setTechMemo(String techMemo) {
		this.techMemo = techMemo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(""+System.currentTimeMillis());
	}

}
