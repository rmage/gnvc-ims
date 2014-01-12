package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SalesOrderDetail implements Serializable {
	
	protected String so_number;
	protected int quantitySO;
	protected Product product;
	protected Bundle bundle;
	protected String sku;
//	protected Timestamp delivery_date;
//	protected String delivery_name;
//	protected String delivery_address;
	protected String corpId;
	protected String whCode;
	
	protected SalesOrder so;
	protected UnitCode unitCode;
	protected User user;
	protected Corporate corp;
	protected Wh wh;
	
	public SalesOrderDetail(){
		
	}

//	public Timestamp getDelivery_date() {
//		return delivery_date;
//	}
//
//	public void setDelivery_date(Timestamp delivery_date) {
//		this.delivery_date = delivery_date;
//	}
//
//	public String getDelivery_name() {
//		return delivery_name;
//	}
//
//	public void setDelivery_name(String delivery_name) {
//		this.delivery_name = delivery_name;
//	}
//
//	public String getDelivery_address() {
//		return delivery_address;
//	}
//
//	public void setDelivery_address(String delivery_address) {
//		this.delivery_address = delivery_address;
//	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	public int getQuantitySO() {
		return quantitySO;
	}

	public void setQuantitySO(int quantitySO) {
		this.quantitySO = quantitySO;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SalesOrder getSo() {
		return so;
	}

	public void setSo(SalesOrder so) {
		this.so = so;
	}

	public UnitCode getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(UnitCode unitCode) {
		this.unitCode = unitCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Corporate getCorp() {
		return corp;
	}

	public void setCorp(Corporate corp) {
		this.corp = corp;
	}

	public Wh getWh() {
		return wh;
	}

	public void setWh(Wh wh) {
		this.wh = wh;
	}
	
	/**
	 * Returns <code>true</code> if the argument is an SalesOrderDetail instance and
	 * all identifiers for this entity equal the identifiers of the argument
	 * entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof SalesOrderDetail)) {
			return false;
		}
		final SalesOrderDetail that = (SalesOrderDetail) object;
		if (this.so_number == null || that.getSo_number() == null || !this.so_number.equals(that.getSo_number())) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode() {
		int hashCode = 0;
		hashCode = 29 * hashCode + (so_number == null ? 0 : so_number.hashCode());

		return hashCode;
	}
	
	/**
	 * Method 'createPk'
	 * 
	 * @return SalesOrderDetailPk
	 */
	public SalesOrderDetailPk createPk()
	{
		return new SalesOrderDetailPk(so_number);
	}
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString(){
		final StringBuffer sb = new StringBuffer();
		sb.append( "SalesOrderDetail");
		sb.append( "{");
		sb.append( "so_number=").append(so_number);
		sb.append( ",quantitySO=").append(quantitySO);
		sb.append( ",sku=").append(sku);
		sb.append( ",product=").append(product);
		sb.append( ",corpId=").append(corpId);
		sb.append( ",whCode=").append(whCode);
		sb.append( ",bundle=").append(bundle);
//		sb.append( "delivery_date=").append(delivery_date);
//		sb.append( "delivery_name=").append(delivery_name);
//		sb.append( "delivery_address=").append(delivery_address);
		sb.append( "}");
		return sb.toString();
	}
	

}
