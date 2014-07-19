package com.client.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.AreaProblemService;
import com.zj.vo.AreaProblem;

public class LoadProblemByCity extends ActionSupport{
	
	private static final long serialVersionUID = 2629990150797439120L;
	private JSONObject jsonobject;
	private String citynumber="harbin";

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}
	@Override
	public String execute() throws Exception {
		jsonobject = new JSONObject();
		AreaProblemService service=new AreaProblemService();
		List<AreaProblem> list=service.getVOsbyCity(citynumber);
		JSONArray array=new JSONArray();
		for(AreaProblem vo:list){
			JSONObject obj=new JSONObject();
			obj.put("number", vo.getNumber());
			obj.put("infotype", vo.getInfotype());
			obj.put("areaid",vo.getAreaid());
			obj.put("envtype", vo.getEnvtype());
			obj.put("title", vo.getTitle());
			obj.put("detailmessage",vo.getDetailmessage());
			array.add(obj);
		}
		jsonobject.put("data", array);
		System.out.println(jsonobject.toString());
		return SUCCESS;
	}

	public String getCitynumber() {
		return citynumber;
	}

	public void setCitynumber(String citynumber) {
		this.citynumber = citynumber;
	}

	
}
