package com.app.web.engine.search;

import java.util.HashMap;

public class PutawaySearch 
{

	
	private String whCode = "";
	private String name = "";
	private String tableAlias = "";
	
	public String getWhCode() {
		return whCode;
	}
	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	
	public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.whCode.equals("")) {
          
            String itemLike = "%" + whCode + "%";
            search += " AND " + tableAlias + ".WH_CODE LIKE :whCode ";
            param.put("whCode", itemLike);
        }

        if (!this.name.equals("")) {
         
            String nameLike = "%" + name + "%";
            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("name", nameLike);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
	


}
