package com.app.wms.web.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.wms.engine.db.dao.*;
import com.app.web.engine.search.BundleSearch;
import com.app.wms.engine.db.dao.BundleDao;
import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.dto.map.BundleProductMap;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.helper.StringHelper;
import com.app.wms.web.util.AppConstant;

public class BundleController extends ReportManagerController 
{


	public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
           
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("1_setup/BundleEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/BundleList", "model", m);
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
            Bundle b = new Bundle();
            b.setCorpId(lu.getCorpId());
            b.setWhCode(lu.getWhCode());
            b.setBundleCode(request.getParameter("bundleCode"));
            b.setBundleName(request.getParameter("name"));
            
            BundleDao dao = DaoFactory.createBundleDao();
            List<Bundle> listSearchPage = dao.findBundlingPaging(b,page);
            System.out.println(">>>>>>>>>>> listSearchPage = "+listSearchPage);
            int total = 2000; 
            m.put("bundle", listSearchPage);
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
            java.lang.String bundleCode = request.getParameter("bundleCode");
            BundleDao dao = DaoFactory.createBundleDao();
            Bundle dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(bundleCode);
            }

            if (dto == null) {
                dto = new Bundle();
                dto.setBundleCode("");
                dto.setBundleName("");
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
			
			BundleDao dao = DaoFactory.createBundleDao();
		
			List<Bundle> dto = dao.findAll();
			
			request.getSession().removeAttribute("resultListBundling");
			return new ModelAndView( "1_setup/BundleList", "result", dto );
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
		request.getSession().removeAttribute("resultListBundling");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		String bundleCode = ""+new ctrlIDGenerator().getIDBundling();
		Date date = (Date)new Date();
		Timestamp ts = new Timestamp(date.getTime());
	    
		Bundle bun = new Bundle();
	    bun.setBundleCode(bundleCode);
	    request.getSession().setAttribute("Bundling", bun);
		
	    HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		m.put("bundleCode",bundleCode);
		m.put("warehouse", warehouse);
		m.put("date", ts);
		m.put("corporate", corporate);
		return new ModelAndView( "1_setup/BundleAdd", "model", m);
	}

	/**
	 * Method 'saveBundling'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView saveBundling(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		
		try{
			String btnAddDtlProduct = request.getParameter("btnAddDtlProduct");
			String btnCancel = request.getParameter("btnCancel");
			
			if (btnCancel != null && !btnCancel.equals("")) {
				 request.getSession().removeAttribute("resultListBundling");
				 return listBundlingByAuthLogin(request, response);
			}else if (btnAddDtlProduct != null && !btnAddDtlProduct.equals("")) {
	             return btnAddDtlProduct(request, response);
	        }
			
			LoginUser lu 			= (LoginUser) request.getSession().getAttribute("user");
			String userId 			= lu.getUserId();
			String bundleDate 		= request.getParameter("bundleDate");
			String corporate 		= request.getParameter("corporate");
			String warehouse 		= request.getParameter("warehouse");
//			String soNumber  		= (String)request.getSession().getAttribute("sessionSO");
			
			String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		    Date bun_date = sdf.parse(bundleDate);
		    Timestamp dt =  new Timestamp(bun_date.getTime());
		    Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			String bundleCode = request.getParameter("bundleCode");
			String bundleName = request.getParameter("bundleName");
			List returnList = (List)request.getSession().getAttribute("resultListBundling");
			
			if (returnList == null)
            {
				  lu = (LoginUser) request.getSession().getAttribute("user");
				  corporate = lu.getCorpId();
				  warehouse = lu.getWhCode();
				  bundleCode = ""+new ctrlIDGenerator().getIDBundling();
				  bundleName = request.getParameter("bundleName");
				  date = (Date)new Date();
			      ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("bundleCode",bundleCode);
				  map.put("bundleName",bundleName);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("msg", " Bundling doesn't have product request list! please input that product or cancel this process");
				  return new ModelAndView( "1_setup/BundleAdd", "model", map);
            }
			
			else if(returnList.size()>0)
			{	
	        	String productRequest  = "";
	        	String productId	   = "";
	        	String productCode     = "";
	        	String productName     = "";
//	        	int    qtyPick		   = 0;
	        	int    productQuantity = 0;
	        	String unitCode		   = "";
				  
	        	for(int i=0;i<returnList.size();i++){		
	        		Map returnMap = (Map)returnList.get(i);  
	        		productRequest  = (String) returnMap.get("productRequest");
	        		productId		= (String) returnMap.get("productId");
		        	productCode     = (String) returnMap.get("productCode");
		        	productName     = (String) returnMap.get("productName");
		        	unitCode		= (String) returnMap.get("unitCode");
//		        	qtyPick			= (Integer) returnMap.get("qtyPick");
		        	productQuantity = (Integer) returnMap.get("requestQuantity");
		        	
		        	BundleDao dao = DaoFactory.createBundleDao();
					BundleDetailDao daodtl = DaoFactory.createBundleDetailDao();
		        	
					Bundle dto 			= dao.findByPrimaryKey(bundleCode);
					BundleDetail dso = null;
					
					boolean isCreate = dto == null;
					boolean detail = dso == null;
					if(isCreate){
						dto = new Bundle();
					}

					dto.setBundleCode(bundleCode);
					dto.setBundleName(bundleName);
					dto.setUserId(userId);
					dto.setCorpId(lu.getCorpId());
					dto.setWhCode(lu.getWhCode());
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
						
						dso = new BundleDetail();
							
							Product          pro = new Product();
						    UnitCode         uni = new UnitCode();
						    User             usr = new User();
						    Corporate 		 crp = new Corporate();
						    Wh				 wh  = new Wh();
						    
//						    dso.setKittingNo((String)returnMap.get("kittingNo"));
//						    dso.setSo_number(soNumber);
						    dso.setBundleCode((String)returnMap.get("bundleCode"));
						    pro.setProductId(productId);
						    pro.setProductCode(productCode);
						    pro.setProductName(productName);
						    uni.setName(unitCode);
//						    dso.setQtyPick((Integer)returnMap.get("qtyPick"));
//						    dso.setQtyKitting((Integer)returnMap.get("requestQuantity"));
						    dso.setQtyBundle((Integer)returnMap.get("requestQuantity"));
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
//						    if(dso.getKittingNo().equalsIgnoreCase((String)returnMap.get("kittingNo"))){
						       daodtl.insert(dso);
//						    }
						
					}
					else
					{
//						daodtl.update(dso.createPk(), dso);
					}
					
	        	
	        	}
	        	
	        	corporate="";
	        	warehouse="";
//	        	kittingNo="";
			} 
			
			  lu = (LoginUser) request.getSession().getAttribute("user");
			  corporate = lu.getCorpId();
			  warehouse = lu.getWhCode();
			  bundleCode = ""+new ctrlIDGenerator().getIDBundling();
			  date = (Date)new Date();
		      ts = new Timestamp(date.getTime());
			
			  map = this.getModelByPrimaryKey(request);
			  map.put("mode", "create");
//			  map.put("kittingNo",kittingNo);
			  map.put("warehouse", warehouse);
			  map.put("date", ts);
			  map.put("corporate", corporate);
			
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "bundling", e);
		}
		// return beforeConfirm(request, response, pickNumber);
		map.put("msg", " Bundling Process Already Created");
		request.getSession().removeAttribute("resultListBundling");
        return new ModelAndView("1_setup/BundleList", "model", map);
	}
	
	 @Transactional
	  public ModelAndView addSelectedProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
		  Map map = new HashMap();
		  boolean add = false;
		  try{
			  if (request.getParameter("productCode") != null && !request.getParameter("productCode").equals("")) {
		     
				  String productId   = request.getParameter("productid");
		          String productCode = request.getParameter("productCode");
		          String productName = request.getParameter("productName");
		          String locationCode = request.getParameter("locationCode");
		          String quantity = request.getParameter("quantity");
		          String unitCode = request.getParameter("unitCode");
		         
		      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
				  String corporate = lu.getCorpId();
				  String warehouse = lu.getWhCode();
				  String bundleCode = ""+new ctrlIDGenerator().getIDBundling();
				  String soNumber  = (String)request.getSession().getAttribute("sessionSODate");
				  
				  Date date = (Date)new Date();
				  Timestamp ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("bundleCode",bundleCode);
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
		          
		          return new ModelAndView("1_setup/BundleAdd", "model", map);
		      } else {
		          return new ModelAndView("1_setup/BundleAdd");
		      }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return new ModelAndView("1_setup/BundleAdd", "model", map);
	  }
	
	 
	 public ModelAndView btnAddDtlProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  Map map = new HashMap();
		
	      try{
	    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    	  	String userId = lu.getUserId();
				String corporate = lu.getCorpId();
				String warehouse = lu.getWhCode();
				String bundleCode = ""+new ctrlIDGenerator().getIDBundling();
				String bundleName = request.getParameter("bundleName");
				Date date = (Date)new Date();
				Timestamp ts = new Timestamp(date.getTime());
				String soNumber = (String)request.getSession().getAttribute("sessionSODate");
				
				map = this.getModelByPrimaryKey(request);
				map.put("mode", "create");
				map.put("bundleCode",bundleCode);
				map.put("bundleName", bundleName);
				map.put("userId", userId);
				map.put("warehouse", warehouse);
				map.put("date", ts);
				map.put("corporate", corporate);
				map.put("salesOrder", soNumber);
	    	  
		      if (request.getParameter("productcode") != null || !request.getParameter("productcode").equals("")) {
//		    	  String productId		 = request.getParameter("productid");
		    	  String productCode     = request.getParameter("productcode");
		          String productName     = request.getParameter("productname");
		          String unitCode		 = request.getParameter("sku");
		          System.out.println("unitCode >>> ="+unitCode);
		       //   int qtyBundle 		 = Integer.parseInt(request.getParameter("quantity"));
		          String productRequest  = productCode.concat(" - ").concat(productName);
		          int	 requestQty      = 0;
		          
		          
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
	        	//  paramMap.put("qtyBundle",qtyBundle);
	        	  paramMap.put("requestQuantity",requestQty);
	        	  paramMap.put("bundleCode",bundleCode);
	        	  paramMap.put("bundleName",bundleName);
	        	  paramMap.put("userId",userId);
	        	  paramMap.put("warehouse",warehouse);
	        	  paramMap.put("corporate",corporate);
				  resultList.add(paramMap);
				  
				  if(request.getParameter("qtyProduct")!=null && !request.getParameter("qtyProduct").equalsIgnoreCase("")){
		        	  requestQty   = Integer.parseInt(request.getParameter("qtyProduct"));
		          }
				  
				  if(requestQty>0){
		        	  if(request.getSession().getAttribute("resultListBundling") != null){
		        		 resultList = (List)request.getSession().getAttribute("resultListBundling");
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
			        	//  paramMap.put("qtyBundle",qtyBundle);
			        	  paramMap.put("requestQuantity",requestQty);
			        	  paramMap.put("bundleCode",bundleCode);
			        	  paramMap.put("bundleName",bundleName);
			        	  paramMap.put("userId",userId);
			        	  paramMap.put("warehouse",warehouse);
			        	  paramMap.put("corporate",corporate);
						  resultList.add(paramMap);
						  
						  for(int i=0;i<resultList.size();i++){						
								returnMap = (Map)resultList.get(i);  
					        	tableMap.put(returnMap, returnMap);
					      }

		        	  }
		        	  
		        	  request.getSession().setAttribute("resultListBundling", resultList);
		        	  List returnList = (List)request.getSession().getAttribute("resultListBundling");
					
		        	  Map tResultMap = new HashMap();
					  for(int d=0;d<returnList.size();d++){						
						Map resultMap = (Map)returnList.get(d);  
						tResultMap.put(resultMap, resultMap);
			          }
					  map.put("tableMap",tResultMap);
				  }
		        	  
		          return new ModelAndView("1_setup/BundleAdd", "model", map);
		      }
	      
		      }catch(Exception e){
		    	  e.printStackTrace();
		    	  
		      }
	      
	      		return new ModelAndView("1_setup/BundleAdd", "model", map);
	  }


	 private ModelAndView listBundlingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListBundling");
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
		 return new ModelAndView("1_setup/BundleList", "model", map);
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

	 /**
	  *
	  * Method 'listProductBundle'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listProductBundle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   Map map = new HashMap();
		   try {
			   
				LoginUser lu = (LoginUser) request.getSession().getAttribute("user");

				String userId = lu.getUserId();
				String corpId = lu.getCorpId();
				String whCode = lu.getWhCode();

				Bundle bundle = new Bundle();
//				String salesOrderNumber = (String)request.getSession().getAttribute("sessionSO");
				BundleDetail bundleDetail = new BundleDetail();
//				kittingDetail.setSo_number(salesOrderNumber);
				BundleDao daoBundling = DaoFactory.createBundleDao();
//				List<BundleDetail> bundleDetailSearch = daoBundling.findProductLocation(bundleDetail);
				List<BundleDetail> bundleDetailSearch = null;
				
				Map tableMap = new HashMap();			
				for (BundleDetail searchProduct : bundleDetailSearch){
				
					Map returnMap = new HashMap();	
					String productId	=((BundleDetail)searchProduct).getProductId();
					String productCode  =((BundleDetail)searchProduct).getProductCode();
					String productName  =((BundleDetail)searchProduct).getProductName();
					int quantity 		=((BundleDetail)searchProduct).getQtyBundle();
					String unitCode		=((BundleDetail)searchProduct).getUnitCode();
					
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
			   return new ModelAndView("Error", "bundling", e);
		   }
	 			
		   return new ModelAndView("10_component/PickListProductBundling", "model", map);
		   
	 }
	
	 @Transactional
	 public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String bundleCode = request.getParameter("bundleCode");
	        String corpId = loginUser.getCorpId();
	        String whCode = loginUser.getWhCode();
	        
	        HashMap model = new HashMap();

	        BundleDetailDao dao = DaoFactory.createBundleDetailDao();
	        BundleDetail dto	= dao.findByPrimaryKey(bundleCode);
	        
	        if(dto != null){
	            System.out.println("[BundleCode][Ajax Document] bundle code : " + bundleCode + " is valid");

	            BundleDetail bud = new BundleDetail();
	            bud.setBundleCode(bundleCode);
	            bud.setCorpId(corpId);
	            bud.setWhCode(whCode);
	            
	           List<BundleDetail> listSearch = dao.findDetail(bud);
	            Map tableMap = new HashMap();
	            for (BundleDetail searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
	            	bundleCode  = ((BundleDetail)searchDetail).getBundleCode();
					String productCode  = ((BundleDetail)searchDetail).getProductCode();
					String productName  = ((BundleDetail)searchDetail).getProductName();
					int quantity    	= ((BundleDetail)searchDetail).getQtyBundle();
					returnMap.put("bundleCode",bundleCode);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					
					tableMap.put(returnMap, returnMap);
				}
	            
	            model.put("master", dto);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[Bundle No][Ajax Document] Bundle no : " + bundleCode + " is not valid");
	        }

	        return new ModelAndView("bundle/BundleDetail", "model", model);
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
			
				
				String bundleCode = request.getParameter("bundleCode");
				System.out.println("bundleCode ="+bundleCode);
				
				templateName = request.getParameter("templateName");
				System.out.println("templateName ="+templateName);
				
				parametersKey = request.getParameter("parametersKey");
				System.out.println("parameterKey ="+parametersKey);
				
				ArrayList resultList = new ArrayList();
				resultList.add(bundleCode);
				setParameterValues(resultList);
				
				List paramKey = new ArrayList();
				paramKey.add(parametersKey);
				setParameterKeys((ArrayList<String>) paramKey);
				outputFormat = "pdf";
				createOnlineReport();
				
				try{
					printToStream(response);
					
				}catch(FileNotFoundException ex){
					Logger.getLogger(BundleController.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(BundleController.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
	 

}
