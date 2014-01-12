package com.app.web.engine.search;

import java.util.HashMap;

public class WarehouseLocationSearch {
	
	private String locationId;
	private String warehouseLocationCode = "";
	private String warehouseLocationName = "";
	private String tableAlias = "";
	
	public WarehouseLocationSearch() {
		
	}
	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getWarehouseLocationName() {
		return warehouseLocationName;
	}

	public void setWarehouseLocationName(String warehouseLocationName) {
		this.warehouseLocationName = warehouseLocationName;
	}

	public String getWarehouseLocationCode() {
		return warehouseLocationCode;
	}

	public void setWarehouseLocationCode(String warehouseLocationCode) {
		this.warehouseLocationCode = warehouseLocationCode;
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
        
        if (!this.warehouseLocationCode.equals("")) {
          
            String warehouseLocationCodeLike = "%" + warehouseLocationCode + "%";
            search += " AND " + tableAlias + ".LOCATION_CODE LIKE :warehouseLocationCode ";
            param.put("warehouseLocationCode", warehouseLocationCodeLike);
        }

        if (!this.warehouseLocationName.equals("")) {
         
            String warehouseLocationNameLike = "%" + warehouseLocationName + "%";
            search += " AND " + tableAlias + ".LOCATION_NAME LIKE :warehouseLocationName ";
            param.put("warehouseLocationName", warehouseLocationNameLike);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
	


}
