package com.ki5s.entity.adminMenu;

   /**
    * AdminMenu 实体类
    * Wed Aug 03 17:57:08 CST 2016 ki5s
    */ 


public class AdminMenu{
	private String id;
	private String name;
	private String parentId;
	private String menuUrl;
	private int sort;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setParentId(String parentId){
	this.parentId=parentId;
	}
	public String getParentId(){
		return parentId;
	}
	public void setMenuUrl(String menuUrl){
	this.menuUrl=menuUrl;
	}
	public String getMenuUrl(){
		return menuUrl;
	}
	public void setSort(int sort){
	this.sort=sort;
	}
	public int getSort(){
		return sort;
	}
}

