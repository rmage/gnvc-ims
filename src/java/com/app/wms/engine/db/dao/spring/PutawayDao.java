/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.wms.hbm.bean.Putaway;
import com.app.wms.hbm.bean.PutawayDetail;
import com.app.wms.hbm.bean.Vputawaydetailproduct;

/**
 *
 * @gnv solution
 */
@Service("putawayDao")
@Transactional
public class PutawayDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Putaway putaway, List<PutawayDetail> putawayDetails) {
        sessionFactory.getCurrentSession().save(putaway);
        for (PutawayDetail putawayDetail : putawayDetails) {            
            sessionFactory.getCurrentSession().save(putawayDetail);
        }
    }
    
    public void update(Putaway putaway, List<PutawayDetail> putawayDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.setCacheMode(CacheMode.IGNORE);
        
        Putaway p = (Putaway)session.createCriteria(Putaway.class)
                .add(Restrictions.eq("putawayId", putaway.getPutawayId()))
                .uniqueResult();
        p.setStatusApp(putaway.getStatusApp());                        
        session.update(p);                
        
        for (PutawayDetail putawayDetail : putawayDetails) {
            PutawayDetail hasilQuery = (PutawayDetail)session.createCriteria(PutawayDetail.class)
                .add(Restrictions.eq("putawayId", putawayDetail.getPutawayId()))
                .add(Restrictions.eq("productCode", putawayDetail.getProductCode()))
                .add(Restrictions.eq("balance", 0))
//                .add(Restrictions.eq("productType", putawayDetail.getProductType()))
                .add(Restrictions.eq("locationCode", putawayDetail.getLocationCode().split("\\|")[0])) // lokasi awal | lokasi akhir
                .list().get(0);
            
            System.out.println(">>>>>>>> ");
            System.out.println(putawayDetail.getPutawayId());
            System.out.println(putawayDetail);
            System.out.println(putawayDetail.getProductCode());
            System.out.println(putawayDetail.getLocationCode().split("\\|")[0]);
            System.out.println(">>>>>>>> ");

//            Vbalanceputawaydetail vbalanceputawaydetail = (Vbalanceputawaydetail) session.createCriteria(Vbalanceputawaydetail.class)
//                .add(Restrictions.eq("locationCode", putawayDetail.getLocationCode().split("\\|")[1]))                 
//                .uniqueResult();
            
            String query = " WITH MAXPUTAWAY AS ( SELECT location_code, wh_code, corp_id , MAX(id) AS id "
                    + "FROM dbo.putaway_detail a INNER JOIN putaway b ON a.putaway_id = b.putaway_id "
                    + "WHERE b.status_app='SUCCESSFULL'  and a.balance != 0 GROUP BY location_code , corp_id, wh_code ) "
                    + "SELECT balance FROM dbo.putaway_detail "
                    + "INNER JOIN MAXPUTAWAY ON dbo.putaway_detail.id = MAXPUTAWAY.id "
                    + "and putaway_detail.location_code = :locationCode "
                    + "and putaway_detail.wh_code = :wh_code "
                    + "and putaway_detail.corp_id = :corp_id ";                    
            
            Integer i = (Integer)session.createSQLQuery(query)
                            .setString("locationCode", putawayDetail.getLocationCode().split("\\|")[1])
                            .setString("wh_code", putawayDetail.getWhCode())
                            .setString("corp_id", putawayDetail.getCorpId())
                            .uniqueResult();
            
            System.out.println("------------");
            System.out.println(putawayDetail.getLocationCode().split("\\|")[1]);
            System.out.println(putawayDetail.getLocationCode());
            System.out.println(hasilQuery);
            System.out.println(i);
            System.out.println("coba menambahkan ----> "+i+" + "+hasilQuery.getQtyPut());
            System.out.println("------------");
            
            // update location
            hasilQuery.setLocationCode(putawayDetail.getLocationCode().split("\\|")[1]);
            if(i != null) // update balance
                hasilQuery.setBalance(i + hasilQuery.getQtyPut());
            else
                hasilQuery.setBalance(hasilQuery.getQtyPut());
            session.update(hasilQuery);    
        }
    }
    
