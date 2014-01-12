/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao.spring;

import com.app.wms.hbm.bean.PutawayDetail;
import com.app.wms.hbm.bean.Stocktake;
import com.app.wms.web.bean.json.StockTakeVO;
import java.util.ArrayList;
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
@Service("stockTakeDao")
@Transactional
public class StockTakeDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Stocktake> getStockTakes() {
        return sessionFactory.getCurrentSession().createCriteria(Stocktake.class).list();
    }
    
    public void save(Stocktake stocktake) {                
        
        // insert stock takenya
        sessionFactory.getCurrentSession().save(stocktake);
        
        if(stocktake.getActualqty() == 0)
            return ;
        
        // ambil value dari location 
        String query = "WITH MAXPUTAWAY AS ( SELECT location_code, MAX(id) AS id FROM dbo.putaway_detail a "
                + "INNER JOIN putaway b ON a.putaway_id = b.putaway_id WHERE b.status_app='SUCCESSFULL' GROUP BY location_code ) "
                + "SELECT MAXPUTAWAY.id FROM dbo.putaway_detail INNER JOIN MAXPUTAWAY ON dbo.putaway_detail.id = MAXPUTAWAY.id "
                + "where putaway_detail.location_code = :location_code "
                + "UNION SELECT null as id FROM wh_location "
                + "WHERE wh_location.location_code NOT IN ( SELECT location_code FROM MAXPUTAWAY ) "
                + "and wh_location.location_code = :location_code";
        
        Integer hasil = (Integer)sessionFactory.getCurrentSession().createSQLQuery(query)
                .setString("location_code", stocktake.getLocationcode()).uniqueResult();
        
        System.out.println(">>>>>>>>");
        System.out.println(stocktake);
        System.out.println(hasil);
        System.out.println(">>>>>>>>");
        
        if(hasil == null) {
            PutawayDetail putawayDetail = new PutawayDetail();
                
            putawayDetail.setLocationCode(stocktake.getLocationcode()); 
            putawayDetail.setProductCode(stocktake.getProductcode());
            putawayDetail.setProductType(null);
            putawayDetail.setQtyPut(stocktake.getActualqty());
            putawayDetail.setQtyOrder(stocktake.getActualqty());
            putawayDetail.setBalance(stocktake.getActualqty());

             sessionFactory.getCurrentSession().save(putawayDetail);
        } else {
             PutawayDetail putawayDetail = (PutawayDetail)sessionFactory.getCurrentSession()
                .get(PutawayDetail.class, hasil);
        
            // update value dari location 
            if(putawayDetail == null)
                return;
            putawayDetail.setBalance(stocktake.getActualqty());
            sessionFactory.getCurrentSession().update(putawayDetail);
        }
    }
    
    public  List<Stocktake> get(String productcode) {
        return sessionFactory.getCurrentSession().createCriteria(Stocktake.class).add(Restrictions.like("productcode","%"+productcode+"%")).list();
    }
    
    public  List<Stocktake> get(String corpId, String whCode) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Stocktake.class)
                .add(Restrictions.eq("corpId",corpId))
                .add(Restrictions.eq("whCode",whCode))
                .list();
    }
    
    
    public List<StockTakeVO> getVO (String corpId, String whCode) {
        String sql = "SELECT wh_location.location_id, wh_location.wh_code, wh_location.corp_id, wh_location.location_code, "
                + "wh_location_detail.product_code, product.product_name, corp.corp_name, wh_location.location_name FROM wh_location "
                + "INNER JOIN wh_location_detail ON ( wh_location.location_id = wh_location_detail.location_id ) "
                + "INNER JOIN product ON ( wh_location_detail.product_id = product.product_id ) "
                + "INNER JOIN corp ON ( wh_location.corp_id = corp.corp_id ) where wh_location.wh_code = :wh_code and wh_location.corp_id = :corp_id";
        
        List<Object[]> objects = sessionFactory.getCurrentSession().createSQLQuery(sql)
                                    .setString("corp_id", corpId)
                                    .setString("wh_code", whCode)
                                    .list();
        StockTakeVO stockTakeVO = null;
        List<StockTakeVO> stockTakeVOs = new ArrayList<StockTakeVO>();
        for (Object[] objects1 : objects) {
            stockTakeVO = new StockTakeVO();
            stockTakeVO.setLocation(objects1[3]+"");
            stockTakeVO.setProductCode(objects1[4]+"");
            stockTakeVO.setProductName(objects1[5]+"");
            stockTakeVO.setLocationName(objects1[7]+"");
            
            stockTakeVOs.add(stockTakeVO);
        }
        return stockTakeVOs;
    }
    
    
}
