package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.app.web.engine.search.ConsolidateSearch;
import com.app.wms.engine.db.dao.ConsolidateDao;
import com.app.wms.engine.db.dao.ConsolidateDetailDao;
import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dao.PackingDao;
import com.app.wms.engine.db.dao.PackingDetailDao;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.Bundle;
import com.app.wms.engine.db.dto.Consolidate;
import com.app.wms.engine.db.dto.ConsolidateDetail;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.Packing;
import com.app.wms.engine.db.dto.PackingDetail;
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

public class ConsolidateController extends ReportManagerController {
	
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
                return new ModelAndView("7_packing/ConsolidateEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("7_packing/ConsolidateList", "model", m);
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
            Consolidate c = new Consolidate();
            c.setCorpId(lu.getCorpId());
            c.setWhCode(lu.getWhCode());
            ConsolidateDao dao = DaoFactory.createConsolidateDao();
            List<Consolidate> listSearchPage = dao.findConsolidatePaging(c,page);

            int total = 2000; 
            m.put("consolidate", listSearchPage);
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
            java.lang.String consolidateCode = request.getParameter("consolidateCode");
            ConsolidateDao dao = DaoFactory.createConsolidateDao();
            Consolidate dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(consolidateCode);
            }

            if (dto == null) {
                dto = new Consolidate();
                dto.setConsolidateNo("");
                dto.setConsolidateDate(null);
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
			
			ConsolidateDao dao = DaoFactory.createConsolidateDao();
		
			List<Consolidate> dto = dao.findAll();
		
			return new ModelAndView( "7_packing/ConsolidateList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereConsolidateCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereConsolidateCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pconsolidateCode = request.getParameter("consolidateCode");
		
			// create the DAO class
			ConsolidateDao dao = DaoFactory.createConsolidateDao();
		
			// execute the finder
			List<Consolidate> dto = dao.findWhereConsolidateNoEquals(pconsolidateCode);
		
			return new ModelAndView( "7_packing/ConsolidateList", "result", dto );
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
	public ModelAndView findWhereDateEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			//Date pname = (Date)request.getParameter("consolidateDate");
		
			// create the DAO class
			ConsolidateDao dao = DaoFactory.createConsolidateDao();
		
			// execute the finder
			//List<Consolidate> dto = dao.findWhereDateEquals(pname);
			List<Consolidate> dto = null;
		
			return new ModelAndView( "7_packing/ConsolidateList", "result", dto );
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
		request.getSession().removeAttribute("resultListConsolidate");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		String consolidateNo = ""+new ctrlIDGenerator().getIDConsolidate();
		Date date = (Date)new Date();
		Timestamp ts = new Timestamp(date.getTime());
	    
		Consolidate cons = new Consolidate();
	    cons.setConsolidateNo(consolidateNo);
	    request.getSession().setAttribute("Consolidate", cons);
		
	    HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		m.put("consolidateNo",consolidateNo);
		m.put("warehouse", warehouse);
		m.put("date", ts);
		m.put("corporate", corporate);
		return new ModelAndView( "7_packing/ConsolidateAdd", "model", m);
	}

	
	
	 private ModelAndView listPickingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListConsolidate");
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
		 return new ModelAndView("7_packing/ConsolidateList", "model", map);
	}
	 
	 
	 /**
	  *
	  * Method 'listPacking'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listPacking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
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
				Packing packing = new Packing();
				//PackingDetail packingDetail = new PackingDetail();
		
				PackingDao daoPacking = DaoFactory.createPackingDao();
				List<Packing> packingSearch = daoPacking.findPacking(packing);
				System.out.println("packingSearch >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> consolidate"+packingSearch);
/*
				Map tableMap = new HashMap();			
				for (Packing searchPacking : packingSearch){
				
					Map returnMap = new HashMap();	
					String packingNo  =((Packing)searchPacking).getPackingNo();
					Date packingDate  =((Packing)searchPacking).getPackingDate();
					//String productId    =((Packing)searchPacking).getProductId();
					//String productCode  =((Packing)searchPacking).getProductCode();
					//String productName  =((Packing)searchPacking).getProductName();
					//int quantity 		=((Packing)searchPacking).getBalance();
					//String unitCode		=((Packing)searchPacking).getUnitcode();
					//String locationCode =((PackingDetail)searchPacking).getWhlocation().getLocationCode();
					
					returnMap.put("packingNo",packingNo);
					returnMap.put("packingDate",packingDate);
					//returnMap.put("quantity",quantity);
					//returnMap.put("unitCode",unitCode);
					//returnMap.put("locationCode",locationCode);		

					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
				*/
				map.put("packingSearch", packingSearch);
		   }
		   
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "consolidate", e);
		   }
	 			
		   return new ModelAndView("10_component/PickListPackingConsolidate", "model", map);
		   
	 }
	 
	 
	 @Transactional
	  public ModelAndView addSelectedPacking(HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
		  Map map = new HashMap();
		  boolean add = false;
		  try{
			  
			  if (request.getParameter("packingNo") != null && !request.getParameter("packingNo").equals("")) {
				  
		          String packingNo = request.getParameter("packingNo");
		          String packingDate = request.getParameter("packingDate");
		        
		      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
				  String corporate = lu.getCorpId();
				  String warehouse = lu.getWhCode();
				  String consolidateNo = ""+new ctrlIDGenerator().getIDConsolidate();
				  
				  Date date = (Date)new Date();
				  Timestamp ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "addSelectedPacking");
				 // map.put("salesOrderNo",salesOrderNo);
				  map.put("consolidateNo",consolidateNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("packingNo",packingNo);
		          map.put("packingDate",packingDate);
		          //map.put("quantity",quantity);
		         // map.put("unitCode", unitCode);
		          //map.put("locationCode",locationCode);	  
		         
		          Map tableMap = new HashMap();	
		          if (request.getSession().getAttribute("resultListConsolidate")!= null){
					  
					  List returnList = (List)request.getSession().getAttribute("resultListConsolidate");
					  for(int i=0;i<returnList.size();i++){						
						Map returnMap = (Map)returnList.get(i);  
			        	tableMap.put(returnMap, returnMap);
			          }
					 	map.put("tableMap",tableMap);
					 
				  }
		          
		          return new ModelAndView("7_packing/ConsolidateAdd", "model", map);
		      } else {
		          return new ModelAndView("7_packing/ConsolidateAdd");
		      }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return new ModelAndView("7_packing/ConsolidateAdd", "model", map);
	  }
	
	 
	 public ModelAndView btnAddDtlConsolidate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		  Map map = new HashMap();
		
	      try{
	    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	    	  	String userId = lu.getUserId();
				String corporate = lu.getCorpId();
				String warehouse = lu.getWhCode();
				String consolidateNo = ""+new ctrlIDGenerator().getIDConsolidate();
				//String salesOrderNo = request.getParameter("salesOrderNo");
				Date date = (Date)new Date();
				Timestamp ts = new Timestamp(date.getTime());
				
				map = this.getModelByPrimaryKey(request);
				map.put("mode", "create");
				map.put("consolidateNo",consolidateNo);
				map.put("userId", userId);
				map.put("warehouse", warehouse);
				map.put("date", ts);
				map.put("corporate", corporate);
	    	  
		      if (request.getParameter("packingNo") != null || !request.getParameter("packingNo").equals("")) {
		    	  String packingNo     = request.getParameter("packingNo");
		    	  String packingDate   = request.getParameter("packingDate");
//		          String productName     = request.getParameter("productName");
//		          String productRequest  = productCode.concat(" - ").concat(productName);
//		          String locationCode    = request.getParameter("locationCode");
//		          int	 requestQty      = 0;
//		          int    productQuantity = 0;
//	        	  int    productStatus   = 0;
		    	  
		    	  PackingDetail pd = new PackingDetail();
		    	  pd.setPackingNo(packingNo);
		    	  PackingDetailDao dao = DaoFactory.createPackingDetailDao();
		    	  List<PackingDetail> dto = dao.findDetail(pd);
		    	  
		    	  String productId = "";
		    	  String productCode = "";
		    	  String productName = "";
		    	  Integer quantity = 0;
		    	  for(PackingDetail pad : dto){
		    		  productId =((PackingDetail)pad).getProductId();
		    		  productCode=((PackingDetail)pad).getProductCode();
		    		  productName=((PackingDetail)pad).getProductName();
		    		  quantity=((PackingDetail)pad).getQuantity();
		    	  }
		         
		          Map paramMap = new HashMap();
		          List resultList = new ArrayList();
	        	  //paramMap.put("productRequest",productRequest);
	        	  paramMap.put("packingNo",packingNo);
	        	  paramMap.put("packingDate",packingDate);
	        	  paramMap.put("productId",productId);
	        	  paramMap.put("productCode",productCode);
	        	  paramMap.put("productName",productName);
	        	  paramMap.put("quantity",quantity);
	        	  paramMap.put("userId",userId);
	        	  paramMap.put("warehouse",warehouse);
	        	  paramMap.put("corporate",corporate);
				  resultList.add(paramMap);
				  /*
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
				  
				  if(requestQty == 0 && proQty == 0){
		    		  String msg = "All product values are empty and zero, please insert that values!";
			          map.put("msg", msg);
		    	  }
				  else if(requestQty == 0){
		    		  String msg = "Request Qty is zero, please insert that values!";
			          map.put("msg", msg);
		    	  }
				  else if(requestQty>proQty){
					  String msg = " Request Qty is to big than Product Qty, please insert smallest values!";
			          map.put("msg", msg);
				  }
				  else 
				  */
				 // if(requestQty>0){
		        	  if(request.getSession().getAttribute("resultListConsolidate") != null){
		        		 resultList = (List)request.getSession().getAttribute("resultListConsolidate");
		        	  }
		        	   
		        	  Map tableMap = new HashMap();
		        	  Map returnMap = new HashMap();
		        	  boolean isHasBeenAdded = false;
		        	  for(int c=0; c<resultList.size();c++){
		        		  returnMap = (Map)resultList.get(c);
		        		  if(returnMap.get("packingNo").equals(packingNo)){
		        			 isHasBeenAdded = true;
		        			 //Integer qty = (Integer)returnMap.get("requestQuantity");
		        			 //returnMap.put("requestQuantity", qty + requestQty);
		        			 returnMap.put("packingNo", packingNo);
		        			 returnMap.put("packingDate", packingDate);
		        			 tableMap.put(returnMap, returnMap);
		        			 break;
		        		  }
		        	  }
		        	  if(!isHasBeenAdded){
			        	  paramMap.put("packingNo",packingNo);
			        	  paramMap.put("packingDate",packingDate);
//			        	  paramMap.put("productName",productName);
//			        	  paramMap.put("productQuantity",productQuantity);
//			        	  paramMap.put("requestQuantity",requestQty);
//			        	  paramMap.put("productStatus",productStatus);
			        	  paramMap.put("consolidateNo",consolidateNo);
			        	  paramMap.put("userId",userId);
			        	  paramMap.put("warehouse",warehouse);
			        	  paramMap.put("corporate",corporate);
						  resultList.add(paramMap);
						  
						  for(int i=0;i<resultList.size();i++){						
								returnMap = (Map)resultList.get(i);  
					        	tableMap.put(returnMap, returnMap);
					      }

		        	  }
		        	  request.getSession().setAttribute("resultListConsolidate", resultList);
		        	  List returnList = (List)request.getSession().getAttribute("resultListConsolidate");
					  Map tResultMap = new HashMap();
					  for(int d=0;d<returnList.size();d++){						
						Map resultMap = (Map)returnList.get(d);  
						tResultMap.put(resultMap, resultMap);
			          }
					 	map.put("tableMap",tResultMap);
		        	  
		         // }
		         
		        	  
		          return new ModelAndView("7_packing/ConsolidateAdd", "model", map);
		      }
	      
		      }catch(Exception e){
		    	  e.printStackTrace();
		    	  
		      }
	      
	      		return new ModelAndView("7_packing/ConsolidateAdd", "model", map);
	  }
	 
	 
	 /**
		 * Method 'saveConsolidate'
		 * 
		 * @param request
		 * @param response
		 * @throws Exception
		 * @return ModelAndView
		 */
		public ModelAndView saveConsolidate(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			Map map = new HashMap();
			
			try{
			
				String btnAddDtlConsolidate = request.getParameter("btnAddDtlConsolidate");
				String btnCancel = request.getParameter("btnCancel");
				
				if (btnCancel != null && !btnCancel.equals("")) {
					 request.getSession().removeAttribute("resultListConsolidate");
					 return listPickingByAuthLogin(request, response);
				}else if (btnAddDtlConsolidate != null && !btnAddDtlConsolidate.equals("")) {
					return btnAddDtlConsolidate(request, response);
		        }
				
				LoginUser lu 			= (LoginUser) request.getSession().getAttribute("user");
				String userId 			= lu.getUserId();
				String consolidateDate 	= request.getParameter("consolidateDate");
				String corporate 		= request.getParameter("corporate");
				String warehouse 		= request.getParameter("warehouse");
				
				String pattern = "yyyy-MM-dd HH:mm:ss";
			    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			    Date co_date = sdf.parse(consolidateDate);
			    Timestamp dt =  new Timestamp(co_date.getTime());
			    Date date = (Date)new Date();
				Timestamp ts = new Timestamp(date.getTime());
				
				String consolidateNo = request.getParameter("consolidateNo");
				List returnList = (List)request.getSession().getAttribute("resultListConsolidate");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>List returnList ="+returnList.size());
				if (returnList == null)
	            {
					  lu = (LoginUser) request.getSession().getAttribute("user");
					  corporate = lu.getCorpId();
					  warehouse = lu.getWhCode();
					  consolidateNo = ""+new ctrlIDGenerator().getIDConsolidate();
					  date = (Date)new Date();
				      ts = new Timestamp(date.getTime());
					
					  map = this.getModelByPrimaryKey(request);
					  map.put("mode", "create");
					  map.put("consolidateNo",consolidateNo);
					  map.put("warehouse", warehouse);
					  map.put("date", ts);
					  map.put("corporate", corporate);
					  map.put("msg", " Consolidate doesn't have packing request list! please input that packing or cancel this process");
					  return new ModelAndView( "7_packing/ConsolidateAdd", "model", map);
	            }
				
				else if(returnList.size()>0)
				{	
		        	String packingNo  = "";
		        	String packDate  = null;
		        	
					  
		        	for(int i=0;i<returnList.size();i++){		
		        		System.out.println("returnList>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ="+returnList);
		        		Map returnMap = (Map)returnList.get(i);  

		        		packingNo  = (String) returnMap.get("packingNo");
		        		packDate = (String)returnMap.get("packingDate");
		        		Date packingDate = sdf.parse(packDate);
		        		Timestamp tsPack =  new Timestamp(packingDate.getTime());
			       
			        	ConsolidateDao dao = DaoFactory.createConsolidateDao();
			        	ConsolidateDetailDao daodtl = DaoFactory.createConsolidateDetailDao();
			        	
						Consolidate dto 			= dao.findByPrimaryKey(consolidateNo);
						//SalesOrderDetail dso 	= daodtl.findByPrimaryKey(salesOrderNo);
						ConsolidateDetail dso = null;
						
						boolean isCreate = dto == null;
						boolean detail = dso == null;
						if(isCreate){
							dto = new Consolidate();
						}

						dto.setConsolidateNo(consolidateNo);
						dto.setConsolidateDate(dt);
						dto.setCreatedBy(userId);
						dto.setCreatedDate(ts);
						dto.setUpdatedBy(userId);
						dto.setUpdatedDate(ts);
						
					    if(isCreate){
						   dao.insert(dto);
						}
//						else
//						{
//						   dao.update(dto.createPk(), dto);
//						}
						
						if(detail){
							
							dso = new ConsolidateDetail();
								
							    dso.setConsolidateNo(consolidateNo);
							    dso.setPackingNo(packingNo);
							    dso.setProductId((String)returnMap.get("productId"));
							    dso.setProductCode((String)returnMap.get("productCode"));
							    dso.setProductName((String)returnMap.get("productName"));
							    dso.setUnitCode((String)returnMap.get("unitCode"));
							    dso.setQtyConsolidate((Integer)returnMap.get("quantity"));
							    dso.setUserId((String)returnMap.get("userId"));
							    dso.setCorpId((String)returnMap.get("corporate"));
							    dso.setWhCode((String)returnMap.get("warehouse"));
							   System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>dso ="+dso);
						    if(!dso.getConsolidateNo().equalsIgnoreCase("")){
						       daodtl.insert(dso);
						    }
							
						}
						
		        	}
		       
				} 
				
				  lu = (LoginUser) request.getSession().getAttribute("user");
				  corporate = lu.getCorpId();
				  warehouse = lu.getWhCode();
				  consolidateNo = ""+new ctrlIDGenerator().getIDConsolidate();
				  date = (Date)new Date();
			      ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("consolidateNo",consolidateNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				
				
			}catch (Exception e){
				e.printStackTrace();
				return new ModelAndView("Error", "salesorder", e);
			}
			map.put("msg", " Consolidate Already Created");
			request.getSession().removeAttribute("resultListConsolidate");
	        return new ModelAndView("7_packing/ConsolidateList", "model", map);
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

					Consolidate cons = new Consolidate();
					
					cons.setConsolidateNo(request.getParameter("consolidateNo"));
					//cons.setConsolidateDate(request.getParameter("consolidateNo"));
					WarehouseLocation location = new WarehouseLocation();
					location.setLocationCode(request.getParameter("whLocation"));
					
			
			   }
			   catch(Exception e){
				   e.printStackTrace();
				   return new ModelAndView("Error", "consolidate", e);
			   }
		  			
			   return new ModelAndView("7_packing/PickListPackingConsolidate", "model", map);
			   
		  }
		  
		  @Transactional
		    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
		        /*
		         *  GET LOGIN USER
		         */
		        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
		        String consolidateNo = request.getParameter("consolidateNo");
		        HashMap model = new HashMap();

		        ConsolidateDetailDao dao = DaoFactory.createConsolidateDetailDao();
		        ConsolidateDetail dto	= dao.findByPrimaryKey(consolidateNo);
		        
		        if(dto != null){
		            System.out.println("[ConsolidateNo][Ajax Document] consolidate no : " + consolidateNo + " is valid");

		            ConsolidateDetail cod = new ConsolidateDetail();
		            cod.setConsolidateNo(consolidateNo);
		            
		            List<ConsolidateDetail> listSearch = dao.findDetail(cod);
		            Map tableMap = new HashMap();
		            for (ConsolidateDetail searchDetail : listSearch){
		            	
		            	Map returnMap = new HashMap();
	                    consolidateNo    = ((ConsolidateDetail)searchDetail).getConsolidateNo();
	                    String packingNo    = ((ConsolidateDetail)searchDetail).getPackingNo();
						String productCode  = ((ConsolidateDetail)searchDetail).getProductCode();
						String productName  = ((ConsolidateDetail)searchDetail).getProductName();
						int quantity    	= ((ConsolidateDetail)searchDetail).getQtyConsolidate();
						returnMap.put("consolidateNo",consolidateNo);
						returnMap.put("packingNo",packingNo);
						returnMap.put("productCode",productCode);
						returnMap.put("productName",productName);
						returnMap.put("quantity",quantity);
						
						tableMap.put(returnMap, returnMap);
					}
		            
		            model.put("master", dto);
		            model.put("tableMap", tableMap);
		            
		        } else{
		            System.out.println("[ConsolidateNo][Ajax Document] consolidate no : " + consolidateNo + " is not valid");
		        }

		        return new ModelAndView("7_packing/util/ConsolidateDetail", "model", model);
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
				
					
					String consolidateNo = request.getParameter("consolidateNo");
					System.out.println("consolidateNo ="+consolidateNo);
					
					templateName = request.getParameter("templateName");
					System.out.println("templateName ="+templateName);
					
					parametersKey = request.getParameter("parametersKey");
					System.out.println("parameterKey ="+parametersKey);
					
					ArrayList resultList = new ArrayList();
					resultList.add(consolidateNo);
					setParameterValues(resultList);
					
					List paramKey = new ArrayList();
					paramKey.add(parametersKey);
					setParameterKeys((ArrayList<String>) paramKey);
					outputFormat = "pdf";
					createOnlineReport();
					
					try{
						printToStream(response);
						
					}catch(FileNotFoundException ex){
						Logger.getLogger(ConsolidateController.class.getName()).log(Level.SEVERE, null, ex);
					}catch(IOException ex){
						Logger.getLogger(ConsolidateController.class.getName()).log(Level.SEVERE, null, ex);
					}
					
				}
	 

}


