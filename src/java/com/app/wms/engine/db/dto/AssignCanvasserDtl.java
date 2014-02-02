package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class AssignCanvasserDtl implements Serializable {
    
    private int id;
    
    private String prsNumber;
    
    private String productCode;
    
    private String userId;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.AssignCanvasserDtl: " );
        sb.append("id = ").append( getId());
        sb.append(", prsNumber = ").append( getPrsNumber());
        sb.append(", productCode = ").append( getProductCode());
        sb.append(", userId = ").append( getUserId());
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrsNumber() {
        return prsNumber;
    }

    public void setPrsNumber(String prsNumber) {
        this.prsNumber = prsNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
