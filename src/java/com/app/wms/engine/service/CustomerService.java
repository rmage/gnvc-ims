/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.service;

import com.app.wms.hbm.bean.Customer;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @gnv solution
 */
@Service("customerService")
@Transactional
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Customer> getCustomers() {
        return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
    }
    
    public List<Customer> getCustomers(int from, int to) {
        return sessionFactory.getCurrentSession().createCriteria(Customer.class).setFirstResult(from).setMaxResults(to).list();
    }
}
