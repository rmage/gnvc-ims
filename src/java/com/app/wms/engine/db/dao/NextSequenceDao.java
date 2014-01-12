/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sequence;
import com.app.wms.engine.db.exceptions.DaoException;

/**
 *
 * @author zyrex
 */
public interface NextSequenceDao {
    public Sequence getNextSequenceVal() throws DaoException;
}
