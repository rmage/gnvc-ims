/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.spring.ProductDao;
import com.app.wms.engine.db.dao.spring.PutawayDao;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.hbm.bean.Putaway;
import com.app.wms.hbm.bean.PutawayDetail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @gnv solution
 */
@Controller
@RequestMapping({"/putawayAdd.htm","/putawayUpdate.htm","/putawayListSPV.htm"})
public class Putaway2Controller {

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private PutawayDao putawayDao;
    
    @Autowired
    private ProductDao productDao;
    
    @RequestMapping(value = "/putawayUpdate.htm", method = RequestMethod.POST)
    public String update(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            String putawayId = request.getParameter("putawayNumber");
            
            Putaway putaway = new Putaway();
            putaway.setStatusApp("SUCCESSFULL"); 
            putaway.setPutawayId(putawayId);                         
            
            String[] productcode1s = request.getParameterValues("productcode1");            
            String[] location1s = request.getParameterValues("location1"); // lokasi akhir
            String[] location2s = request.getParameterValues("location2"); // lokasi awal
            String[] qty1s = request.getParameterValues("qty1");
            String[] producttype1 = request.getParameterValues("producttype1");
            List<PutawayDetail> details = new ArrayList<PutawayDetail>();
            for (int i = 0; i < productcode1s.length; i++) {
                PutawayDetail putawayDetail = new PutawayDetail();
                
                putawayDetail.setLocationCode(location2s[i].trim() +"|"+location1s[i].trim()); // lokasi awal | lokasi akhir
                putawayDetail.setProductCode(productcode1s[i].trim());
                putawayDetail.setProductType(producttype1[i].trim());
                putawayDetail.setQtyPut(Integer.parseInt(qty1s[i].replace("<br />", "").trim()));
                
                putawayDetail.setPutawayId(putawayId);
                
                putawayDetail.setCorpId(lu.getCorpId());
                putawayDetail.setWhCode(lu.getWhCode());
                
                details.add(putawayDetail);
            }
            
            putawayDao.update(putaway, details);
        } catch (Exception e) {
            logger.error(e,e);
        }
        return "redirect:/Putaway.htm";
    }
    
    @RequestMapping(value = "/putawayListSPV.htm", method = RequestMethod.GET)
    public ModelAndView getSPV(HttpServletRequest request, HttpServletResponse response) {
        
    	return new ModelAndView("3_putaway/PutawayListSPV");
    }
    
    
    @RequestMapping(value = "/putawayAdd.htm", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response) {

        try {

            com.app.wms.engine.db.dto.map.LoginUser lu =
                    (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");

            Putaway putaway = new Putaway();
            putaway.setAppDate(new Date());
            putaway.setCreatedBy(lu.getUsername());
            putaway.setCreatedDate(new Date());
            putaway.setGrDate(new Date());
            putaway.setGrNumber(request.getParameter("grnumber"));
            putaway.setPutawayDate(new Date());
            putaway.setPutawayId(generatePUNumber(putawayDao.size(lu.getCorpId(), lu.getWhCode()), lu.getWhCode()));
            putaway.setStatusApp("PENDING");            
            putaway.setUpdatedBy(lu.getUsername());
            putaway.setUpdatedDate(new Date());
            putaway.setTallyman(request.getParameter("tallyman").split("~")[0]);
            
            List<PutawayDetail> details = new ArrayList<PutawayDetail>();
            String[] productcode1s = request.getParameterValues("productcode1");
            String[] qty1s = request.getParameterValues("qty11");
            String[] location1s = request.getParameterValues("location1");
            String[] productType1 = request.getParameterValues("productType1");
            
            for (int i = 0; i < productcode1s.length; i++) {                               
                String[] hasilPecahLocation = location1s[i].trim().split("<br />");
                String[] hasilPecahQty = qty1s[i].trim().split("<br />");                                   
                
                for (int j = 0; j < hasilPecahLocation.length; j++) {     
                    
                    PutawayDetail putawayDetail = new PutawayDetail();
                    
                    putawayDetail.setProductCode(productcode1s[i]);                
                    putawayDetail.setProductType(productType1[i]);                
                    putawayDetail.setLotid(Integer.parseInt(request.getParameter("lotid")));                
                    putawayDetail.setProductId(productDao.getProduct(productcode1s[i]).getProductId());                
                    
                    putawayDetail.setPutawayId(putaway.getPutawayId()); // get putaway id
                    putawayDetail.setUserId(lu.getUserId());
                    putawayDetail.setWhCode(lu.getWhCode());
                    putawayDetail.setCorpId(lu.getCorpId());
                    putawayDetail.setReceivedDate(new Date());
                    
                    putawayDetail.setLocationCode(hasilPecahLocation[j].trim());
                    putawayDetail.setQtyOrder(Integer.parseInt(hasilPecahQty[j].trim()));
                    putawayDetail.setQtyPut(Integer.parseInt(hasilPecahQty[j].trim()));
                    putawayDetail.setBalance(0);            
                    
                    // set sku
                    putawayDetail.setUnitCode(productDao.getSKU(productcode1s[i]));
                    
                    logger.debug("content is "+putawayDetail);
                    
                    details.add(putawayDetail);
                }
            }
            
            logger.debug("size is "+details.size());
            
            putawayDao.save(putaway, details);

            return "redirect:/Putaway.htm";
        } catch (Exception e) {
            logger.error(e, e);
            return "redirect:/Putaway.htm";
        }
    }
    
    private String generatePUNumber(int size, String whCode) {
             // PUWHYYYYXXXXXXX
             String head = "PU";             
             String wh = whCode.replace("WH-", "");                
             String year =  new SimpleDateFormat("yyyy").format(new Date());
             String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
             
             return head+wh+year+tail;
         }
    
}
