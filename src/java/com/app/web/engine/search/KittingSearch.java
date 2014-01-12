package com.app.web.engine.search;

import java.util.HashMap;

public class KittingSearch 
{
	
    private String kittingNo = "";
    private String kittingDate = "";
    private String tableAlias = "";

    public String getKittingNo() {
		return kittingNo;
	}

	public void setKittingNo(String kittingNo) {
		this.kittingNo = kittingNo;
	}

	public String getKittingDate() {
		return kittingDate;
	}

	public void setKittingDate(String kittingDate) {
		this.kittingDate = kittingDate;
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

        if (!this.kittingNo.equals("")) {
            String itemLike = "%" + kittingNo + "%";

            search += " AND " + tableAlias + ".KITTING_NO LIKE :kittingNo ";
            param.put("kittingNo", itemLike);
        }
        /*
        if (!this.kittingDate.equals("")) {
            String nameLike = "%" + kittingDate + "%";

            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("name", kittingDate);
        }
        */
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }

}
