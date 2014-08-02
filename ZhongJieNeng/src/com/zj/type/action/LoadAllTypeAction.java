package com.zj.type.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.TypeService;
import com.zj.vo.TypeVO;

public class LoadAllTypeAction extends ActionSupport {
	private static final long serialVersionUID = 4928445350202869420L;
	private JSONObject jsonobject;
	//类别
	private String zhtype;
	private String typeName;
	private String typeMemo;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}

	/**
	 * 新增一个类别
	 * */
	public String addType(){
		if(StringUtils.isBlank(zhtype)) return ERROR;
		if(StringUtils.isBlank(typeName)) return ERROR;
	//	if(StringUtils.isBlank(typeMemo)) return ERROR;
		TypeVO vo=new TypeVO();
		vo.setFather(zhtype);
		vo.setName(typeName);
		vo.setMemo(typeMemo);
		
		TypeService service = new TypeService();
		boolean flag=service.addType(vo);
		if(flag)return SUCCESS;
		return ERROR;
	}
	public String loadExitTypes() throws Exception {
		JSONArray array = new JSONArray();
		jsonobject = new JSONObject();
		TypeService service = new TypeService();
		List<TypeVO> list = new ArrayList<TypeVO>();// service.getwareHouseList();
		list = service.getTypes();

		JSONObject empty = new JSONObject();
		empty.put("value", "00");
		empty.put("name", "无");
		array.add(empty);
		// 为空的情况
		if (list.size() < 1) {
			jsonobject.put("data", array);
			return SUCCESS;
		}
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
	public HashMap<String, String> construct(List<TypeVO> ls) {
		HashMap<String, String> result = new HashMap<String, String>();
		HashMap<String, String> num_name = new HashMap<String, String>();
		for (TypeVO vv : ls) {
			num_name.put(vv.getNumber(), vv.getName());
		}

		for (TypeVO vo : ls) {
			if (vo.getNumber().length() <= 4) {
				result.put(vo.getNumber(), vo.getName());
			} else {
				int i = 0;
				String name = "";
				String number = "";
				for (; i < (vo.getNumber().length() - 4);) {
					number = vo.getNumber().substring(0, i + 4);
					i += 4;
					name = name + num_name.get(number) + "-->";
				}
				result.put(vo.getNumber(), name + vo.getName());
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeMemo() {
		return typeMemo;
	}

	public void setTypeMemo(String typeMemo) {
		this.typeMemo = typeMemo;
	}
	
	

}
