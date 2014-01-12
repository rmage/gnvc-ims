/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.hbm.bean.Pocsv;

/**
 *
 * @gnv solution
 */
@Service("poCSVDao")
@Transactional
public class PoCSVDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(List<Pocsv> pocsvs) {        
        for (Pocsv pocsv : pocsvs) {
            sessionFactory.getCurrentSession().save(pocsv);
        }
    }
    
    public List<Pocsv> get(String hash) {        
        return sessionFactory.getCurrentSession()
                .createCriteria(Pocsv.class).add(Restrictions.eq("hash",hash)).list();
    }
    
}
