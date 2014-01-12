package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.StockInventory;

public class PRSCanvassingSelectedJsonMap implements ParameterizedRowMapper <PrsDetail> {
	
	public PrsDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		PrsDetail pd = new PrsDetail();
		Prs prs = new Prs();
		CanvassingDetail cd = new CanvassingDetail();
		
		/*
		 * 
		 *  sb.append(" select p.id, p.prsnumber, pd.productcode, pd.productname, pd.qty, pd.uom_name , " 
	        		    +" cd.priceunit, cd.warranty, cd.termpayment, cd.termdelivery, cd.discount, cd.pph, cd.ppn, cd.supplier_code, cd.is_selected, " 
	        			+" p.prsdate, p.requestdate, p.deliverydate, p.remarks, p.department_name, p.createdby, p.poreferensi " 
	        			+" from prs p inner join canvassing_detail cd on p.prsnumber = cd.prsnumber "
	        			+" inner join prs_detail pd on p.prsnumber = pd.prsnumber where pd.productcode = cd.productcode "
	        			+" and cd.priceunit > 0 and p.prsnumber= '"+prsnumber+"' ");
		 */
		
		prs.setId(rs.getInt(1));
		prs.setPrsnumber(rs.getString(2));
		pd.setProductcode(rs.getString(3));
		pd.setProductname(rs.getString(4));
		pd.setQty(rs.getBigDecimal(5));
		pd.setUomName(rs.getString(6));
		cd.setPriceunit(rs.getBigDecimal(7));
		cd.setWarranty(rs.getDate(8));
		cd.setTermpayment(rs.getString(9));
		cd.setTermdelivery(rs.getString(10));
		cd.setDiscount(rs.getBigDecimal(11));
		cd.setPph(rs.getBigDecimal(12));
		cd.setPpn(rs.getBigDecimal(13));
		cd.setSupplierCode(rs.getString(14));
		cd.setIsSelected(rs.getString(15));
		pd.setCanvassingDetail(cd);
		prs.setPrsdate(rs.getDate(16));
		prs.setRequestdate(rs.getDate(17));
		prs.setDeliverydate(rs.getDate(18));
		prs.setRemarks(rs.getString(19));
		prs.setDepartmentName(rs.getString(20));
		prs.setCreatedby(rs.getString(21));
		
		return pd;
	}

}
