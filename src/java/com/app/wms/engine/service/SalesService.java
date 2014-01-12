/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.service;

/**
 *
 * @author programmer
 */
public interface SalesService {
    public void confirmSalesStock(String documentNo, int userId) throws Exception;
}
