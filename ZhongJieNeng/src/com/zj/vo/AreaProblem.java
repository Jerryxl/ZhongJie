package com.zj.vo;

/***
 * 区域问题介绍
 * */
public class AreaProblem {
	
	private String id;
	private String number;
	private String infotype;//暂且1，环境 2，省份问题 3 技术介绍
	private String areaid;//暂且黑龙江hei,ji吉林，辽宁liao 00东北
	private String envtype;//暂且water 水，air大气，soil土壤，noise噪声
	private String title;
	private String shortmessage;
	private String detailmessage;
	public AreaProblem(){}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
