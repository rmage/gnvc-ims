package com.app.wms.hbm.bean;
// Generated Dec 12, 2012 7:52:13 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;




/**
 * ReturnMaster generated by hbm2java
 */
public class ReturnMaster  implements java.io.Serializable {


     private int id;
     private String returnNo;
     private Date returnDate;
     private String whCode;
     private String companyCode;
     private String createdBy;

    public ReturnMaster() {
    }

	
   
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getReturnNo() {
        return this.returnNo;
    }
    
    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo;
    }
    public Date getReturnDate() {
        return this.returnDate;
    }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public String getWhCode() {
        return this.whCode;
    }
    
    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}


