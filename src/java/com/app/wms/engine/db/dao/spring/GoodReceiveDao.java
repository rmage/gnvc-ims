/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.hbm.bean.Goodreceive;
import com.app.wms.hbm.bean.GoodreceiveDetail;
import com.app.wms.hbm.bean.Vgrdetailproductcategory;

/**
 *
 * @gnv solution
 */
@Service("goodReceiveDao")
@Transactional
public class GoodReceiveDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Goodreceive> getGR() {
        return sessionFactory.getCurrentSession().createCriteria(Goodreceive.class)
                .addOrder(Order.desc("id"))
                .list();
    }
    
    public Integer size() {
        Integer size = (Integer)sessionFactory.getCurrentSession().createCriteria(Goodreceive.class)
                .setProjection(Projections.rowCount()).uniqueResult();
        return size+1;
    }
    
    public List<Goodreceive> getGRPopup(String corpid, String whCode) {
        List<Object[]> objects = sessionFactory.getCurrentSession().createSQLQuery("SELECT * from goodreceive "
                + "where grnumber not in (select gr_number from putaway) "
                + "and corpid = :corpid and wh_code = :whCode "
                + "order by id desc")
                .setString("corpid", corpid)
                .setString("whCode", whCode)
                .list();
        
        List<Goodreceive> goodreceives = new ArrayList<Goodreceive>();
        for (Object[] object : objects) {
            Goodreceive goodreceive = new Goodreceive();
            
            goodreceive.setId(Integer.parseInt(""+object[0]));
            goodreceive.setGrnumber(""+object[1]);
            goodreceive.setPonumber(""+object[2]);
            goodreceive.setReceiveddate((Date)object[3]);
            goodreceive.setCreatedby(""+object[4]);
            goodreceive.setCorpid(""+object[5]);
            goodreceive.setLotid(""+object[6]);
            goodreceive.setWhCode(""+object[7]);
            
            goodreceives.add(goodreceive);
        }
        
        
        return goodreceives;
    }
    
    public List<Goodreceive> getGR(String grcode, String grdate) {
         Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Goodreceive.class)
                .addOrder(Order.desc("id"));

         if(grcode != null) {
             criteria.add(Restrictions.like("grnumber", "%"+grcode+"%"));
         }
         if(grdate != null) {
             try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(grdate);
                criteria.add(Restrictions.eq("receiveddate",date));
            } catch (Exception e) {
                e.printStackTrace();
            }
         }
         
        return criteria.list();
    }
    
