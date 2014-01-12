/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.controller;

import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dao.spring.PODao;
import com.app.wms.engine.db.dao.spring.PoCSVDao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Po;
import com.app.wms.hbm.bean.PoDetail;
import com.app.wms.hbm.bean.Pocsv;
import com.app.wms.hbm.bean.Vgrdetailproduct;
import com.app.wms.web.util.AppConstant;

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
 * @gnv solution
 */
@Controller
@RequestMapping({"/purchaseOrderAdd.htm", "/purchaseorderjsonx.htm", "/purchaseorderdetailjson.htm", 
    "/poCSVJson.htm", "/purchaseorderjson1.htm"})
public class PurchaseOrderContoller {

    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    private PODao poDao;
    
    @Autowired
    private PoCSVDao poCSVDao;

    /**
     * 
     *  Simpan PurchaseOrder
     * 
     * @param request
     * @param response
     * @return success path
     */
    @RequestMapping(value = "/purchaseOrderAdd.htm", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response) {

        try {

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String strError = "";
            Po po = new Po();
            po.setPonumber(request.getParameter("purchaseNo"));
            po.setPodate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("pocreated1")));
            String estimationDate = request.getParameter("requestdate");     
            po.setPrsnumber(request.getParameter("prsnumber"));
            po.setPrsdate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("prsdate")));
            po.setDeliverydate(new SimpleDateFormat("yyyy-MM-dd").parse(estimationDate));
            
            po.setCreatedby(lu.getUsername());
            po.setCorpid(lu.getCorpId());
            po.setWhCode(lu.getWhCode());
            po.setPoreferensi(request.getParameter("poreferensi"));
            po.setDepartmentName(request.getParameter("departmentname"));
            po.setSupplierName(request.getParameter("suppliername"));
            System.out.println("suplier ="+request.getParameter("suppliername"));
            String[] productcode1s = request.getParameterValues("productCode1");
