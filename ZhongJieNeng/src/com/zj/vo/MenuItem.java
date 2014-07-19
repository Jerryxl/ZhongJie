package com.zj.vo;

import java.util.ArrayList;

/**
 * 菜单项实体
 * */
public class MenuItem {
	private String code;
	private String name;
	private String father;
	private int level;
	private String type;//菜单类型 1是污染物类别，2是工艺类别 ，3是具体工艺
	private ArrayList<MenuItem> child;
	public MenuItem(){}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<MenuItem> getChild() {
		return child;
	}
	public void setChild(ArrayList<MenuItem> child) {
		this.child = child;
	}
}
