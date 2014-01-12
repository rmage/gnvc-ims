/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.StockBalanceDao;
import com.app.wms.engine.db.dao.StockInventoryDao;
import com.app.wms.engine.db.dao.spring.GoodReceiveDao;
import com.app.wms.engine.db.dto.StockBalance;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Goodreceive;
import com.app.wms.hbm.bean.GoodreceiveDetail;
import com.app.wms.hbm.bean.Vgrdetailproductcategory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @genevacons
 */
@Controller
@RequestMapping({"/goodReceiveAdd.htm","/goodreceivejson.htm","/goodReceiveDetailJson.htm","/goodReceiveSPV.htm"})
public class GoodReceive2Controller {

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private GoodReceiveDao goodReceiveDao;

    @RequestMapping(value = "/goodReceiveAdd.htm", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response) {
       try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            StockInventory dtoInv = new StockInventory();
            StockInventoryDao daoInv = DaoFactory.createStockInventoryDao();
            
            StockBalance dtoBalance = new StockBalance();
            StockBalanceDao daoBalance = DaoFactory.createStockBalanceDao();
            
            Goodreceive goodreceive = new Goodreceive();
            goodreceive.setCorpid("");
            goodreceive.setWhCode(request.getParameter("warehouse"));
            goodreceive.setLotid(""); //lotid1
            goodreceive.setWsNo("");
            goodreceive.setUnitCost(0);
            goodreceive.setAmount(0);
            
            System.out.println("----------------------------------------------");
            System.out.println("checkedBy ="+request.getParameter("checkedBy"));
            System.out.println("receivedBy ="+request.getParameter("receivedBy"));
            System.out.println("evaluatedBy ="+request.getParameter("evaluatedBy"));
            System.out.println("approvedBy ="+request.getParameter("approvedBy"));
            System.out.println("departmentName ="+request.getParameter("departmentName"));
            System.out.println("----------------------------------------------");
            
            goodreceive.setCheckedBy(request.getParameter("checkedBy"));
            goodreceive.setAccEntry(request.getParameter("receivedBy"));
            goodreceive.setEvalBy(request.getParameter("evaluatedBy"));
            goodreceive.setAppBy(request.getParameter("approvedBy"));
            goodreceive.setDepartmentName(request.getParameter("departmentName"));
            
            goodreceive.setCreatedby(lu.getUsername());
            
            goodreceive.setGrnumber(request.getParameter("rrNo")); // create gr number 
            
            goodreceive.setPonumber(request.getParameter("purchaseNo"));
            if(!"".equals(request.getParameter("grreceivedate")))
                goodreceive.setReceiveddate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("grreceivedate") + ""));
            else
                goodreceive.setReceiveddate(new Date());
            
            List<GoodreceiveDetail> goodreceiveDetails = new ArrayList<GoodreceiveDetail>();
            String[] productCode1s = request.getParameterValues("productCode1");
            String[] statuss = request.getParameterValues("status");
            String[] lotids = request.getParameterValues("lotid");
            String[] qtyreals = request.getParameterValues("qtyreal");
            String[] remarks = request.getParameterValues("remark");
            String[] qtygoods = request.getParameterValues("qtygood");
            String[] qtydmgs = request.getParameterValues("qtydmg");
            String[] expirydates = request.getParameterValues("expirydate");
            String[] producttype = request.getParameterValues("producttype");
            
            for (int i = 0; i < productCode1s.length; i++) {
                GoodreceiveDetail goodreceiveDetail = new GoodreceiveDetail();
                
                if(!expirydates[i].equals(""))
                    goodreceiveDetail.setExpirydate(new SimpleDateFormat("dd/MM/yyyy").parse(expirydates[i] + ""));
                
                goodreceiveDetail.setGrnumber(request.getParameter("rrNo"));                
                goodreceiveDetail.setProductcode(productCode1s[i]);
                goodreceiveDetail.setQtyreal(new BigDecimal(qtyreals[i].replaceAll(",", "")));
                goodreceiveDetail.setRemark(remarks[i]);
                goodreceiveDetail.setStatus(statuss[i]);
                
                goodreceiveDetail.setProducttype("");
                
                logger.debug(">>>> >>>> "+goodreceiveDetail);
                
                if (qtygoods[i].equals("0")||qtygoods[i].equals("0.00") && qtydmgs[i].equals("0")||qtydmgs[i].equals("0.00")){
                    continue;
                }
                
                if (!qtygoods[i].equals("0")||!qtygoods[i].equals("0.00")){
                	System.out.println("qtygoods----------------------------@@@@@@@@@");
                    goodreceiveDetail.setQtygood(new BigDecimal(qtygoods[i].replaceAll(",", "")));
                    goodreceiveDetails.add(goodreceiveDetail);
                } 
                
                if (!qtydmgs[i].equals("0")||!qtydmgs[i].equals("0.00")){
                	System.out.println("qtydamages----------------------------@@@@@@@@@");
                    goodreceiveDetail.setQtydmg(new BigDecimal(qtydmgs[i].replaceAll(",", "")));
                    goodreceiveDetails.add(goodreceiveDetail);
                }   
                
                logger.debug(">>>> "+goodreceiveDetails.size()); 
                
                //check balance
                //Integer bInv = 0;
                BigDecimal bInv = BigDecimal.ZERO;
                dtoInv.setProductCode(productCode1s[i]);
                dtoInv.setWhCode(request.getParameter("warehouse"));
                List <StockInventory> b = daoInv.balance(dtoInv);
        		if(b.size() > 0){
        			for(StockInventory inv : b){
        				bInv = (inv).getBalance();
        			}
        			
        		}else{
        			    bInv = BigDecimal.ZERO;
        		}
               
                dtoInv.setBalance(bInv.add(new BigDecimal(qtyreals[i].replaceAll(",", ""))));
                dtoInv.setStartBalance(BigDecimal.ZERO);
                dtoInv.setIsActive("Y");
                dtoInv.setIsDelete("N");
                dtoInv.setCreatedBy(lu.getUserId());
                dtoInv.setCreatedDate(new Date());
                logger.debug(">>>> dtoInv="+dtoInv); 
                
                dtoBalance.setDocumentNo(request.getParameter("rrNo"));
                dtoBalance.setDocumentDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("grreceivedate") + ""));
                dtoBalance.setProductCode(productCode1s[i]);
                dtoBalance.setQtyIn(new BigDecimal(qtyreals[i].replaceAll(",", "")));
                dtoBalance.setBalance(bInv.add(new BigDecimal(qtyreals[i].replaceAll(",", ""))));
                dtoBalance.setCreatedBy(lu.getUserId());
                dtoBalance.setCreatedDate(new Date());
                logger.debug(">>>> dtoBalance="+dtoBalance); 
                
                daoBalance.insert(dtoBalance);
                
                //check porduct dan warehouse
                List <StockInventory> inv = daoInv.findProductandWarehouse(dtoInv);
                if(inv.size()>0){
                	
	                for(StockInventory stinv : inv){
	                	String productCode = (stinv).getProductCode();
	                	String warehouse  = (stinv).getWhCode();
	
	                	if(warehouse != null){
	                		
	                		if(dtoInv.getWhCode().equalsIgnoreCase(warehouse)&& dtoInv.getProductCode().equalsIgnoreCase(productCode)){ 
	                				daoInv.updateBalance(dtoInv);
	    	                }
	                		
	                	}else{
	                		        System.out.println("@@@@@@@@@@@@@ warehouse = "+dtoInv.getWhCode());
	                		        System.out.println("@@@@@@@@@@@@@ balance = "+dtoInv.getBalance());
	                		        daoInv.updateBalanceNoWarehouse(dtoInv);
	                	}
	                	
	                }
    	            
                }else{
                	
                	daoInv.insert(dtoInv); 
            	}
                
            }
            
         goodReceiveDao.save(goodreceive, goodreceiveDetails);
            
            
        } catch (Exception e) {
            logger.error(e,e);
        }
        return "redirect:/GoodReceive.htm";
    }
    
    @RequestMapping(value = "/goodreceivejson.htm", method = RequestMethod.GET)
    public ModelAndView json(HttpServletRequest request, HttpServletResponse response) {  
        
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");        
        
        List<Goodreceive> goodreceives = new ArrayList<Goodreceive>();
        goodreceives.addAll(goodReceiveDao.getGRPopup(lu.getCorpId(), lu.getWhCode()));
        
        Map model = new HashMap();
        model.put("page", 1);
        model.put("total ", 1);
        model.put("records ", goodreceives.size());                       
        model.put("rows", goodreceives);

        return new ModelAndView("jsonView", model);
    }
    
    @RequestMapping(value = "/goodReceiveDetailJson.htm", method = RequestMethod.GET)
    public ModelAndView jsonDetail(HttpServletRequest request, HttpServletResponse response) {  
       
        List<Vgrdetailproductcategory> vgrdetailproductcategorys = new ArrayList<Vgrdetailproductcategory>();
        vgrdetailproductcategorys.addAll(goodReceiveDao.getGRDetail(request.getParameter("param")));
        
        for (Vgrdetailproductcategory vgrdetailproductcategory : vgrdetailproductcategorys) {
            logger.debug(">>> "+vgrdetailproductcategory);            
        }
        
        Map model = new HashMap();                        
        model.put("grDetails", vgrdetailproductcategorys);

        return new ModelAndView("jsonView", model);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/goodReceiveSPV.htm")
    public ModelAndView spvGet(HttpServletRequest request, HttpServletResponse response) {       
        
      LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        HashMap m = new HashMap();
        m.put("grs", goodReceiveDao.getGR(lu.getCorpId(), lu.getWhCode()));                
        return new ModelAndView("2_receive/GoodReceiveList", "model", m);
    }
    
    private String generateGRNumber(Integer size) {
	     String head = "RR";             
	    // String wh = whCode.replace("WH-", "");             
	     String year =  new SimpleDateFormat("yyyy").format(new Date());
	     String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
	     
	     return head+year+tail;
    }
}
