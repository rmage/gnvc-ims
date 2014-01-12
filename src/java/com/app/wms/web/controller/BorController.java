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
import com.app.wms.engine.db.dao.BorDao;
import com.app.wms.engine.db.dao.BorDetailDao;
import com.app.wms.engine.db.dto.Bor;
import com.app.wms.engine.db.dto.BorDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Vgrdetailproduct;

public class BorController extends ReportManagerController {
	
   public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
   try {
       
       HashMap m = null;
       final String mode = request.getParameter("mode");
       if (mode != null && mode.equals("edit")) {
           m = this.getModelByPrimaryKey(request);
           m.put("mode", "edit");
           return new ModelAndView("8_delivery/BorEdit", "model", m);
       } else {
           m = this.searchAndPaging(request, response);
           return new ModelAndView("8_delivery/BorList", "model", m);
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
	
	    Bor b = new Bor();
	    String borNo = request.getParameter("bornumber");
	    String borDate = request.getParameter("bordate");
	    b.setBornumber(borNo);

	    BorDao dao = DaoFactory.createBorDao();
	    if(borDate == null || borDate.equalsIgnoreCase("")){
	    	List<Bor> listSearchPage = dao.findAll();
	    	m.put("bookOrderReport", listSearchPage);
	    }else{
	    	b.setBordate(new SimpleDateFormat("dd/MM/yyyy").parse(borDate));
	    	List<Bor> listSearchPage = dao.findBorPaging(b,page);
	    	m.put("bookOrderReport", listSearchPage);
	    }
	      
	    int total = 2000; 
	   
	    m.put("totalRows", total);
	    m.put("page", page);
	    m.put("paging", paging);
	    m.put("bordate", borDate);
	    m.put("bornumber", borNo);

	    return m;

		} catch (Exception e){
		  throw e;
		}
	}

	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
	try {
		 BorDao dao = DaoFactory.createBorDao();
         Bor dto = new Bor();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getBornumber() == null) {
        	 
        	 dto.setBornumber("");
        	 dto.setBordate(null);
        	 dto.setBorreferensi("");
        	 dto.setBuyerName("");
        	
         }
         
         if(dto.getBornumber() != null || dto.getBordate() != null){
        	 dto.setBornumber(dto.getBornumber());
        	 dto.setBordate(dto.getBordate());
        	 dto.setBorreferensi(dto.getBorreferensi());
        	 dto.setBuyerName(dto.getBuyerName());
        	 
         }
         
         //edit
         HashMap m = new HashMap();
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
        return new ModelAndView("8_delivery/BorAdd", "model", m);
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

            Bor b = new Bor();
            BorDao dao = DaoFactory.createBorDao();
            BorDetailDao daod = DaoFactory.createBorDetailDao();
          
            b.setBornumber(request.getParameter("bornumber"));
            b.setBordate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("bordate") +""));
            b.setBuyerName(request.getParameter("buyername"));
            b.setCreatedby(createdBy);
            
            String[] productcode1s = request.getParameterValues("productcode1");
            String[] producttypes = request.getParameterValues("producttype");
            String[] qtys = request.getParameterValues("qty");

            List<BorDetail> borDetails = new ArrayList<BorDetail>();

            for (int i = 0; i < productcode1s.length; i++) {
                BorDetail borDetail = new BorDetail();
                borDetail.setBornumber(request.getParameter("bornumber"));
                borDetail.setProductcode(productcode1s[i]);
                borDetail.setProducttype(producttypes[i]);
                borDetail.setQty(Integer.parseInt(qtys[i] + ""));
                daod.insert(borDetail);
            }

            dao.insert(b);
            Map m = new HashMap();
            m = this.searchAndPaging(request, response);
            return new ModelAndView("8_delivery/BorList", "model", m);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e, e);
            return new ModelAndView("8_delivery/BorList");
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
            System.out.println("[BorDetail][Ajax Document] bor no : " + poNo + " is not valid");
        }

        return new ModelAndView("8_delivery/BorDetail", "model", model);
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
