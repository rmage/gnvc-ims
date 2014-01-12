package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.DeliverySearch;
import com.app.wms.engine.db.dao.DeliveryDao;
import com.app.wms.engine.db.dao.DoDetailDao;
import com.app.wms.engine.db.dao.DrDao;
import com.app.wms.engine.db.dao.DrDetailDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dto.Delivery;
import com.app.wms.engine.db.dto.DoDetail;
import com.app.wms.engine.db.dto.Dr;
import com.app.wms.engine.db.dto.DrDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.util.AppConstant;

public class DeliveryController extends ReportManagerController 
{

	/**
	 * Method 'findByPrimaryKey'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
           
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("8_delivery/DeliveryEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("8_delivery/DeliveryList", "model", m);
            }

        }
		catch (Throwable e) {
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

            Dr d = new Dr();
            String drNo = request.getParameter("drno");
            String drdate = request.getParameter("drdate");
            d.setDrnumber(drNo);
            
            DrDao dao = DaoFactory.createDrDao();
            if(drdate == null || drdate == ""){
            	List<Dr> listSearchPage = dao.findAll();
            	m.put("delivery", listSearchPage);
            }else if (drdate != null && drdate != ""){
            	d.setDrdate(new SimpleDateFormat("dd/MM/yyyy").parse(drdate));
            	List<Dr> listSearchPage = dao.findDeliveryPaging(d, page);
            	m.put("delivery", listSearchPage);
            }

            int total = 2000; 
            
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }

	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            java.lang.String deliveryCode = request.getParameter("deliveryCode");
            DeliveryDao dao = DaoFactory.createDeliveryDao();
            Delivery dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
//                dto = dao.findByPrimaryKey(deliveryCode);
            }

            if (dto == null) {
                dto = new Delivery();
            }    

            HashMap m = new HashMap();
            m.put("dto", dto);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String deliveryCode = request.getParameter("deliveryCode");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        BigDecimal pcreatedBy = BigDecimal.ZERO;
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	pcreatedBy = new BigDecimal(lu.getUserId());
        }
       
        DeliveryDao dao = DaoFactory.createDeliveryDao();

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("8_delivery/DeliveryList", "model", m);
    }

	/**
	 * Method 'findAll'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			
			DeliveryDao dao = DaoFactory.createDeliveryDao();
		
			List<Delivery> dto = dao.findAll();
		
			return new ModelAndView( "8_delivery/DeliveryList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereWhCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pdeliveryCode = request.getParameter("deliveryCode");
		
			// create the DAO class
			DeliveryDao dao = DaoFactory.createDeliveryDao();
		
			// execute the finder
			List<Delivery> dto = dao.findWhereDeliveryNoEquals(pdeliveryCode);
		
			return new ModelAndView( "8_delivery/DeliveryList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereNameEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pname = request.getParameter("name");
		
			// create the DAO class
			DeliveryDao dao = DaoFactory.createDeliveryDao();
		
			// execute the finder
			List<Delivery> dto = dao.findWhereDeliveryNameEquals(pname);
		
			return new ModelAndView( "8_delivery/DeliveryList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	

	/**
	 * Method 'create'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	
		String deliveryNo = ""+new ctrlIDGenerator().getIDDelivery();
		Date date 		 = (Date)new Date();
		Timestamp ts     = new Timestamp(date.getTime());
		
		HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		m.put("deliveryNo", deliveryNo);
		return new ModelAndView( "8_delivery/DeliveryAdd", "model", m);
	}
	
	 private ModelAndView listDeliveryByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListDelivery");
		 Map map = new HashMap();
		 try{
			 
			 LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
			 if (lu == null) {
		            String msg = "You haven't login or your session has been expired! Please do login again";
		            map.put("msg", msg);
		            return new ModelAndView("login", "model", map);
		     }
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }
		 return new ModelAndView("8_delivery/DeliveryList", "model", map);
	}

	/**
	 * Method 'saveDelivery'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception
	
	{
		
		Map map = new HashMap();
	    boolean isCreate = true;
	    String strError = "";
	    java.lang.String mode = request.getParameter("mode");
      
      try {
    	  
    	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = "";
          if (lu == null) {
  			HashMap m = new HashMap();
              String msg = "You haven't login or your session has been expired! Please do login again";
              m.put("msg", msg);
              return new ModelAndView("login", "model", m);
          }else{
        	  userId = (String)(lu.getUserId());
          }
          
          Dr dto = new Dr();
          DrDetail dd = new DrDetail();
          DrDao dao = DaoFactory.createDrDao();
          DrDetailDao daod = DaoFactory.createDrDetailDao();
          
          String drno = request.getParameter("drno");
          
          List<Dr> tmp = dao.findWhereDrnumberEquals(drno);
          if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getDrnumber().equals(drno))) {
	  		  strError += "DR No. already exists. Please try a different values" + AppConstant.EOL;
	  	  }
          
          String drdate = request.getParameter("drdate");
          String to = request.getParameter("to");
          String orno = request.getParameter("orNo");
          String location = request.getParameter("location");
          String dmno = request.getParameter("dmNo");
          String deliveryby = request.getParameter("deliveryBy");
          String approvedby = request.getParameter("approvedBy");
          String receivedby = request.getParameter("receivedBy");
          String remarks = request.getParameter("remarks");
         
          dto.setDrnumber(drno);
          dto.setDrdate(new SimpleDateFormat("dd/MM/yyyy").parse(drdate));
          dto.setSupplierName(to);
          dto.setOrnumber(orno);
          dto.setLotid(location);
          dto.setDmnumber(dmno);
          dto.setDeliveredBy(deliveryby);
          dto.setApprovedBy(approvedby);
          dto.setReceivedBy(receivedby);
          dto.setRemarks(remarks);
          
	      List<DrDetail> drDetail = new ArrayList<DrDetail>();
	      String[] productcode1s = request.getParameterValues("productCode1");
		  String[] productname1 = request.getParameterValues("productName1");
	      String[] qtys = request.getParameterValues("qty");
	      String[] uomName = request.getParameterValues("uomName1");
	      
	      for(int i = 0; i < productcode1s.length; i++){
	    	    DrDetail drDetails = new DrDetail();
	    	    drDetails.setDrnumber(drno);
	    	    drDetails.setProductcode(productcode1s[i]);
	    	    drDetails.setQtyreal(new BigDecimal(qtys[i]));
	        	daod.insert(drDetails);
	        }
	      
	      dao.insert(dto);
	      
      } catch (Exception e) {
         e.printStackTrace();
         return new ModelAndView("Error", "deliveryreceipt", e);
      }
      
      return new ModelAndView("8_delivery/DeliveryList", "model", map);

  }
	
	@Transactional
    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
         *  GET LOGIN USER
         */
        String deliveryNo = request.getParameter("deliveryNo");
        HashMap model = new HashMap();

