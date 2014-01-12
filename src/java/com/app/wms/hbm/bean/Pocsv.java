package com.app.wms.hbm.bean;
// Generated Dec 18, 2012 4:13:03 PM by Hibernate Tools 3.2.1.GA



/**
 * Pocsv generated by hbm2java
 */
public class Pocsv  implements java.io.Serializable {


     private int id;
     private String hash;
     private Integer qty;
     private String productCode;
     private String productName;

    public Pocsv() {
    }

	
    public Pocsv(int id, String hash) {
        this.id = id;
        this.hash = hash;
    }
    public Pocsv(int id, String hash, Integer qty, String productCode) {
       this.id = id;
       this.hash = hash;
       this.qty = qty;
       this.productCode = productCode;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getHash() {
        return this.hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    public Integer getQty() {
        return this.qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "Pocsv{" + "id=" + id + ", hash=" + hash + ", qty=" + qty + ", productCode=" + productCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + (this.hash != null ? this.hash.hashCode() : 0);
        hash = 23 * hash + (this.qty != null ? this.qty.hashCode() : 0);
        hash = 23 * hash + (this.productCode != null ? this.productCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pocsv other = (Pocsv) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.hash == null) ? (other.hash != null) : !this.hash.equals(other.hash)) {
            return false;
        }
        if (this.qty != other.qty && (this.qty == null || !this.qty.equals(other.qty))) {
            return false;
        }
        if ((this.productCode == null) ? (other.productCode != null) : !this.productCode.equals(other.productCode)) {
            return false;
        }
        return true;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }




}


