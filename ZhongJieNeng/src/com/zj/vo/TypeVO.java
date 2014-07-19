package com.zj.vo;

public class TypeVO {

	private String id;//主键
	private String number;//编号
	private String name;//名称
	private String father;//父节点
	private String memo;//备注
	public TypeVO(){}
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
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
		
}
