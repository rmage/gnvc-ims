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

import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.PoDetailDao;
import com.app.wms.engine.db.dao.PrsDao;
import com.app.wms.engine.db.dao.PrsDetailDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.UomDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.exceptions.PrsDaoException;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Po;

public class PurchaseRequisitionController extends ReportManagerController {
	
	private Integer size = 0;
	
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			Map m = new HashMap();
			final String mode = request.getParameter("mode");
			if(mode != null && mode.equals("edit")){
				
				m = this.getModelByPrimaryKey(request);
				m.put("mode", "edit");
				return new ModelAndView ("2_receive/PRSEdit", "model", m);
			}else{
				
				m = this.searchAndPaging(request, response);
				return new ModelAndView ("2_receive/PRSList", "model", m);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "th", e);
		}
	}
	
	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{

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

            Prs p = new Prs();
            String prsNo = request.getParameter("prsnumber");
            String prsDate = request.getParameter("prsdate");
            p.setPrsnumber(prsNo);
            
            PrsDao dao = DaoFactory.createPrsDao();
            if(prsDate == null || prsDate == ""){
            	List<Prs> listSearchPage = dao.findAll();
            	m.put("purchaseReq", listSearchPage);
            }else if (prsDate != null && prsDate != ""){
            	p.setPrsdate(new SimpleDateFormat("dd/MM/yyyy").parse(prsDate));
            	List<Prs> listSearchPage = dao.findPrsPaging(p, page);
            	m.put("purchaseReq", listSearchPage);
            }
           
            int total = 2000; 

            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);
            m.put("prsdate",prsDate);
            m.put("prsnumber",prsNo);

            return m;

		}catch (Exception e){
			throw e;
		}
		
	}
	
	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
	try {
			Prs dto = new Prs();
		 
	         //edit
	         HashMap m = new HashMap();
	         DepartmentDao daoDep = DaoFactory.createDepartmentDao();
	         SupplierDao daoSupp = DaoFactory.createSupplierDao();
	         CurrencyDao daoCurr = DaoFactory.createCurrencyDao();
	         UomDao daoUoM = DaoFactory.createUomDao();
	         List<Department> dropListDepartment = daoDep.findAll();
	 		 //List<Supplier> dropListSupplier = daoSupp.findAll();
	 		 //List<Currency> dropListCurrency = daoCurr.findAll();
	 		 
			 m.put("dropListDepartment", dropListDepartment);
			 //m.put("dropListSupplier", dropListSupplier);
			 //m.put("dropListCurrency", dropListCurrency);
	         m.put("dto", dto);
	         
	         return m;
         
		} catch (Exception e) {
            throw e;
        }
	}
	
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		map = this.getModelByPrimaryKey(request);
		map.put("mode", "create");		
		
		SupplierDao dao = DaoFactory.createSupplierDao();
		return new ModelAndView ("2_receive/PRSAdd", "model", map);
	}
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
		
		try{
			
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
			
			Prs p = new Prs();
			PrsDao dao = DaoFactory.createPrsDao();
			PrsDetailDao daod = DaoFactory.createPrsDetailDao();
			
			p.setPrsnumber(generatePrsNumber(request));
			p.setPrsdate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("prsdate") + ""));
			p.setRequestdate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("requestdate") + ""));
//			p.setDeliverydate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("deliverydate") + ""));
			p.setDeliverydate(new Date());
