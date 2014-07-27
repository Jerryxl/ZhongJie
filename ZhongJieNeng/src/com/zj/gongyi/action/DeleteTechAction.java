package com.zj.gongyi.action;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.GeneralTechService;

public class DeleteTechAction extends ActionSupport {

	private static final long serialVersionUID = 1281015017421669672L;
	private String tid;
	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	@Override
	public String execute() throws Exception {
		jsonobject =new JSONObject();
		GeneralTechService service=new GeneralTechService();
		boolean flag=service.deletebyId(tid);
		if(flag){
			jsonobject.put("result", "1");
			
		}else{
			jsonobject.put("result", "0");
		}
		return SUCCESS;
	}
	
}
