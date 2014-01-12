package com.app.web.engine.search;

import java.util.HashMap;


public class CorporateSearch {
	
	private String id = "";
	private String name = "";
	private String address1 = "";
	private String address2 = "";
	private String address3 = "";
	private String zipcode = "";
	private String phone1 = "";
	private String phone2 = "";
	private String fax = "";
	private String province = "";
	private String isActive = "";
	private String isDeleted = "";
	private String tableAlias = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
	public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.id.equals("")) {
          
            String itemLike = "%" + id + "%";
            search += " AND " + tableAlias + ".CORP_ID LIKE :id ";
            param.put("id", itemLike);
        }

        if (!this.name.equals("")) {
         
            String nameLike = "%" + name + "%";
            search += " AND " + tableAlias + ".CORP_NAME LIKE :name ";
            param.put("name", nameLike);
        }
        
        if (!this.address1.equals("")) {
            
            String address1Like = "%" + address1 + "%";
            search += " AND " + tableAlias + ".ADDRESS1 LIKE :address1 ";
            param.put("address1", address1Like);
        }
        
        if (!this.address2.equals("")) {
            
            String address2Like = "%" + address2 + "%";
            search += " AND " + tableAlias + ".ADDRESS2 LIKE :address2 ";
            param.put("address2", address2Like);
        }
        
        if (!this.address3.equals("")) {
            
            String address3Like = "%" + address3 + "%";
            search += " AND " + tableAlias + ".ADDRESS3 LIKE :address3 ";
            param.put("address3", address3Like);
        }
        
        if (!this.zipcode.equals("")) {
            
            String zipcodeLike = "%" + zipcode + "%";
            search += " AND " + tableAlias + ".ZIPCODE LIKE :zipcode ";
            param.put("zipcode", zipcodeLike);
        }
        
        if (!this.phone1.equals("")) {
            
            String phone1Like = "%" + phone1 + "%";
            search += " AND " + tableAlias + ".PHONE1 LIKE :phone1 ";
            param.put("phone1", phone1Like);
        }

		if (!this.phone2.equals("")) {
		    
		    String phone2Like = "%" + phone2 + "%";
		    search += " AND " + tableAlias + ".PHONE2 LIKE :phone2 ";
		    param.put("phone2", phone2Like);
		}
		
		if (!this.fax.equals("")) {
		    
		    String faxLike = "%" + fax + "%";
		    search += " AND " + tableAlias + ".FAX LIKE :fax ";
		    param.put("fax", faxLike);
		}
		
		if (!this.province.equals("")) {
		    
		    String provinceLike = "%" + province + "%";
		    search += " AND " + tableAlias + ".PROVINCE LIKE :province ";
		    param.put("province", provinceLike);
		}
		
		if (!this.isActive.equals("")) {
		    
		    String isActiveLike = "%" + isActive + "%";
		    search += " AND " + tableAlias + ".ISACTIVE LIKE :is_active ";
		    param.put("isActive", isActiveLike);
		}
		
		if (!this.isDeleted.equals("")) {
		    
		    String isDeletedLike = "%" + isDeleted + "%";
		    search += " AND " + tableAlias + ".ISDELETED LIKE :isDeleted ";
		    param.put("isDeleted", isDeletedLike);
		}
        
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }

}