//			p.setPoreferensi(request.getParameter("poreferensi"));
			p.setRemarks(request.getParameter("remarks"));
			
			p.setCreatedby(createdBy);
			p.setDepartmentName(request.getParameter("departmentName"));
			p.setIsApproved("N");
			
			String[] productcode1s = request.getParameterValues("productCode1");
			String[] productname1 = request.getParameterValues("productName1");
	        String[] qtys = request.getParameterValues("qty");
	        String[] uomName = request.getParameterValues("uomName1");
	        
	        List<PrsDetail> prsDetails = new ArrayList<PrsDetail>();
	        
	        for(int i = 0; i < productcode1s.length; i++){
	        	PrsDetail prsDetail = new PrsDetail();
	        	prsDetail.setPrsnumber(p.getPrsnumber());
	        	prsDetail.setProductcode(productcode1s[i]);
	        	prsDetail.setProductname(productname1[i]);
	        	prsDetail.setQty(new BigDecimal(qtys[i]));
	        	prsDetail.setUomName(uomName[i]);
	        	daod.insert(prsDetail);
	        	
	        }
	        
	        dao.insert(p);
	        
	        Map m = new HashMap();
	        
	        List<Prs> listSearchPage = dao.findAll();
        	m.put("purchaseReq", listSearchPage);
	        //m = this.searchAndPaging(request, response);
	        return new ModelAndView("2_receive/PRSList","model",m);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e, e);
			 return new ModelAndView("2_receive/PRSList");
		}
	}
	
	private String generatePrsNumber (HttpServletRequest request) throws PrsDaoException{
		
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String roleCode = lu.getRoleCode();
		String role = roleCode.substring(0, 2);
		
		PrsDao dao = DaoFactory.createPrsDao();
		List<Prs> s = dao.findAll();
		Integer size = s.size()+1;
		String year = new SimpleDateFormat("yy").format(new Date());
		String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
		
		return role+tail;
	}
	
	public ModelAndView approvedPRS(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		String prsnumber = request.getParameter("prsNo");
        
        Prs prs = new Prs();
        prs.setIsApproved("Y");
        prs.setPrsnumber(prsnumber);
        prs.setApprovedDate(new Date());
        prs.setApprovedBy(approvedBy);
        
        PrsDao dao = DaoFactory.createPrsDao();
        dao.update(prs);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView ("2_receive/PRSList", "model", m);
    }
    
    public ModelAndView cancelPRS(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		String prsnumber = request.getParameter("prsNo");
        
        Prs prs = new Prs();
        prs.setIsApproved("N");
        prs.setPrsnumber(prsnumber);
        prs.setApprovedDate(new Date());
        prs.setApprovedBy(approvedBy);
        
        PrsDao dao = DaoFactory.createPrsDao();
        dao.update(prs);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView ("2_receive/PRSList", "model", m);
   }

   @Transactional
    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        
        String prsNo = request.getParameter("prsNo");
        System.out.println("prsNo ="+prsNo);
        HashMap model = new HashMap();
        PrsDetailDao dao = DaoFactory.createPrsDetailDao();
        List<PrsDetail> listSearch = dao.findWherePrsnumberEqualsDetail(prsNo);
        System.out.println("listSearch ="+listSearch);
        if(listSearch != null){
            System.out.println("[prsNo][Ajax Document] prs number : " + prsNo + " is valid");
           
            Map tableMap = new HashMap();
            for (PrsDetail searchDetail : listSearch){
            		Map returnMap = new HashMap();
                    String prsno    = (searchDetail).getPrs().getPrsnumber();
                    String productCode  = (searchDetail).getProductcode();
                    String productName  = (searchDetail).getProductname();
                    BigDecimal qty = (searchDetail).getQty();
                    String uom = (searchDetail).getUomName();
                    BigDecimal balance = (searchDetail).getStockInventory().getBalance();
                    String department = (searchDetail).getPrs().getDepartmentName();
                    Date requestdate = (searchDetail).getPrs().getRequestdate();
                    String remarks = (searchDetail).getPrs().getRemarks();
                    returnMap.put("prsnumber", prsno);
                    returnMap.put("productCode",productCode);
                    returnMap.put("productName",productName);
                    returnMap.put("qty",qty);
                    returnMap.put("uom", uom);
                    returnMap.put("balance", balance);
                    returnMap.put("department", department);
                    returnMap.put("requestdate", requestdate);
                    returnMap.put("remarks", remarks);

                    tableMap.put(returnMap, returnMap);
                }
            
            model.put("master", listSearch);
            model.put("tableMap", tableMap);
            
        } else{
            System.out.println("[PRSDetail][Ajax Document] prs no : " + prsNo + " is not valid");
        }

        return new ModelAndView("2_receive/util/PRSListDetail", "model", model);
    }
    
	    public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
	    {
			
			String prsnumber = request.getParameter("prsnumber");
			System.out.println("prsnumber ="+prsnumber);
			
			templateName = request.getParameter("templateName");
			System.out.println("templateName ="+templateName);
			
			parametersKey = request.getParameter("parametersKey");
			System.out.println("parameterKey ="+parametersKey);
			
			ArrayList resultList = new ArrayList();
			resultList.add(prsnumber);
			setParameterValues(resultList);
			
			List paramKey = new ArrayList();
			paramKey.add(parametersKey);
			setParameterKeys((ArrayList<String>) paramKey);
			outputFormat = "pdf";
			createOnlineReport();
			
			try{
				printToStream(response);
				
			}catch(FileNotFoundException ex){
//				Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
	                    ex.printStackTrace();
	                    
			}catch(Exception ex){
//				Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
	                    ex.printStackTrace();
			}
			
		}
}
