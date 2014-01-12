package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PickingDetail implements Serializable {
	
	protected String pickingId;
	protected String pickingDate;
	protected Timestamp expiredDate;
	protected Timestamp receivedDate;
	protected String unitCode;
	protected int qtyOrder;
	protected int qtyPick;
	protected int balance;
	protected String userId;
	protected String lotId;
	protected Product products;
	protected Corporate corp;
	protected WarehouseLocation whlocation;
	protected Wh wh;
	protected SalesOrderDetail soDetail;
	protected SalesOrder so;
	
	public SalesOrder getSo() {
		return so;
	}


	public void setSo(SalesOrder so) {
		this.so = so;
	}


	public PickingDetail(){
		
	}
	
	
	public String getLotId() {
		return lotId;
	}


	public void setLotId(String lotId) {
		this.lotId = lotId;
	}


	public String getPickingDate() {
		return pickingDate;
	}


	public void setPickingDate(String pickingDate) {
		this.pickingDate = pickingDate;
	}


	public Timestamp getExpiredDate() {
		return expiredDate;
	}


	public void setExpiredDate(Timestamp expiredDate) {
		this.expiredDate = expiredDate;
	}


	public Timestamp getReceivedDate() {
		return receivedDate;
	}


	public void setReceivedDate(Timestamp receivedDate) {
		this.receivedDate = receivedDate;
	}


	public SalesOrderDetail getSoDetail() {
		return soDetail;
	}


	public void setSoDetail(SalesOrderDetail soDetail) {
		this.soDetail = soDetail;
	}


	public String getPickingId() {
		return pickingId;
	}


	public void setPickingId(String pickingId) {
		this.pickingId = pickingId;
	}


	public String getUnitCode() {
		return unitCode;
	}


	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}


	public int getQtyOrder() {
		return qtyOrder;
	}


	public void setQtyOrder(int qtyOrder) {
		this.qtyOrder = qtyOrder;
	}


	public int getQtyPick() {
		return qtyPick;
	}


	public void setQtyPick(int qtyPick) {
		this.qtyPick = qtyPick;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Product getProducts() {
		return products;
	}


	public void setProducts(Product products) {
		this.products = products;
	}


	public Corporate getCorp() {
		return corp;
	}


	public void setCorp(Corporate corp) {
		this.corp = corp;
	}


	public WarehouseLocation getWhlocation() {
		return whlocation;
	}


	public void setWhlocation(WarehouseLocation whlocation) {
		this.whlocation = whlocation;
	}


	public Wh getWh() {
		return wh;
	}


	public void setWh(Wh wh) {
		this.wh = wh;
	}
	
	/**
	 * Returns <code>true</code> if the argument is an PickingDetail instance and
	 * all identifiers for this entity equal the identifiers of the argument
	 * entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof PickingDetail)) {
			return false;
		}
		final PickingDetail that = (PickingDetail) object;
		if (this.pickingId == null || that.getPickingId() == null || !this.pickingId.equals(that.getPickingId())) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode() {
		int hashCode = 0;
		hashCode = 29 * hashCode + (pickingId == null ? 0 : pickingId.hashCode());

		return hashCode;
	}
	
	/**
	 * Method 'createPk'
	 * 
	 * @return PickingDetailPk
	 */
	public PickingDetailPk createPk()
	{
		return new PickingDetailPk(pickingId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		final StringBuffer sb = new StringBuffer();
		sb.append( "PickingDetail" );
		sb.append( "{" );
		sb.append( "products=").append(products);
		sb.append( ",pickingId=").append(pickingId);
		sb.append( ",pickingDate=").append(pickingDate);
		sb.append( ",receivedDate=").append(receivedDate);
		sb.append( ",expiredDate=").append(expiredDate);
		sb.append( ",unitCode=").append(unitCode);
		sb.append( ",qtyOrder=").append(qtyOrder);
		sb.append( ",qtyPick=").append(qtyPick);
		sb.append( ",balance=").append(balance);
		sb.append( ",lotId=").append(lotId);
		sb.append( ",userId=").append(userId);	
		sb.append( ",corp=").append(corp);	
		sb.append( ",whlocation=").append(whlocation);	
		sb.append( ",wh=").append(wh);	
		sb.append( ",soDetail=").append(soDetail);	
		sb.append( ",so=").append(so);	
		sb.append( "}" );	
		return sb.toString();
	}

}
