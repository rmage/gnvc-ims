package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class BeritaAcara implements Serializable
{
	/** 
	 * This attribute maps to the column id in the berita_acara table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column berita_no in the berita_acara table.
	 */
	protected String beritaNo;

	/** 
	 * This attribute maps to the column berita_date in the berita_acara table.
	 */
	protected Date beritaDate;

	/** 
	 * This attribute maps to the column ponumber in the berita_acara table.
	 */
	protected String ponumber;

	/** 
	 * This attribute maps to the column podate in the berita_acara table.
	 */
	protected Date podate;

	/** 
	 * This attribute maps to the column productcode in the berita_acara table.
	 */
	protected String productcode;

	/** 
	 * This attribute maps to the column qty in the berita_acara table.
	 */
	protected BigDecimal qty;

	/** 
	 * This attribute represents whether the primitive attribute qty is null.
	 */
	protected boolean qtyNull = true;

	/** 
	 * This attribute maps to the column uom_name in the berita_acara table.
	 */
	protected String uomName;

	/** 
	 * This attribute maps to the column total in the berita_acara table.
	 */
	protected BigDecimal total;

	/** 
	 * This attribute represents whether the primitive attribute total is null.
	 */
	protected boolean totalNull = true;

	/** 
	 * This attribute maps to the column contractor_name in the berita_acara table.
	 */
	protected String contractorName;

	/** 
	 * This attribute maps to the column result_work in the berita_acara table.
	 */
	protected BigDecimal resultWork;

	/** 
	 * This attribute represents whether the primitive attribute resultWork is null.
	 */
	protected boolean resultWorkNull = true;

	/** 
	 * This attribute maps to the column pertial_completion in the berita_acara table.
	 */
	protected BigDecimal pertialCompletion;

	/** 
	 * This attribute represents whether the primitive attribute pertialCompletion is null.
	 */
	protected boolean pertialCompletionNull = true;

	/** 
	 * This attribute maps to the column retention in the berita_acara table.
	 */
	protected BigDecimal retention;

	/** 
	 * This attribute represents whether the primitive attribute retention is null.
	 */
	protected boolean retentionNull = true;

	/** 
	 * This attribute maps to the column description in the berita_acara table.
	 */
	protected String description;

	/** 
	 * This attribute maps to the column progress in the berita_acara table.
	 */
	protected BigDecimal progress;

	/** 
	 * This attribute represents whether the primitive attribute progress is null.
	 */
	protected boolean progressNull = true;

	/** 
	 * This attribute maps to the column total_progress in the berita_acara table.
	 */
	protected BigDecimal totalProgress;

	/** 
	 * This attribute represents whether the primitive attribute totalProgress is null.
	 */
	protected boolean totalProgressNull = true;

	/** 
	 * This attribute maps to the column end_user in the berita_acara table.
	 */
	protected String endUser;

	/** 
	 * This attribute maps to the column plant_engineer in the berita_acara table.
	 */
	protected String plantEngineer;

	/** 
	 * This attribute maps to the column operation_engineer in the berita_acara table.
	 */
	protected String operationEngineer;

	/** 
	 * This attribute maps to the column is_active in the berita_acara table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the berita_acara table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the berita_acara table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the berita_acara table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the berita_acara table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the berita_acara table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'BeritaAcara'
	 * 
	 */
	public BeritaAcara()
	{
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getResultWork() {
		return resultWork;
	}

	public void setResultWork(BigDecimal resultWork) {
		this.resultWork = resultWork;
	}

	public BigDecimal getPertialCompletion() {
		return pertialCompletion;
	}

	public void setPertialCompletion(BigDecimal pertialCompletion) {
		this.pertialCompletion = pertialCompletion;
	}

	public BigDecimal getRetention() {
		return retention;
	}

	public void setRetention(BigDecimal retention) {
		this.retention = retention;
	}

	public BigDecimal getProgress() {
		return progress;
	}

	public void setProgress(BigDecimal progress) {
		this.progress = progress;
	}

	public BigDecimal getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(BigDecimal totalProgress) {
		this.totalProgress = totalProgress;
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
	 * Method 'getBeritaNo'
	 * 
	 * @return String
	 */
	public String getBeritaNo()
	{
		return beritaNo;
	}

	/**
	 * Method 'setBeritaNo'
	 * 
	 * @param beritaNo
	 */
	public void setBeritaNo(String beritaNo)
	{
		this.beritaNo = beritaNo;
	}

	/**
	 * Method 'getBeritaDate'
	 * 
	 * @return Date
	 */
	public Date getBeritaDate()
	{
		return beritaDate;
	}

	/**
	 * Method 'setBeritaDate'
	 * 
	 * @param beritaDate
	 */
	public void setBeritaDate(Date beritaDate)
	{
		this.beritaDate = beritaDate;
	}

	/**
	 * Method 'getPonumber'
	 * 
	 * @return String
	 */
	public String getPonumber()
	{
		return ponumber;
	}

	/**
	 * Method 'setPonumber'
	 * 
	 * @param ponumber
	 */
	public void setPonumber(String ponumber)
	{
		this.ponumber = ponumber;
	}

	/**
	 * Method 'getPodate'
	 * 
	 * @return Date
	 */
	public Date getPodate()
	{
		return podate;
	}

	/**
	 * Method 'setPodate'
	 * 
	 * @param podate
	 */
	public void setPodate(Date podate)
	{
		this.podate = podate;
	}

	/**
	 * Method 'getProductcode'
	 * 
	 * @return String
	 */
	public String getProductcode()
	{
		return productcode;
	}

	/**
	 * Method 'setProductcode'
	 * 
	 * @param productcode
	 */
	public void setProductcode(String productcode)
	{
		this.productcode = productcode;
	}

	/**
	 * Method 'setQtyNull'
	 * 
	 * @param value
	 */
	public void setQtyNull(boolean value)
	{
		this.qtyNull = value;
	}

	/**
	 * Method 'isQtyNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyNull()
	{
		return qtyNull;
	}

	/**
	 * Method 'getUomName'
	 * 
	 * @return String
	 */
	public String getUomName()
	{
		return uomName;
	}

	/**
	 * Method 'setUomName'
	 * 
	 * @param uomName
	 */
	public void setUomName(String uomName)
	{
		this.uomName = uomName;
	}


	/**
	 * Method 'setTotalNull'
	 * 
	 * @param value
	 */
	public void setTotalNull(boolean value)
	{
		this.totalNull = value;
	}

	/**
	 * Method 'isTotalNull'
	 * 
	 * @return boolean
	 */
	public boolean isTotalNull()
	{
		return totalNull;
	}

	/**
	 * Method 'getContractorName'
	 * 
	 * @return String
	 */
	public String getContractorName()
	{
		return contractorName;
	}

	/**
	 * Method 'setContractorName'
	 * 
	 * @param contractorName
	 */
	public void setContractorName(String contractorName)
	{
		this.contractorName = contractorName;
	}

	/**
	 * Method 'setResultWorkNull'
	 * 
	 * @param value
	 */
	public void setResultWorkNull(boolean value)
	{
		this.resultWorkNull = value;
	}

	/**
	 * Method 'isResultWorkNull'
	 * 
	 * @return boolean
	 */
	public boolean isResultWorkNull()
	{
		return resultWorkNull;
	}

	/**
	 * Method 'setPertialCompletionNull'
	 * 
	 * @param value
	 */
	public void setPertialCompletionNull(boolean value)
	{
		this.pertialCompletionNull = value;
	}

	/**
	 * Method 'isPertialCompletionNull'
	 * 
	 * @return boolean
	 */
	public boolean isPertialCompletionNull()
	{
		return pertialCompletionNull;
	}

	/**
	 * Method 'setRetentionNull'
	 * 
	 * @param value
	 */
	public void setRetentionNull(boolean value)
	{
		this.retentionNull = value;
	}

	/**
	 * Method 'isRetentionNull'
	 * 
	 * @return boolean
	 */
	public boolean isRetentionNull()
	{
		return retentionNull;
	}

	/**
	 * Method 'getDescription'
	 * 
	 * @return String
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Method 'setDescription'
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Method 'setProgressNull'
	 * 
	 * @param value
	 */
	public void setProgressNull(boolean value)
	{
		this.progressNull = value;
	}

	/**
	 * Method 'isProgressNull'
	 * 
	 * @return boolean
	 */
	public boolean isProgressNull()
	{
		return progressNull;
	}

	/**
	 * Method 'setTotalProgressNull'
	 * 
	 * @param value
	 */
	public void setTotalProgressNull(boolean value)
	{
		this.totalProgressNull = value;
	}

	/**
	 * Method 'isTotalProgressNull'
	 * 
	 * @return boolean
	 */
	public boolean isTotalProgressNull()
	{
		return totalProgressNull;
	}

	/**
	 * Method 'getEndUser'
	 * 
	 * @return String
	 */
	public String getEndUser()
	{
		return endUser;
	}

	/**
	 * Method 'setEndUser'
	 * 
	 * @param endUser
	 */
	public void setEndUser(String endUser)
	{
		this.endUser = endUser;
	}

	/**
	 * Method 'getPlantEngineer'
	 * 
	 * @return String
	 */
	public String getPlantEngineer()
	{
		return plantEngineer;
	}

	/**
	 * Method 'setPlantEngineer'
	 * 
	 * @param plantEngineer
	 */
	public void setPlantEngineer(String plantEngineer)
	{
		this.plantEngineer = plantEngineer;
	}

	/**
	 * Method 'getOperationEngineer'
	 * 
	 * @return String
	 */
	public String getOperationEngineer()
	{
		return operationEngineer;
	}

	/**
	 * Method 'setOperationEngineer'
	 * 
	 * @param operationEngineer
	 */
	public void setOperationEngineer(String operationEngineer)
	{
		this.operationEngineer = operationEngineer;
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
		
		if (!(_other instanceof BeritaAcara)) {
			return false;
		}
		
		final BeritaAcara _cast = (BeritaAcara) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (beritaNo == null ? _cast.beritaNo != beritaNo : !beritaNo.equals( _cast.beritaNo )) {
			return false;
		}
		
		if (beritaDate == null ? _cast.beritaDate != beritaDate : !beritaDate.equals( _cast.beritaDate )) {
			return false;
		}
		
		if (ponumber == null ? _cast.ponumber != ponumber : !ponumber.equals( _cast.ponumber )) {
			return false;
		}
		
		if (podate == null ? _cast.podate != podate : !podate.equals( _cast.podate )) {
			return false;
		}
		
		if (productcode == null ? _cast.productcode != productcode : !productcode.equals( _cast.productcode )) {
			return false;
		}
		
		if (qtyNull != _cast.qtyNull) {
			return false;
		}
		
		if (uomName == null ? _cast.uomName != uomName : !uomName.equals( _cast.uomName )) {
			return false;
		}
		
		if (total != _cast.total) {
			return false;
		}
		
		if (totalNull != _cast.totalNull) {
			return false;
		}
		
		if (contractorName == null ? _cast.contractorName != contractorName : !contractorName.equals( _cast.contractorName )) {
			return false;
		}
		
		if (resultWork != _cast.resultWork) {
			return false;
		}
		
		if (resultWorkNull != _cast.resultWorkNull) {
			return false;
		}
		
		if (pertialCompletion != _cast.pertialCompletion) {
			return false;
		}
		
		if (pertialCompletionNull != _cast.pertialCompletionNull) {
			return false;
		}
		
		if (retention != _cast.retention) {
			return false;
		}
		
		if (retentionNull != _cast.retentionNull) {
			return false;
		}
		
		if (description == null ? _cast.description != description : !description.equals( _cast.description )) {
			return false;
		}
		
		if (progress != _cast.progress) {
			return false;
		}
		
		if (progressNull != _cast.progressNull) {
			return false;
		}
		
		if (totalProgress != _cast.totalProgress) {
			return false;
		}
		
		if (totalProgressNull != _cast.totalProgressNull) {
			return false;
		}
		
		if (endUser == null ? _cast.endUser != endUser : !endUser.equals( _cast.endUser )) {
			return false;
		}
		
		if (plantEngineer == null ? _cast.plantEngineer != plantEngineer : !plantEngineer.equals( _cast.plantEngineer )) {
			return false;
		}
		
		if (operationEngineer == null ? _cast.operationEngineer != operationEngineer : !operationEngineer.equals( _cast.operationEngineer )) {
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
		if (beritaNo != null) {
			_hashCode = 29 * _hashCode + beritaNo.hashCode();
		}
		
		if (beritaDate != null) {
			_hashCode = 29 * _hashCode + beritaDate.hashCode();
		}
		
		if (ponumber != null) {
			_hashCode = 29 * _hashCode + ponumber.hashCode();
		}
		
		if (podate != null) {
			_hashCode = 29 * _hashCode + podate.hashCode();
		}
		
		if (productcode != null) {
			_hashCode = 29 * _hashCode + productcode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (qtyNull ? 1 : 0);
		if (uomName != null) {
			_hashCode = 29 * _hashCode + uomName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (totalNull ? 1 : 0);
		if (contractorName != null) {
			_hashCode = 29 * _hashCode + contractorName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (resultWorkNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (pertialCompletionNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (retentionNull ? 1 : 0);
		if (description != null) {
			_hashCode = 29 * _hashCode + description.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (progressNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (totalProgressNull ? 1 : 0);
		if (endUser != null) {
			_hashCode = 29 * _hashCode + endUser.hashCode();
		}
		
		if (plantEngineer != null) {
			_hashCode = 29 * _hashCode + plantEngineer.hashCode();
		}
		
		if (operationEngineer != null) {
			_hashCode = 29 * _hashCode + operationEngineer.hashCode();
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
	 * @return BeritaAcaraPk
	 */
	public BeritaAcaraPk createPk()
	{
		return new BeritaAcaraPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.BeritaAcara: " );
		ret.append( "id=" + id );
		ret.append( ", beritaNo=" + beritaNo );
		ret.append( ", beritaDate=" + beritaDate );
		ret.append( ", ponumber=" + ponumber );
		ret.append( ", podate=" + podate );
		ret.append( ", productcode=" + productcode );
		ret.append( ", qty=" + qty );
		ret.append( ", uomName=" + uomName );
		ret.append( ", total=" + total );
		ret.append( ", contractorName=" + contractorName );
		ret.append( ", resultWork=" + resultWork );
		ret.append( ", pertialCompletion=" + pertialCompletion );
		ret.append( ", retention=" + retention );
		ret.append( ", description=" + description );
		ret.append( ", progress=" + progress );
		ret.append( ", totalProgress=" + totalProgress );
		ret.append( ", endUser=" + endUser );
		ret.append( ", plantEngineer=" + plantEngineer );
		ret.append( ", operationEngineer=" + operationEngineer );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
