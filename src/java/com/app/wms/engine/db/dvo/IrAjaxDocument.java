/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dvo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Rofy
 */
public class IrAjaxDocument implements Serializable{
        protected String documentNo;
        protected String itemCode;
        protected String snStart;
        protected String snEnd;
        protected BigDecimal quantity;
        protected String erpDocumentNo;
        
        public IrAjaxDocument(){
        }
        
        public String getDocumentNo(){
		return documentNo;
	}

	public void setDocumentNo(String documentNo){
		this.documentNo = documentNo;
	}
        
        public String getItemCode(){
		return itemCode;
	}

	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}
        
        public String getSNStart(){
		return snStart;
	}

	public void setSNStart(String snStart){
		this.snStart = snStart;
	}
        
        public String getSNEnd(){
		return snEnd;
	}

	public void setSNEnd(String snEnd){
		this.snEnd = snEnd;
	}
        
        public BigDecimal getQuantity(){
		return quantity;
	}

	public void setQuantity(BigDecimal quantity){
		this.quantity = quantity;
	}
        
        public String getErpDocumentNo(){
		return erpDocumentNo;
	}

	public void setErpDocumentNo(String erpDocumentNo){
		this.erpDocumentNo = erpDocumentNo;
	}
    }
