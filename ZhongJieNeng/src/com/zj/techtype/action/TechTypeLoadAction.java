package com.zj.techtype.action;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.TechTypeService;
import com.zj.vo.TechTypeVO;

/**
 * 工艺技术分类类别加载
 * */
public class TechTypeLoadAction extends ActionSupport{

	private static final long serialVersionUID = 4498256488006452990L;
	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
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
					number = vo.getNumber().substring(i, i + 4);
					i += 4;
					name = name + num_name.get(number) + "-->";
				}
				result.put(vo.getCh_type()+"_"+vo.getNumber(), name + vo.getName());
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(""+System.currentTimeMillis());
	}

}
