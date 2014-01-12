package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.PickingDetailDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
import com.app.wms.engine.db.dao.SalesOrderDetailDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;


public class PickingController extends ReportManagerController
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

            m = this.searchAndPaging(request, response);
            return new ModelAndView("6_picking/PickingList", "model", m);

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
            
            PickingDao dao = DaoFactory.createPickingDao();
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            
            Picking pi = new Picking();
            pi.setCorpId(lu.getCorpId());
            pi.setWhCode(lu.getWhCode());
            /*
            pi.setPickingNo(request.getParameter("pickingNo"));
            pi.setPickingDate(new Date(request.getParameter("pickingDate")));
            */
            //# source untuk paging
		    List<Picking> listSearchPage = dao.findPickingPaging(pi, page);
            
            m.put("totalRows", total);
	        m.put("page", page);
	        m.put("paging", paging);
	        m.put("picking", listSearchPage);
		
			 
		}catch (Exception e){
			System.out.println("error : "+e.getMessage());
		}
		return new ModelAndView("6_picking/PickingList", "model", m);
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
            
          //#Global DAO
            PickingDao dao = DaoFactory.createPickingDao();
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            Picking pi = new Picking();
            pi.setCorpId(lu.getCorpId());
            pi.setWhCode(lu.getWhCode());
            
            //# source untuk paging
		    List<Picking> listSearchPage = dao.findPickingPaging(pi, page);
            
            int total = 2000; 
            m.put("picking", listSearchPage);
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
            java.lang.String pickingNo = request.getParameter("pickingNo");
            
            PickingDao dao = DaoFactory.createPickingDao();
            Picking dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(pickingNo);
            }

            if (dto == null) {
                dto = new Picking();
                dto.setPickingNo("");
                dto.setPickingDate(null);
              
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
			
			PickingDao dao = DaoFactory.createPickingDao();
		
			List<Picking> dto = dao.findAll();
		
			return new ModelAndView( "6_picking/PickingList", "result", dto );
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
		LoginUser lu 	 = (LoginUser) request.getSession().getAttribute("user");
		String corporate = lu.getCorpId();
		String warehouse = lu.getWhCode();
		String pickingNo = ""+new ctrlIDGenerator().getIDPicking();
		Date date 		 = (Date)new Date();
		Timestamp ts     = new Timestamp(date.getTime());
		
		Picking picking = new Picking();
		picking.setPickingNo(pickingNo);
		request.getSession().setAttribute("pickingNo",picking);
		
		HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
		m.put("pickingNo",pickingNo);
		m.put("warehouse", warehouse);
		m.put("corporate", corporate);
		m.put("date", ts);
		return new ModelAndView( "6_picking/PickingAdd", "model", m);
	}

	/**
	 * Method 'savePicking'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	
	public ModelAndView savePicking(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map map = new HashMap();
		try{
			
			String btnCancel 		= request.getParameter("btnCancel");
			if (btnCancel != null && !btnCancel.equals("")) {
				 request.getSession().removeAttribute("resultListPicking");
				 return listPickingByAuthLogin(request, response);
			}
			
			LoginUser lu 		= (LoginUser) request.getSession().getAttribute("user");
			String userId 		= lu.getUserId();
			
			String pickingNo 	= request.getParameter("pickingNo");
			String pickingDate  = request.getParameter("pickingDate");
			String corporate 	= request.getParameter("corporate");
			String warehouse 	= request.getParameter("warehouse");
			String soNumber    	= request.getParameter("so_number");
			String tallyMan		= request.getParameter("tallyman");

			String pattern = "yyyy-MM-dd HH:mm:ss";
		    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		    Date pick_date = sdf.parse(pickingDate);
		    Timestamp dt =  new Timestamp(pick_date.getTime());
		    Date date = (Date)new Date();
			Timestamp ts = new Timestamp(date.getTime());
			
			SalesOrderDao dao = DaoFactory.createSalesOrderDao();
		   	SalesOrder so = dao.findByPrimaryKey(soNumber);
		   	Timestamp soDate = so.getSo_date();
		   	logger.debug("soDate ="+soDate);
		   	String statusApp	= "PENDING";
			Timestamp appDate	= ts;
		   
			PickingDao daoPick 		       	= DaoFactory.createPickingDao();
			PickingDetailDao daoPickDetail 	= DaoFactory.createPickingDetailDao();
			
			Picking pickDto 				= daoPick.findByPrimaryKey(pickingNo);
			PickingDetail pickDetailDto 	= null;
			
			boolean isCreate = pickDto == null;
			boolean detail   = pickDetailDto == null;
			
			if(isCreate){
				pickDto = new Picking();
			}
			
			pickDto.setPickingNo(pickingNo);
			pickDto.setPickingDate(dt);
			pickDto.setSoNumber(soNumber);
			pickDto.setSoDate(soDate);
			pickDto.setStatusApp(statusApp);
			pickDto.setAppDate(appDate);
			pickDto.setCreatedBy(userId);
			pickDto.setCreatedDate(ts);
			pickDto.setUpdatedBy(userId);
			pickDto.setUpdatedDate(ts);
			pickDto.setTallyman(tallyMan);
			logger.debug(">>> picking dto ="+pickDto);
			if(isCreate){
				daoPick.insert(pickDto);
			}
			else
			{
				//daoPick.update(pickDto.createPk(), pickDto);
			}
			
			PickingDetail dto 	= new PickingDetail();
			if(detail){
				
				String[]productId	= request.getParameterValues("productId");
				String[]productCode = request.getParameterValues("productCode");
				String[]productName = request.getParameterValues("productName");
				String[]reqQty 		= request.getParameterValues("qty");
				String[]unitCode 	= request.getParameterValues("unitCode");
				String[]balance 	= request.getParameterValues("balance");
				
				String[]location	= request.getParameterValues("location1");
				String[]receivedDate= request.getParameterValues("receivedDate1");
				String[]expiryDate	= request.getParameterValues("expiryDate1");
				String[]lotId		= request.getParameterValues("lotId1");
				String[]value		= request.getParameterValues("value1");
				
				Product products 		= new Product();
		        Corporate corp   		= new Corporate();
			    Wh wh   		 		= new Wh();
			    WarehouseLocation wLoc  = new WarehouseLocation();
			    
				for(int i=0; i<location.length; i++){
					
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				 String[]location1 		= location[i].trim().split("<br />");
//				 String[]receivedDate1 	= receivedDate[i].trim().split("<br />");
//				 String[]expiryDate1 	= expiryDate[i].trim().split("<br />");
//				 String[]lotId1 	= lotId[i].trim().split("<br />");
				 String[]value1 	= value[i].trim().split("<br />");
				 String[]balance1 	= balance[i].trim().split("<br />");
				 
				 //List<String>locationList = Arrays.asList(location1);
				 
				 
				 for(int c=0;c<location1.length;c++){
					 
					 /*
					 java.util.Date parsedDate = dateFormat.parse(receivedDate1[c]);
					 java.sql.Timestamp tsReceive = new java.sql.Timestamp(parsedDate.getTime());
					 
					 Date todate = new Date();
					 java.sql.Timestamp tsExpiry = null;
					 if(expiryDate[i].startsWith("undefined<br />")){
						 tsExpiry = new Timestamp(todate.getTime());
					 }
					 else{
						 java.util.Date parsedDates = dateFormat.parse(expiryDate[c]);
						 tsExpiry = new java.sql.Timestamp(parsedDates.getTime()); 
					 }
					*/
					 
					 pickingNo 	= request.getParameter("pickingNo");
					 dto.setPickingId(pickingNo);
					 products.setProductId(productId[i].trim());
					 products.setProductCode(productCode[i].trim());
					 products.setProductName(productName[i].trim());
					 dto.setProducts(products);
