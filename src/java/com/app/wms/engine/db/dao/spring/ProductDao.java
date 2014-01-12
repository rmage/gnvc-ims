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

import com.app.wms.hbm.bean.Product;
import com.app.wms.hbm.bean.Vproductsku;

/**
 *
 * @gnv solution
 */
@Service("productDao")
@Transactional
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> getProducts() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }
    
    public List<Vproductsku> getProductsWithSKU(String corpId, String whCode) {
        return sessionFactory.getCurrentSession().createCriteria(Vproductsku.class)
                .add(Restrictions.eq("corpId", corpId))
                .add(Restrictions.eq("whCode", whCode))
                .list();
    }
    
    public List<Vproductsku> getProductsWithSKU() {
        return sessionFactory.getCurrentSession().createCriteria(Vproductsku.class)
                .list();
    }

    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }
    
    public Product getProduct(String productCode) {
        return (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("productCode", productCode)).list().get(0);
    }

    public List<Product> get(String productname) {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.like("productName", "%" + productname + "%")).list();
    }
    
    public String getSKU(String productCode) {
        Vproductsku vproductsku = (Vproductsku)sessionFactory.getCurrentSession().createCriteria(Vproductsku.class)
                .add(Restrictions.eq("productCode",productCode)).list().get(0);
        if(vproductsku != null)
            return vproductsku.getSku();
        return "";
    }
}
