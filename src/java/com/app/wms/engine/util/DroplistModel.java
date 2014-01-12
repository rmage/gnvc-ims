package com.app.wms.engine.util;

public class DroplistModel {
	
	private String code;
	private String name;
	
	public DroplistModel(){
		
	}
	
	public DroplistModel(String code, String name){
		this.code = code;
		this.name = name;
	}

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
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.code);
		sb.append(" - ").append(this.name);
		return sb.toString();
		
	}

}
