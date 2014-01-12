package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Pts implements Serializable
{
	/** 
	 * This attribute maps to the column id in the pts table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column pts_code in the pts table.
	 */
	protected String ptsCode;

	/** 
	 * This attribute maps to the column pts_date in the pts table.
	 */
	protected String ptsDate;

	/** 
	 * This attribute maps to the column pack_style_size in the pts table.
	 */
	protected String packStyleSize;

	/** 
	 * This attribute maps to the column can_code in the pts table.
	 */
	protected String canCode;

	/** 
	 * This attribute maps to the column for_brand in the pts table.
	 */
	protected String forBrand;

	/** 
	 * This attribute maps to the column reff in the pts table.
	 */
	protected String reff;

	/** 
	 * This attribute maps to the column ns_ds in the pts table.
	 */
	protected String nsDs;

	/** 
	 * This attribute maps to the column product_batch in the pts table.
	 */
	protected String productBatch;

	/** 
	 * This attribute maps to the column basket in the pts table.
	 */
	protected String basket;

	/** 
	 * This attribute maps to the column quantity in the pts table.
	 */
	protected int quantity;

	/** 
	 * This attribute represents whether the primitive attribute quantity is null.
	 */
	protected boolean quantityNull = true;

	/** 
	 * This attribute maps to the column flk_percent in the pts table.
	 */
	protected int flkPercent;

	/** 
	 * This attribute represents whether the primitive attribute flkPercent is null.
	 */
	protected boolean flkPercentNull = true;

	/** 
	 * This attribute maps to the column nw in the pts table.
	 */
	protected String nw;

	/** 
	 * This attribute maps to the column dw in the pts table.
	 */
	protected String dw;

	/** 
	 * This attribute maps to the column pw in the pts table.
	 */
	protected String pw;

	/** 
	 * This attribute maps to the column release_to in the pts table.
	 */
	protected String releaseTo;

	/** 
	 * This attribute maps to the column remarks in the pts table.
	 */
	protected String remarks;

	/** 
	 * This attribute maps to the column issued_by in the pts table.
	 */
	protected String issuedBy;

	/** 
	 * This attribute maps to the column checked_by in the pts table.
	 */
	protected String checkedBy;

	/** 
	 * This attribute maps to the column received_by in the pts table.
	 */
	protected String receivedBy;

	/** 
	 * This attribute maps to the column is_active in the pts table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the pts table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the pts table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the pts table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the pts table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the pts table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Pts'
	 * 
	 */
	public Pts()
	{
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
	 * Method 'getPtsCode'
	 * 
	 * @return String
	 */
	public String getPtsCode()
	{
		return ptsCode;
	}

	/**
	 * Method 'setPtsCode'
	 * 
	 * @param ptsCode
	 */
	public void setPtsCode(String ptsCode)
	{
		this.ptsCode = ptsCode;
	}

	/**
	 * Method 'getPtsDate'
	 * 
	 * @return String
	 */
	public String getPtsDate()
	{
		return ptsDate;
	}

	/**
	 * Method 'setPtsDate'
	 * 
	 * @param ptsDate
	 */
	public void setPtsDate(String ptsDate)
	{
		this.ptsDate = ptsDate;
	}

	/**
	 * Method 'getPackStyleSize'
	 * 
	 * @return String
	 */
	public String getPackStyleSize()
	{
		return packStyleSize;
	}

	/**
	 * Method 'setPackStyleSize'
	 * 
	 * @param packStyleSize
	 */
	public void setPackStyleSize(String packStyleSize)
	{
		this.packStyleSize = packStyleSize;
	}

	/**
	 * Method 'getCanCode'
	 * 
	 * @return String
	 */
	public String getCanCode()
	{
		return canCode;
	}

	/**
	 * Method 'setCanCode'
	 * 
	 * @param canCode
	 */
	public void setCanCode(String canCode)
	{
		this.canCode = canCode;
	}

	/**
	 * Method 'getForBrand'
	 * 
	 * @return String
	 */
	public String getForBrand()
	{
		return forBrand;
	}

	/**
	 * Method 'setForBrand'
	 * 
	 * @param forBrand
	 */
	public void setForBrand(String forBrand)
	{
		this.forBrand = forBrand;
	}

	/**
	 * Method 'getReff'
	 * 
	 * @return String
	 */
	public String getReff()
	{
		return reff;
	}

	/**
	 * Method 'setReff'
	 * 
	 * @param reff
	 */
	public void setReff(String reff)
	{
		this.reff = reff;
	}

	/**
	 * Method 'getNsDs'
	 * 
	 * @return String
	 */
	public String getNsDs()
	{
		return nsDs;
	}

	/**
	 * Method 'setNsDs'
	 * 
	 * @param nsDs
	 */
	public void setNsDs(String nsDs)
	{
		this.nsDs = nsDs;
	}

	/**
	 * Method 'getProductBatch'
	 * 
	 * @return String
	 */
	public String getProductBatch()
	{
		return productBatch;
	}

	/**
	 * Method 'setProductBatch'
	 * 
	 * @param productBatch
	 */
	public void setProductBatch(String productBatch)
	{
		this.productBatch = productBatch;
	}

	/**
	 * Method 'getBasket'
	 * 
	 * @return String
	 */
	public String getBasket()
	{
		return basket;
	}

	/**
	 * Method 'setBasket'
	 * 
	 * @param basket
	 */
	public void setBasket(String basket)
	{
		this.basket = basket;
	}

	/**
	 * Method 'getQuantity'
	 * 
	 * @return int
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Method 'setQuantity'
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
		this.quantityNull = false;
	}

	/**
	 * Method 'setQuantityNull'
	 * 
	 * @param value
	 */
	public void setQuantityNull(boolean value)
	{
		this.quantityNull = value;
	}

	/**
	 * Method 'isQuantityNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuantityNull()
	{
		return quantityNull;
	}

	/**
	 * Method 'getFlkPercent'
	 * 
	 * @return int
	 */
	public int getFlkPercent()
	{
		return flkPercent;
	}

	/**
	 * Method 'setFlkPercent'
	 * 
	 * @param flkPercent
	 */
	public void setFlkPercent(int flkPercent)
	{
		this.flkPercent = flkPercent;
		this.flkPercentNull = false;
	}

	/**
	 * Method 'setFlkPercentNull'
	 * 
	 * @param value
	 */
	public void setFlkPercentNull(boolean value)
	{
		this.flkPercentNull = value;
	}

	/**
	 * Method 'isFlkPercentNull'
	 * 
	 * @return boolean
	 */
	public boolean isFlkPercentNull()
	{
		return flkPercentNull;
	}

	/**
	 * Method 'getNw'
	 * 
	 * @return String
	 */
	public String getNw()
	{
		return nw;
	}

	/**
	 * Method 'setNw'
	 * 
	 * @param nw
	 */
	public void setNw(String nw)
	{
		this.nw = nw;
	}

	/**
	 * Method 'getDw'
	 * 
	 * @return String
	 */
	public String getDw()
	{
		return dw;
	}

	/**
	 * Method 'setDw'
	 * 
	 * @param dw
	 */
	public void setDw(String dw)
	{
		this.dw = dw;
	}

	/**
	 * Method 'getPw'
	 * 
	 * @return String
	 */
	public String getPw()
	{
		return pw;
	}

	/**
	 * Method 'setPw'
	 * 
	 * @param pw
	 */
	public void setPw(String pw)
	{
		this.pw = pw;
	}

	/**
	 * Method 'getReleaseTo'
	 * 
	 * @return String
	 */
	public String getReleaseTo()
	{
		return releaseTo;
	}

	/**
	 * Method 'setReleaseTo'
	 * 
	 * @param releaseTo
	 */
	public void setReleaseTo(String releaseTo)
	{
		this.releaseTo = releaseTo;
	}

	/**
	 * Method 'getRemarks'
	 * 
	 * @return String
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Method 'setRemarks'
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	/**
	 * Method 'getIssuedBy'
	 * 
	 * @return String
	 */
	public String getIssuedBy()
	{
		return issuedBy;
	}

	/**
	 * Method 'setIssuedBy'
	 * 
	 * @param issuedBy
	 */
	public void setIssuedBy(String issuedBy)
	{
		this.issuedBy = issuedBy;
	}

	/**
	 * Method 'getCheckedBy'
	 * 
	 * @return String
	 */
	public String getCheckedBy()
	{
		return checkedBy;
	}

	/**
	 * Method 'setCheckedBy'
	 * 
	 * @param checkedBy
	 */
	public void setCheckedBy(String checkedBy)
	{
		this.checkedBy = checkedBy;
	}

	/**
	 * Method 'getReceivedBy'
	 * 
	 * @return String
	 */
	public String getReceivedBy()
	{
		return receivedBy;
	}

	/**
	 * Method 'setReceivedBy'
	 * 
	 * @param receivedBy
	 */
	public void setReceivedBy(String receivedBy)
	{
		this.receivedBy = receivedBy;
	}

	/**
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
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
		
		if (!(_other instanceof Pts)) {
			return false;
		}
		
		final Pts _cast = (Pts) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (ptsCode == null ? _cast.ptsCode != ptsCode : !ptsCode.equals( _cast.ptsCode )) {
			return false;
		}
		
		if (ptsDate == null ? _cast.ptsDate != ptsDate : !ptsDate.equals( _cast.ptsDate )) {
			return false;
		}
		
		if (packStyleSize == null ? _cast.packStyleSize != packStyleSize : !packStyleSize.equals( _cast.packStyleSize )) {
			return false;
		}
		
		if (canCode == null ? _cast.canCode != canCode : !canCode.equals( _cast.canCode )) {
			return false;
		}
		
		if (forBrand == null ? _cast.forBrand != forBrand : !forBrand.equals( _cast.forBrand )) {
			return false;
		}
		
		if (reff == null ? _cast.reff != reff : !reff.equals( _cast.reff )) {
			return false;
		}
		
		if (nsDs == null ? _cast.nsDs != nsDs : !nsDs.equals( _cast.nsDs )) {
			return false;
		}
		
		if (productBatch == null ? _cast.productBatch != productBatch : !productBatch.equals( _cast.productBatch )) {
			return false;
		}
		
		if (basket == null ? _cast.basket != basket : !basket.equals( _cast.basket )) {
			return false;
		}
		
		if (quantity != _cast.quantity) {
			return false;
		}
		
		if (quantityNull != _cast.quantityNull) {
			return false;
		}
		
		if (flkPercent != _cast.flkPercent) {
			return false;
		}
		
		if (flkPercentNull != _cast.flkPercentNull) {
			return false;
		}
		
		if (nw == null ? _cast.nw != nw : !nw.equals( _cast.nw )) {
			return false;
		}
		
		if (dw == null ? _cast.dw != dw : !dw.equals( _cast.dw )) {
			return false;
		}
		
		if (pw == null ? _cast.pw != pw : !pw.equals( _cast.pw )) {
			return false;
		}
		
		if (releaseTo == null ? _cast.releaseTo != releaseTo : !releaseTo.equals( _cast.releaseTo )) {
			return false;
		}
		
		if (remarks == null ? _cast.remarks != remarks : !remarks.equals( _cast.remarks )) {
			return false;
		}
		
		if (issuedBy == null ? _cast.issuedBy != issuedBy : !issuedBy.equals( _cast.issuedBy )) {
			return false;
		}
		
		if (checkedBy == null ? _cast.checkedBy != checkedBy : !checkedBy.equals( _cast.checkedBy )) {
			return false;
		}
		
		if (receivedBy == null ? _cast.receivedBy != receivedBy : !receivedBy.equals( _cast.receivedBy )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
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
		if (ptsCode != null) {
			_hashCode = 29 * _hashCode + ptsCode.hashCode();
		}
		
		if (ptsDate != null) {
			_hashCode = 29 * _hashCode + ptsDate.hashCode();
		}
		
		if (packStyleSize != null) {
			_hashCode = 29 * _hashCode + packStyleSize.hashCode();
		}
		
		if (canCode != null) {
			_hashCode = 29 * _hashCode + canCode.hashCode();
		}
		
		if (forBrand != null) {
			_hashCode = 29 * _hashCode + forBrand.hashCode();
		}
		
		if (reff != null) {
			_hashCode = 29 * _hashCode + reff.hashCode();
		}
		
		if (nsDs != null) {
			_hashCode = 29 * _hashCode + nsDs.hashCode();
		}
		
		if (productBatch != null) {
			_hashCode = 29 * _hashCode + productBatch.hashCode();
		}
		
		if (basket != null) {
			_hashCode = 29 * _hashCode + basket.hashCode();
		}
		
		_hashCode = 29 * _hashCode + quantity;
		_hashCode = 29 * _hashCode + (quantityNull ? 1 : 0);
		_hashCode = 29 * _hashCode + flkPercent;
		_hashCode = 29 * _hashCode + (flkPercentNull ? 1 : 0);
		if (nw != null) {
			_hashCode = 29 * _hashCode + nw.hashCode();
		}
		
		if (dw != null) {
			_hashCode = 29 * _hashCode + dw.hashCode();
		}
		
		if (pw != null) {
			_hashCode = 29 * _hashCode + pw.hashCode();
		}
		
		if (releaseTo != null) {
			_hashCode = 29 * _hashCode + releaseTo.hashCode();
		}
		
		if (remarks != null) {
			_hashCode = 29 * _hashCode + remarks.hashCode();
		}
		
		if (issuedBy != null) {
			_hashCode = 29 * _hashCode + issuedBy.hashCode();
		}
		
		if (checkedBy != null) {
			_hashCode = 29 * _hashCode + checkedBy.hashCode();
		}
		
		if (receivedBy != null) {
			_hashCode = 29 * _hashCode + receivedBy.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
		}
		
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
	 * @return PtsPk
	 */
	public PtsPk createPk()
	{
		return new PtsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Pts: " );
		ret.append( "id=" + id );
		ret.append( ", ptsCode=" + ptsCode );
		ret.append( ", ptsDate=" + ptsDate );
		ret.append( ", packStyleSize=" + packStyleSize );
		ret.append( ", canCode=" + canCode );
		ret.append( ", forBrand=" + forBrand );
		ret.append( ", reff=" + reff );
		ret.append( ", nsDs=" + nsDs );
		ret.append( ", productBatch=" + productBatch );
		ret.append( ", basket=" + basket );
		ret.append( ", quantity=" + quantity );
		ret.append( ", flkPercent=" + flkPercent );
		ret.append( ", nw=" + nw );
		ret.append( ", dw=" + dw );
		ret.append( ", pw=" + pw );
		ret.append( ", releaseTo=" + releaseTo );
		ret.append( ", remarks=" + remarks );
		ret.append( ", issuedBy=" + issuedBy );
		ret.append( ", checkedBy=" + checkedBy );
		ret.append( ", receivedBy=" + receivedBy );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
