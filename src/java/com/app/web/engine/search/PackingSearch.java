package com.app.web.engine.search;

import java.sql.Timestamp;
import java.util.HashMap;

public class PackingSearch 
{

	
	private String packingNo = "";
	private Timestamp packingDate;
	private String tableAlias = "";
	
	
	public String getPackingNo() {
		return packingNo;
	}
	public void setPackingNo(String packingNo) {
		this.packingNo = packingNo;
	}
	public Timestamp getPackingDate() {
		return packingDate;
	}
	public void setPackingDate(Timestamp packingDate) {
		this.packingDate = packingDate;
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

        if (!this.packingNo.equals("")) {
          
            String packingNoLike = "%" + packingNo + "%";
            search += " AND " + tableAlias + ".packing_no LIKE :packingNo ";
            param.put("packingNo", packingNoLike);
        }
        
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
	


}
