package com.app.wms.engine.db.dvo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ruli
 */
public class GrDtlView implements Serializable {

    /**
     * This attribute maps to the column DOCUMENT_NO in the IR_DTL table.
     */
    protected String documentNo;
    /**
     * This attribute maps to the column ITEM_CODE in the IR_DTL table.
     */
    protected String itemCode;
    /**
     * This attribute maps to the column AMOUNT in the IR_DTL table.
     */
    protected BigDecimal quantity;
    /**
     * This attribute maps to the column CREATED_BY in the IR_DTL table.
     */
    protected BigDecimal createdBy;
    /**
     * This attribute maps to the column CREATED_DATE in the IR_DTL table.
     */
    protected Date createdDate;
    /**
     * This attribute maps to the column UPDATED_BY in the IR_DTL table.
     */
    protected BigDecimal updatedBy;
    /**
     * This attribute maps to the column UPDATED_DATE in the IR_DTL table.
     */
    protected Date updatedDate;
    
    protected String catalogCode;

    public GrDtlView() {
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(BigDecimal updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
