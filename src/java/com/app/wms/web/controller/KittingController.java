package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.app.web.engine.search.KittingSearch;
import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.UnitCode;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;


public class KittingController extends ReportManagerController 

{

	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
           
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("7_packing/KittingEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("7_packing/KittingList", "model", m);
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

            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            Kitting k = new Kitting();
            k.setCorpId(lu.getCorpId());
            k.setWhCode(lu.getWhCode());
            KittingDao dao = DaoFactory.createKittingDao();
            List<Kitting> listSearchPage = dao.findKittingPaging(k,page);

            int total = 2000; 
            m.put("kitting", listSearchPage);
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
            java.lang.String kittingCode = request.getParameter("kittingCode");
            KittingDao dao = DaoFactory.createKittingDao();
            Kitting dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(kittingCode);
            }

            if (dto == null) {
                dto = new Kitting();
                dto.setKittingNo("");
                //dto.setName("");
                //dto.setIsActive("Y");
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
			
			KittingDao dao = DaoFactory.createKittingDao();
		
			List<Kitting> dto = dao.findAll();
			
			request.getSession().removeAttribute("resultListKitting");
			return new ModelAndView( "7_packing/KittingList", "result", dto );
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
		request.getSession().removeAttribute("resultListKitting");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		String kittingNo = ""+new ctrlIDGenerator().getIDKitting();
		Date date = (Date)new Date();
		Timestamp ts = new Timestamp(date.getTime());
	    
		Kitting kitt = new Kitting();
	    kitt.setKittingNo(kittingNo);
	    request.getSession().setAttribute("Kitting", kitt);
		
	    HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		
		m.put("mode", "create");
		m.put("kittingNo",kittingNo);
		m.put("warehouse", warehouse);
		m.put("date", ts);
		m.put("corporate", corporate);
		return new ModelAndView( "7_packing/KittingAdd", "model", m);
	}

