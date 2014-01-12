package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DateFormat;
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
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.SwsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import com.app.wms.engine.db.exceptions.SwsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Vgrdetailproduct;

public class StoreWithdrawalSlipController extends ReportManagerController {
	
   public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
   try {
       
       HashMap m = null;
       final String mode = request.getParameter("mode");
       if (mode != null && mode.equals("edit")) {
           m = this.getModelByPrimaryKey(request);
           m.put("mode", "edit");
           return new ModelAndView("8_delivery/SwsEdit", "model", m);
       } else {
           m = this.searchAndPaging(request, response);
           return new ModelAndView("8_delivery/SwsList", "model", m);
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
	
	    Sws s = new Sws();
	    String swsNo = request.getParameter("swsnumber");
	    String swsDate = request.getParameter("swsdate");
	    s.setSwsnumber(swsNo);

	    SwsDao dao = DaoFactory.createSwsDao();
	    if(swsDate == null || swsDate.equalsIgnoreCase("")){
	    	List<Sws> listSearchPage = dao.findAll();
	    	m.put("storesWithdrawalSlip", listSearchPage);
	    }else{
	    	s.setSwsdate(new SimpleDateFormat("dd/MM/yyyy").parse(swsDate));
	    	List<Sws> listSearchPage = dao.findSwsPaging(s,page);
	    	m.put("storesWithdrawalSlip", listSearchPage);
	    }
	      
	    int total = 2000; 
	   
	    m.put("totalRows", total);
	    m.put("page", page);
	    m.put("paging", paging);
	    m.put("swsdate", swsDate);
	    m.put("swsnumber", swsNo);

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
        return new ModelAndView("8_delivery/SwsAdd", "model", m);
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

            Sws sws = new Sws();
            SwsDao dao = DaoFactory.createSwsDao();
            SwsDetailDao daod = DaoFactory.createSwsDetailDao();
          
            sws.setSwsnumber(generateSwsNumber(request));
            sws.setSwsdate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("swsDate") + ""));
            sws.setDepartmentName(request.getParameter("departmentName"));
            sws.setCreatedby(createdBy);
            sws.setRemarks(request.getParameter("remarks"));
            sws.setRequestedBy(request.getParameter("requestedBy"));
            sws.setApprovedBy(request.getParameter("appBy"));

            String[] productcode1s = request.getParameterValues("productCode1");
            //String[] producttypes = request.getParameterValues("producttype");
            String[] productname1s = request.getParameterValues("productName1");
            String[] qtys = request.getParameterValues("qty");
            String[] uom = request.getParameterValues("uomName1");

            List<SwsDetail> swsDetails = new ArrayList<SwsDetail>();

            for (int i = 0; i < productcode1s.length; i++) {
                SwsDetail swsDetail = new SwsDetail();
                Product p = new Product();
                swsDetail.setSwsnumber(generateSwsNumber(request));
                swsDetail.setProductcode(productcode1s[i]);
                p.setProductName(productname1s[i]);
                p.setUom(uom[i]);
                swsDetail.setProduct(p);
                swsDetail.setProducttype("");
                swsDetail.setQty(new BigDecimal(qtys[i]));
                System.out.println("@@@@@@@@@@ wsDetail ="+swsDetail);
                daod.insert(swsDetail);
            }
            	System.out.println("@@@@@@@@@@ ws ="+sws);
                dao.insert(sws);

            Map m = new HashMap(); 
            m = this.searchAndPaging(request, response);
            return new ModelAndView("8_delivery/SwsList", "model", m);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e, e);
            return new ModelAndView("8_delivery/SwsList");
        }
    
	}
	
	private String generateSwsNumber (HttpServletRequest request) throws SwsDaoException{
		
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String roleCode = lu.getRoleCode();
		String role = roleCode.substring(0, 2);
		
		SwsDao dao = DaoFactory.createSwsDao();
		List<Sws> s = dao.findAll();
		Integer size = s.size()+1;
		String year = new SimpleDateFormat("yy").format(new Date());
		String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
		
		return role+"W"+tail;
	}
	
	public ModelAndView approvedSWS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String approvedBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	approvedBy = lu.getUserId();
        }
		
		String swsnumber = request.getParameter("swsNo");
        
        Sws sws = new Sws();
        sws.setIsApproved("Y");
        sws.setSwsnumber(swsnumber);
        sws.setApprovedDate(new Date());
        sws.setApprovedBy(approvedBy);
        sws.setSwsnumber(swsnumber);
        
        SwsDao dao = DaoFactory.createSwsDao();
        dao.update(sws);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView ("8_delivery/SwsList", "model", m);
    }
    
    public ModelAndView cancelSWS(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String approvedBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	approvedBy = lu.getUserId();
        }
		
		String swsnumber = request.getParameter("swsNo");
        
        Sws sws = new Sws();
        sws.setIsApproved("N");
        sws.setSwsnumber(swsnumber);
        sws.setApprovedDate(new Date());
        sws.setApprovedBy(approvedBy);
        sws.setSwsnumber(swsnumber);
        
        SwsDao dao = DaoFactory.createSwsDao();
        dao.update(sws);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView ("8_delivery/SwsList", "model", m);    
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