//            String[] producttypes = request.getParameterValues("producttype");
            String[] uom = request.getParameterValues("uom");
            String[] qtys = request.getParameterValues("qty");
            String[] currency = request.getParameterValues("currency1");
            
            String[] ppn = request.getParameterValues("ppn"); 
            String[] unitprice = request.getParameterValues("unitprice");
            String[] amount = request.getParameterValues("amount");
            String[] remarks2 = request.getParameterValues("remarks21");
            
            String[] warranty = request.getParameterValues("warranty"); 
            String[] termpayment = request.getParameterValues("termpayment"); 
            String[] termdelivery = request.getParameterValues("termdelivery"); 
            String[] discount = request.getParameterValues("discount"); 
            String[] pph = request.getParameterValues("pph"); 
            String[] total = request.getParameterValues("total1"); 
            
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<PoDetail> poDetails = new ArrayList<PoDetail>();
            for (int i = 0; i < productcode1s.length; i++) {
            	
                PoDetail poDetail = new PoDetail();
                poDetail.setPonumber(request.getParameter("purchaseNo"));
                poDetail.setProductcode(productcode1s[i]);
//                poDetail.setProducttype(producttypes[i]);
                poDetail.setQty(new BigDecimal(qtys[i].replaceAll(",", "")));
                poDetail.setUom(uom[i]);
               
                poDetail.setPpn(new BigDecimal(ppn[i].replaceAll(",", "")));
                poDetail.setUnitprice(new BigDecimal (unitprice[i].replaceAll(",", "")));
                poDetail.setAmount(new BigDecimal(amount[i].replaceAll(",", "")));
                poDetail.setPoremarks(remarks2[i]);

                poDetail.setWarranty(new SimpleDateFormat("yyyy-MM-dd").parse(warranty[i]));
                poDetail.setTermpayment(termpayment[i]);
                poDetail.setTermdelivery(termdelivery[i]);
                poDetail.setDiscount(new BigDecimal(discount[i].replaceAll(",", "")));
                poDetail.setPph(new BigDecimal(pph[i].replaceAll(",", "")));
            	poDetail.setCurrencyCode(currency[i]);
            	poDetail.setTotal(new BigDecimal(total[i].replaceAll(",", "")));
            	
            	totalAmount = new BigDecimal(total[i].replaceAll(",", ""));
                poDetails.add(poDetail);
            }

            String departmentCode = request.getParameter("departmentname");
            ApprovalRangeDao arangeDao = DaoFactory.createApprovalRangeDao();
            System.out.println("departmentCode="+departmentCode);
            System.out.println("total="+totalAmount);
            
            String roleCode = "";
            List<ApprovalRange> listTotal = arangeDao.findApprovalRangeTotal(totalAmount);
            BigDecimal fromAmount = BigDecimal.ZERO;
            BigDecimal toAmount = BigDecimal.ZERO;
            for(ApprovalRange arg : listTotal){
            	fromAmount = arg.getFromAmount();
            	toAmount = arg.getToAmount();
            }
            
        	List<ApprovalRange> listRoleCode = arangeDao.findApprovalRangeRoleCode(departmentCode, totalAmount, fromAmount, toAmount);
        	if(listRoleCode.size()>0)
	        	for(ApprovalRange ar : listRoleCode){
	             	roleCode = ar.getRoleCode();
	            }
        	else
        		departmentCode ="ALL";
        		listRoleCode = arangeDao.findApprovalRangeRoleCode(departmentCode, totalAmount, fromAmount, toAmount);
        		for(ApprovalRange ar : listRoleCode){
	             	roleCode = ar.getRoleCode();
	            }

            System.out.println("roleCode ="+roleCode);
            po.setStatus("N");
            po.setRoleCode(roleCode);
             
            poDao.save(po, poDetails);

            return "redirect://Purchase.htm";
        } catch (Exception e) {
            logger.error(e, e);
            return "redirect://Purchase.htm";
        }
    }

    /**
     * 
     *  mengambil list of PurchaseOrders
     * 
     * @param request
     * @param response
     * @return json responses of POs
     */
    @RequestMapping(value = "/purchaseorderjson1.htm", method = RequestMethod.GET)
     public ModelAndView json1(HttpServletRequest request, HttpServletResponse response) {      

        List<Po> pos = new ArrayList<Po>();
       
        pos.addAll(poDao.getPos());
        
        Map model = new HashMap();
        model.put("page", 1);
        model.put("total ", 1);
        model.put("records ", pos.size());                       
        model.put("rows", pos);

        return new ModelAndView("jsonView", model);
    }
    
    /**
     * 
     *  mengambil list of PurchaseOrders
     * 
     * @param request
     * @param response
     * @return json responses of POs
     */
    @RequestMapping(value = "/purchaseorderjsonx.htm", method = RequestMethod.GET)
     public ModelAndView json(HttpServletRequest request, HttpServletResponse response) {      

        List<Po> pos = new ArrayList<Po>();
        
        pos.addAll(poDao.getPopupPos());
        
        Map model = new HashMap();
        model.put("page", 1);
        model.put("total ", 1);
        model.put("records ", pos.size());                       
        model.put("rows", pos);
        
        return new ModelAndView("jsonView", model);
    }
    
    /**
     * 
     *  mengambil list of PurchaseOrderDetail 
     * 
     * @param request
     * @param response
     * @return json responses of PurchaseOrderDetail
     */
    @RequestMapping(value = "/purchaseorderdetailjson.htm", method = RequestMethod.GET)
     public ModelAndView jsonDetail(HttpServletRequest request, HttpServletResponse response) {      

        String param = request.getParameter("param");
        
        List<Vgrdetailproduct> poDetails = new ArrayList<Vgrdetailproduct>();
        
        poDetails.addAll(poDao.getDetail(param));
        
        Map model = new HashMap();                      
        model.put("poDetails", poDetails);

        return new ModelAndView("jsonView", model);
    }
    
    

    /**
     *  mengambil list dari hasil csv upload
     * 
     * 
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value = "/poCSVJson.htm", method = RequestMethod.GET)
     public ModelAndView jsonPoCSVJson(HttpServletRequest request, HttpServletResponse response) {      

        String param = request.getParameter("param");
        
        List<Pocsv> pocsvs = poCSVDao.get(param);
                
        Map model = new HashMap();                      
        model.put("pocsv", pocsvs);

        return new ModelAndView("jsonView", model);
    }
    
}
