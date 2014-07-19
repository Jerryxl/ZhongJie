package com.zj.gongyi.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.AreaProblemService;
import com.zj.vo.AreaProblem;


public class AreaProblemAction extends ActionSupport {

	private static final long serialVersionUID = 7652813044585909074L;
	private String infotype;//暂且1，环境 2，省份问题 3 技术介绍
	private String areaid;//暂且黑龙江hei,ji吉林，辽宁liao 00东北
	private String envtype;//暂且water 水，air大气，soil土壤，noise噪声
	private String title;
	private String shortmessage;
	private String detailmessage;
	

	public String ExecuteAdd(){
		AreaProblem pro=new AreaProblem();
		pro.setInfotype(getInfotype());
		pro.setAreaid(getAreaid());
		pro.setEnvtype(getEnvtype());
		pro.setTitle(getTitle());
		pro.setShortmessage(getShortmessage());
		pro.setDetailmessage(getDetailmessage());
		AreaProblemService service=new AreaProblemService();
		service.addAreaProblem(pro);
		return SUCCESS;
	}
	public String getInfotype() {
		return infotype;
	}
	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getEnvtype() {
		return envtype;
	}
	public void setEnvtype(String envtype) {
		this.envtype = envtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortmessage() {
		return shortmessage;
	}
	public void setShortmessage(String shortmessage) {
		this.shortmessage = shortmessage;
	}
	public String getDetailmessage() {
		return detailmessage;
	}
	public void setDetailmessage(String detailmessage) {
		this.detailmessage = detailmessage;
	}
}
