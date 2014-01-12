/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.hbm.bean.ReturnMaster;

/**
 *
 * @gnv solution
 */
@Service("returnDao")
@Transactional
public class ReturnDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<ReturnMaster> getReturnMaster() {
        return sessionFactory.getCurrentSession().createCriteria(ReturnMaster.class)
                .addOrder(Order.desc("returnDate"))
                .list();
    }
    
    public void save(ReturnMaster returnMaster) {
        sessionFactory.getCurrentSession().save(returnMaster);        
    }
    
}
