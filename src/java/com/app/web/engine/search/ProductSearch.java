/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.web.engine.search;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author programmer
 */
public class ProductSearch {
	
	private String tableAlias = "";
	
	/** 
	 * This attribute maps to the column id in the product table.
	 */
	protected String id;

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
	 * This attribute maps to the column unit_weight in the product table.
	 */
	protected String unitWeight;

	/** 
	 * This attribute maps to the column unit_length in the product table.
	 */
	protected int unitLength;

	/** 
	 * This attribute represents whether the primitive attribute unitLength is null.
	 */
	protected boolean unitLengthNull = true;

	/** 
	 * This attribute maps to the column unit_width in the product table.
	 */
	protected int unitWidth;

	/** 
	 * This attribute represents whether the primitive attribute unitWidth is null.
	 */
	protected boolean unitWidthNull = true;

	/** 
	 * This attribute maps to the column unit_height in the product table.
	 */
	protected int unitHeight;

	/** 
	 * This attribute represents whether the primitive attribute unitHeight is null.
	 */
	protected boolean unitHeightNull = true;

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

	/**
	 * Method 'Product'
	 * 
	 */
	public ProductSearch()
	{
	}


	public String getTableAlias() {
		return tableAlias;
	}


	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
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
	 * Method 'getUnitLength'
	 * 
	 * @return int
	 */
	public int getUnitLength()
	{
		return unitLength;
	}

	/**
	 * Method 'setUnitLength'
	 * 
	 * @param unitLength
	 */
	public void setUnitLength(int unitLength)
	{
		this.unitLength = unitLength;
		this.unitLengthNull = false;
	}

	/**
	 * Method 'setUnitLengthNull'
	 * 
	 * @param value
	 */
	public void setUnitLengthNull(boolean value)
	{
		this.unitLengthNull = value;
	}

	/**
	 * Method 'isUnitLengthNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitLengthNull()
	{
		return unitLengthNull;
	}

	/**
	 * Method 'getUnitWidth'
	 * 
	 * @return int
	 */
	public int getUnitWidth()
	{
		return unitWidth;
	}

	/**
	 * Method 'setUnitWidth'
	 * 
	 * @param unitWidth
	 */
	public void setUnitWidth(int unitWidth)
	{
		this.unitWidth = unitWidth;
		this.unitWidthNull = false;
	}

	/**
	 * Method 'setUnitWidthNull'
	 * 
	 * @param value
	 */
	public void setUnitWidthNull(boolean value)
	{
		this.unitWidthNull = value;
	}

	/**
	 * Method 'isUnitWidthNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitWidthNull()
	{
		return unitWidthNull;
	}

	/**
	 * Method 'getUnitHeight'
	 * 
	 * @return int
	 */
	public int getUnitHeight()
	{
		return unitHeight;
	}

	/**
	 * Method 'setUnitHeight'
	 * 
	 * @param unitHeight
	 */
	public void setUnitHeight(int unitHeight)
	{
		this.unitHeight = unitHeight;
		this.unitHeightNull = false;
	}

	/**
	 * Method 'setUnitHeightNull'
	 * 
	 * @param value
	 */
	public void setUnitHeightNull(boolean value)
	{
		this.unitHeightNull = value;
	}

	/**
	 * Method 'isUnitHeightNull'
	 * 
	 * @return boolean
	 */
	public boolean isUnitHeightNull()
	{
		return unitHeightNull;
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
	
	public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.productCode.equals("")) {
            String itemLike = "%" + productCode + "%";

            search += " AND upper(" + tableAlias + ".PRODUCT_CODE) LIKE :productCode ";
            param.put("productCode", itemLike.toUpperCase());
        }
        
        if (!this.productName.equals("")) {
            String itemLike = "%" + productCode + "%";

            search += " AND upper(" + tableAlias + ".PRODUCT_CODE) LIKE :productCode ";
            param.put("productCode", itemLike.toUpperCase());
        }
        
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }

 
}