	/**
	 * Method 'saveKitting'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView saveKitting(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		
		try{
			String btnAddDtlProduct = request.getParameter("btnAddDtlProduct");
			String btnCancel = request.getParameter("btnCancel");
			
			if (btnCancel != null && !btnCancel.equals("")) {
				 request.getSession().removeAttribute("resultListKitting");
				 return listPickingByAuthLogin(request, response);
			}else if (btnAddDtlProduct != null && !btnAddDtlProduct.equals("")) {
	             return btnAddDtlProduct(request, response);
	        }
			
			LoginUser lu 			= (LoginUser) request.getSession().getAttribute("user");
			String userId 			= lu.getUserId();
			String kittingDate 		= request.getParameter("kittingDate");
			String corporate 		= request.getParameter("corporate");
			String warehouse 		= request.getParameter("warehouse");
			String soNumber  		= (String)request.getSession().getAttribute("sessionSO");
			
			String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		    Date kitt_date = sdf.parse(kittingDate);
		    Timestamp dt =  new Timestamp(kitt_date.getTime());
		    Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			String kittingNo = request.getParameter("kittingNo");
			List returnList = (List)request.getSession().getAttribute("resultListKitting");
			
			if (returnList == null)
            {
				  lu = (LoginUser) request.getSession().getAttribute("user");
				  corporate = lu.getCorpId();
				  warehouse = lu.getWhCode();
				  kittingNo = ""+new ctrlIDGenerator().getIDKitting();
				  date = (Date)new Date();
			      ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("kittingNo",kittingNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("msg", " Kitting doesn't have product request list! please input that product or cancel this process");
				  return new ModelAndView( "7_packing/KittingAdd", "model", map);
            }
			
			else if(returnList.size()>0)
			{	
	        	String productRequest  = "";
	        	String productId	   = "";
	        	String productCode     = "";
	        	String productName     = "";
	        	int    qtyPick		   = 0;
	        	int    productQuantity = 0;
	        	String unitCode		   = "";
				  
	        	for(int i=0;i<returnList.size();i++){		
	        		Map returnMap = (Map)returnList.get(i);  

	        		productRequest  = (String) returnMap.get("productRequest");
	        		productId		= (String) returnMap.get("productId");
		        	productCode     = (String) returnMap.get("productCode");
		        	productName     = (String) returnMap.get("productName");
		        	unitCode		= (String) returnMap.get("unitCode");
		        	qtyPick			= (Integer) returnMap.get("qtyPick");
		        	productQuantity = (Integer) returnMap.get("requestQuantity");
		        	
		        	KittingDao dao = DaoFactory.createKittingDao();
					KittingDetailDao daodtl = DaoFactory.createKittingDetailDao();
		        	
					Kitting dto 			= dao.findByPrimaryKey(kittingNo);
					KittingDetail dso = null;
					
					boolean isCreate = dto == null;
					boolean detail = dso == null;
					if(isCreate){
						dto = new Kitting();
					}

					dto.setKittingNo(kittingNo);
					dto.setKittingDate(dt);
					dto.setCreatedBy(userId);
					dto.setCreatedDate(ts);
					dto.setUpdatedBy(userId);
					dto.setUpdatedDate(ts);
					
				    if(isCreate){
					   dao.insert(dto);
					}
					else
					{
					   dao.update(dto.createPk(), dto);
					}
					
					if(detail){
						
						dso = new KittingDetail();
							
							Product          pro = new Product();
						    UnitCode         uni = new UnitCode();
						    User             usr = new User();
						    Corporate 		 crp = new Corporate();
						    Wh				 wh  = new Wh();
						    
						    dso.setKittingNo((String)returnMap.get("kittingNo"));
						    dso.setSo_number(soNumber);
						    pro.setProductId(productId);
						    pro.setProductCode(productCode);
						    pro.setProductName(productName);
						    uni.setName(unitCode);
						    dso.setQtyPick((Integer)returnMap.get("qtyPick"));
						    dso.setQtyKitting((Integer)returnMap.get("requestQuantity"));
						    usr.setUserId((String)returnMap.get("userId"));
						    crp.setId((String)returnMap.get("corporate"));
						    wh.setWhCode((String)returnMap.get("warehouse"));
						    dso.setProductId(pro.getProductId());
						    dso.setProductCode(pro.getProductCode());
						    dso.setProductName(pro.getProductName());
						    dso.setUnitCode(uni.getName());
						    dso.setUserId(usr.getUserId());
						    dso.setCorpId(crp.getId());
						    dso.setWhCode(wh.getWhCode());
						    if(dso.getKittingNo().equalsIgnoreCase((String)returnMap.get("kittingNo"))){
						       daodtl.insert(dso);
						    }
						
					}
					else
					{
						daodtl.update(dso.createPk(), dso);
					}
					
	        	
	        	}
	        	
	        	corporate="";
	        	warehouse="";
	        	kittingNo="";
			} 
			
			  lu = (LoginUser) request.getSession().getAttribute("user");
			  corporate = lu.getCorpId();
			  warehouse = lu.getWhCode();
			  kittingNo = ""+new ctrlIDGenerator().getIDKitting();
			  date = (Date)new Date();
		      ts = new Timestamp(date.getTime());
			
			  map = this.getModelByPrimaryKey(request);
			  map.put("mode", "create");
			  map.put("kittingNo",kittingNo);
			  map.put("warehouse", warehouse);
			  map.put("date", ts);
			  map.put("corporate", corporate);
			
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "kitting", e);
		}
		// return beforeConfirm(request, response, pickNumber);
		map.put("msg", " Kitting Process Already Created");
		request.getSession().removeAttribute("resultListKitting");
        return new ModelAndView("7_packing/KittingList", "model", map);
	}
	
	 @Transactional
	  public ModelAndView addSelectedProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
		  Map map = new HashMap();
		  boolean add = false;
		  try{
			  if (request.getParameter("productCode") != null && !request.getParameter("productCode").equals("")) {
		     
				  String productId   = request.getParameter("productId");
		          String productCode = request.getParameter("productCode");
		          String productName = request.getParameter("productName");
		          String locationCode = request.getParameter("locationCode");
		          String quantity = request.getParameter("quantity");
		          String unitCode = request.getParameter("unitCode");
		         
		      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
				  String corporate = lu.getCorpId();
				  String warehouse = lu.getWhCode();
				  String kittingNo = ""+new ctrlIDGenerator().getIDKitting();
				  String soNumber  = (String)request.getSession().getAttribute("sessionSODate");
				  
				  Date date = (Date)new Date();
				  Timestamp ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("kittingNo",kittingNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("salesOrder", soNumber);
				  map.put("productId",productId);
				  map.put("productCode",productCode);
		          map.put("productName",productName);
		          map.put("quantity",quantity);
		          map.put("unitCode", unitCode);
		          map.put("locationCode",locationCode);	  
		         
		          Map tableMap = new HashMap();	
		          if (request.getSession().getAttribute("resultListKitting")!= null){
					  
					  List returnList = (List)request.getSession().getAttribute("resultListKitting");
					  for(int i=0;i<returnList.size();i++){						
						Map returnMap = (Map)returnList.get(i);  
			        	tableMap.put(returnMap, returnMap);
			          }
					 	map.put("tableMap",tableMap);
					 
				  }
		          
		          return new ModelAndView("7_packing/KittingAdd", "model", map);
		      } else {
		          return new ModelAndView("7_packing/KittingAdd");
		      }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return new ModelAndView("7_packing/KittingAdd", "model", map);
	  }
	
	 
	 public ModelAndView btnAddDtlProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  Map map = new HashMap();
		
	      try{
	    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    	  	String userId = lu.getUserId();
				String corporate = lu.getCorpId();
				String warehouse = lu.getWhCode();
				String kittingNo = ""+new ctrlIDGenerator().getIDKitting();
				Date date = (Date)new Date();
				Timestamp ts = new Timestamp(date.getTime());
				String soNumber = (String)request.getSession().getAttribute("sessionSODate");
				
				map = this.getModelByPrimaryKey(request);
				map.put("mode", "create");
				map.put("kittingNo",kittingNo);
				map.put("userId", userId);
				map.put("warehouse", warehouse);
				map.put("date", ts);
				map.put("corporate", corporate);
				map.put("salesOrder", soNumber);
	    	  
		      if (request.getParameter("productCode") != null || !request.getParameter("productCode").equals("")) {
		    	  String productId		 = request.getParameter("productId");
		    	  String productCode     = request.getParameter("productCode");
		          String productName     = request.getParameter("productName");
		          String unitCode		 = request.getParameter("unitCode");
		          int qtyPick 			 = Integer.parseInt(request.getParameter("quantity"));
		          String productRequest  = productCode.concat(" - ").concat(productName);
		          int	 requestQty      = 0;
		        
		          Map paramMap = new HashMap();
		          List resultList = new ArrayList();
		          paramMap.put("productRequest",productRequest);
		          paramMap.put("productId",productId);
	        	  paramMap.put("productCode",productCode);
	        	  paramMap.put("productName",productName);
	        	  paramMap.put("unitCode",unitCode);
	        	  paramMap.put("qtyPick",qtyPick);
	        	  paramMap.put("requestQuantity",requestQty);
	        	  paramMap.put("kittingNo",kittingNo);
	        	  paramMap.put("userId",userId);
	        	  paramMap.put("warehouse",warehouse);
	        	  paramMap.put("corporate",corporate);
				  resultList.add(paramMap);
				  
				  if(request.getParameter("qtyProduct")!=null && !request.getParameter("qtyProduct").equalsIgnoreCase("")){
		        	  requestQty   = Integer.parseInt(request.getParameter("qtyProduct"));
		          }
				  
				  if(requestQty>0){
		        	  if(request.getSession().getAttribute("resultListKitting") != null){
		        		 resultList = (List)request.getSession().getAttribute("resultListKitting");
		        	  }
		        	   
		        	  Map tableMap = new HashMap();
		        	  Map returnMap = new HashMap();
		        	  boolean isHasBeenAdded = false;
		        	  for(int c=0; c<resultList.size();c++){
		        		  returnMap = (Map)resultList.get(c);
		        		  if(returnMap.get("productCode").equals(productCode)&&
		        			 returnMap.get("productName").equals(productName)){
		        			 isHasBeenAdded = true;
		        			 Integer qty = (Integer)returnMap.get("requestQuantity");
		        			 returnMap.put("requestQuantity", qty + requestQty);
		        			 tableMap.put(returnMap, returnMap);
		        			 break;
		        		  }
		        	  }
		        	  if(!isHasBeenAdded){
		        		  paramMap.put("productRequest",productRequest);
		        		  paramMap.put("productId",productId);
			        	  paramMap.put("productCode",productCode);
			        	  paramMap.put("productName",productName);
			        	  paramMap.put("unitCode",unitCode);
			        	  paramMap.put("qtyPick",qtyPick);
			        	  paramMap.put("requestQuantity",requestQty);
			        	  paramMap.put("kittingNo",kittingNo);
			        	  paramMap.put("userId",userId);
			        	  paramMap.put("warehouse",warehouse);
			        	  paramMap.put("corporate",corporate);
						  resultList.add(paramMap);
						  
						  for(int i=0;i<resultList.size();i++){						
								returnMap = (Map)resultList.get(i);  
					        	tableMap.put(returnMap, returnMap);
					      }

		        	  }
		        	  
		        	  request.getSession().setAttribute("resultListKitting", resultList);
		        	  List returnList = (List)request.getSession().getAttribute("resultListKitting");
					
		        	  Map tResultMap = new HashMap();
					  for(int d=0;d<returnList.size();d++){						
						Map resultMap = (Map)returnList.get(d);  
						tResultMap.put(resultMap, resultMap);
			          }
					  map.put("tableMap",tResultMap);
				  }
		        	  
		          return new ModelAndView("7_packing/KittingAdd", "model", map);
		      }
	      
		      }catch(Exception e){
		    	  e.printStackTrace();
		    	  
		      }
	      
	      		return new ModelAndView("7_packing/KittingAdd", "model", map);
	  }


	 private ModelAndView listPickingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListKitting");
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
		 return new ModelAndView("7_packing/KittingList", "model", map);
	}

	  /**
	  *
	  * Method 'listSalesOrderKitting'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listSalesOrderKitting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   Map map = new HashMap();
		   try {
			   	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
			    Picking p = new Picking();
			    p.setCorpId(lu.getCorpId());
			    p.setWhCode(lu.getWhCode());
			   	PickingDao dao = DaoFactory.createPickingDao();
			   	List<Picking> listSalesOrder = dao.findSalesOrderPickingList(p);
			   	System.out.println("listSalesOrder>>="+listSalesOrder);
			   	String remarks = "";
			   	for(Picking pi : listSalesOrder){
			   		remarks = ((Picking)pi).getSalesOrder().getRemarks();
			   	}
			   	System.out.println("remarks ="+remarks);
				map.put("remarks", remarks);
			   	map.put("listSalesOrder", listSalesOrder);
			   	/*
				Map tableMap = new HashMap();			
				for (Picking searchSalesOrder : listSalesOrder){
				
					Map returnMap = new HashMap();			
					String salesOrderNumber  =((Picking)searchSalesOrder).getSoNumber();
					Date salesOrderDate  =((Picking)searchSalesOrder).getPickingDate();
	
					returnMap.put("salesOrderNumber",salesOrderNumber);
					returnMap.put("salesOrderDate",salesOrderDate);
	
					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
				*/
			
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "Pick List Sales Order", e);
		   }
	 			
		   return new ModelAndView("10_component/PickListSalesOrderKitting", "model", map);
		   
	 }
	 
	 @Transactional
	 public ModelAndView addSelectedSalesOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		  Map map = new HashMap();
		  boolean add = false;
		  try{
		      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
				  String corporate = lu.getCorpId();
				  String warehouse = lu.getWhCode();
				  String kittingNo = ""+new ctrlIDGenerator().getIDKitting();
				  String salesOrderNumber = request.getParameter("salesOrderNumber");
				  request.getSession().setAttribute("sessionSO", salesOrderNumber);
		          String salesOrderDate   = request.getParameter("salesOrderDate");
		          String salesOrder       = salesOrderNumber.concat(" ").concat("  -  ").concat(" ").concat(salesOrderDate);
		          request.getSession().setAttribute("sessionSODate", salesOrder);
		          Date date = (Date)new Date();
				  Timestamp ts = new Timestamp(date.getTime());
				  
				  SalesOrder so = new SalesOrder();
					so.setSo_number(salesOrderNumber);
					SalesOrderDao dao = DaoFactory.createSalesOrderDao();
					List<SalesOrder> soSearch = dao.findSO(so);
					
					String remarks ="";
					for(SalesOrder s : soSearch){
						remarks = ((SalesOrder)s).getRemarks();
					}
					System.out.println("remarks ="+remarks);
		          
				  map = this.getModelByPrimaryKey(request);
				  
				  map.put("mode", "create");
				  map.put("kittingNo",kittingNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("salesOrderNumber", salesOrderNumber);
				  map.put("salesOrderDate", salesOrderDate);
				  map.put("salesOrder", salesOrder);
				  map.put("remarks", remarks);
		         
		          //Map tableMap = new HashMap();	
		          
		          return new ModelAndView("7_packing/KittingAdd", "model", map);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return new ModelAndView("7_packing/KittingAdd", "model", map);
	 }

	 /**
	  *
	  * Method 'listProductKitting'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listProductKitting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   Map map = new HashMap();
		   try {
			   
				LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

				String userId = lu.getUserId();
				String corpId = lu.getCorpId();
				String whCode = lu.getWhCode();

				Kitting kitting = new Kitting();
				String salesOrderNumber = (String)request.getSession().getAttribute("sessionSO");
				KittingDetail kittingDetail = new KittingDetail();
				kittingDetail.setSo_number(salesOrderNumber);
				KittingDao daoKitting = DaoFactory.createKittingDao();
				List<KittingDetail> kittingDetailSearch = daoKitting.findProductLocation(kittingDetail);
				
				Map tableMap = new HashMap();			
				for (KittingDetail searchProduct : kittingDetailSearch){
				
					Map returnMap = new HashMap();	
					String productId	=((KittingDetail)searchProduct).getProductId();
					String productCode  =((KittingDetail)searchProduct).getProductCode();
					String productName  =((KittingDetail)searchProduct).getProductName();
					int quantity 		=((KittingDetail)searchProduct).getQtyPick();
					String unitCode		=((KittingDetail)searchProduct).getUnitCode();
					
					returnMap.put("productId",productId);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					returnMap.put("unitCode",unitCode);

					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "kitting", e);
		   }
	 			
		   return new ModelAndView("10_component/PickListProductKitting", "model", map);
		   
	 }
	
	 @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        /*
	         *  GET LOGIN USER
	         */
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String kittingNo = request.getParameter("kittingNo");
	        HashMap model = new HashMap();

	        KittingDetailDao dao = DaoFactory.createKittingDetailDao();
	        KittingDetail dto	= dao.findByPrimaryKey(kittingNo);
	        
	        if(dto != null){
	            System.out.println("[KittingNo][Ajax Document] kitting no : " + kittingNo + " is valid");

	            KittingDetail kd = new KittingDetail();
	            kd.setKittingNo(kittingNo);
	            
	            List<KittingDetail> listSearch = dao.findDetail(kd);
	            Map tableMap = new HashMap();
	            for (KittingDetail searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
                    kittingNo  = ((KittingDetail)searchDetail).getKittingNo();
					String productCode  = ((KittingDetail)searchDetail).getProductCode();
					String productName  = ((KittingDetail)searchDetail).getProductName();
					int quantity    	= ((KittingDetail)searchDetail).getQtyKitting();
					returnMap.put("kittingNo",kittingNo);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					
					tableMap.put(returnMap, returnMap);
				}
	            
	            model.put("master", dto);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[KittingNo][Ajax Document] kitting no : " + kittingNo + " is not valid");
	        }

	        return new ModelAndView("7_packing/util/KittingDetail", "model", model);
	    }

	 /**
		 * Method 'findAll'
		 * 
		 * @param request
		 * @param response
		 * @throws Exception
		 * @return ModelAndView
		 */
	 @Transactional
		public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			
				
				String kittingNo = request.getParameter("kittingNo");
				System.out.println("kittingNo ="+kittingNo);
				
				templateName = request.getParameter("templateName");
				System.out.println("templateName ="+templateName);
				
				parametersKey = request.getParameter("parametersKey");
				System.out.println("parameterKey ="+parametersKey);
				
				ArrayList resultList = new ArrayList();
				resultList.add(kittingNo);
				setParameterValues(resultList);
				
				List paramKey = new ArrayList();
				paramKey.add(parametersKey);
				setParameterKeys((ArrayList<String>) paramKey);
				outputFormat = "pdf";
				createOnlineReport();
				
				try{
					printToStream(response);
					
				}catch(FileNotFoundException ex){
					Logger.getLogger(KittingController.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(KittingController.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
	 
	 /**
	   *
	   * Method 'doSearch'
	   *
	   * @param request
	   * @param response
	   * @return ModelAndView
	   * @throws Exception
	   */
	  @Transactional
	  public ModelAndView doSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
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
				Kitting kitting = new Kitting();
				KittingDetail kittingDetail = new KittingDetail();
				
				products.setProductCode(request.getParameter("productCode"));
				products.setProductName(request.getParameter("productName"));
				WarehouseLocation location = new WarehouseLocation();
				location.setLocationCode(request.getParameter("whLocation"));
				
				//kittingDetail.setProducts(products);
				kittingDetail.setProductCode(products.getProductCode());
				kittingDetail.setProductName(products.getProductName());
				//kittingDetail.setWhlocation(location);
				
				KittingDao daoKitting = DaoFactory.createKittingDao();
				//List<KittingDetail> kittingDetailSearch = daoKitting.findSearchProductLocation(kittingDetail);
				List<KittingDetail> kittingDetailSearch=null;
				
				String productCode ="";
				String productName ="";
				String locationCode ="";

				Map tableMap = new HashMap();			
				for (KittingDetail searchProduct : kittingDetailSearch){
				
					Map returnMap = new HashMap();			
					productCode  =((KittingDetail)searchProduct).getProductCode();
					productName  =((KittingDetail)searchProduct).getProductName();
					//int quantity =((KittingDetail)searchProduct).getBalance();
					String unitCode		=((KittingDetail)searchProduct).getUnitCode();
					//locationCode =((KittingDetail)searchProduct).getWhlocation().getLocationCode();
					
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					//returnMap.put("quantity",quantity);
					returnMap.put("unitCode",unitCode);
					returnMap.put("locationCode",locationCode);				

					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "kitting", e);
		   }
	  			
		   return new ModelAndView("7_packing/ProductListKitting", "model", map);
		   
	  }
	  
}
