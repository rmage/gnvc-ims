/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dvo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Programmer
 */
public class RepairStockMovePeriphMap implements ParameterizedRowMapper<RepairStockMovePeriphMap> {

    protected String periphCode;
    protected String periphName;
    protected String description;
    protected String itemCode;

    public RepairStockMovePeriphMap() {
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getPeriphCode() {
	return periphCode;
    }

    public void setPeriphCode(String periphCode) {
	this.periphCode = periphCode;
    }

    public String getPeriphName() {
	return periphName;
    }

    public void setPeriphName(String periphName) {
	this.periphName = periphName;
    }

    public String getItemCode() {
	return itemCode;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    public RepairStockMovePeriphMap mapRow(ResultSet rs, int row) throws SQLException {
	RepairStockMovePeriphMap dto = new RepairStockMovePeriphMap();
	dto.setPeriphCode(rs.getString("PERIPH_CODE"));
	dto.setPeriphName(rs.getString("PERIPH_NAME"));
	dto.setDescription(rs.getString("DESCRIPTION"));
	dto.setItemCode(rs.getString("ITEM_CODE"));
	return dto;
    }
}
