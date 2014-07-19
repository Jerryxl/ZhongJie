package com.zj.vo;

/**
 * 精简版的工艺表示
 * 只包含编号
 * 名称
 * 工艺类别
 * */
public class ShortGeneralVO {

	private String number;
	private String techname;
	private String techtype;
	public ShortGeneralVO(){}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTechname() {
		return techname;
	}
	public void setTechname(String techname) {
		this.techname = techname;
	}
	public String getTechtype() {
		return techtype;
	}
	public void setTechtype(String techtype) {
		this.techtype = techtype;
	}
	
}
