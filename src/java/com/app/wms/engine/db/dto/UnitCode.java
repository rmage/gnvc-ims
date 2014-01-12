package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class UnitCode implements Serializable {
	
	protected String id;
	protected String name;
	protected int piece;
	protected int box;
	protected int cartoon;
	protected int pallete;
	protected String isActive;
	protected String isDelete;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	
	public UnitCode(){
		
	}
	
	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public int getCartoon() {
		return cartoon;
	}

	public void setCartoon(int cartoon) {
		this.cartoon = cartoon;
	}

	public int getPallete() {
		return pallete;
	}

	public void setPallete(int pallete) {
		this.pallete = pallete;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.UnitCode: " );
		sb.append( "id=" + id );
		sb.append( ", name=" + name );
		sb.append( ", piece=" + piece );
		sb.append( ", box=" + box );
		sb.append( ", cartoon=" + cartoon );
		sb.append( ", pallete=" + pallete );
		sb.append( ", isActive=" + isActive );
		sb.append( ", isDelete=" + isDelete );
		sb.append( ", createdBy=" + createdBy );
		sb.append( ", createdDate=" + createdDate );
		sb.append( ", updatedBy=" + updatedBy );
		sb.append( ", updatedDate=" + updatedDate );
		return sb.toString();
	}

}
