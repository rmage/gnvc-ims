/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.hbm.bean.Vlocationproduct;
import com.app.wms.hbm.bean.WhLocatingArea;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @gnv solution
 */
@Service("vlocationproductDao")
@Transactional
public class VlocationproductDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Vlocationproduct> getVlocationproducts() {
        return sessionFactory.getCurrentSession().createCriteria(Vlocationproduct.class).list();
    }

    public List<Vlocationproduct> getVlocationproducts(String productId, Boolean karantina, String locationArea, String corpId, String whCode) {
        
        
        if(karantina == null) {
            return sessionFactory.getCurrentSession().createCriteria(Vlocationproduct.class)
                    .add(Restrictions.eq("productCode", productId))
                    .addOrder(Order.asc("balance"))
                    .addOrder(Order.asc("id"))
                    .setMaxResults(3)
                    .list();
        }
        
//        List<WhLocatingArea> whLocatingAreasGood = sessionFactory.getCurrentSession()
//                                .createCriteria(WhLocatingArea.class)
//                                .add(Restrictions.eq("locatingCondition", "GOOD")).list();
//        
//        List<String> areaGood = new ArrayList<String>();
//        for (WhLocatingArea whLocatingArea : whLocatingAreasGood) {
//            areaGood.add(whLocatingArea.getLocatingArea());
//        }
        
        List<WhLocatingArea> whLocatingAreasDamaged = sessionFactory.getCurrentSession()
                                .createCriteria(WhLocatingArea.class)
                                .add(Restrictions.eq("locatingCondition", "DAMAGE")).list();
               
        List<String> damages = sessionFactory.getCurrentSession()
        	.createSQLQuery("select locating_area from wh_locating_area where locating_condition = 'DAMAGE'")
        	.list();
        
        
        System.out.println(" >>>>  size >>>>>> "+damages.size());
        
//        List<String> areaDamaged = new ArrayList<String>();
//        for (WhLocatingArea whLocatingArea : whLocatingAreasDamaged) {
//            areaDamaged.add(whLocatingArea.getLocatingArea());
//        }
        
        if (karantina) { // damaged
            Query criteria = sessionFactory.getCurrentSession()
                    .createQuery(
                        "from Vlocationproduct v where v.balance < v.maxProduct "
                    + "and v.locationArea in (:locationArea) "
                    + "and v.productCode = :productCode "
                    + "and v.corpId = :corpId "
                    + "and v.whCode = :whCode "
                    + "order by v.balance, v.id"
                    );
            
            
            if(!damages.isEmpty()) {
                criteria.setParameterList("locationArea", damages) ; // lokasi barang quarantine
            } 
            
            // add product id
            criteria.setString("productCode", productId);
            
            // add whCode
            criteria.setString("whCode", whCode);
            
            // add corpId
            criteria.setString("corpId", corpId);                                    
            
//            for(String a : areaDamaged) {
//            	System.out.println(" se>>>>>> "+a);
//            }
            
            return criteria
                    .setMaxResults(10)
                    .list();
            
        } else { // good 
            
             Query criteria = sessionFactory.getCurrentSession()
                    .createQuery(
                        "from Vlocationproduct v where v.balance < v.maxProduct "
                    + "and v.productCode = :productCode "
                    + "and (locationArea = :locationArea or locationArea = 'Quarantine Good')"
                    + "and v.corpId = :corpId "
                    + "and v.whCode = :whCode "
                    + "order by v.balance, v.id"
                    );
             
             // add product id
            criteria.setString("productCode", productId);
            
            // add whCode
            criteria.setString("whCode", whCode);
            
            // add corpId
            criteria.setString("corpId", corpId);  
            
            // add locationArea
            criteria.setString("locationArea", locationArea);  
            
            return criteria
//                    .add(Restrictions.eq("productCode", productId))
//                    .add(Restrictions.or(Restrictions.eq("locationArea", locationArea), Restrictions.eq("locationArea", "Quarantine Good")))
//                    .add(Restrictions.eq("corpId", corpId))
//                    .add(Restrictions.eq("whCode", whCode))
//                    .add(Restrictions.like("locationArea", "General", MatchMode.ANYWHERE)) // lokasi barang General
//                    .addOrder(Order.asc("balance"))
//                    .addOrder(Order.asc("id"))
                    .setMaxResults(10)
                    .list();
        }
    }
}
