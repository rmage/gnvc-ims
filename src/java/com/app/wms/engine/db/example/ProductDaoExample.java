package com.app.wms.engine.db.example;

import java.math.*;
import java.util.Date;
import java.util.List;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.exceptions.ProductDaoException;
import com.app.wms.engine.db.factory.DaoFactory;

public class ProductDaoExample
{
	/**
	 * Method 'main'
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception
	{
		// Uncomment one of the lines below to test the generated code
		
		// findAll();
		// findWhereIdEquals(0);
		// findWhereProductIdEquals("");
		// findWhereBarCodeEquals("");
		// findWhereProductCodeEquals("");
		// findWhereProductNameEquals("");
		// findWhereProductAliasEquals("");
		// findWhereProductCategoryEquals("");
		// findWhereBrandNameEquals("");
		// findWhereProductTypeEquals("");
		// findWhereProductColorEquals("");
		// findWhereProductDescriptionEquals("");
		// findWhereUnitWeightEquals("");
		// findWhereUnitLengthEquals(0);
		// findWhereUnitWidthEquals(0);
		// findWhereUnitHeightEquals(0);
		// findWhereUnitPieceEquals(0);
		// findWhereUnitBoxEquals(0);
		// findWhereUnitCartoonEquals(0);
		// findWhereUnitPalleteEquals(0);
		// findWhereUserIdEquals("");
		// findWhereCorpIdEquals("");
		// findWhereWhCodeEquals("");
		// findWhereIsActiveEquals("");
		// findWhereIsDeleteEquals("");
		// findWhereCreatedByEquals("");
		// findWhereCreatedDateEquals(null);
		// findWhereUpdatedByEquals("");
		// findWhereUpdatedDateEquals(null);
	}

	/**
	 * Method 'findAll'
	 * 
	 * @throws Exception
	 */
	public static void findAll() throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findAll();
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIdEquals'
	 * 
	 * @param id
	 * @throws Exception
	 */
	public static void findWhereIdEquals(int id) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereIdEquals(id);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductIdEquals'
	 * 
	 * @param productId
	 * @throws Exception
	 */
	public static void findWhereProductIdEquals(String productId) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductIdEquals(productId);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBarCodeEquals'
	 * 
	 * @param barCode
	 * @throws Exception
	 */
	public static void findWhereBarCodeEquals(String barCode) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereBarCodeEquals(barCode);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCodeEquals'
	 * 
	 * @param productCode
	 * @throws Exception
	 */
	public static void findWhereProductCodeEquals(String productCode) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductCodeEquals(productCode);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductNameEquals'
	 * 
	 * @param productName
	 * @throws Exception
	 */
	public static void findWhereProductNameEquals(String productName) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductNameEquals(productName);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductAliasEquals'
	 * 
	 * @param productAlias
	 * @throws Exception
	 */
	public static void findWhereProductAliasEquals(String productAlias) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductAliasEquals(productAlias);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductCategoryEquals'
	 * 
	 * @param productCategory
	 * @throws Exception
	 */
	public static void findWhereProductCategoryEquals(String productCategory) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductCategoryEquals(productCategory);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereBrandNameEquals'
	 * 
	 * @param brandName
	 * @throws Exception
	 */
	public static void findWhereBrandNameEquals(String brandName) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereBrandNameEquals(brandName);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductTypeEquals'
	 * 
	 * @param productType
	 * @throws Exception
	 */
	public static void findWhereProductTypeEquals(String productType) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductTypeEquals(productType);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductColorEquals'
	 * 
	 * @param productColor
	 * @throws Exception
	 */
	public static void findWhereProductColorEquals(String productColor) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductColorEquals(productColor);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereProductDescriptionEquals'
	 * 
	 * @param productDescription
	 * @throws Exception
	 */
	public static void findWhereProductDescriptionEquals(String productDescription) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereProductDescriptionEquals(productDescription);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitWeightEquals'
	 * 
	 * @param unitWeight
	 * @throws Exception
	 */
	public static void findWhereUnitWeightEquals(String unitWeight) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitWeightEquals(unitWeight);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitLengthEquals'
	 * 
	 * @param unitLength
	 * @throws Exception
	 */
	public static void findWhereUnitLengthEquals(int unitLength) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitLengthEquals(unitLength);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitWidthEquals'
	 * 
	 * @param unitWidth
	 * @throws Exception
	 */
	public static void findWhereUnitWidthEquals(int unitWidth) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitWidthEquals(unitWidth);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitHeightEquals'
	 * 
	 * @param unitHeight
	 * @throws Exception
	 */
	public static void findWhereUnitHeightEquals(int unitHeight) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitHeightEquals(unitHeight);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitPieceEquals'
	 * 
	 * @param unitPiece
	 * @throws Exception
	 */
	public static void findWhereUnitPieceEquals(int unitPiece) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitPieceEquals(unitPiece);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitBoxEquals'
	 * 
	 * @param unitBox
	 * @throws Exception
	 */
	public static void findWhereUnitBoxEquals(int unitBox) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitBoxEquals(unitBox);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitCartoonEquals'
	 * 
	 * @param unitCartoon
	 * @throws Exception
	 */
	public static void findWhereUnitCartoonEquals(int unitCartoon) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitCartoonEquals(unitCartoon);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUnitPalleteEquals'
	 * 
	 * @param unitPallete
	 * @throws Exception
	 */
	public static void findWhereUnitPalleteEquals(int unitPallete) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUnitPalleteEquals(unitPallete);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUserIdEquals'
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public static void findWhereUserIdEquals(String userId) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUserIdEquals(userId);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCorpIdEquals'
	 * 
	 * @param corpId
	 * @throws Exception
	 */
	public static void findWhereCorpIdEquals(String corpId) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereCorpIdEquals(corpId);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param whCode
	 * @throws Exception
	 */
	public static void findWhereWhCodeEquals(String whCode) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereWhCodeEquals(whCode);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsActiveEquals'
	 * 
	 * @param isActive
	 * @throws Exception
	 */
	public static void findWhereIsActiveEquals(String isActive) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereIsActiveEquals(isActive);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereIsDeleteEquals'
	 * 
	 * @param isDelete
	 * @throws Exception
	 */
	public static void findWhereIsDeleteEquals(String isDelete) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereIsDeleteEquals(isDelete);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedByEquals'
	 * 
	 * @param createdBy
	 * @throws Exception
	 */
	public static void findWhereCreatedByEquals(String createdBy) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereCreatedByEquals(createdBy);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereCreatedDateEquals'
	 * 
	 * @param createdDate
	 * @throws Exception
	 */
	public static void findWhereCreatedDateEquals(Date createdDate) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereCreatedDateEquals(createdDate);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedByEquals'
	 * 
	 * @param updatedBy
	 * @throws Exception
	 */
	public static void findWhereUpdatedByEquals(String updatedBy) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUpdatedByEquals(updatedBy);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'findWhereUpdatedDateEquals'
	 * 
	 * @param updatedDate
	 * @throws Exception
	 */
	public static void findWhereUpdatedDateEquals(Date updatedDate) throws Exception
	{
		ProductDao dao = DaoFactory.createProductDao();
		List<Product> _result = dao.findWhereUpdatedDateEquals(updatedDate);
		for (Product dto : _result) {
			display(dto);
		}
		
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(Product dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getId() );
		buf.append( ", " );
		buf.append( dto.getProductId() );
		buf.append( ", " );
		buf.append( dto.getBarCode() );
		buf.append( ", " );
		buf.append( dto.getProductCode() );
		buf.append( ", " );
		buf.append( dto.getProductName() );
		buf.append( ", " );
		buf.append( dto.getProductAlias() );
		buf.append( ", " );
		buf.append( dto.getProductCategory() );
		buf.append( ", " );
		buf.append( dto.getBrandName() );
		buf.append( ", " );
		buf.append( dto.getProductType() );
		buf.append( ", " );
		buf.append( dto.getProductColor() );
		buf.append( ", " );
		buf.append( dto.getProductDescription() );
		buf.append( ", " );
		buf.append( dto.getUnitWeight() );
		buf.append( ", " );
		buf.append( dto.getUnitLength() );
		buf.append( ", " );
		buf.append( dto.getUnitWidth() );
		buf.append( ", " );
		buf.append( dto.getUnitHeight() );
		buf.append( ", " );
		buf.append( dto.getUnitPiece() );
		buf.append( ", " );
		buf.append( dto.getUnitBox() );
		buf.append( ", " );
		buf.append( dto.getUnitCartoon() );
		buf.append( ", " );
		buf.append( dto.getUnitPallete() );
		buf.append( ", " );
		buf.append( dto.getUserId() );
		buf.append( ", " );
		buf.append( dto.getCorpId() );
		buf.append( ", " );
		buf.append( dto.getWhCode() );
		buf.append( ", " );
		buf.append( dto.getIsActive() );
		buf.append( ", " );
		buf.append( dto.getIsDelete() );
		buf.append( ", " );
		buf.append( dto.getCreatedBy() );
		buf.append( ", " );
		buf.append( dto.getCreatedDate() );
		buf.append( ", " );
		buf.append( dto.getUpdatedBy() );
		buf.append( ", " );
		buf.append( dto.getUpdatedDate() );
		System.out.println( buf.toString() );
	}

}