//    public List<Putaway> getPutaways() {
//        return sessionFactory.getCurrentSession().createCriteria(Putaway.class).list();
//    }
    
    public List<Putaway> getPutaways(String corpId, String whCode) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT distinct(putaway.putaway_id), putaway.* FROM dbo.putaway INNER JOIN dbo.putaway_detail ON ( dbo.putaway.putaway_id = dbo.putaway_detail.putaway_id ) "
                + "where putaway_detail.corp_id = :corpId and putaway_detail.wh_code = :whCode  ")    
                .addEntity(Putaway.class)
                .setString("corpId", corpId)
                .setString("whCode", whCode)      
                .list();
    }
    
    public List<Putaway> getPutaways(String corpId, String whCode , String putawaycode, String grcode, String condition) {
        String sql = "SELECT distinct(putaway.putaway_id), putaway.* FROM dbo.putaway INNER JOIN dbo.putaway_detail ON ( dbo.putaway.putaway_id = dbo.putaway_detail.putaway_id ) "
                + "where putaway_detail.corp_id = :corpId and putaway_detail.wh_code = :whCode  ";
        
        if(putawaycode != null) {
            sql= sql + " and putaway.putaway_id like :putawaycode ";
        }
        if(grcode != null) {
            sql= sql + " and gr_number like :grcode ";
        }
        if(condition != null && !condition.equals("-")) {
            sql= sql + " and status_app like :condition ";
        }
        
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery(sql)    
                .addEntity(Putaway.class)
                .setString("corpId", corpId)
                .setString("whCode", whCode);
        
        if(putawaycode != null) {
            query.setString("putawaycode", "%"+putawaycode+"%");
        }
        if(grcode != null) {
            query.setString("grcode", "%"+grcode+"%");
        }
        if(condition != null && !condition.equals("-")) {
            query.setString("condition", "%"+condition+"%");
        }
        
        
        return  query.list();
    }
    
    public Integer size(String corpid, String whCode) {
        Integer size = (Integer)sessionFactory.getCurrentSession()
                .createSQLQuery("SELECT count (distinct(putaway.putaway_id)) FROM dbo.putaway INNER JOIN dbo.putaway_detail ON ( dbo.putaway.putaway_id = dbo.putaway_detail.putaway_id ) "
                + "where putaway_detail.corp_id = :corpId and putaway_detail.wh_code = :whCode ")                    
                .setString("corpId", corpid)
                .setString("whCode", whCode) .uniqueResult();
        return size+1;
    }
    
    public Putaway getPutaway(String putawayId) {
        return (Putaway) sessionFactory.getCurrentSession().createCriteria(Putaway.class)
                .add(Restrictions.eq("putawayId", putawayId))
                .add(Restrictions.eq("statusApp", "PENDING"))
                .uniqueResult();
    }
    
    public List<Putaway> getPendingPutaways() {
        return sessionFactory.getCurrentSession().createCriteria(Putaway.class)
                .add(Restrictions.eq("statusApp", "PENDING"))                
                .list();
    }
    
    /* Source before 
    public List<Vputawaydetailproduct> getPutawayDetails(String putawayId) {
        List<Object[]> objects = sessionFactory.getCurrentSession()
                .createSQLQuery(" SELECT dbo.putaway_detail.id, dbo.product.product_name, "
                + "dbo.product_category.category_name, dbo.product.product_code, dbo.putaway_detail.putaway_id, "
                + "dbo.putaway_detail.received_date, dbo.putaway_detail.user_id, dbo.putaway_detail.corp_id, "
                + "dbo.putaway_detail.qty_put, dbo.putaway_detail.location_code, dbo.putaway_detail.wh_code, "
                + "dbo.putaway_detail.product_type, dbo.wh_location.locating_area FROM dbo.putaway_detail "
                + "INNER JOIN dbo.product ON ( dbo.putaway_detail.product_id = dbo.product.product_id ) "
                + "LEFT JOIN dbo.product_category ON ( dbo.product.product_category = dbo.product_category.category_name ) "
                + "inner join dbo.wh_location on (dbo.wh_location.location_code =  dbo.putaway_detail.location_code) "
                + "where putaway_id = :putaway_id")
                .setString("putaway_id", putawayId)                
                .list();
        List<Vputawaydetailproduct> vputawaydetailproducts = new ArrayList<Vputawaydetailproduct>();
        for (Object[] object : objects) {
            Vputawaydetailproduct vputawaydetailproduct = new Vputawaydetailproduct();
            
            vputawaydetailproduct.setId(Integer.parseInt(""+object[0]));
            vputawaydetailproduct.setProductName(""+object[1]);
            vputawaydetailproduct.setCategoryName(""+(object[2]==null?"":object[2]));
            vputawaydetailproduct.setProductCode(""+object[3]);
            vputawaydetailproduct.setPutawayId(""+object[4]);
            vputawaydetailproduct.setReceivedDate((Date)object[5]);
            vputawaydetailproduct.setUserId(""+object[6]);
            vputawaydetailproduct.setCorpId(""+object[7]);
            vputawaydetailproduct.setQtyOrder(Integer.parseInt(""+object[8]));
            vputawaydetailproduct.setLocationCode(""+object[9]);
            vputawaydetailproduct.setWhCode(""+object[10]);
            vputawaydetailproduct.setProductType(""+object[12]);
            
            vputawaydetailproducts.add(vputawaydetailproduct);
        }
        
        return vputawaydetailproducts;
    }
    */
    
    public List<Vputawaydetailproduct> getPutawayDetails(String putawayId, String corpId, String whCode) {
        List<Object[]> objects = sessionFactory.getCurrentSession()
                .createSQLQuery(" SELECT dbo.putaway_detail.id, dbo.product.product_name, "
                + "dbo.product_category.category_name, dbo.product.product_code, dbo.putaway_detail.putaway_id, "
                + "dbo.putaway_detail.received_date, dbo.putaway_detail.user_id, dbo.putaway_detail.corp_id, "
                + "dbo.putaway_detail.qty_put, dbo.putaway_detail.location_code, dbo.putaway_detail.wh_code, "
                + "dbo.putaway_detail.product_type, dbo.wh_location.locating_area FROM dbo.putaway_detail "
                + "INNER JOIN dbo.product ON ( dbo.putaway_detail.product_id = dbo.product.product_id ) "
                + "LEFT JOIN dbo.product_category ON ( dbo.product.product_category = dbo.product_category.category_name ) "
                + "inner join dbo.wh_location on (dbo.wh_location.location_code =  dbo.putaway_detail.location_code) "
                + "where putaway_id = :putaway_id and dbo.wh_location.corp_id = :corp_id and dbo.wh_location.wh_code= :wh_code")
                .setString("putaway_id", putawayId) 
                .setString("corp_id", corpId)
                .setString("wh_code", whCode)
                .list();
        List<Vputawaydetailproduct> vputawaydetailproducts = new ArrayList<Vputawaydetailproduct>();
        for (Object[] object : objects) {
            Vputawaydetailproduct vputawaydetailproduct = new Vputawaydetailproduct();
            
            vputawaydetailproduct.setId(Integer.parseInt(""+object[0]));
            vputawaydetailproduct.setProductName(""+object[1]);
            vputawaydetailproduct.setCategoryName(""+(object[2]==null?"":object[2]));
            vputawaydetailproduct.setProductCode(""+object[3]);
            vputawaydetailproduct.setPutawayId(""+object[4]);
            vputawaydetailproduct.setReceivedDate((Date)object[5]);
            vputawaydetailproduct.setUserId(""+object[6]);
            vputawaydetailproduct.setCorpId(""+object[7]);
            vputawaydetailproduct.setQtyOrder(Integer.parseInt(""+object[8]));
            vputawaydetailproduct.setLocationCode(""+object[9]);
            vputawaydetailproduct.setWhCode(""+object[10]);
            vputawaydetailproduct.setProductType(""+object[12]);
            
            vputawaydetailproducts.add(vputawaydetailproduct);
        }
        
        return vputawaydetailproducts;
    }
    
    public List<PutawayDetail> getPutawayDetails2(String putawayId) {
        return sessionFactory.getCurrentSession().createCriteria(PutawayDetail.class)
                .add(Restrictions.eq("putawayId", putawayId))
                .list();
    }
    
}
