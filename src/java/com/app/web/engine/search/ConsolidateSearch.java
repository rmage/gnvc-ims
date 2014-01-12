package com.app.web.engine.search;

import java.util.HashMap;

public class ConsolidateSearch 
{
	private String consolidateCode = "";
	private String name = "";
	private String tableAlias = "";
	
	
	public String getConsolidateCode() {
		return consolidateCode;
	}
	public void setConsolidateCode(String consolidateCode) {
		this.consolidateCode = consolidateCode;
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

        if (!this.consolidateCode.equals("")) {
          
            String itemLike = "%" + consolidateCode + "%";
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
