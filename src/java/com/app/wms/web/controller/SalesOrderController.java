package com.app.wms.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.SalesOrderSearch;
import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.UnitCode;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.util.AppConstant;

public class SalesOrderController extends ReportManagerController 

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
                return new ModelAndView("6_picking/SalesOrderEdit", "model", m);
            } else {
            	
                m = this.searchAndPaging(request, response);
                
                return new ModelAndView("6_picking/SalesOrderList", "model", m);
            }

        }
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'Search'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	@Transactional
	public ModelAndView Search(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map m = new HashMap();
		try{
			
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
            int total = 2000; 
            
            SalesOrderDao dao = DaoFactory.createSalesOrderDao();
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
           
            SalesOrder so = new SalesOrder();
            so.setCorpId(lu.getCorpId());
            so.setWhCode(lu.getWhCode());
            //# source untuk paging
		    List<SalesOrder> listSearchPage = dao.findSalesOrderPaging(so, page);
            
            m.put("totalRows", total);
	        m.put("page", page);
	        m.put("paging", paging);
			
			 String salesOrderStartDate = request.getParameter("salesOrderStartDate");
			 String salesOrderEndDate = request.getParameter("salesOrderEndDate");
			 
			 Timestamp tsStart = null;
			 Timestamp tsEnd = null;
			 
			 if(salesOrderStartDate.equalsIgnoreCase("") && salesOrderEndDate.equalsIgnoreCase("")){
			     m.put("salesorder", listSearchPage);
			 }
			 else if(salesOrderStartDate.equalsIgnoreCase("")&& salesOrderEndDate.equalsIgnoreCase(salesOrderEndDate)){
				 m.put("msg", " this From Date can't be empty !");
				 return new ModelAndView("6_picking/SalesOrderList", "model", m);
			 }
			 else if(salesOrderStartDate.equalsIgnoreCase(salesOrderStartDate) && salesOrderEndDate.equalsIgnoreCase("")){
				 m.put("msg", " this To Date can't be empty !");
				 return new ModelAndView("6_picking/SalesOrderList", "model", m);
			 }
			 else
			 {	 
				 DateFormat sdf ; 
				 Date startDate ; 
				 Date endDate;
				 String pattern = "yyyy-MM-dd";
				 sdf = new SimpleDateFormat(pattern);
				 startDate = (Date)sdf.parse(salesOrderStartDate);
				 endDate = (Date)sdf.parse(salesOrderEndDate);
		 		 tsStart = new Timestamp(startDate.getTime());
		 		 tsEnd = new Timestamp(endDate.getTime());
		 		 SalesOrder so1 = new SalesOrder();
				 so1.setStartDate(tsStart);
				 so1.setEndDate(tsEnd);

				 dao = DaoFactory.createSalesOrderDao();
			     List<SalesOrder> listSearch = dao.findSalesOrder(so1);
			    
			     m.put("salesorder", listSearch);
			    
			 }
			 
		}catch (Exception e){
			System.out.println("error : "+e.getMessage());
		}
		return new ModelAndView("6_picking/SalesOrderList", "model", m);
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
            
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            SalesOrder so = new SalesOrder();
            so.setCorpId(lu.getCorpId());
            so.setWhCode(lu.getWhCode());
            //# source untuk paging
            SalesOrderDao dao = DaoFactory.createSalesOrderDao();
		    List<SalesOrder> listSearchPage = dao.findSalesOrderPaging(so,page);
            System.out.println("listSearchPage ="+listSearchPage);
            int total = 2000; 
            m.put("salesorder", listSearchPage);
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
            java.lang.String salesOrderNo = request.getParameter("salesOrderNo");
            
            SalesOrderDao dao = DaoFactory.createSalesOrderDao();
            SalesOrder dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(salesOrderNo);
            }

            if (dto == null) {
                dto = new SalesOrder();
                dto.setSo_number("");
                dto.setSo_date(null);
            }

             
            HashMap m = new HashMap();
            m.put("dto", dto);

            return m;
		
        } catch (Exception e) {
            throw e;
        }
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
			
			SalesOrderDao dao = DaoFactory.createSalesOrderDao();
		
			List<SalesOrder> dto = dao.findAll();
			
			request.getSession().removeAttribute("resultListSalesOrder");
			return new ModelAndView( "6_picking/SalesOrderList", "result", dto );
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
		request.getSession().removeAttribute("resultListSalesOrder");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		//String salesOrderNo = ""+new ctrlIDGenerator().getIDSalesOrder();
		//String salesOrderNo = request.getParameter("salesOrderNo");
		//System.out.println("salesOrderNo = >>>>>>>>>>>"+salesOrderNo);
		Date date = (Date)new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		StringBuilder now = new StringBuilder(sdf.format(date));
		//Timestamp ts = new Timestamp(date.getTime());
	    
	    //request.getSession().setAttribute("soNumber", salesOrderNo);
		//SalesOrder so = new SalesOrder();
	    //so.setSo_number(salesOrderNo);
	    //request.getSession().setAttribute("SalesOrder", so);
		
	    HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		//m.put("salesOrderNo",salesOrderNo);
		m.put("warehouse", warehouse);
		m.put("date", now);
		m.put("corporate", corporate);
		return new ModelAndView( "6_picking/SalesOrderAdd", "model", m);
	}

	/**
	 * Method 'saveSalesOrder'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView saveSalesOrder(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		
		try{
			String btnUploadProduct = request.getParameter("btnUploadProduct");
			String btnAddDtlProduct = request.getParameter("btnAddDtlProduct");
			String btnAddDtlBundle = request.getParameter("btnAddDtlBundle");
			String btnCancel = request.getParameter("btnCancel");
			
			if (btnCancel != null && !btnCancel.equals("")) {
				 request.getSession().removeAttribute("resultListSalesOrder");
				 return listPickingByAuthLogin(request, response);
			}else if (btnAddDtlProduct != null && !btnAddDtlProduct.equals("")) {
	             return btnAddDtlProduct(request, response);
			}else if (btnAddDtlBundle != null && !btnAddDtlBundle.equals("")) {
	             return btnAddDtlBundle(request, response);
	        }else if (btnUploadProduct != null && !btnUploadProduct.equals("")) {
	             return btnUploadProduct(request, response);
	        }
			
			LoginUser lu 			= (LoginUser) request.getSession().getAttribute("user");
			String userId 			= lu.getUserId();
			String salesOrderDate 	= request.getParameter("salesOrderDate");
			String corporate 		= request.getParameter("corporate");
			String warehouse 		= request.getParameter("warehouse");
			String remarks			= request.getParameter("remarks");
			
			String pattern = "dd.MM.yyyy HH:mm";
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		    Date so_date = sdf.parse(salesOrderDate);
		    Timestamp dt =  new Timestamp(so_date.getTime());
		    Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
		
			String salesOrderNo = request.getParameter("salesOrderNo");
			
			SalesOrderDao sodao = DaoFactory.createSalesOrderDao();
			SalesOrder so = new SalesOrder();
			so.setSo_number(salesOrderNo);
			String strError="";
			
			List<SalesOrder> soList = sodao.findSO(so);
			if(soList != null && soList.size()>0 && soList.get(0).getSo_number().equalsIgnoreCase(salesOrderNo)){
				strError += "Sales Order No:"+salesOrderNo +" Already Existed!" + AppConstant.EOL;
			}
			
			String deliveryDate = request.getParameter("deliverydate");
			String delivery_name = "";
			if(request.getParameter("deliveryname")!=null){
				delivery_name = request.getParameter("deliveryname");
			}
			if(request.getParameter("deliveryname1")!=null){
				delivery_name = request.getParameter("deliveryname1");
			}
			
			String delivery_address = request.getParameter("deliveryaddress");
			remarks = request.getParameter("remarks");
			
			if (strError.length() > 0) {
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("salesOrderNo",salesOrderNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("msg", strError);
				  return new ModelAndView( "6_picking/SalesOrderAdd", "model", map);
	        }
			
			Timestamp delivery_date = null;
			if(!deliveryDate.equalsIgnoreCase("")){
				
				SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				Date d = f.parse(deliveryDate);
				delivery_date = new Timestamp(d.getTime());
				
			}
						
			List returnList = (List)request.getSession().getAttribute("resultListSalesOrder");
			
			if (returnList == null)
            {
				  lu = (LoginUser) request.getSession().getAttribute("user");
				  corporate = lu.getCorpId();
				  warehouse = lu.getWhCode();
//				  salesOrderNo = ""+new ctrlIDGenerator().getIDSalesOrder();
				  date = (Date)new Date();
			      ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
//				  map.put("salesOrderNo",salesOrderNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("msg", " Sales Order doesn't have product request list! please input that product or cancel this process");
				  return new ModelAndView( "6_picking/SalesOrderAdd", "model", map);
            }
			
			else if(returnList.size()>0)
			{	
	        	String productRequest  = "";
	        	String productId	   = "";
	        	String productCode     = "";
	        	String productName     = "";
	        	int    productQuantity = 0;
	        	String bundlecode      = "";
	        	String bundlename      = "";
	        	String unitCode		   = "";
				  
	        	for(int i=0;i<returnList.size();i++){		
	        		System.out.println("returnList ="+returnList);
	        		Map returnMap = (Map)returnList.get(i);  

	        		productRequest  = (String) returnMap.get("productRequest");
	        		productId		= (String) returnMap.get("productId");
		        	productCode     = (String) returnMap.get("productCode");
		        	productName     = (String) returnMap.get("productName");
		        	productQuantity = (Integer) returnMap.get("requestQuantity");
		        	unitCode 		= (String) returnMap.get("unitCode");
		        	bundlecode		= (String) returnMap.get("bundlecode");
		        	bundlename		= (String) returnMap.get("bundlename");
		        	
		        	SalesOrderDao dao = DaoFactory.createSalesOrderDao();
					SalesOrderDetailDao daodtl = DaoFactory.createSalesOrderDetailDao();
		        	
					SalesOrder dto 			= dao.findByPrimaryKey(salesOrderNo);
					//SalesOrderDetail dso 	= daodtl.findByPrimaryKey(salesOrderNo);
					SalesOrderDetail dso = null;
					
					boolean isCreate = dto == null;
					boolean detail = dso == null;
					if(isCreate){
						dto = new SalesOrder();
					}

					dto.setSo_number(salesOrderNo);
					dto.setSo_date(dt);
					dto.setCreatedBy(userId);
					dto.setCreatedDate(ts);
					dto.setUpdatedBy(userId);
					dto.setUpdatedDate(ts);
					dto.setDelivery_name(delivery_name);
					dto.setDelivery_date(delivery_date);
					dto.setDelivery_address(delivery_address);
					dto.setRemarks(remarks);
					System.out.println("dto>>>>>>>>>>>>>>>>>>> ="+dto);
				    if(isCreate){
					   dao.insert(dto);
					}
//					else
//					{
//					   dao.update(dto.createPk(), dto);
//					}
					
					if(detail){
						
						dso = new SalesOrderDetail();
							
							Product          pro = new Product();
						    Bundle           bun = new Bundle(); 
						    UnitCode         uni = new UnitCode();
						    User             usr = new User();
						    Corporate 		 crp = new Corporate();
						    Wh				 wh  = new Wh();
						    
						    dso.setSo_number(salesOrderNo);
						    pro.setProductId((String)returnMap.get("productId"));
						    pro.setProductCode((String)returnMap.get("productCode"));
						    pro.setProductName((String)returnMap.get("productName"));
						    bun.setBundleCode(bundlecode);
						    bun.setBundleName(bundlename);
						    uni.setName(unitCode);
						    dso.setQuantitySO((Integer)returnMap.get("requestQuantity"));
						    usr.setUserId((String)returnMap.get("userId"));
						    crp.setId((String)returnMap.get("corporate"));
						    wh.setWhCode((String)returnMap.get("warehouse"));
						    dso.setProduct(pro);
						    dso.setBundle(bun);
						    dso.setUnitCode(uni);
						    dso.setUser(usr);
						    dso.setCorp(crp);
						    dso.setWh(wh);
						   // if(dso.getSo_number()!=null){
						       daodtl.insert(dso);
						   // }
						
					}
					else
					{
//						daodtl.update(dso.createPk(), dso);
					}
					
	        	
	        	}
	        	
	        	corporate="";
	        	warehouse="";
	        	salesOrderNo="";
			} 
			
			  lu = (LoginUser) request.getSession().getAttribute("user");
			  corporate = lu.getCorpId();
			  warehouse = lu.getWhCode();
//			  salesOrderNo = ""+new ctrlIDGenerator().getIDSalesOrder();
			  date = (Date)new Date();
		      ts = new Timestamp(date.getTime());
			
			  map = this.getModelByPrimaryKey(request);
			  map.put("mode", "create");
//			  map.put("salesOrderNo",salesOrderNo);
			  map.put("warehouse", warehouse);
			  map.put("date", ts);
			  map.put("corporate", corporate);
			
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "salesorder", e);
		}
		// return beforeConfirm(request, response, pickNumber);
		map.put("msg", "Sales Order Already Created");
		request.getSession().removeAttribute("resultListSalesOrder");
        return new ModelAndView("6_picking/SalesOrderList", "model", map);
	}
	
	 @Transactional
	  public ModelAndView addSelectedProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
		  Map map = new HashMap();
		  boolean add = false;
		  try{
			  if (request.getParameter("productCode") != null && !request.getParameter("productCode").equals("")) {
		     
		          String productCode = request.getParameter("productCode");
		          String productName = request.getParameter("productName");
		          String locationCode = request.getParameter("locationCode");
		          String quantity = request.getParameter("quantity");
		          String unitCode = request.getParameter("unitCode");
		          
		          String delivery_name = "";
					if(request.getParameter("deliveryname")!=null){
						delivery_name = request.getParameter("deliveryname");
					}
					if(request.getParameter("deliveryname1")!=null){
						delivery_name = request.getParameter("deliveryname1");
					}
		         
		      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
				  String corporate = lu.getCorpId();
				  String warehouse = lu.getWhCode();
				  String salesOrderNo = ""+new ctrlIDGenerator().getIDSalesOrder();
				  
				  Date date = (Date)new Date();
				  Timestamp ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("salesOrderNo",salesOrderNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("productCode",productCode);
		          map.put("productName",productName);
		          map.put("deliveryName",delivery_name);
		          map.put("quantity",quantity);
		          map.put("unitCode", unitCode);
		          map.put("locationCode",locationCode);	  
		         
		          Map tableMap = new HashMap();	
		          if (request.getSession().getAttribute("resultListSalesOrder")!= null){
					  
					  List returnList = (List)request.getSession().getAttribute("resultListSalesOrder");
					  for(int i=0;i<returnList.size();i++){						
						Map returnMap = (Map)returnList.get(i);  
			        	tableMap.put(returnMap, returnMap);
			          }
					 	map.put("tableMap",tableMap);
					 
				  }
		          
		          return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
		      } else {
		          return new ModelAndView("6_picking/SalesOrderAdd");
		      }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
	  }
	
	 public ModelAndView btnAddDtlBundle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  System.out.println("btnAddDtlBundle ?>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		 
		 Map map = new HashMap();
		 try{

	    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    	  	String userId = lu.getUserId();
				String corporate = lu.getCorpId();
				String warehouse = lu.getWhCode();
				Date date = (Date)new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				StringBuilder now = new StringBuilder(sdf.format(date));
				
				String salesOrderNo = request.getParameter("salesOrderNo");
				//String deliveryName = request.getParameter("deliveryname");
				String deliveryAddress = request.getParameter("deliveryaddress");
				String deliveryDate = request.getParameter("deliverydate");
				String remarks = request.getParameter("remarks");
				
				 String delivery_name = "";
					if(request.getParameter("deliveryname")!=null){
						delivery_name = request.getParameter("deliveryname");
					}
					if(request.getParameter("deliveryname1")!=null){
						delivery_name = request.getParameter("deliveryname1");
					}
				
				map = this.getModelByPrimaryKey(request);
				map.put("mode", "create");
				map.put("salesOrderNo",salesOrderNo);
				map.put("userId", userId);
				map.put("deliveryName", delivery_name);
				map.put("deliveryAddress", deliveryAddress);
				map.put("deliveryDate", deliveryDate);
				map.put("remarks", remarks);
				map.put("warehouse", warehouse);
				map.put("date", now);
				map.put("corporate", corporate);
	    	  
		      if (request.getParameter("bundlecode") != null || !request.getParameter("bundlecode").equals("")) {
		    	  
		    	  String bundlecode      = request.getParameter("bundlecode");
		          String bundlename      = request.getParameter("bundlename");
		          String productRequest  = bundlecode.concat(" - ").concat(bundlename);
		          String unitCode    	 = request.getParameter("unitcode");;
		          int	 requestQty      = 0;
		          int    productQuantity = 0;
	        	  int    productStatus   = 0;
	        	  
		          Map paramMap = new HashMap();
		          List resultList = new ArrayList();
	        	  paramMap.put("productRequest",productRequest);
	        	  paramMap.put("bundlecode",bundlecode);
	        	  paramMap.put("bundlename",bundlename);
	        	  paramMap.put("unitCode",unitCode);
	        	  paramMap.put("productQuantity",productQuantity);
	        	  paramMap.put("requestQuantity",requestQty);
	        	  paramMap.put("productStatus",productStatus);
	        	  paramMap.put("salesOrderNo",salesOrderNo);
	        	  paramMap.put("userId",userId);
	        	  paramMap.put("warehouse",warehouse);
	        	  paramMap.put("corporate",corporate);
				  resultList.add(paramMap);
				  
				  if(request.getParameter("qtyBundle")!=null && !request.getParameter("qtyBundle").equalsIgnoreCase("")){
		        	  requestQty   = Integer.parseInt(request.getParameter("qtyBundle"));
		          }
		          
				  int proQty=0;
				  if(request.getParameter("quantity")== null || request.getParameter("quantity").equals("")){
					  proQty=0;
				  }
				  else
				  {
					  proQty = Integer.parseInt(request.getParameter("quantity"));
				  }
				  
				   if(requestQty>0){
		        	  if(request.getSession().getAttribute("resultListSalesOrder") != null){
		        		 resultList = (List)request.getSession().getAttribute("resultListSalesOrder");
		        	  }
		        	   
		        	  Map tableMap = new HashMap();
		        	  Map returnMap = new HashMap();
		        	  boolean isHasBeenAdded = false;
		        	  for(int c=0; c<resultList.size();c++){
		        		  returnMap = (Map)resultList.get(c);
		        		  if(returnMap.get("productRequest").equals(productRequest)&&returnMap.get("bundlecode").equals(bundlecode)&&
		        			 returnMap.get("bundlename").equals(bundlename)){
		        			 isHasBeenAdded = true;
		        			 Integer qty = (Integer)returnMap.get("requestQuantity");
		        			 returnMap.put("requestQuantity", qty + requestQty);
		        			 tableMap.put(returnMap, returnMap);
		        			 break;
		        		  }
		        	  }
		        	  if(!isHasBeenAdded){
		        		  
			        	  paramMap.put("productRequest",productRequest);
			        	  paramMap.put("bundlecode",bundlecode);
			        	  paramMap.put("bundlename",bundlename);
			        	  paramMap.put("unitCode",unitCode);
			        	  paramMap.put("productQuantity",productQuantity);
			        	  paramMap.put("requestQuantity",requestQty);
			        	  paramMap.put("productStatus",productStatus);
			        	  paramMap.put("salesOrderNo",salesOrderNo);
			        	  paramMap.put("userId",userId);
			        	  paramMap.put("warehouse",warehouse);
			        	  paramMap.put("corporate",corporate);
						  resultList.add(paramMap);
						  
						  for(int i=0;i<resultList.size();i++){						
								returnMap = (Map)resultList.get(i);  
					        	tableMap.put(returnMap, returnMap);
					      }

		        	  }
		        	  request.getSession().setAttribute("resultListSalesOrder", resultList);
		        	  List returnList = (List)request.getSession().getAttribute("resultListSalesOrder");
					  Map tResultMap = new HashMap();
					  for(int d=0;d<returnList.size();d++){						
						Map resultMap = (Map)returnList.get(d);  
						tResultMap.put(resultMap, resultMap);
			          }
					 	map.put("tableMap",tResultMap);
		        	  
		          }
		         
		        	  
		          return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
		      }
	      
		      
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
	 }
	 public ModelAndView btnAddDtlProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  Map map = new HashMap();
		
	      try{
	    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    	  	String userId = lu.getUserId();
				String corporate = lu.getCorpId();
				String warehouse = lu.getWhCode();
				//String salesOrderNo = ""+new ctrlIDGenerator().getIDSalesOrder();
				//String salesOrderNo = request.getParameter("salesOrderNo");
				Date date = (Date)new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				StringBuilder now = new StringBuilder(sdf.format(date));
				
				String salesOrderNo = request.getParameter("salesOrderNo");
				//String deliveryName = request.getParameter("deliveryname");
				String deliveryAddress = request.getParameter("deliveryaddress");
				String deliveryDate = request.getParameter("deliverydate");
				String remarks = request.getParameter("remarks");
				
				 String delivery_name = "";
					if(request.getParameter("deliveryname")!=null){
						delivery_name = request.getParameter("deliveryname");
					}
					if(request.getParameter("deliveryname1")!=null){
						delivery_name = request.getParameter("deliveryname1");
					}
				
				map = this.getModelByPrimaryKey(request);
				map.put("mode", "create");
				map.put("salesOrderNo",salesOrderNo);
				map.put("userId", userId);
				map.put("deliveryName", delivery_name);
				map.put("deliveryAddress", deliveryAddress);
				map.put("deliveryDate", deliveryDate);
				map.put("remarks", remarks);
				map.put("warehouse", warehouse);
				map.put("date", now);
				map.put("corporate", corporate);
	    	  
		      if (request.getParameter("productcode") != null || !request.getParameter("productcode").equals("")) {
		    	  String productCode     = request.getParameter("productcode");
		          String productName     = request.getParameter("productname");
		          String productRequest  = productCode.concat(" - ").concat(productName);
		          String unitCode    = request.getParameter("sku");
		          int	 requestQty      = 0;
		          int    productQuantity = 0;
	        	  int    productStatus   = 0;
	        	  
	        	  ProductDao dao = DaoFactory.createProductDao();
        	      String productId = "";
        	      List<Product> p = dao.findWhereProductCodeEquals(productCode);
        	      for(Product pro : p){
        	    	  productId = pro.getProductId();
        	      }
		         
		          Map paramMap = new HashMap();
		          List resultList = new ArrayList();
	        	  paramMap.put("productRequest",productRequest);
	        	  paramMap.put("productId",productId);
	        	  paramMap.put("productCode",productCode);
	        	  paramMap.put("productName",productName);
	        	  paramMap.put("unitCode",unitCode);
	        	  paramMap.put("productQuantity",productQuantity);
	        	  paramMap.put("requestQuantity",requestQty);
	        	  paramMap.put("productStatus",productStatus);
	        	  paramMap.put("salesOrderNo",salesOrderNo);
	        	  paramMap.put("userId",userId);
	        	  paramMap.put("warehouse",warehouse);
	        	  paramMap.put("corporate",corporate);
				  resultList.add(paramMap);
				  
				  if(request.getParameter("qtyProduct")!=null && !request.getParameter("qtyProduct").equalsIgnoreCase("")){
		        	  requestQty   = Integer.parseInt(request.getParameter("qtyProduct"));
		          }
		          
				  int proQty=0;
				  if(request.getParameter("quantity")== null || request.getParameter("quantity").equals("")){
					  proQty=0;
				  }
				  else
				  {
					  proQty = Integer.parseInt(request.getParameter("quantity"));
				  }
				  
				   if(requestQty>0){
		        	  if(request.getSession().getAttribute("resultListSalesOrder") != null){
		        		 resultList = (List)request.getSession().getAttribute("resultListSalesOrder");
		        	  }
		        	   
		        	  Map tableMap = new HashMap();
		        	  Map returnMap = new HashMap();
		        	  boolean isHasBeenAdded = false;
		        	  for(int c=0; c<resultList.size();c++){
		        		  returnMap = (Map)resultList.get(c);
		        		  if(returnMap.get("productRequest").equals(productRequest)&&returnMap.get("productCode").equals(productCode)&&
		        			 returnMap.get("productName").equals(productName)){
		        			 isHasBeenAdded = true;
		        			 Integer qty = (Integer)returnMap.get("requestQuantity");
		        			 returnMap.put("requestQuantity", qty + requestQty);
		        			 tableMap.put(returnMap, returnMap);
		        			 break;
		        		  }
		        	  }
		        	  if(!isHasBeenAdded){
		        		  
		        	      paramMap.put("productId",productId);  
			        	  paramMap.put("productRequest",productRequest);
			        	  paramMap.put("productCode",productCode);
			        	  paramMap.put("productName",productName);
			        	  paramMap.put("unitCode",unitCode);
			        	  paramMap.put("productQuantity",productQuantity);
			        	  paramMap.put("requestQuantity",requestQty);
			        	  paramMap.put("productStatus",productStatus);
			        	  paramMap.put("salesOrderNo",salesOrderNo);
			        	  paramMap.put("userId",userId);
			        	  paramMap.put("warehouse",warehouse);
			        	  paramMap.put("corporate",corporate);
						  resultList.add(paramMap);
						  
						  for(int i=0;i<resultList.size();i++){						
								returnMap = (Map)resultList.get(i);  
					        	tableMap.put(returnMap, returnMap);
					      }

		        	  }
		        	  request.getSession().setAttribute("resultListSalesOrder", resultList);
		        	  List returnList = (List)request.getSession().getAttribute("resultListSalesOrder");
					  Map tResultMap = new HashMap();
					  for(int d=0;d<returnList.size();d++){						
						Map resultMap = (Map)returnList.get(d);  
						tResultMap.put(resultMap, resultMap);
			          }
					 	map.put("tableMap",tResultMap);
		        	  
		          }
		         
		        	  
		          return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
		      }
	      
		      }catch(Exception e){
		    	  e.printStackTrace();
		    	  
		      }
	      
	      		return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
	  }

	 public ModelAndView btnUploadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  Map map = new HashMap();
		 
	      try{
	    	  
	    	  String salesOrderFile = request.getParameter("salesorderFile");
	    	  File file = new File("C:\\Program Files\\UploadFile\\product.txt");
	    	  String path = file.toString();
	    	  
	    	  /*
				BULK
				INSERT CSVTest
				FROM 'C:\Program Files\UploadFile\product.txt'
				WITH
				(
				FIELDTERMINATOR = ',',
				ROWTERMINATOR = '\n'
				)
	    	  */
	    	  
	    	  
	    	  
	    	  /*
	    	  String salesOrderFile = request.getParameter("salesorderFile");
	    	  //String path = request.getRealPath("salesorderFile");
	    	  //System.out.println("path : "+path);
	    	  	File files = new File(salesOrderFile);
				String filePath = files.getAbsolutePath();
				System.out.println("filePath ="+filePath);
	    	  
	    	  
	    	  
	    	  InputStream is = new ByteArrayInputStream(salesOrderFile.getBytes());
	    	  
	    	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	  
	    	  BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    	  
	    	  String line = null;
	    	  URL url = null;
	    	  while ((line = br.readLine()) != null) {
	    		  
	    		  File file = new File(line);
	    		 // System.out.println("directory ="+file.getAbsolutePath());
		    	  url = file.toURL();
		    	   
	    	  }
	    	 
	    	   br.close();
	    	   
	    	   */
	    	   
	    	  // System.out.println("url ="+url);
	    	  
	    	   
	    	   
		      return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
		      
	      
		      }catch(Exception e){
		    	  System.out.println("error upload : "+e.getMessage());
		    	  e.printStackTrace();
		    	  
		      }
	      
	      	 return new ModelAndView("6_picking/SalesOrderAdd", "model", map);
	  }


	 private ModelAndView listPickingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListSalesOrder");
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
		 return new ModelAndView("6_picking/SalesOrderList", "model", map);
	}


	/**
	  *
	  * Method 'listProduct'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   Map map = new HashMap();
		   try {
			   
				LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

				String userId = lu.getUserId();
				String corpId = lu.getCorpId();
				String whCode = lu.getWhCode();

				WarehouseLocationDao daoWhLocation = DaoFactory.createWarehouseLocationDao();
				List<WarehouseLocation> dropListWhLocation = daoWhLocation.findAll();
				if (dropListWhLocation.size() > 0) {
					map.put("dropListWhLocation", dropListWhLocation);
				}

				Product products = new Product();
				Picking picking = new Picking();
				PickingDetail pickingDetail = new PickingDetail();
		
				PickingDao daoPicking = DaoFactory.createPickingDao();
				List<PickingDetail> pickingDetailSearch = daoPicking.findProductLocation(pickingDetail);

				Map tableMap = new HashMap();			
				for (PickingDetail searchProduct : pickingDetailSearch){
				
					Map returnMap = new HashMap();			
					String productCode  =((PickingDetail)searchProduct).getProducts().getProductCode();
					String productName  =((PickingDetail)searchProduct).getProducts().getProductName();
					int quantity 		=((PickingDetail)searchProduct).getBalance();
					String unitCode		=((PickingDetail)searchProduct).getUnitCode();
					String locationCode =((PickingDetail)searchProduct).getWhlocation().getLocationCode();
					
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					returnMap.put("unitCode",unitCode);
					returnMap.put("locationCode",locationCode);		

					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "picking", e);
		   }
	 			
		   return new ModelAndView("10_component/PickListProductSO", "model", map);
		   
	 }
	
	 @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	       
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String salesOrderNo = request.getParameter("salesOrderNo");
	        String corpId = loginUser.getCorpId();
	        String whCode = loginUser.getWhCode();
	        
	        HashMap model = new HashMap();

	        SalesOrderDetailDao dao = DaoFactory.createSalesOrderDetailDao();
	        SalesOrderDetail dto	= dao.findByPrimaryKey(salesOrderNo);
	        
	        if(dto != null){
	            System.out.println("[SalesOrderNo][Ajax Document] sales order no : " + salesOrderNo + " is valid");

	            SalesOrderDetail sod = new SalesOrderDetail();
	            sod.setSo_number(salesOrderNo);
	            sod.setCorpId(corpId);
	            sod.setWhCode(whCode);
	            
	            List<SalesOrderDetail> listSearch = dao.findDetail(sod);
	            System.out.println("listSearch ="+listSearch);
	            Map tableMap = new HashMap();
	            for (SalesOrderDetail searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
                    String so_number    = ((SalesOrderDetail)searchDetail).getSo_number();
					String productCode  = ((SalesOrderDetail)searchDetail).getProduct().getProductCode();
					String productName  = ((SalesOrderDetail)searchDetail).getProduct().getProductName();
					String bundleCode  = ((SalesOrderDetail)searchDetail).getBundle().getBundleCode();
					String bundleName  = ((SalesOrderDetail)searchDetail).getBundle().getBundleName();
										
					int quantity    	= ((SalesOrderDetail)searchDetail).getQuantitySO();
					returnMap.put("so_number",so_number);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("bundleCode",bundleCode);
					returnMap.put("bundleName",bundleName);
					returnMap.put("quantity",quantity);
					
					tableMap.put(returnMap, returnMap);
				}
	            
	            model.put("master", dto);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[SalesOrderNo][Ajax Document] sales order no : " + salesOrderNo + " is not valid");
	        }

	        return new ModelAndView("6_picking/util/SalesOrderDetail", "model", model);
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
					Logger.getLogger(SalesOrderController.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(SalesOrderController.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
			
			
		
}
