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

import com.app.wms.hbm.bean.Customer;

/**
 *
 * @gnv solution
 */
@Service("customer2Dao")
@Transactional
public class Customer2Dao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Customer> getCustomers() {
        return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
    }
    
    public void save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }
    
    public  List<Customer> get(String customercode) {
        return sessionFactory.getCurrentSession().createCriteria(Customer.class).add(Restrictions.like("customercode","%"+customercode+"%")).list();
    }
}
