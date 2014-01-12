package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Product implements Serializable
{
	/** 
	 * This attribute maps to the column id in the product table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column product_id in the product table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column bar_code in the product table.
	 */
	protected String barCode;

	/** 
	 * This attribute maps to the column product_code in the product table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the product table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column product_alias in the product table.
	 */
	protected String productAlias;

	/** 
	 * This attribute maps to the column product_category in the product table.
	 */
	protected String productCategory;

	/** 
	 * This attribute maps to the column brand_name in the product table.
	 */
	protected String brandName;

	/** 
	 * This attribute maps to the column product_type in the product table.
	 */
	protected String productType;

	/** 
	 * This attribute maps to the column product_color in the product table.
	 */
	protected String productColor;

	/** 
	 * This attribute maps to the column product_description in the product table.
	 */
	protected String productDescription;

	/** 
	 * This attribute maps to the column volume_weight in the product table.
	 */
//	protected double volumeWeight;

	/** 
	 * This attribute represents whether the primitive attribute volumeWeight is null.
	 */
//	protected boolean volumeWeightNull = true;

	/** 
	 * This attribute maps to the column unit_weight in the product table.
	 */
	protected String unitWeight;

	/** 
	 * This attribute maps to the column volume_matrix in the product table.
	 */
//	protected double volumeMatrix;

	/** 
	 * This attribute represents whether the primitive attribute volumeMatrix is null.
	 */
//	protected boolean volumeMatrixNull = true;

	/** 
	 * This attribute maps to the column unit_matrix in the product table.
	 */
	protected String unitMatrix;

	/** 
	 * This attribute maps to the column unit_length in the product table.
	 */
//	protected double unitLength;

	/** 
	 * This attribute represents whether the primitive attribute unitLength is null.
	 */
//	protected boolean unitLengthNull = true;

	/** 
	 * This attribute maps to the column unit_width in the product table.
	 */
//	protected double unitWidth;

	/** 
	 * This attribute represents whether the primitive attribute unitWidth is null.
	 */
//	protected boolean unitWidthNull = true;

	/** 
	 * This attribute maps to the column unit_height in the product table.
	 */
//	protected double unitHeight;

	/** 
	 * This attribute represents whether the primitive attribute unitHeight is null.
	 */
//	protected boolean unitHeightNull = true;

	/** 
	 * This attribute maps to the column unit_piece in the product table.
	 */
	protected int unitPiece;

	/** 
	 * This attribute represents whether the primitive attribute unitPiece is null.
	 */
	protected boolean unitPieceNull = true;

	/** 
	 * This attribute maps to the column unit_box in the product table.
	 */
	protected int unitBox;

	/** 
	 * This attribute represents whether the primitive attribute unitBox is null.
	 */
	protected boolean unitBoxNull = true;

	/** 
	 * This attribute maps to the column unit_cartoon in the product table.
	 */
	protected int unitCartoon;

	/** 
	 * This attribute represents whether the primitive attribute unitCartoon is null.
	 */
	protected boolean unitCartoonNull = true;

	/** 
	 * This attribute maps to the column unit_pallete in the product table.
	 */
	protected int unitPallete;

	/** 
	 * This attribute represents whether the primitive attribute unitPallete is null.
	 */
	protected boolean unitPalleteNull = true;

	/** 
	 * This attribute maps to the column user_id in the product table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the product table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the product table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column is_active in the product table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the product table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the product table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the product table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the product table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the product table.
	 */
	protected Date updatedDate;
	
	protected String volumeWeight;
	protected String volumeMatrix;
	protected String unitLength;
	protected String unitWidth;
	protected String unitHeight;
	
	protected String uom;
	
	protected String supplier;
	protected String buyer;
	protected String packstyle;
	protected String packsize;
	protected String lid;
	protected String nwdwpw;
	protected String canCode;

	/**
	 * Method 'Product'
	 * 
	 */
	public Product()
	{
	}

	public String getCanCode() {
		return canCode;
	}

	public void setCanCode(String canCode) {
		this.canCode = canCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getPackstyle() {
		return packstyle;
	}

	public void setPackstyle(String packstyle) {
		this.packstyle = packstyle;
	}

	public String getPacksize() {
		return packsize;
	}

	public void setPacksize(String packsize) {
		this.packsize = packsize;
	}

	public String getLid() {
		return lid;
	}

	public void setLid(String lid) {
		this.lid = lid;
	}

	public String getNwdwpw() {
		return nwdwpw;
	}

	public void setNwdwpw(String nwdwpw) {
		this.nwdwpw = nwdwpw;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getVolumeWeight() {
		return volumeWeight;
	}

	public void setVolumeWeight(String volumeWeight) {
		this.volumeWeight = volumeWeight;
	}

	public String getVolumeMatrix() {
		return volumeMatrix;
	}

	public void setVolumeMatrix(String volumeMatrix) {
		this.volumeMatrix = volumeMatrix;
	}

	public String getUnitLength() {
		return unitLength;
	}

	public void setUnitLength(String unitLength) {
		this.unitLength = unitLength;
	}

	public String getUnitWidth() {
		return unitWidth;
	}

	public void setUnitWidth(String unitWidth) {
		this.unitWidth = unitWidth;
	}

	public String getUnitHeight() {
		return unitHeight;
	}

	public void setUnitHeight(String unitHeight) {
		this.unitHeight = unitHeight;
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
	 * Method 'getProductId'
	 * 
	 * @return String
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * Method 'setProductId'
	 * 
	 * @param productId
	 */
	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	/**
	 * Method 'getBarCode'
	 * 
	 * @return String
	 */
	public String getBarCode()
	{
		return barCode;
	}

	/**
	 * Method 'setBarCode'
	 * 
	 * @param barCode
	 */
	public void setBarCode(String barCode)
	{
		this.barCode = barCode;
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
	 * Method 'getProductAlias'
	 * 
	 * @return String
	 */
	public String getProductAlias()
	{
		return productAlias;
	}

	/**
	 * Method 'setProductAlias'
	 * 
	 * @param productAlias
	 */
	public void setProductAlias(String productAlias)
	{
		this.productAlias = productAlias;
	}

	/**
	 * Method 'getProductCategory'
	 * 
	 * @return String
	 */
	public String getProductCategory()
	{
		return productCategory;
	}

	/**
	 * Method 'setProductCategory'
	 * 
	 * @param productCategory
	 */
	public void setProductCategory(String productCategory)
	{
		this.productCategory = productCategory;
	}

	/**
	 * Method 'getBrandName'
	 * 
	 * @return String
	 */
	public String getBrandName()
	{
		return brandName;
	}

	/**
	 * Method 'setBrandName'
	 * 
	 * @param brandName
	 */
	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}

	/**
	 * Method 'getProductType'
	 * 
	 * @return String
	 */
	public String getProductType()
	{
		return productType;
	}

	/**
	 * Method 'setProductType'
	 * 
	 * @param productType
	 */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	/**
	 * Method 'getProductColor'
	 * 
	 * @return String
	 */
	public String getProductColor()
	{
		return productColor;
	}

	/**
	 * Method 'setProductColor'
	 * 
	 * @param productColor
	 */
	public void setProductColor(String productColor)
	{
		this.productColor = productColor;
	}

	/**
	 * Method 'getProductDescription'
	 * 
	 * @return String
	 */
	public String getProductDescription()
	{
		return productDescription;
	}

	/**
	 * Method 'setProductDescription'
	 * 
	 * @param productDescription
	 */
	public void setProductDescription(String productDescription)
	{
		this.productDescription = productDescription;
	}

	
	/**
	 * Method 'getUnitWeight'
	 * 
	 * @return String
	 */
	public String getUnitWeight()
	{
		return unitWeight;
	}

	/**
	 * Method 'setUnitWeight'
	 * 
	 * @param unitWeight
	 */
	public void setUnitWeight(String unitWeight)
	{
		this.unitWeight = unitWeight;
	}

	
	/**
	 * Method 'getUnitMatrix'
	 * 
	 * @return String
	 */
	public String getUnitMatrix()
	{
		return unitMatrix;
	}

	/**
	 * Method 'setUnitMatrix'
	 * 
	 * @param unitMatrix
	 */
	public void setUnitMatrix(String unitMatrix)
	{
		this.unitMatrix = unitMatrix;
	}

	
	/**
	 * Method 'getUnitPiece'
	 * 
	 * @return int
	 */
	public int getUnitPiece()
	{
		return unitPiece;
	}

	/**
	 * Method 'setUnitPiece'
	 * 
	 * @param unitPiece
	 */
	public void setUnitPiece(int unitPiece)
	{
		this.unitPiece = unitPiece;
		this.unitPieceNull = false;
	}

	/**
	 * Method 'setUnitPieceNull'
	 * 
	 * @param value
	 */
	public void setUnitPieceNull(boolean value)
	{
		this.unitPieceNull = value;
	}

	/**
	 * Method 'isUnitPieceNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitPieceNull()
	{
		return unitPieceNull;
	}

	/**
	 * Method 'getUnitBox'
	 * 
	 * @return int
	 */
	public int getUnitBox()
	{
		return unitBox;
	}

	/**
	 * Method 'setUnitBox'
	 * 
	 * @param unitBox
	 */
	public void setUnitBox(int unitBox)
	{
		this.unitBox = unitBox;
		this.unitBoxNull = false;
	}

	/**
	 * Method 'setUnitBoxNull'
	 * 
	 * @param value
	 */
	public void setUnitBoxNull(boolean value)
	{
		this.unitBoxNull = value;
	}

	/**
	 * Method 'isUnitBoxNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitBoxNull()
	{
		return unitBoxNull;
	}

	/**
	 * Method 'getUnitCartoon'
	 * 
	 * @return int
	 */
	public int getUnitCartoon()
	{
		return unitCartoon;
	}

	/**
	 * Method 'setUnitCartoon'
	 * 
	 * @param unitCartoon
	 */
	public void setUnitCartoon(int unitCartoon)
	{
		this.unitCartoon = unitCartoon;
		this.unitCartoonNull = false;
	}

	/**
	 * Method 'setUnitCartoonNull'
	 * 
	 * @param value
	 */
	public void setUnitCartoonNull(boolean value)
	{
		this.unitCartoonNull = value;
	}

	/**
	 * Method 'isUnitCartoonNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitCartoonNull()
	{
		return unitCartoonNull;
	}

	/**
	 * Method 'getUnitPallete'
	 * 
	 * @return int
	 */
	public int getUnitPallete()
	{
		return unitPallete;
	}

	/**
	 * Method 'setUnitPallete'
	 * 
	 * @param unitPallete
	 */
	public void setUnitPallete(int unitPallete)
	{
		this.unitPallete = unitPallete;
		this.unitPalleteNull = false;
	}

	/**
	 * Method 'setUnitPalleteNull'
	 * 
	 * @param value
	 */
	public void setUnitPalleteNull(boolean value)
	{
		this.unitPalleteNull = value;
	}

	/**
	 * Method 'isUnitPalleteNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitPalleteNull()
	{
		return unitPalleteNull;
	}

	/**
	 * Method 'getUserId'
	 * 
	 * @return String
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Method 'setUserId'
	 * 
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Method 'getCorpId'
	 * 
	 * @return String
	 */
	public String getCorpId()
	{
		return corpId;
	}

	/**
	 * Method 'setCorpId'
	 * 
	 * @param corpId
	 */
	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
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
		
		if (!(_other instanceof Product)) {
			return false;
		}
		
		final Product _cast = (Product) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (productId == null ? _cast.productId != productId : !productId.equals( _cast.productId )) {
			return false;
		}
		
		if (barCode == null ? _cast.barCode != barCode : !barCode.equals( _cast.barCode )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (productAlias == null ? _cast.productAlias != productAlias : !productAlias.equals( _cast.productAlias )) {
			return false;
		}
		
		if (productCategory == null ? _cast.productCategory != productCategory : !productCategory.equals( _cast.productCategory )) {
			return false;
		}
		
		if (brandName == null ? _cast.brandName != brandName : !brandName.equals( _cast.brandName )) {
			return false;
		}
		
		if (productType == null ? _cast.productType != productType : !productType.equals( _cast.productType )) {
			return false;
		}
		
		if (productColor == null ? _cast.productColor != productColor : !productColor.equals( _cast.productColor )) {
			return false;
		}
		
		if (productDescription == null ? _cast.productDescription != productDescription : !productDescription.equals( _cast.productDescription )) {
			return false;
		}
		
		
		if (unitWeight == null ? _cast.unitWeight != unitWeight : !unitWeight.equals( _cast.unitWeight )) {
			return false;
		}
		
		if (unitMatrix == null ? _cast.unitMatrix != unitMatrix : !unitMatrix.equals( _cast.unitMatrix )) {
			return false;
		}
		
		if (unitPiece != _cast.unitPiece) {
			return false;
		}
		
		if (unitPieceNull != _cast.unitPieceNull) {
			return false;
		}
		
		if (unitBox != _cast.unitBox) {
			return false;
		}
		
		if (unitBoxNull != _cast.unitBoxNull) {
			return false;
		}
		
		if (unitCartoon != _cast.unitCartoon) {
			return false;
		}
		
		if (unitCartoonNull != _cast.unitCartoonNull) {
			return false;
		}
		
		if (unitPallete != _cast.unitPallete) {
			return false;
		}
		
		if (unitPalleteNull != _cast.unitPalleteNull) {
			return false;
		}
		
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
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
		if (productId != null) {
			_hashCode = 29 * _hashCode + productId.hashCode();
		}
		
		if (barCode != null) {
			_hashCode = 29 * _hashCode + barCode.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		if (productAlias != null) {
			_hashCode = 29 * _hashCode + productAlias.hashCode();
		}
		
		if (productCategory != null) {
			_hashCode = 29 * _hashCode + productCategory.hashCode();
		}
		
		if (brandName != null) {
			_hashCode = 29 * _hashCode + brandName.hashCode();
		}
		
		if (productType != null) {
			_hashCode = 29 * _hashCode + productType.hashCode();
		}
		
		if (productColor != null) {
			_hashCode = 29 * _hashCode + productColor.hashCode();
		}
		
		if (productDescription != null) {
			_hashCode = 29 * _hashCode + productDescription.hashCode();
		}
		
		
		_hashCode = 29 * _hashCode + unitPiece;
		_hashCode = 29 * _hashCode + (unitPieceNull ? 1 : 0);
		_hashCode = 29 * _hashCode + unitBox;
		_hashCode = 29 * _hashCode + (unitBoxNull ? 1 : 0);
		_hashCode = 29 * _hashCode + unitCartoon;
		_hashCode = 29 * _hashCode + (unitCartoonNull ? 1 : 0);
		_hashCode = 29 * _hashCode + unitPallete;
		_hashCode = 29 * _hashCode + (unitPalleteNull ? 1 : 0);
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
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
	 * @return ProductPk
	 */
	public ProductPk createPk()
	{
		return new ProductPk(productId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Product: " );
		ret.append( "id=" + id );
		ret.append( ", productId=" + productId );
		ret.append( ", barCode=" + barCode );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", productAlias=" + productAlias );
		ret.append( ", productCategory=" + productCategory );
		ret.append( ", brandName=" + brandName );
		ret.append( ", productType=" + productType );
		ret.append( ", productColor=" + productColor );
		ret.append( ", productDescription=" + productDescription );
		ret.append( ", volumeWeight=" + volumeWeight );
		ret.append( ", unitWeight=" + unitWeight );
		ret.append( ", volumeMatrix=" + volumeMatrix );
		ret.append( ", unitMatrix=" + unitMatrix );
		ret.append( ", unitLength=" + unitLength );
		ret.append( ", unitWidth=" + unitWidth );
		ret.append( ", unitHeight=" + unitHeight );
		ret.append( ", unitPiece=" + unitPiece );
		ret.append( ", unitBox=" + unitBox );
		ret.append( ", unitCartoon=" + unitCartoon );
		ret.append( ", unitPallete=" + unitPallete );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", uom=" + uom );
		ret.append( ", supplier=" + supplier );
		ret.append( ", buyer=" + buyer );
		ret.append( ", packstyle=" + packstyle );
		ret.append( ", packsize=" + packsize );
		ret.append( ", lid=" + lid );
		ret.append( ", nwdwpw=" + nwdwpw );
		return ret.toString();
	}

}
