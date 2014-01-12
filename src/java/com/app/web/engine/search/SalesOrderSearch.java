package com.app.web.engine.search;

import java.sql.Timestamp;
import java.util.HashMap;

public class SalesOrderSearch {
	
	protected String so_number;
	protected Timestamp so_date;
	protected Timestamp do_date;
	protected String tableAlias;
	
	public SalesOrderSearch(){
		
	}

	public Timestamp getDo_date() {
		return do_date;
	}

	public void setDo_date(Timestamp do_date) {
		this.do_date = do_date;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	public Timestamp getSo_date() {
		return so_date;
	}

	public void setSo_date(Timestamp so_date) {
		this.so_date = so_date;
	}
	
	public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.so_number.equals("")) {
          
            String soNumber = "%" + so_number + "%";
            search += " and " + tableAlias + ".so_number like :so_number ";
            param.put("soNumber", soNumber);
        }

        if (!this.so_date.equals("")) {
         
            String soDate = "%" + so_date + "%";
            search += " and " + tableAlias + ".so_date like :so_date ";
            param.put("soDate", soDate);
        }
        
        if (!this.do_date.equals("")) {
            
            String doDate = "%" + so_date + "%";
            search += " and " + tableAlias + ".do_date like :do_date ";
            param.put("doDate", doDate);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }

}
