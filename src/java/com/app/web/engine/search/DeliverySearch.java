package com.app.web.engine.search;

import java.util.HashMap;

public class DeliverySearch 
{

	
	private String deliveryCode = "";
	private String name = "";
	private String tableAlias = "";
	
	
	public String getDeliveryCode() {
		return deliveryCode;
	}
	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
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

        if (!this.deliveryCode.equals("")) {
          
            String itemLike = "%" + deliveryCode + "%";
            search += " AND " + tableAlias + ".DELIVERY_CODE LIKE :deliveryCode ";
            param.put("deliveryCode", itemLike);
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
