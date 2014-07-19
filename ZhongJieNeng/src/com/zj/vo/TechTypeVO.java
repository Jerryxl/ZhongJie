package com.zj.vo;

/**
 * 工艺的类别
 * */
public class TechTypeVO {

	private String id;
	private String number;
	private String name;
	private String memo;
	private String father;
	private String ch_type;
	public  TechTypeVO(){}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getCh_type() {
		return ch_type;
	}
	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}
	
}
