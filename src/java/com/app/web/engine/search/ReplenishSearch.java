package com.app.web.engine.search;

import java.util.Date;
import java.util.HashMap;

public class ReplenishSearch 
{

	
	private String replenishNo;
	private Date replenishDate;
	private String tableAlias;
	
	
	public String getReplenishNo() {
		return replenishNo;
	}
	public void setReplenishNo(String replenishNo) {
		this.replenishNo = replenishNo;
	}
	public Date getReplenishDate() {
		return replenishDate;
	}
	public void setReplenishDate(Date name) {
		this.replenishDate = name;
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

            if (!this.replenishNo.equals("")) {

                String replenishNo = "%" + getReplenishNo() + "%";
                search += " AND " + tableAlias + ".REPLENISH_NO LIKE :replenishNo ";
                param.put("replenishNo", replenishNo);
            }

            if (this.replenishDate != null) {
                search += " AND " + tableAlias + ".REPLENISH_DATE LIKE :replenishDate ";
                param.put("replenishDate", getReplenishDate());
            }
            m.put("search", search);
            m.put("parameter", param);
            
            return m;
        }
	


}
