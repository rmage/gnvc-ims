package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.SwsDao;
import com.app.wms.engine.db.dao.SwsDetailDao;
import com.app.wms.engine.db.dao.EdsDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.dto.Eds;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Vgrdetailproduct;

public class EDSController extends ReportManagerController {

	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
	       
	       HashMap m = null;
	       final String mode = request.getParameter("mode");
	       if (mode != null && mode.equals("edit")) {
	           m = this.getModelByPrimaryKey(request);
	           m.put("mode", "edit");
	           return new ModelAndView("8_delivery/EDSEdit", "model", m);
	       } else {
	           m = this.searchAndPaging(request, response);
	           return new ModelAndView("8_delivery/EDSList", "model", m);
	       }

	   	} catch (Throwable e) {
		  e.printStackTrace();
		  return new ModelAndView( "Error", "th", e );
		}
		   
	}

	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

	    HashMap m = new HashMap();
	    Integer page = null;
	    Integer paging = null;
        if (request.getParameter("page") != null) {
           page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("paging") != null) {
           paging = Integer.parseInt(request.getParameter("paging"));
        }
        if (page == null) {
           page = 1;
        }
        if (paging == null) {
           paging = 10;
        }
        
	    int start = (page - 1) * paging + 1;
	    int end = start + paging - 1;
	
	    Eds t = new Eds();
	    String edsNo = request.getParameter("tsnumber");
	    String edsDate = request.getParameter("tsdate");
	    t.setEdsnumber(edsNo);

	    EdsDao dao = DaoFactory.createEdsDao();
	    if(edsDate == null || edsDate.equalsIgnoreCase("")){
	    	List<Eds> listSearchPage = dao.findAll();
	    	m.put("EDSlip", listSearchPage);
	    }else{
	    	t.setEdsdate(new SimpleDateFormat("dd/MM/yyyy").parse(edsDate));
	    	List<Eds> listSearchPage = dao.findEdsPaging(t,page);
	    	m.put("EDSlip", listSearchPage);
	    }
	      
	    int total = 2000; 
	   
	    m.put("totalRows", total);
	    m.put("page", page);
	    m.put("paging", paging);
	    m.put("tsdate", edsDate);
	    m.put("tsnumber", edsNo);

	    return m;

		} catch (Exception e){
		  throw e;
		}
	}

	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
	try {
		 SupplierDao dao = DaoFactory.createSupplierDao();
         Supplier dto = new Supplier();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getSupplierCode() == null) {
        	 
        	 dto.setSupplierCode("");
        	 dto.setSupplierName("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
         }
         
         if (dto.getSupplierCode() != null || dto.getSupplierName() != null) {
        	 dto.setSupplierCode(dto.getSupplierCode());
        	 dto.setSupplierName(dto.getSupplierName());
             dto.setIsActive(dto.getIsActive());
             dto.setIsDelete(dto.getIsDelete());
         }
         //edit
         HashMap m = new HashMap();
         DepartmentDao daoDep = DaoFactory.createDepartmentDao();
         List<Department> dropListDepartment = daoDep.findAll();
 		 
		 m.put("dropListDepartment", dropListDepartment);
         m.put("dto", dto);
         
         return m;
         
		} catch (Exception e) {
            throw e;
        }
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = this.getModelByPrimaryKey(request);
        m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");
        m.put("hash", new Date().getTime());
        return new ModelAndView("8_delivery/EDSAdd", "model", m);
    }
	
	public ModelAndView save (HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String createdBy = "";
            if (lu == null) {
    			HashMap m = new HashMap();
                String msg = "You haven't login or your session has been expired! Please do login again";
                m.put("msg", msg);
                return new ModelAndView("login", "model", m);
            }else{
            	createdBy = lu.getUserId();
            }

            Sws ws = new Sws();
            SwsDao dao = DaoFactory.createSwsDao();
            SwsDetailDao daod = DaoFactory.createSwsDetailDao();
          
            ws.setSwsnumber(request.getParameter("swsNo"));
            ws.setSwsdate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("swsDate") + ""));
            ws.setDepartmentName(request.getParameter("departmentName"));
            ws.setCreatedby(createdBy);
            ws.setRemarks(request.getParameter("remarks"));
            ws.setRequestedBy(request.getParameter("requestedBy"));
            ws.setApprovedBy(request.getParameter("appBy"));

            String[] productcode1s = request.getParameterValues("productcode1");
            String[] producttypes = request.getParameterValues("producttype");
            String[] qtys = request.getParameterValues("qty");

            List<SwsDetail> wsDetails = new ArrayList<SwsDetail>();

            for (int i = 0; i < productcode1s.length; i++) {
                SwsDetail wsDetail = new SwsDetail();
                wsDetail.setSwsnumber(request.getParameter("swsNo"));
                wsDetail.setProductcode(productcode1s[i]);
                wsDetail.setProducttype(producttypes[i]);
                wsDetail.setQty(new BigDecimal(qtys[i]));
                daod.insert(wsDetail);
            }

            dao.insert(ws);

            Map m = new HashMap(); 
            m = this.searchAndPaging(request, response);
            return new ModelAndView("8_delivery/EDSList", "model", m);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e, e);
            return new ModelAndView("8_delivery/EDSList");
        }
    
	}

	@Transactional
    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
         *  GET LOGIN USER
         */
        String poNo = request.getParameter("poNo");
        HashMap model = new HashMap();

        
        //List<Vgrdetailproduct> listSearch	= poDao.getDetail(poNo);
        List<Vgrdetailproduct> listSearch	= null;
        System.out.println("poNo "+poNo);
        
        if(listSearch != null){
            System.out.println("[poNo][Ajax Document] sales order no : " + poNo + " is valid");
           
            Map tableMap = new HashMap();
            for (Vgrdetailproduct searchDetail : listSearch){
            	
            	Map returnMap = new HashMap();
                  String po_number    = (searchDetail).getPonumber();
                  String productCode  = (searchDetail).getProductCode();
                  String productName  = (searchDetail).getProductName();
                  BigDecimal quantity    	= (searchDetail).getQty();

                  returnMap.put("po_number",po_number);
                  returnMap.put("productCode",productCode);
                  returnMap.put("productName",productName);
                  returnMap.put("quantity",quantity);

                  tableMap.put(returnMap, returnMap);
              }
            
            model.put("master", listSearch);
            model.put("tableMap", tableMap);
            
        } else{
            System.out.println("[PurchaseOrderDetail][Ajax Document] purchase order no : " + poNo + " is not valid");
        }

        return new ModelAndView("2_receive/util/PurchaseOrderDetail", "model", model);
    }

  
  public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	String salesOrderNo = request.getParameter("sonumber");
	System.out.println("salesOrderNo ="+salesOrderNo);
	
	templateName = request.getParameter("templateName");
	System.out.println("templateName ="+templateName);
	
	parametersKey = request.getParameter("parametersKey");
	System.out.println("parameterKey ="+parametersKey);
	
	ArrayList resultList = new ArrayList();
	resultList.add(salesOrderNo);
	setParameterValues(resultList);
	
	List paramKey = new ArrayList();
	paramKey.add(parametersKey);
	setParameterKeys((ArrayList<String>) paramKey);
	outputFormat = "pdf";
	createOnlineReport();
		
	try{
		printToStream(response);
		
	}catch(FileNotFoundException ex){
     ex.printStackTrace();
              
	}catch(Exception ex){
     ex.printStackTrace();
	}
		
  }
	
}
