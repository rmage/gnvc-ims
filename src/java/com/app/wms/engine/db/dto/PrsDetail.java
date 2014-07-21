package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrsDetail implements Serializable {

    /**
     * This attribute maps to the column id in the prs_detail table.
     */
    protected int id;

    /**
     * This attribute maps to the column prsnumber in the prs_detail table.
     */
    protected String prsnumber;

    /**
     * This attribute maps to the column productcode in the prs_detail table.
     */
    protected String productcode;

    /**
     * This attribute maps to the column productname in the prs_detail table.
     */
    protected String productname;

    /**
     * This attribute maps to the column qty in the prs_detail table.
     */
    //protected float qty;
    protected BigDecimal qty;

    /**
     * This attribute represents whether the primitive attribute qty is null.
     */
    protected boolean qtyNull = true;

    /**
     * This attribute maps to the column uom_name in the prs_detail table.
     */
    protected String uomName;

    protected Prs prs;
    protected StockInventory stockInventory;
    protected CanvassingDetail canvassingDetail;

    private BigDecimal prsStockOnHand;

    /**
     * Method 'PrsDetail'
     *
     */
    public PrsDetail() {
    }

    public CanvassingDetail getCanvassingDetail() {
        return canvassingDetail;
    }

    public void setCanvassingDetail(CanvassingDetail canvassingDetail) {
        this.canvassingDetail = canvassingDetail;
    }

    public StockInventory getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(StockInventory stockInventory) {
        this.stockInventory = stockInventory;
    }

    public Prs getPrs() {
        return prs;
    }

    public void setPrs(Prs prs) {
        this.prs = prs;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * Method 'getId'
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method 'setId'
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method 'getPrsnumber'
     *
     * @return String
     */
    public String getPrsnumber() {
        return prsnumber;
    }

    /**
     * Method 'setPrsnumber'
     *
     * @param prsnumber
     */
    public void setPrsnumber(String prsnumber) {
        this.prsnumber = prsnumber;
    }

    /**
     * Method 'getProductcode'
     *
     * @return String
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * Method 'setProductcode'
     *
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    /**
     * Method 'getProductname'
     *
     * @return String
     */
    public String getProductname() {
        return productname;
    }

    /**
     * Method 'setProductname'
     *
     * @param productname
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * Method 'getQty'
     *
     * @return float
     */
//	public float getQty()
//	{
//		return qty;
//	}
    /**
     * Method 'setQty'
     *
     * @param qty
     */
//	public void setQty(float qty)
//	{
//		this.qty = qty;
//		this.qtyNull = false;
//	}
    /**
     * Method 'setQtyNull'
     *
     * @param value
     */
    public void setQtyNull(boolean value) {
        this.qtyNull = value;
    }

    /**
     * Method 'isQtyNull'
     *
     * @return boolean
     */
    public boolean isQtyNull() {
        return qtyNull;
    }

    /**
     * Method 'getUomName'
     *
     * @return String
     */
    public String getUomName() {
        return uomName;
    }

    /**
     * Method 'setUomName'
     *
     * @param uomName
     */
    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    /**
     * Method 'equals'
     *
     * @param _other
     * @return boolean
     */
    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }

        if (_other == this) {
            return true;
        }

        if (!(_other instanceof PrsDetail)) {
            return false;
        }

        final PrsDetail _cast = (PrsDetail) _other;
        if (id != _cast.id) {
            return false;
        }

        if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals(_cast.prsnumber)) {
            return false;
        }

        if (productcode == null ? _cast.productcode != productcode : !productcode.equals(_cast.productcode)) {
            return false;
        }

        if (productname == null ? _cast.productname != productname : !productname.equals(_cast.productname)) {
            return false;
        }

//		if (qty != _cast.qty) {
//			return false;
//		}
        if (qtyNull != _cast.qtyNull) {
            return false;
        }

        if (uomName == null ? _cast.uomName != uomName : !uomName.equals(_cast.uomName)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'hashCode'
     *
     * @return int
     */
    public int hashCode() {
        int _hashCode = 0;
        _hashCode = 29 * _hashCode + id;
        if (prsnumber != null) {
            _hashCode = 29 * _hashCode + prsnumber.hashCode();
        }

        if (productcode != null) {
            _hashCode = 29 * _hashCode + productcode.hashCode();
        }

        if (productname != null) {
            _hashCode = 29 * _hashCode + productname.hashCode();
        }

//		_hashCode = 29 * _hashCode + Float.floatToIntBits(qty);
        _hashCode = 29 * _hashCode + (qtyNull ? 1 : 0);
        if (uomName != null) {
            _hashCode = 29 * _hashCode + uomName.hashCode();
        }

        return _hashCode;
    }

    /**
     * Method 'createPk'
     *
     * @return PrsDetailPk
     */
    public PrsDetailPk createPk() {
        return new PrsDetailPk(id);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("com.app.wms.engine.db.dto.PrsDetail: ");
        ret.append("id=" + id);
        ret.append(", prsnumber=" + prsnumber);
        ret.append(", productcode=" + productcode);
        ret.append(", productname=" + productname);
        ret.append(", qty=" + qty);
        ret.append(", uomName=" + uomName);
        ret.append(", prs=" + prs);
        ret.append(", stockInventory=" + stockInventory);
        return ret.toString();
    }

    public BigDecimal getPrsStockOnHand() {
        return prsStockOnHand;
    }

    public void setPrsStockOnHand(BigDecimal prsStockOnHand) {
        this.prsStockOnHand = prsStockOnHand;
    }

}
