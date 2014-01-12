package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class PriceUpload implements Serializable
{
	/** 
	 * This attribute maps to the column UPLOAD_ID in the PRICE_UPLOAD table.
	 */
	protected BigDecimal uploadId;

	/** 
	 * This attribute maps to the column FILENAME in the PRICE_UPLOAD table.
	 */
	protected String filename;

	/** 
	 * This attribute maps to the column FILEDATA in the PRICE_UPLOAD table.
	 */
	protected byte[] filedata;

	/** 
	 * This attribute maps to the column UPLOAD_DATE in the PRICE_UPLOAD table.
	 */
	protected Date uploadDate;

	/** 
	 * This attribute maps to the column IS_PROCESSED in the PRICE_UPLOAD table.
	 */
	protected String isProcessed;

	/** 
	 * This attribute maps to the column PROCESS_DATE in the PRICE_UPLOAD table.
	 */
	protected Date processDate;

	/** 
	 * This attribute maps to the column CREATED_BY in the PRICE_UPLOAD table.
	 */
	protected BigDecimal createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the PRICE_UPLOAD table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the PRICE_UPLOAD table.
	 */
	protected BigDecimal updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the PRICE_UPLOAD table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'PriceUpload'
	 * 
	 */
	public PriceUpload()
	{
	}

	/**
	 * Method 'getUploadId'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getUploadId()
	{
		return uploadId;
	}

	/**
	 * Method 'setUploadId'
	 * 
	 * @param uploadId
	 */
	public void setUploadId(BigDecimal uploadId)
	{
		this.uploadId = uploadId;
	}

	/**
	 * Method 'getFilename'
	 * 
	 * @return String
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * Method 'setFilename'
	 * 
	 * @param filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	/**
	 * Method 'getFiledata'
	 * 
	 * @return byte[]
	 */
	public byte[] getFiledata()
	{
		return filedata;
	}

	/**
	 * Method 'setFiledata'
	 * 
	 * @param filedata
	 */
	public void setFiledata(byte[] filedata)
	{
		this.filedata = filedata;
	}

	/**
	 * Method 'getUploadDate'
	 * 
	 * @return Date
	 */
	public Date getUploadDate()
	{
		return uploadDate;
	}

	/**
	 * Method 'setUploadDate'
	 * 
	 * @param uploadDate
	 */
	public void setUploadDate(Date uploadDate)
	{
		this.uploadDate = uploadDate;
	}

	/**
	 * Method 'getIsProcessed'
	 * 
	 * @return String
	 */
	public String getIsProcessed()
	{
		return isProcessed;
	}

	/**
	 * Method 'setIsProcessed'
	 * 
	 * @param isProcessed
	 */
	public void setIsProcessed(String isProcessed)
	{
		this.isProcessed = isProcessed;
	}

	/**
	 * Method 'getProcessDate'
	 * 
	 * @return Date
	 */
	public Date getProcessDate()
	{
		return processDate;
	}

	/**
	 * Method 'setProcessDate'
	 * 
	 * @param processDate
	 */
	public void setProcessDate(Date processDate)
	{
		this.processDate = processDate;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(BigDecimal createdBy)
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
	 * @return BigDecimal
	 */
	public BigDecimal getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(BigDecimal updatedBy)
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
		
		if (!(_other instanceof PriceUpload)) {
			return false;
		}
		
		final PriceUpload _cast = (PriceUpload) _other;
		if (uploadId == null ? _cast.uploadId != uploadId : !uploadId.equals( _cast.uploadId )) {
			return false;
		}
		
		if (filename == null ? _cast.filename != filename : !filename.equals( _cast.filename )) {
			return false;
		}
		
		if (filedata == null ? _cast.filedata != filedata : !filedata.equals( _cast.filedata )) {
			return false;
		}
		
		if (uploadDate == null ? _cast.uploadDate != uploadDate : !uploadDate.equals( _cast.uploadDate )) {
			return false;
		}
		
		if (isProcessed == null ? _cast.isProcessed != isProcessed : !isProcessed.equals( _cast.isProcessed )) {
			return false;
		}
		
		if (processDate == null ? _cast.processDate != processDate : !processDate.equals( _cast.processDate )) {
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
		if (uploadId != null) {
			_hashCode = 29 * _hashCode + uploadId.hashCode();
		}
		
		if (filename != null) {
			_hashCode = 29 * _hashCode + filename.hashCode();
		}
		
		if (filedata != null) {
			_hashCode = 29 * _hashCode + filedata.hashCode();
		}
		
		if (uploadDate != null) {
			_hashCode = 29 * _hashCode + uploadDate.hashCode();
		}
		
		if (isProcessed != null) {
			_hashCode = 29 * _hashCode + isProcessed.hashCode();
		}
		
		if (processDate != null) {
			_hashCode = 29 * _hashCode + processDate.hashCode();
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
	 * @return PriceUploadPk
	 */
	public PriceUploadPk createPk()
	{
		return new PriceUploadPk(uploadId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.PriceUpload: " );
		ret.append( "uploadId=" + uploadId );
		ret.append( ", filename=" + filename );
		ret.append( ", filedata=" + filedata );
		ret.append( ", uploadDate=" + uploadDate );
		ret.append( ", isProcessed=" + isProcessed );
		ret.append( ", processDate=" + processDate );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
