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

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.PickingDao;
import com.app.wms.engine.db.dao.PickingDetailDao;
import com.app.wms.engine.db.dao.PutawayDetailDao;
import com.app.wms.engine.db.dao.SalesOrderDao;
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


public class Picking2Controller extends ReportManagerController
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
                return new ModelAndView("6_picking/PickingEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("6_picking/Picking2List", "model", m);
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
            
          //#Global DAO
            PickingDao dao = DaoFactory.createPickingDao();
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            Picking pi = new Picking();
            pi.setCorpId(lu.getCorpId());
            pi.setWhCode(lu.getWhCode());
            
            //# source untuk paging
		    List<Picking> listSearchPage = dao.findPickingPaging(pi, page);
            
            m.put("totalRows", total);
	        m.put("page", page);
	        m.put("paging", paging);
	        m.put("picking", listSearchPage);
		
			 
		}catch (Exception e){
			System.out.println("error : "+e.getMessage());
		}
		return new ModelAndView("6_picking/Picking2List", "model", m);
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
            PickingDetail pid = new PickingDetail();
            PickingDetailDao daod = DaoFactory.createPickingDetailDao();
            List<PickingDetail> pd = daod.findWherePickingIdEquals(pickingNo);
            
            Corporate corp = new Corporate();
            Wh wh	= new Wh();
            LoginUser lu 	 = (LoginUser) request.getSession().getAttribute("user");
    		String corporate = lu.getCorpId();
    		String warehouse = lu.getWhCode();
    		corp.setId(corporate);
            wh.setWhCode(warehouse);
            pid.setCorp(corp);
            pid.setWh(wh);

            Map resultMap = new HashMap();
            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(pickingNo);
                dto.setPickingDetail(pid);
               
            }

            if (dto == null) {
                dto = new Picking();
                
                Date date = new Date();
                Timestamp ts     = new Timestamp(date.getTime());
                corp.setId("");
                wh.setWhCode("");
                pid.setCorp(corp);
                pid.setWh(wh);
                dto.setPickingNo("");
                dto.setPickingDate(ts);
                dto.setPickingDetail(pid);
            }
             
            HashMap m = new HashMap();
            
            m.put("dto", dto);
            m.put("tableMap", resultMap);
            m.put("pd", pd);

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
		
			return new ModelAndView( "6_picking/Picking2List", "result", dto );
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
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception
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
		return new ModelAndView( "6_picking/PickingEdit", "model", m);
	}

	/**
	 * Method 'updatePicking'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	
	/*
	 * Noted By : rendra pratama
	 * Method ini guna-nya untuk mengupdate balance pada putaway_detail
	 * 
	 * pada table putaway_detail:
		1. qty_lot = qty_put - qty_pick
		
		2. balance = max(balance) - qty_pick ---> pada suatu product
		
		pada table picking_detail:
		
		1. balance adalah balance awal sebelum di picking
		
		2. balance tidak sama dengan putaway_detail
	 * 
	 */
	public ModelAndView UpdatePicking(HttpServletRequest request, HttpServletResponse response) throws Exception
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
		   	String statusApp	= "SUCCESSFULL";
			Timestamp appDate	= ts;
		   
			PickingDao daoPick 		       	= DaoFactory.createPickingDao();
			PickingDetailDao daoPickDetail 	= DaoFactory.createPickingDetailDao();
			PutawayDetailDao daoPutDetail	= DaoFactory.createPutawayDetailDao();
			
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
			if(!isCreate){
				daoPick.update(pickDto.createPk(), pickDto);
			}
			
			PickingDetail dto 	= new PickingDetail();
			PutawayDetail dtoput= new PutawayDetail();
			
			if(detail){
				
				String[] productId	= request.getParameterValues("productId");
				String[] productCode = request.getParameterValues("productCode");
//				String[] lotId		 = request.getParameterValues("lotId");
				String[] productName = request.getParameterValues("productName");
				String[] reqQty 	 = request.getParameterValues("qty");
				String[] unitCode 	 = request.getParameterValues("sku");
				String[] balance 	 = request.getParameterValues("balance");
				
				String[] location	 = request.getParameterValues("location");
//				String[] receivedDate= request.getParameterValues("receivedDate");
//				String[] expiryDate	 = request.getParameterValues("expiryDate");
				String[] value		 = request.getParameterValues("value");
				
				Product products 		= new Product();
		        Corporate corp   		= new Corporate();
			    Wh wh   		 		= new Wh();
			    WarehouseLocation wLoc  = new WarehouseLocation();
				
				for (int i = 0; i < productCode.length; i++) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//					java.util.Date parsedDate = dateFormat.parse(receivedDate[i]);
//					java.sql.Timestamp tsReceive = new java.sql.Timestamp(parsedDate.getTime());
					
//					java.sql.Timestamp tsExpiry = new Timestamp(new Date().getTime());
					//System.out.println("expiryDate ="+expiryDate[i]);
					
//					if(expiryDate[i] == null){
//						tsExpiry = null;
//					}
//					else {
//						 logger.debug("not null");
//						 java.util.Date parsedDates = dateFormat.parse(expiryDate[i]);
//						 tsExpiry = new java.sql.Timestamp(parsedDates.getTime());
//					}
					
					 dto.setPickingId(pickingNo);
					 products.setProductId(productId[i].trim());
					 products.setProductCode(productCode[i].trim());
					 products.setProductName(productName[i].trim());
					 dto.setProducts(products);
//					 dto.setReceivedDate(tsReceive);
//					 dto.setExpiredDate(tsExpiry);
					 dto.setUnitCode(unitCode[i].trim());
					 
					 if(Integer.parseInt(reqQty[i].trim())>0){
						 dto.setQtyOrder(Integer.parseInt(reqQty[i].trim()));
					 }
					 
					
					 dto.setQtyPick(Integer.parseInt(value[i].trim().replace("<br />", "")));
					
					 //dto.setBalance(Balance);
					 
//					 dto.setLotId(lotId[i].trim());
					 wLoc.setLocationCode(location[i].trim());
					 dto.setWhlocation(wLoc);
					 dto.setUserId(userId);
					 corp.setId(corporate);
					 dto.setCorp(corp);
					 wh.setWhCode(warehouse);
					 dto.setWh(wh);
					 //daoPickDetail.update(dto); untuk sync tidak terjadi update pada table picking_detail
					 
					 PutawayDetail pud	= new PutawayDetail();
					 PutawayDetailDao putdao = DaoFactory.createPutawayDetailDao();
					 pud.setProductId(dto.getProducts().getProductId());
					 pud.setLocationCode(dto.getWhlocation().getLocationCode());
//					 pud.setLotid(dto.getLotId());
					 pud.setCorpId(dto.getCorp().getId());
					 pud.setWhCode(dto.getWh().getWhCode());
					 List<PutawayDetail> putd = putdao.findWhereProductModel(pud);
					 
					 String putawayId ="";
					 Integer qtyPut = 0;
					 for(PutawayDetail pd : putd){
						 putawayId =  pd.getPutawayId();
						 qtyPut = pd.getQtyPut();
					 }
					 System.out.println("putawayId ="+putawayId);
					 
					 dtoput.setQtyPut(qtyPut);
					 dtoput.setQtyPick(dto.getQtyPick());
//					 dtoput.setQtyLot(dtoput.getQtyPut() - dtoput.getQtyPick());
					 dtoput.setPutawayId(putawayId);
					 dtoput.setProductId(dto.getProducts().getProductId());
//					 dtoput.setLotid(dto.getLotId());
					 dtoput.setLocationCode(dto.getWhlocation().getLocationCode());
					 dtoput.setCorpId(dto.getCorp().getId());
					 dtoput.setWhCode(dto.getWh().getWhCode());
					 
					 //update qty pick
					 daoPutDetail.update(dtoput);
					
//					 if(lotId[i].trim().length() == 1){
//						 System.out.println(" >>>lotid length =1 ="+dtoput.getLotid().length());
						 
						 String locationCode = dtoput.getLocationCode();
						 System.out.println("locationCode ="+locationCode);
						 
						//update balance location
						 List<PutawayDetail> bLocation = putdao.findBalanceLocation(pud);
						 Integer balanceLocation = 0;
						 int ID = 0;
						 for(PutawayDetail bl : bLocation){
							 balanceLocation = bl.getBalance();
						 }
						 System.out.println("balance location ="+balanceLocation);
						 dtoput.setBalance(balanceLocation - dtoput.getQtyPick());
						 System.out.println(" current balance ="+dtoput.getBalance());
						 dtoput.setLocationCode(locationCode);
						 dtoput.setId(ID);
						 
						//update balance by location
						 daoPutDetail.updateBalance(dtoput);
						 System.out.println(" >>> finished update <<< ");
					    //update total balance to all location 
						//max id
//						 List<PutawayDetail> maxId = putdao.findMaxId(pud);
//						 Integer Id = 0;
//						 for(PutawayDetail pdI : maxId){
//							 Id = pdI.getId();
//						 }
//						 System.out.println("max id ="+Id);
//						 pud.setId(Id);
//						 
						 //catatan rendra 23 May 2013
						 
						 
						 
						 //update max balance 
//						 List<PutawayDetail> mb = putdao.findMaxBalance1(pud);
//						 @SuppressWarnings("unused")
//						 Integer maxbalance1 = 0;
//						 for(PutawayDetail pdB : mb){
//							 maxbalance1 =  pdB.getBalance();
//							 
//						 }
//						 System.out.println("max balance1 ="+maxbalance1);
						 
//						 dtoput.setBalance(maxbalance1 - dtoput.getQtyPick());
//						 dtoput.setId(Id);
						 
						//update balance with maxId
						 //daoPutDetail.updateBalance1(dtoput);
					 
						//update qty_put
		   //			 daoPutDetail.updateQtyPut(dtoput);
					 
					 
					 /*
					 else if(lotId[i].trim().length() > 1){
						 System.out.println(" ********************************* >>>lotid length >1 ="+dtoput.getLotid().length());
						 
						 	// update total balance to all location 
							//max id
							 List<PutawayDetail> maxId = putdao.findMaxId(pud);
							 Integer Id = 0;
							 for(PutawayDetail pdI : maxId){
								 Id = pdI.getId();
							 }
							 System.out.println("max id ="+Id);
							 pud.setId(Id);
							 
							 //update max balance 
							 List<PutawayDetail> mb = putdao.findMaxBalance1(pud);
							 @SuppressWarnings("unused")
							 Integer maxbalance1 = 0;
							 for(PutawayDetail pdB : mb){
								 maxbalance1 =  pdB.getBalance();
								 
							 }
							 System.out.println("max balance1 ="+maxbalance1);
							 
							 dtoput.setBalance(maxbalance1 - dtoput.getQtyPick());
							 dtoput.setId(Id);
							 
							//update balance with maxId
							 daoPutDetail.updateBalance1(dtoput);
							 
							//update qty_put
							 daoPutDetail.updateQtyPut(dtoput);
						
					 }
					 
					 
					*/
					 
					 
				}
				
				
				
				
			}
			
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("Error", "picking", e);
		}
        return new ModelAndView("6_picking/Picking2List", "model", map);
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
		 return new ModelAndView("6_picking/Picking2List", "model", map);
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
					returnMap.put("pickingNo",pickingNo);
					returnMap.put("productCode",productCode);
					returnMap.put("productName",productName);
					returnMap.put("quantity",quantity);
					
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
					Logger.getLogger(Picking2Controller.class.getName()).log(Level.SEVERE, null, ex);
				}catch(IOException ex){
					Logger.getLogger(Picking2Controller.class.getName()).log(Level.SEVERE, null, ex);
				}
				
			}
  
  
}