        DoDetailDao dao = DaoFactory.createDoDetailDao();
        DoDetail dto	= dao.findByPrimaryKey(deliveryNo);
        
        if(dto != null){
            System.out.println("[SalesOrderNo][Ajax Document] sales order no : " + deliveryNo + " is valid");

            DoDetail dod = new DoDetail();
            dod.setDeliveryNo(deliveryNo);
            
            List<DoDetail> listSearch = dao.findDetail(dod);
            Map tableMap = new HashMap();
            for (DoDetail searchDetail : listSearch){
            	
            	Map returnMap = new HashMap();
                deliveryNo    = ((DoDetail)searchDetail).getDeliveryNo();
				String productCode  = ((DoDetail)searchDetail).getProductCode();
				String productName  = ((DoDetail)searchDetail).getProductName();
				BigDecimal quantity    	= ((DoDetail)searchDetail).getQtyDelivery();
				returnMap.put("deliveryNo",deliveryNo);
				returnMap.put("productCode",productCode);
				returnMap.put("productName",productName);
				returnMap.put("quantity",quantity);
				
				tableMap.put(returnMap, returnMap);
			}
            
            model.put("master", dto);
            model.put("tableMap", tableMap);
            
        } else{
            System.out.println("[DeliveryNo][Ajax Document] delivery order no : " + deliveryNo + " is not valid");
        }

        return new ModelAndView("8_delivery/util/DeliveryDetail", "model", model);
    }

 /**
	 * Method 'doPrint'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
 @Transactional
	public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
			
			String deliveryNo = request.getParameter("deliveryNo");
			System.out.println("deliveryNo ="+deliveryNo);
			
			templateName = request.getParameter("templateName");
			System.out.println("templateName ="+templateName);
			
			parametersKey = request.getParameter("parametersKey");
			System.out.println("parameterKey ="+parametersKey);
			
			ArrayList resultList = new ArrayList();
			resultList.add(deliveryNo);
			setParameterValues(resultList);
			
			List paramKey = new ArrayList();
			paramKey.add(parametersKey);
			setParameterKeys((ArrayList<String>) paramKey);
			outputFormat = "pdf";
			createOnlineReport();
			
			try{
				printToStream(response);
				
			}catch(FileNotFoundException ex){
				Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
			}catch(IOException ex){
				Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
 
 /**
	 * Method 'doPrint'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
 	@Transactional
	public void doPrint2(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
			
			String deliveryNo = request.getParameter("deliveryNo");
			System.out.println("deliveryNo ="+deliveryNo);
			
			templateName = request.getParameter("templateName");
			System.out.println("templateName ="+templateName);
			
			parametersKey = request.getParameter("parametersKey");
			System.out.println("parameterKey ="+parametersKey);
			
			ArrayList resultList = new ArrayList();
			resultList.add(deliveryNo);
			setParameterValues(resultList);
			
			List paramKey = new ArrayList();
			paramKey.add(parametersKey);
			setParameterKeys((ArrayList<String>) paramKey);
			outputFormat = "pdf";
			createOnlineReport();
			
			try{
				printToStream(response);
				
			}catch(FileNotFoundException ex){
				Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
			}catch(IOException ex){
				Logger.getLogger(DeliveryController.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}



}