//					 dto.setReceivedDate(tsReceive);
//					 dto.setExpiredDate(tsExpiry);
					 dto.setUnitCode(unitCode[i].trim());
					 
					 dto.setQtyPick(Integer.parseInt(value1[c].trim()));
					 
					 int qtyPick = dto.getQtyPick();
					 if(qtyPick > 0){
						 dto.setQtyOrder(Integer.parseInt(reqQty[i].trim()));
					 }
					 
					 dto.setBalance(Integer.parseInt(balance1[c].trim()));
					 //dto.setBalance(0);
//					 dto.setLotId(lotId1[c].trim());
					 wLoc.setLocationCode(location1[c].trim());
					 dto.setWhlocation(wLoc);
					 dto.setUserId(userId);
					 corp.setId(corporate);
					 dto.setCorp(corp);
					 wh.setWhCode(warehouse);
					 dto.setWh(wh);
					 logger.debug(">>>pickingdetail dto="+dto);
					 daoPickDetail.insert(dto);
				 }
					 
				}
			}
			else
			{
				    //daoPickDetail.update(dto.createPk(), pickDetailDto);
			}


		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "picking", e);
		}
        return new ModelAndView("6_picking/PickingList", "model", map);
	}
	
	 private ModelAndView listPickingByAuthLogin(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
		 request.getSession().removeAttribute("resultListPicking");
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
		 return new ModelAndView("6_picking/PickingList", "model", map);
	}

	 @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        /*
	         *  GET LOGIN USER
	         */
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String pickingNo = request.getParameter("pickingNo");
	        HashMap model = new HashMap();

	        PickingDetailDao dao = DaoFactory.createPickingDetailDao();
	        PickingDetail dto	= dao.findByPrimaryKey(pickingNo);
	        
	        if(dto != null){
	            System.out.println("[SalesOrderNo][Ajax Document] sales order no : " + pickingNo + " is valid");

	            PickingDetail pid = new PickingDetail();
	            pid.setPickingId(pickingNo);
	            
	            List<PickingDetail> listSearch = dao.findDetail(pid);
	            Map tableMap = new HashMap();
	            for (PickingDetail searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
	            	pickingNo    = ((PickingDetail)searchDetail).getPickingId();
					String productCode  = ((PickingDetail)searchDetail).getProducts().getProductCode();
					String productName  = ((PickingDetail)searchDetail).getProducts().getProductName();
					int quantity    	= ((PickingDetail)searchDetail).getQtyPick();
					String lotid        = ((PickingDetail)searchDetail).getLotId();
					String locationCode = ((PickingDetail)searchDetail).getWhlocation().getLocationCode();
					returnMap.put("pickingNo",pickingNo);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					returnMap.put("lotid",lotid);
					returnMap.put("locationCode",locationCode);
					
					tableMap.put(returnMap, returnMap);
				}
	            
	            model.put("master", dto);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[SalesOrderNo][Ajax Document] picking no : " + pickingNo + " is not valid");
	        }

	        return new ModelAndView("6_picking/util/PickingDetail", "model", model);
	    }
	 
	 @Transactional
		public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			
				
				String pickingNo = request.getParameter("pickingNo");
				System.out.println("pickingNo ="+pickingNo);
				
				templateName = request.getParameter("templateName");
				System.out.println("templateName ="+templateName);
				
				parametersKey = request.getParameter("parametersKey");
				System.out.println("parameterKey ="+parametersKey);
				
				ArrayList resultList = new ArrayList();
				resultList.add(pickingNo);
				setParameterValues(resultList);
				
				List paramKey = new ArrayList();
				paramKey.add(parametersKey);
				setParameterKeys((ArrayList<String>) paramKey);
				outputFormat = "pdf";
				createOnlineReport();
				
				try{
					printToStream(response);
					
				}catch(FileNotFoundException ex){
					Logger.getLogger(PickingController.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(PickingController.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
  
  
}
