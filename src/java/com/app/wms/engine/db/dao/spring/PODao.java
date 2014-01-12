/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.hbm.bean.Po;
import com.app.wms.hbm.bean.PoDetail;
import com.app.wms.hbm.bean.Vgrdetailproduct;

/**
 *
 * @gnv solution
 */
@Service("poDao")
@Transactional
public class PODao {
    @Autowired
    private SessionFactory sessionFactory;
    /*
    public List<Po> getPos(String corpid, String whCode) {
        return sessionFactory.getCurrentSession().createCriteria(Po.class)
                .add(Restrictions.eq("corpid", corpid))
                .add(Restrictions.eq("whCode", whCode))
                .addOrder(Order.desc("deliverydate"))
                .list();
    }
    */
    public List<Po> getPos() {
        return sessionFactory.getCurrentSession().createCriteria(Po.class)
                .addOrder(Order.desc("deliverydate"))
                .list();
    }
    /*
    public List<Po> getPopupPos(String corpid, String whCode) {
        
        List<Object[]> objects = sessionFactory.getCurrentSession().createSQLQuery("SELECT * from po "
                + "where ponumber not in (select ponumber from goodreceive) and corpid = :corpid and wh_code = :whCode "
                + "order by deliverydate desc")
                .setString("corpid", corpid)
                .setString("whCode", whCode)
                .list();
        
        List<Po> pos = new ArrayList<Po>();
        for (Object[] object : objects) {
            Po po = new Po();
            
            po.setId(new Long(""+object[0]));
            po.setPonumber((""+object[1]));
            po.setDeliverydate((Date)(object[2]));
            po.setPoreferensi((""+object[3]));
            po.setCreatedby((""+object[4]));
            po.setCorpid((""+object[5]));
            po.setWhCode((""+object[6]));
            
            pos.add(po);
        }
        
        
        return pos;
    }
    */
    
    public List<Po> getPopupPos() {
    	String sql = "select * from po where ponumber not in (select ponumber from goodreceive) order by deliverydate desc";
        
        List<Object[]> objects = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
        
        System.out.println("objects ="+objects);
        
        List<Po> pos = new ArrayList<Po>();
        for (Object[] object : objects) {
            Po po = new Po();
            
            po.setId(new Long(""+object[0]));
            po.setPonumber((""+object[1]));
            po.setDeliverydate((Date)(object[2]));
            po.setPoreferensi((""+object[3]));
            po.setCreatedby((""+object[4]));
            po.setCorpid((""+object[5]));
            po.setWhCode((""+object[6]));
           // po.setDepartmentName((""+object[7]));
           // po.setSupplierName((""+object[8]));
            
            pos.add(po);
        }
        
        
        return pos;
    }
    
    
    public void save(Po po, List<PoDetail> poDetails) {
        sessionFactory.getCurrentSession().save(po);
        for (PoDetail poDetail : poDetails) {
            sessionFactory.getCurrentSession().save(poDetail);
        }
    }
    /*
    public  List<Po> get(String poNumber, String estimationDeliveryDate, String corpid, String whCode) {
        Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Po.class);
        
        if(poNumber != null)
            criteria.add(Restrictions.like("ponumber","%"+poNumber+"%"));
        if(estimationDeliveryDate != null)
        {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(estimationDeliveryDate);
                criteria.add(Restrictions.eq("deliverydate",date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        criteria.add(Restrictions.eq("corpid", corpid)).add(Restrictions.eq("whCode", whCode));
        
        return criteria.list();
    }
    */
    public  List<Po> get(String poNumber, String estimationDeliveryDate) {
        Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Po.class);
        
        if(poNumber != null)
            criteria.add(Restrictions.like("ponumber","%"+poNumber+"%"));
        if(estimationDeliveryDate != null)
        {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(estimationDeliveryDate);
                criteria.add(Restrictions.eq("deliverydate",date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
       // criteria.add(Restrictions.eq("corpid", corpid)).add(Restrictions.eq("whCode", whCode));
        
        return criteria.list();
    }
    /*
    public  List<Vgrdetailproduct> getDetail(String ponumber, String corpId, String whCode ) {
        return sessionFactory.getCurrentSession().createCriteria(Vgrdetailproduct.class)
                .add(Restrictions.eq("ponumber",""+ponumber+""))
                .add(Restrictions.eq("corpId",""+corpId+""))
                .add(Restrictions.eq("whCode",""+whCode+""))
                .list();
    }
    */
    public  List<Vgrdetailproduct> getDetail(String ponumber) {
        return sessionFactory.getCurrentSession().createCriteria(Vgrdetailproduct.class)
                .add(Restrictions.eq("ponumber",""+ponumber+""))
                .list();
    }
}
