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
import com.app.web.engine.search.PackingSearch;
import com.app.wms.engine.db.dao.KittingDao;
import com.app.wms.engine.db.dao.KittingDetailDao;
import com.app.wms.engine.db.dao.PackingDao;
import com.app.wms.engine.db.dao.PackingDetailDao;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.PickingDetailDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Kitting;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.Packing;
import com.app.wms.engine.db.dto.PackingDetail;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.UnitCode;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.util.AppConstant;

public class PackingController extends ReportManagerController 
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
                return new ModelAndView("7_packing/PackingEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("7_packing/PackingList", "model", m);
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
            Packing p = new Packing();
            p.setCorpId(lu.getCorpId());
            p.setWhCode(lu.getWhCode());
            PackingDao dao = DaoFactory.createPackingDao();
            List<Packing> listSearchPage = dao.findPackingPaging(p, page);

            int total = 2000; 
            m.put("packing", listSearchPage);
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
            java.lang.String packingCode = request.getParameter("packingCode");
            PackingDao dao = DaoFactory.createPackingDao();
            Packing dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(packingCode);
            }

            if (dto == null) {
                dto = new Packing();
                dto.setPackingNo("");
               // dto.setName("");
               // dto.setIsActive("Y");
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
			
			PackingDao dao = DaoFactory.createPackingDao();
		
			List<Packing> dto = dao.findAll();
		
			return new ModelAndView( "7_packing/PackingList", "result", dto );
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
		request.getSession().removeAttribute("resultListPacking");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		String packingNo = ""+new ctrlIDGenerator().getIDPacking();
		Date date = (Date)new Date();
		Timestamp ts = new Timestamp(date.getTime());
	    
		Packing pack = new Packing();
		pack.setPackingNo(packingNo);
	    request.getSession().setAttribute("Packing", pack);
		
	    HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		m.put("packingNo",packingNo);
		m.put("warehouse", warehouse);
		m.put("date", ts);
		m.put("corporate", corporate);
		return new ModelAndView( "7_packing/PackingAdd", "model", m);
	}

	/**
	 * Method 'savePacking'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	
	public ModelAndView savePacking(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		
		try{
			String btnViewDtlProduct = request.getParameter("btnViewDtlProduct");
			String btnCancel = request.getParameter("btnCancel");
			
			if (btnCancel != null && !btnCancel.equals("")) {
				 request.getSession().removeAttribute("resultListPacking");
				 return listPickingByAuthLogin(request, response);
			}else if (btnViewDtlProduct != null && !btnViewDtlProduct.equals("")) {
	             return btnViewDtlProduct(request, response);
	        }
			
			LoginUser lu 			= (LoginUser) request.getSession().getAttribute("user");
			String userId 			= lu.getUserId();
			String packingDate 		= request.getParameter("packingDate");
			String corporate 		= request.getParameter("corporate");
			String warehouse 		= request.getParameter("warehouse");
			String soNumber  		= (String)request.getSession().getAttribute("sessionSO");
			
			String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		    Date kitt_date = sdf.parse(packingDate);
		    Timestamp dt =  new Timestamp(kitt_date.getTime());
		    Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			String packingNo = request.getParameter("packingNo");
			
			List returnList = (List)request.getSession().getAttribute("resultListPacking");
			if (returnList == null)
            {
				  lu = (LoginUser) request.getSession().getAttribute("user");
				  corporate = lu.getCorpId();
				  warehouse = lu.getWhCode();
				  packingNo = ""+new ctrlIDGenerator().getIDPacking();
				  date = (Date)new Date();
			      ts = new Timestamp(date.getTime());
				
				  map = this.getModelByPrimaryKey(request);
				  map.put("mode", "create");
				  map.put("packingNo",packingNo);
				  map.put("warehouse", warehouse);
				  map.put("date", ts);
				  map.put("corporate", corporate);
				  map.put("msg", " Packing doesn't have product request list! please input that product or cancel this process");
				  return new ModelAndView( "7_packing/PackingAdd", "model", map);
            }
			
			else if(returnList.size()>0)
			{	
				
				String salesOrderNumber	= "";
				String pickingNo		= "";
				String kittingNo		= "";
	        	String productRequest  	= "";
	        	String productId	   	= "";
	        	String productCode     	= "";
	        	String productName     	= "";
	        	String unitCode = "";
	        	int quantity    = 0;
				  
	        	for(int i=0;i<returnList.size();i++){		
	        		Map returnMap = (Map)returnList.get(i); 
	        		
	        		salesOrderNumber= (String) returnMap.get("salesOrderNumber");
					pickingNo		= (String) returnMap.get("pickingNo");
					kittingNo		= (String) returnMap.get("kittingNo");

	        		productRequest  = (String) returnMap.get("productRequest");
	        		productId		= (String) returnMap.get("productId");
		        	productCode     = (String) returnMap.get("productCode");
		        	productName     = (String) returnMap.get("productName");
		        	
		        	if(returnMap.get("unitCodePick")!= null){
		        		unitCode		= (String) returnMap.get("unitCodePick");
		        	}
		        	if(returnMap.get("unitCodeKitt") != null){
		        		unitCode		= (String) returnMap.get("unitCodeKitt");
		        	}
		        	if(returnMap.get("qtyPicking") != null){
		        		quantity    = (Integer) returnMap.get("qtyPicking");
		        	}
		        	if(returnMap.get("qtyKitting") != null){
		        		quantity    = (Integer) returnMap.get("qtyKitting");
		        	}
		        	
		        	PackingDao dao = DaoFactory.createPackingDao();
					PackingDetailDao daodtl = DaoFactory.createPackingDetailDao();
		        	System.out.println("packingNo= "+packingNo);
					Packing dto 	  = dao.findByPrimaryKey(packingNo);
					PackingDetail dso = null;
					
					boolean isCreate = dto == null;
					boolean detail = dso == null;
					if(isCreate){
						dto = new Packing();
					}

					dto.setPackingNo(packingNo);
					dto.setPackingDate(dt);
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
						
						dso = new PackingDetail();
							
							Product          pro = new Product();
						    UnitCode         uni = new UnitCode();
						    User             usr = new User();
						    Corporate 		 crp = new Corporate();
						    Wh				 wh  = new Wh();

						    dso.setPackingNo((String)returnMap.get("packingNo"));
						    dso.setSoNumber(salesOrderNumber);
						    dso.setPickingId(pickingNo);
						    dso.setKittingNo(kittingNo);
						   
						    pro.setProductId(productId);
						    pro.setProductCode(productCode);
						    pro.setProductName(productName);
						    uni.setName(unitCode);
						    
						    dso.setUnitcode(uni.getName());
						    dso.setQuantity(quantity);
						   
						    usr.setUserId(userId);
						    crp.setId(corporate);
						    wh.setWhCode(warehouse);
						    dso.setProductId(pro.getProductId());
						    dso.setProductCode(pro.getProductCode());
						    dso.setProductName(pro.getProductName());
						  
						    dso.setUserId(usr.getUserId());
						    dso.setCorpId(crp.getId());
						    dso.setWhCode(wh.getWhCode());
						    if(dso.getPackingNo().equalsIgnoreCase((String)returnMap.get("packingNo"))){
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
	        	packingNo="";
			} 
			
			
			  lu = (LoginUser) request.getSession().getAttribute("user");
			  corporate = lu.getCorpId();
			  warehouse = lu.getWhCode();
			  packingNo = ""+new ctrlIDGenerator().getIDPacking();
			  date = (Date)new Date();
		      ts = new Timestamp(date.getTime());
			
			  map = this.getModelByPrimaryKey(request);
			  map.put("mode", "create");
			  map.put("packingNo",packingNo);
			  map.put("warehouse", warehouse);
			  map.put("date", ts);
			  map.put("corporate", corporate);
			
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "packing", e);
		}
		map.put("msg", " Packing Process Already Created");
		request.getSession().removeAttribute("resultListPacking");
        return new ModelAndView("7_packing/PackingList", "model", map);
	}
	
	
	 public ModelAndView btnViewDtlProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map map = new HashMap();
		try{
			
    	  	LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
    	  	String userId = lu.getUserId();
			String corporate = lu.getCorpId();
			String warehouse = lu.getWhCode();
			String packingNo = ""+new ctrlIDGenerator().getIDPacking();
			Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
			String soNumber = (String)request.getSession().getAttribute("sessionSODate");
			String salesOrderNumber = (String)request.getSession().getAttribute("sessionSO");
			
			map = this.getModelByPrimaryKey(request);
			map.put("mode", "create");
			map.put("packingNo",packingNo);
			map.put("userId", userId);
			map.put("warehouse", warehouse);
			map.put("date", ts);
			map.put("corporate", corporate);
			map.put("salesOrder", soNumber);
			
			PickingDetailDao pdDao = DaoFactory.createPickingDetailDao();
			List<PickingDetail> listPickingDetail = pdDao.findPickingDetailList(salesOrderNumber);

			List resultList = new ArrayList();
			Map tableMap1 = new HashMap();			
			for (PickingDetail searchPicking : listPickingDetail){
			
				Map returnMap = new HashMap();
				String pickingNo 	  = ((PickingDetail)searchPicking).getPickingId();
				String productId 	  = ((PickingDetail)searchPicking).getProducts().getProductId();
				String productCode 	  = ((PickingDetail)searchPicking).getProducts().getProductCode();
				String productName 	  = ((PickingDetail)searchPicking).getProducts().getProductName();
				String productRequest = productCode.concat(" - ").concat(productName);
				String unitCodePick   = ((PickingDetail)searchPicking).getUnitCode();
				int qtyPicking		  = (Integer)((PickingDetail)searchPicking).getQtyPick();
				
				returnMap.put("packingNo", packingNo);
				returnMap.put("salesOrderNumber", salesOrderNumber);
				returnMap.put("pickingNo", pickingNo);
				returnMap.put("productId", productId);
				returnMap.put("productCode", productCode);
				returnMap.put("productName", productName);
				returnMap.put("productRequest", productRequest);
				returnMap.put("unitCodePick", unitCodePick);
				returnMap.put("qtyPicking", qtyPicking);
				resultList.add(returnMap);
				
				tableMap1.put(returnMap, returnMap);
			}	
			map.put("tableMap1", tableMap1);
			
			KittingDetailDao kdDao = DaoFactory.createKittingDetailDao();
			List<KittingDetail> listKittingDetail = kdDao.findKittingDetailList(salesOrderNumber);
			
			Map tableMap2 = new HashMap();			
			for (KittingDetail searchKitting : listKittingDetail){
			
				Map returnMap = new HashMap();
				String kittingNo 	  = ((KittingDetail)searchKitting).getKittingNo();
				String productId 	  = ((KittingDetail)searchKitting).getProductId();
				String productCode 	  = ((KittingDetail)searchKitting).getProductCode();
				String productName 	  = ((KittingDetail)searchKitting).getProductName();
				String productRequest = productCode.concat(" - ").concat(productName);
				String unitCodeKitt   = ((KittingDetail)searchKitting).getUnitCode();
				int qtyKitting		  = (Integer)((KittingDetail)searchKitting).getQtyKitting();
				
				returnMap.put("packingNo", packingNo);
				returnMap.put("salesOrderNumber", salesOrderNumber);
				returnMap.put("kittingNo", kittingNo);
				returnMap.put("productId", productId);
				returnMap.put("productCode", productCode);
				returnMap.put("productName", productName);
				returnMap.put("productRequest", productRequest);
				returnMap.put("unitCodeKitt", unitCodeKitt);
				returnMap.put("qtyKitting", qtyKitting);
				resultList.add(returnMap);
				
				tableMap2.put(returnMap, returnMap);
			}	
			map.put("tableMap2", tableMap2);
			
			request.getSession().setAttribute("resultListPacking", resultList);
			
	      }catch(Exception e){
	    	  e.printStackTrace();
	    	  
	      }
      
      		return new ModelAndView("7_packing/PackingAdd", "model", map);
	}

	private ModelAndView listPickingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListPacking");
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
		 return new ModelAndView("7_packing/PackingList", "model", map);
	}
	
	 /**
	  *
	  * Method 'listSalesOrderPacking'
	  *
	  * @param request
	  * @param response
	  * @return ModelAndView
	  * @throws Exception
	  */
	 @Transactional
	 public ModelAndView listSalesOrderPacking(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   Map map = new HashMap();
		   try {
			    LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
			    Picking p = new Picking();
			    p.setCorpId(lu.getCorpId());
			    p.setWhCode(lu.getWhCode());
			    
			   	PickingDao dao = DaoFactory.createPickingDao();
			   	List<Picking> listSalesOrder = dao.findSalesOrderPickingList(p);
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
	 			
		   return new ModelAndView("10_component/PickListSalesOrderPacking", "model", map);
		   
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
				Packing packing = new Packing();
				PackingDetail packingDetail = new PackingDetail();
				
				products.setProductCode(request.getParameter("productCode"));
				products.setProductName(request.getParameter("productName"));
				WarehouseLocation location = new WarehouseLocation();
				location.setLocationCode(request.getParameter("whLocation"));
				
				packingDetail.setProductCode(products.getProductCode());
				packingDetail.setProductName(products.getProductName());
				
				PackingDao daoPacking = DaoFactory.createPackingDao();
				List<PackingDetail> packingDetailSearch=null;
				
				String productCode ="";
				String productName ="";
				String locationCode ="";

				Map tableMap = new HashMap();			
				for (PackingDetail searchProduct : packingDetailSearch){
				
					Map returnMap = new HashMap();			
					productCode  =((PackingDetail)searchProduct).getProductCode();
					productName  =((PackingDetail)searchProduct).getProductName();
					String unitCode		=((PackingDetail)searchProduct).getUnitcode();
					
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("unitCode",unitCode);
					returnMap.put("locationCode",locationCode);				

					tableMap.put(returnMap, returnMap);
				}
				
				map.put("tableMap", tableMap);
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return new ModelAndView("Error", "packing", e);
		   }
	  			
		   return new ModelAndView("7_packing/ProductListPacking", "model", map);
		   
	  }
	  
	  @Transactional
		 public ModelAndView addSelectedSalesOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    
			  Map map = new HashMap();
			  boolean add = false;
			  try{
			      	  LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
					  String corporate = lu.getCorpId();
					  String warehouse = lu.getWhCode();
					  String packingNo = ""+new ctrlIDGenerator().getIDPacking();
					  String salesOrderNumber = request.getParameter("salesOrderNumber");
					  request.getSession().setAttribute("sessionSO", salesOrderNumber);
			          String salesOrderDate   = request.getParameter("salesOrderDate");
			          String salesOrder       = salesOrderNumber.concat(" ").concat("  -  ").concat(" ").concat(salesOrderDate);
			          request.getSession().setAttribute("sessionSODate", salesOrder);
			          Date date = (Date)new Date();
					  Timestamp ts = new Timestamp(date.getTime());
			          
					  map = this.getModelByPrimaryKey(request);
					  map.put("mode", "create");
					  map.put("packingNo",packingNo);
					  map.put("warehouse", warehouse);
					  map.put("date", ts);
					  map.put("corporate", corporate);
					  map.put("salesOrderNumber", salesOrderNumber);
					  map.put("salesOrderDate", salesOrderDate);
					  map.put("salesOrder", salesOrder);
			         
			          return new ModelAndView("7_packing/PackingAdd", "model", map);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return new ModelAndView("7_packing/PackingAdd", "model", map);
		 }
	  
	  @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        /*
	         *  GET LOGIN USER
	         */
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String packingNo = request.getParameter("packingNo");
	        HashMap model = new HashMap();

	        PackingDetailDao dao = DaoFactory.createPackingDetailDao();
	        PackingDetail dto	= dao.findByPrimaryKey(packingNo);
	        
	        if(dto != null){
	            System.out.println("[PackingNo][Ajax Document] packing no : " + packingNo + " is valid");

	            PackingDetail pd = new PackingDetail();
	            pd.setPackingNo(packingNo);
	            
	            List<PackingDetail> listSearch = dao.findDetail(pd);
	            Map tableMap = new HashMap();
	            for (PackingDetail searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
                    packingNo    = ((PackingDetail)searchDetail).getPackingNo();
					String productCode  = ((PackingDetail)searchDetail).getProductCode();
					String productName  = ((PackingDetail)searchDetail).getProductName();
					int quantity    	= ((PackingDetail)searchDetail).getQuantity();
					returnMap.put("packingNo",packingNo);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					
					tableMap.put(returnMap, returnMap);
				}
	            
	            model.put("master", dto);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[PackingNo][Ajax Document] packing no : " + packingNo + " is not valid");
	        }

	        return new ModelAndView("7_packing/util/PackingDetail", "model", model);
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
				String packingNo = request.getParameter("packingNo");
				System.out.println("packingNo ="+packingNo);
				
				templateName = request.getParameter("templateName");
				System.out.println("templateName ="+templateName);
				
				parametersKey = request.getParameter("parametersKey");
				System.out.println("parameterKey ="+parametersKey);
				
				ArrayList resultList = new ArrayList();
				resultList.add(packingNo);
				setParameterValues(resultList);
				
				List paramKey = new ArrayList();
				paramKey.add(parametersKey);
				setParameterKeys((ArrayList<String>) paramKey);
				outputFormat = "pdf";
				createOnlineReport();
				
				try{
					printToStream(response);
					
				}catch(FileNotFoundException ex){
					Logger.getLogger(PackingController.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(PackingController.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}

}
