package com.app.web.engine.search;

import java.util.HashMap;

/**
 *
 * @author genevacons
 */
public class BundleDtlSearch {

    private String bundleCode = "";
    private String itemCode = "";
    private String tableAlias = "";
    private Boolean isLike = true;

    public String getTableAlias() {
	return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
	this.tableAlias = tableAlias;
    }

    public String getBundleCode() {
	return bundleCode;
    }

    public void setBundleCode(String bundleCode) {
	this.bundleCode = bundleCode;
    }

    public String getItemCode() {
	return itemCode;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    public HashMap getCriteria() {

	HashMap m = new HashMap();

	String search = " (1=1) ";
	HashMap param = new HashMap();

	if (!this.bundleCode.equals("")) {
	    // dibikin jgn exact mathing saja lah
	    String itemLike = "%" + bundleCode + "%";

	    if (isLike) {
		search += " AND " + tableAlias + ".BUNDLE_CODE LIKE :bundleCode ";
		param.put("bundleCode", itemLike);
	    } else {
		search += " AND " + tableAlias + ".BUNDLE_CODE = :bundleCode ";
		param.put("bundleCode", bundleCode);
	    }
	}

	if (!this.itemCode.equals("")) {
	    // bukan exact mathing
	    String itemCodeLike = "%" + itemCode + "%";

	    search += " AND " + tableAlias + ".ITEM_CODE LIKE :itemCode ";
	    param.put("itemCode", itemCodeLike);
	}
	m.put("search", search);
	m.put("parameter", param);
	return m;
    }

    public Boolean getIsLike() {
	return isLike;
    }

    public void setIsLike(Boolean isLike) {
	this.isLike = isLike;
    }
}

