package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dao.StockDao;
import com.app.wms.engine.db.dto.Stock;
import com.app.wms.engine.db.dto.StockPk;
import com.app.wms.engine.db.dvo.StockView;
import com.app.wms.engine.db.exceptions.StockDaoException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface StockDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return StockPk
     */
    public StockPk insert(Stock dto);

    /**
     * Updates a single row in the STOCK table.
     */
    public void update(StockPk pk, Stock dto) throws StockDaoException;

    /**
     * Deletes a single row in the STOCK table.
     */
    public void delete(StockPk pk) throws StockDaoException;

    /**
     * Returns all rows from the STOCK table that match the criteria 'STOCK_CODE = :stockCode'.
     */
    public Stock findByPrimaryKey(String stockCode) throws StockDaoException;

//    /**
//     * Returns all rows from the STOCK table that match the criteria ''.
//     */
//    public List<Stock> findAll() throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'BG_CODE = :bgCode'.
//     */
//    public List<Stock> findByBg(String bgCode) throws StockDaoException;
//

    /**
     * Returns all rows from the STOCK table that match the criteria 'STOCK_CODE = :stockCode'.
     */
    public List<Stock> findByStockCode(String stockCode) throws StockDaoException;

//    /**
//     * Returns all rows from the STOCK table that match the criteria 'BG_CODE = :bgCode'.
//     */
//    public List<Stock> findWhereBgCodeEquals(String bgCode) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'STOCK_CODE = :stockCode'.
//     */
//    public List<Stock> findWhereStockCodeEquals(String stockCode) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'START_DATE = :startDate'.
//     */
//    public List<Stock> findWhereStartDateEquals(Date startDate) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'END_DATE = :endDate'.
//     */
//    public List<Stock> findWhereEndDateEquals(Date endDate) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'IS_CURRENT = :isCurrent'.
//     */
//    public List<Stock> findWhereIsCurrentEquals(String isCurrent) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'CREATED_BY = :createdBy'.
//     */
//    public List<Stock> findWhereCreatedByEquals(BigDecimal createdBy) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'CREATED_DATE = :createdDate'.
//     */
//    public List<Stock> findWhereCreatedDateEquals(Date createdDate) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'UPDATED_BY = :updatedBy'.
//     */
//    public List<Stock> findWhereUpdatedByEquals(BigDecimal updatedBy) throws StockDaoException;
//
//    /**
//     * Returns all rows from the STOCK table that match the criteria 'UPDATED_DATE = :updatedDate'.
//     */
//    public List<Stock> findWhereUpdatedDateEquals(Date updatedDate) throws StockDaoException;

    /**
     * Returns the rows from the STOCK table that matches the specified primary-key value.
     */
    public Stock findByPrimaryKey(StockPk pk) throws StockDaoException;

    /**
     * Returns all rows from the STOCK table that match the specified user-id value.
     */
    public List<Stock> findByUser(BigDecimal userId) throws StockDaoException;

    /**
     * Returns all rows from the STOCK table that is_current true and match the specified user-id value.
     */
    public List<Stock> findByUserIsCurrent(BigDecimal userId) throws StockDaoException;

    /**
     * Returns the rows from the STOCK table that matches the specified BG_CODE value.
     */
    public Stock findCurrentStock(String bgCode) throws StockDaoException;

    /**
     * Returns the rows from the STOCK and STOCK_RANGE table that matches the specified BG_CODE value.
     */
    public Stock findCurrentStockRange(String bgCode) throws StockDaoException;

    public List<StockView> findViewByUserIsCurrent(BigDecimal userId) throws StockDaoException;

    
}