//    public List<Goodreceive> getGR() {
//        List<Goodreceive> goodreceives = new ArrayList<Goodreceive>();
//        goodreceives.addAll(sessionFactory.getCurrentSession().createCriteria(Goodreceive.class)                
//                .list());
//        return goodreceives;
//    }

    public List<Object[]> getExpiredProducts(String whcode) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT dbo.goodreceive_detail.id, dbo.goodreceive.wh_code, dbo.goodreceive.corpid, "
                + "dbo.goodreceive.createdby, dbo.goodreceive_detail.grnumber, dbo.goodreceive_detail.productcode, dbo.goodreceive_detail.expirydate, "
                + "dbo.goodreceive_detail.lotid, product.product_name FROM dbo.goodreceive "
                + "INNER JOIN dbo.goodreceive_detail ON ( dbo.goodreceive.grnumber = dbo.goodreceive_detail.grnumber ) "
                + " INNER JOIN dbo.product ON ( dbo.product.product_code = dbo.goodreceive_detail.productcode ) "
                + "where goodreceive.wh_code = :wh_code and expirydate < :expirydate and expirydate is not null")
                .setString("wh_code", whcode)
                .setDate("expirydate", calendar.getTime());

        return query.list();
    }

    public List<Vgrdetailproductcategory> getGRDetail(String grnumber) {
        List<Object[]> objects = sessionFactory.getCurrentSession()
                .createSQLQuery(" SELECT DISTINCT( vgrdetailproductcategory.id ), "
                + "vgrdetailproductcategory.grnumber, vgrdetailproductcategory.productcode, po_detail.qty AS qtyreal, "
                + "vgrdetailproductcategory.status, vgrdetailproductcategory.expirydate, vgrdetailproductcategory.remark, "
                + "vgrdetailproductcategory.lotid, vgrdetailproductcategory.product_name, vgrdetailproductcategory.category_name, "
                + "vgrdetailproductcategory.qtygood, vgrdetailproductcategory.qtydmg, dbo.po_detail.producttype "
//                + "(goodreceive_detail.qtyreal - po_detail.qty) as qtyplus "
                + "FROM dbo.vgrdetailproductcategory "
                + "INNER JOIN dbo.goodreceive ON ( dbo.vgrdetailproductcategory.grnumber = dbo.goodreceive.grnumber ) "
                + "INNER JOIN dbo.po ON ( dbo.goodreceive.ponumber = dbo.po.ponumber ) "
                + "INNER JOIN dbo.po_detail ON ( dbo.po.ponumber = dbo.po_detail.ponumber ) "
                + "INNER JOIN dbo.goodreceive_detail ON ( dbo.goodreceive_detail.grnumber = dbo.goodreceive.grnumber ) "
                + "WHERE po_detail.productcode = vgrdetailproductcategory.productcode "
                + "AND dbo.goodreceive_detail.producttype = dbo.vgrdetailproductcategory.producttype "
                + "AND dbo.goodreceive_detail.producttype = dbo.po_detail.producttype "
                + "AND dbo.goodreceive_detail.grnumber = dbo.goodreceive.grnumber "
                + "AND (vgrdetailproductcategory.qtygood is not null or vgrdetailproductcategory.qtydmg is not null) "
                + "and  vgrdetailproductcategory.grnumber = :grnumber " )
                .setString("grnumber", grnumber)
                .list();

        List<Vgrdetailproductcategory> vgrdetailproductcategorys = new ArrayList<Vgrdetailproductcategory>();

        for (Object[] object : objects) {
            Vgrdetailproductcategory vgrdetailproductcategory = new Vgrdetailproductcategory();

            vgrdetailproductcategory.setProductcode(object[2] + "");
            vgrdetailproductcategory.setProductName(object[8] + "");
            vgrdetailproductcategory.setQtygood(object[10] == null ? 0 : (Integer) object[10]);
            vgrdetailproductcategory.setQtydmg(object[11] == null ? 0 : (Integer) object[11]);
            vgrdetailproductcategory.setQtyreal((Integer) object[3]);
            vgrdetailproductcategory.setProductType(object[12] + "");
            
            if(vgrdetailproductcategory.getQtygood() != 0)
                vgrdetailproductcategory.setQtyplus(vgrdetailproductcategory.getQtygood() - vgrdetailproductcategory.getQtyreal());
            else
                vgrdetailproductcategory.setQtyplus((Integer)0);
            
            if (vgrdetailproductcategory.getQtygood() == 0 && vgrdetailproductcategory.getQtydmg() == 0) {
                continue;
            }
            if (vgrdetailproductcategory.getQtygood() == null && vgrdetailproductcategory.getQtydmg() == null) {
                continue;
            }
            
            vgrdetailproductcategorys.add(vgrdetailproductcategory);
        }
        return vgrdetailproductcategorys;
    }

    public void save(Goodreceive goodreceive, List<GoodreceiveDetail> goodreceiveDetails) {
    	System.out.println(">> inserting1 " + goodreceive);
    	sessionFactory.getCurrentSession().save(goodreceive);
        for (GoodreceiveDetail goodreceiveDetail : goodreceiveDetails) {
            System.out.println(">> inserting2 " + goodreceiveDetail);
            sessionFactory.getCurrentSession().save(goodreceiveDetail);
        }
    }
}
