package com.app.wms.engine.db.exceptions;

public class StockWhDaoException extends DaoException {

    /**
     * Method 'StockWhDaoException'
     *
     * @param message
     */
    public StockWhDaoException(String message) {
        super(message);
    }

    /**
     * Method 'StockWhDaoException'
     *
     * @param message
     * @param cause
     */
    public StockWhDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
