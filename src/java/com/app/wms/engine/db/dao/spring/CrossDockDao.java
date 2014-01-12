/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.util.Calendar;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @gnv solution
 */
/*@Service("crossdockDao")
@Transactional
public class CrossDockDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Crossdock> getCrossdocks() {
        return sessionFactory.getCurrentSession().createCriteria(Crossdock.class).list();
    }
    
    public List<Crossdock> getExpiredCrossdocks(String whcode) {    
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -3);
        return sessionFactory.getCurrentSession()
                .createCriteria(Crossdock.class)
                .add(Restrictions.le("receivedate",calendar.getTime()))
                .list();          
    }
    
    public void save(Crossdock crossdock, List<CrossdockDetail> crossdockDetails) {
        sessionFactory.getCurrentSession().save(crossdock);
        for (CrossdockDetail crossdockDetail : crossdockDetails) {
            sessionFactory.getCurrentSession().save(crossdockDetail);
        }
    }
    
}*/
