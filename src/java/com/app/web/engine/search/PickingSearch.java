package com.app.web.engine.search;

import java.sql.Timestamp;
import java.util.HashMap;

public class PickingSearch 
{

	
	private String pickingNo;
	private Timestamp pickingDate;
	private String tableAlias;
	
	public String getPickingNo() {
		return pickingNo;
	}
	public void setPickingNo(String pickingNo) {
		this.pickingNo = pickingNo;
	}
	public Timestamp getPickingDate() {
		return pickingDate;
	}
	public void setPickingDate(Timestamp pickingDate) {
		this.pickingDate = pickingDate;
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

        if (!this.pickingNo.equals("")) {
          
            String itemLike = "%" + pickingNo + "%";
            search += " AND " + tableAlias + ".ID LIKE :id ";
            param.put("id", itemLike);
        }

        if (!this.pickingDate.equals("")) {
         
            String pickingDateLike = "%" + pickingDate + "%";
            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("pickingDate",pickingDateLike);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
	


}
