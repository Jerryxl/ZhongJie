package com.client.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.AreaProblemService;
import com.zj.vo.AreaProblem;

public class LoadProblemAction extends ActionSupport {

	private static final long serialVersionUID = -2464464860362812608L;

	private JSONObject jsonobject;

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
		List<AreaProblem> list=service.getAllVOs();
		if(list==null){
			return SUCCESS;
		}
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
	
}
