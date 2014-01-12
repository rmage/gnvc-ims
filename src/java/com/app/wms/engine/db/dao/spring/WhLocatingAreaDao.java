/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @gnv solution
 */
@Service("whLocatingAreaDao")
@Transactional
public class WhLocatingAreaDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    
}
