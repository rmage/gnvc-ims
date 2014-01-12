package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Accounting implements Serializable
{
	/** 
	 * This attribute maps to the column id in the accounting table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column accounting_no in the accounting table.
	 */
	protected String accountingNo;

	/** 
	 * This attribute maps to the column accounting_date in the accounting table.
	 */
	protected Date accountingDate;

	/** 
	 * This attribute maps to the column document_no in the accounting table.
	 */
	protected String documentNo;

	/** 
	 * This attribute maps to the column document_date in the accounting table.
	 */
	protected Date documentDate;

	/** 
	 * This attribute maps to the column product_code in the accounting table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the accounting table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column qty_in in the accounting table.
	 */
	protected BigDecimal qtyIn;

	/** 
	 * This attribute represents whether the primitive attribute qtyIn is null.
	 */
	protected boolean qtyInNull = true;

	/** 
	 * This attribute maps to the column qty_out in the accounting table.
	 */
	protected BigDecimal qtyOut;

	/** 
	 * This attribute represents whether the primitive attribute qtyOut is null.
	 */
	protected boolean qtyOutNull = true;

	/** 
	 * This attribute maps to the column balance in the accounting table.
	 */
	protected BigDecimal balance;

	/** 
	 * This attribute represents whether the primitive attribute balance is null.
	 */
	protected boolean balanceNull = true;

	/** 
	 * This attribute maps to the column wh_code in the accounting table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column unitprice in the accounting table.
	 */
	protected BigDecimal unitprice;

	/** 
	 * This attribute represents whether the primitive attribute unitprice is null.
	 */
	protected boolean unitpriceNull = true;

	/** 
	 * This attribute maps to the column amount in the accounting table.
	 */
	protected BigDecimal amount;

	/** 
	 * This attribute represents whether the primitive attribute amount is null.
	 */
	protected boolean amountNull = true;

	/** 
	 * This attribute maps to the column created_by in the accounting table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the accounting table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the accounting table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the accounting table.
	 */
	protected Date updatedDate;
	
	protected StockBalance stockBalance;
	
	protected StockInventory stockinventory;
	
	protected Product product;

	/**
	 * Method 'Accounting'
	 * 
	 */
	public Accounting()
	{
	}

	public BigDecimal getQtyIn() {
		return qtyIn;
	}

	public void setQtyIn(BigDecimal qtyIn) {
		this.qtyIn = qtyIn;
	}

	public BigDecimal getQtyOut() {
		return qtyOut;
	}

	public void setQtyOut(BigDecimal qtyOut) {
		this.qtyOut = qtyOut;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public StockBalance getStockBalance() {
		return stockBalance;
	}

	public void setStockBalance(StockBalance stockBalance) {
		this.stockBalance = stockBalance;
	}

	public StockInventory getStockinventory() {
		return stockinventory;
	}

	public void setStockinventory(StockInventory stockinventory) {
		this.stockinventory = stockinventory;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Method 'getAccountingNo'
	 * 
	 * @return String
	 */
	public String getAccountingNo()
	{
		return accountingNo;
	}

	/**
	 * Method 'setAccountingNo'
	 * 
	 * @param accountingNo
	 */
	public void setAccountingNo(String accountingNo)
	{
		this.accountingNo = accountingNo;
	}

	/**
	 * Method 'getAccountingDate'
	 * 
	 * @return Date
	 */
	public Date getAccountingDate()
	{
		return accountingDate;
	}

	/**
	 * Method 'setAccountingDate'
	 * 
	 * @param accountingDate
	 */
	public void setAccountingDate(Date accountingDate)
	{
		this.accountingDate = accountingDate;
	}

	/**
	 * Method 'getDocumentNo'
	 * 
	 * @return String
	 */
	public String getDocumentNo()
	{
		return documentNo;
	}

	/**
	 * Method 'setDocumentNo'
	 * 
	 * @param documentNo
	 */
	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;
	}

	/**
	 * Method 'getDocumentDate'
	 * 
	 * @return Date
	 */
	public Date getDocumentDate()
	{
		return documentDate;
	}

	/**
	 * Method 'setDocumentDate'
	 * 
	 * @param documentDate
	 */
	public void setDocumentDate(Date documentDate)
	{
		this.documentDate = documentDate;
	}

	/**
	 * Method 'getProductCode'
	 * 
	 * @return String
	 */
	public String getProductCode()
	{
		return productCode;
	}

	/**
	 * Method 'setProductCode'
	 * 
	 * @param productCode
	 */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	/**
	 * Method 'getProductName'
	 * 
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * Method 'setProductName'
	 * 
	 * @param productName
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}


	/**
	 * Method 'setQtyInNull'
	 * 
	 * @param value
	 */
	public void setQtyInNull(boolean value)
	{
		this.qtyInNull = value;
	}

	/**
	 * Method 'isQtyInNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyInNull()
	{
		return qtyInNull;
	}

	/**
	 * Method 'setQtyOutNull'
	 * 
	 * @param value
	 */
	public void setQtyOutNull(boolean value)
	{
		this.qtyOutNull = value;
	}

	/**
	 * Method 'isQtyOutNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyOutNull()
	{
		return qtyOutNull;
	}

	/**
	 * Method 'setBalanceNull'
	 * 
	 * @param value
	 */
	public void setBalanceNull(boolean value)
	{
		this.balanceNull = value;
	}

	/**
	 * Method 'isBalanceNull'
	 * 
	 * @return boolean
	 */
	public boolean isBalanceNull()
	{
		return balanceNull;
	}

	/**
	 * Method 'getWhCode'
	 * 
	 * @return String
	 */
	public String getWhCode()
	{
		return whCode;
	}

	/**
	 * Method 'setWhCode'
	 * 
	 * @param whCode
	 */
	public void setWhCode(String whCode)
	{
		this.whCode = whCode;
	}

	/**
	 * Method 'setUnitpriceNull'
	 * 
	 * @param value
	 */
	public void setUnitpriceNull(boolean value)
	{
		this.unitpriceNull = value;
	}

	/**
	 * Method 'isUnitpriceNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitpriceNull()
	{
		return unitpriceNull;
	}

	/**
	 * Method 'setAmountNull'
	 * 
	 * @param value
	 */
	public void setAmountNull(boolean value)
	{
		this.amountNull = value;
	}

	/**
	 * Method 'isAmountNull'
	 * 
	 * @return boolean
	 */
	public boolean isAmountNull()
	{
		return amountNull;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Accounting)) {
			return false;
		}
		
		final Accounting _cast = (Accounting) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (accountingNo == null ? _cast.accountingNo != accountingNo : !accountingNo.equals( _cast.accountingNo )) {
			return false;
		}
		
		if (accountingDate == null ? _cast.accountingDate != accountingDate : !accountingDate.equals( _cast.accountingDate )) {
			return false;
		}
		
		if (documentNo == null ? _cast.documentNo != documentNo : !documentNo.equals( _cast.documentNo )) {
			return false;
		}
		
		if (documentDate == null ? _cast.documentDate != documentDate : !documentDate.equals( _cast.documentDate )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (qtyIn != _cast.qtyIn) {
			return false;
		}
		
		if (qtyInNull != _cast.qtyInNull) {
			return false;
		}
		
		if (qtyOut != _cast.qtyOut) {
			return false;
		}
		
		if (qtyOutNull != _cast.qtyOutNull) {
			return false;
		}
		
		if (balance != _cast.balance) {
			return false;
		}
		
		if (balanceNull != _cast.balanceNull) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (unitprice != _cast.unitprice) {
			return false;
		}
		
		if (unitpriceNull != _cast.unitpriceNull) {
			return false;
		}
		
		if (amount != _cast.amount) {
			return false;
		}
		
		if (amountNull != _cast.amountNull) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + id;
		if (accountingNo != null) {
			_hashCode = 29 * _hashCode + accountingNo.hashCode();
		}
		
		if (accountingDate != null) {
			_hashCode = 29 * _hashCode + accountingDate.hashCode();
		}
		
		if (documentNo != null) {
			_hashCode = 29 * _hashCode + documentNo.hashCode();
		}
		
		if (documentDate != null) {
			_hashCode = 29 * _hashCode + documentDate.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (qtyInNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (qtyOutNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (balanceNull ? 1 : 0);
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (unitpriceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (amountNull ? 1 : 0);
		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}
		
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}
		
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AccountingPk
	 */
	public AccountingPk createPk()
	{
		return new AccountingPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Accounting: " );
		ret.append( "id=" + id );
		ret.append( ", accountingNo=" + accountingNo );
		ret.append( ", accountingDate=" + accountingDate );
		ret.append( ", documentNo=" + documentNo );
		ret.append( ", documentDate=" + documentDate );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", qtyIn=" + qtyIn );
		ret.append( ", qtyOut=" + qtyOut );
		ret.append( ", balance=" + balance );
		ret.append( ", whCode=" + whCode );
		ret.append( ", unitprice=" + unitprice );
		ret.append( ", amount=" + amount );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
