package com.app.wms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.web.engine.search.WarehouseLocationSearch;
import com.app.wms.engine.db.dao.CorporateDao;
import com.app.wms.engine.db.dao.WarehouseLocationDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dao.WhLocatingAreaDao;
import com.app.wms.engine.db.dao.WhLocationDao;
import com.app.wms.engine.db.dao.WhLocationDetailDao;
import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.WhLocatingArea;
import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.dto.WhLocationDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class WarehouseLocationController extends MultiActionController {
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
                return new ModelAndView("1_setup/WarehouseLocationEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/WarehouseLocationList", "model", m);
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

            /*
            WarehouseLocationSearch c = null;
            if (request.getParameter("btnViewAll") != null) {
                request.getSession().setAttribute("WarehouseLocationSearch", null);
            } else if (request.getParameter("btnSearch") != null) {
                System.out.println("create new search session");
                c = new WarehouseLocationSearch();
                c.setWarehouseLocationCode(request.getParameter("locationCode"));
                c.setWarehouseLocationName(request.getParameter("locationName"));
                request.getSession().setAttribute("WarehouseLocationSearch", c);
            } else if (request.getSession().getAttribute("WarehouseLocationSearch") != null) {
                System.out.println("use previous search session");
                c = (WarehouseLocationSearch) request.getSession().getAttribute("WarehouseLocationSearch");
            } else {
                // no criteria
            }
			*/
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            WhLocation w = new WhLocation();
            w.setLocationCode(request.getParameter("locationCode"));
            w.setLocationName(request.getParameter("locationName"));
            
            w.setCorpId(lu.getCorpId());
            w.setWhCode(lu.getWhCode());
            WhLocationDao dao = DaoFactory.createWhLocationDao();
            List<WhLocation> listSearchPage = dao.findByLocationPaging(w,page);

            int total = 2000; 
            m.put("location", listSearchPage);
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
        	
        	 LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
             WhLocation w = new WhLocation();
        	 w.setCorpId(lu.getCorpId());
        	 w.setWhCode(lu.getWhCode());
             
            String locationId = request.getParameter("locationId");
            System.out.println("locationId ="+locationId);
            WhLocationDao dao = DaoFactory.createWhLocationDao();
            WhLocation dto = new WhLocation();

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
//                dto = dao.findByPrimaryKey(locationId);
            	List<WhLocation> listLocation = dao.findWhereLocationIdEquals(w,locationId);
            	
            	for(WhLocation wl : listLocation){
            	  dto.setLocationId(((WhLocation)wl).getLocationId());
              	  dto.setLocationCode(((WhLocation)wl).getLocationCode()); 
              	  dto.setLocationName(((WhLocation)wl).getLocationName()); 
              	  dto.setLocationType(((WhLocation)wl).getLocationType());
              	  dto.setProductCode(((WhLocation)wl).getProductCode());
              	  dto.setProductName(((WhLocation)wl).getProductName());
              	  dto.setMinProduct(((WhLocation)wl).getMinProduct());
              	  dto.setMaxProduct(((WhLocation)wl).getMaxProduct());
              	  dto.setLocatingArea(((WhLocation)wl).getLocatingArea());
              	  dto.setLocatingZone(((WhLocation)wl).getLocatingZone());
              	  dto.setLocationBay(((WhLocation)wl).getLocationBay());
              	  dto.setLocationLevel(((WhLocation)wl).getLocationLevel());
              	  dto.setLocationPosition(((WhLocation)wl).getLocationPosition());
              	  dto.setWorkZone(((WhLocation)wl).getWorkZone());
              	  dto.setIsActive(((WhLocation)wl).getIsActive());
              	 
                }
                
            }

            if (dto.getLocationCode() == null) {
                dto = new WhLocation();
                dto.setLocationCode("");
                dto.setLocationName("");
                dto.setMinProduct(0);
                dto.setMaxProduct(0);
                dto.setLocatingZone("");
                dto.setLocationBay("");
                dto.setLocationLevel("");
                dto.setAllocationZone("");
                dto.setWorkZone("");
                
                dto.setIsActive("Y");
                dto.setIsDelete("N");
            }
            
            HashMap m = new HashMap();
            WhLocatingAreaDao daoLocating = DaoFactory.createWhLocatingAreaDao();
    		List<WhLocatingArea> dropListLocatingArea = daoLocating.findAll();
    		
    		m.put("dropListLocatingArea", dropListLocatingArea);
            m.put("dto", dto);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String locationId = request.getParameter("locationId");
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String pcreatedBy = lu.getUserId();
       
        WhLocationDao dao = DaoFactory.createWhLocationDao();
        WhLocation dto = dao.findByPrimaryKey(locationId);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/WarehouseLocationList", "model", m);
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
			
			WhLocationDao dao = DaoFactory.createWhLocationDao();
		
			List<WhLocation> dto = dao.findAll();
		
			return new ModelAndView( "1_setup/WarehouseLocationList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereLocationCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereLocationCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String locationCode = request.getParameter("locationCode");
		
			// create the DAO class
			WhLocationDao dao = DaoFactory.createWhLocationDao();
		
			// execute the finder
			List<WhLocation> dto = dao.findWhereLocationCodeEquals(locationCode);
		
			return new ModelAndView( "1_setup/WarehouseLocationList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereLocationNameEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereLocationNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pname = request.getParameter("name");
		
			// create the DAO class
			WhLocationDao dao = DaoFactory.createWhLocationDao();
		
			// execute the finder
			List<WhLocation> dto = dao.findWhereLocationNameEquals(pname);
		
			return new ModelAndView( "1_setup/WarehouseLocationList", "result", dto );
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
		Map m = new HashMap();
		m = this.getModelByPrimaryKey(request);
		/*
		WhDao daoWarehouse = DaoFactory.createWhDao();
		List<Wh> dropListWarehouse = daoWarehouse.findAll();

		CorporateDao daoCorporates = DaoFactory.createCorporateDao();
		List<Corporate> dropListCorporate = daoCorporates.findAll();

		if(dropListWarehouse.size()>0 && dropListCorporate.size()>0){  			
		  m.put("dropListWarehouse", dropListWarehouse);
		  m.put("dropListCorporate", dropListCorporate);
		}
		*/
		WhLocatingAreaDao daoLocating = DaoFactory.createWhLocatingAreaDao();
		List<WhLocatingArea> dropListLocatingArea = daoLocating.findAll();
		
		m.put("dropListLocatingArea", dropListLocatingArea);
		m.put("mode", "create");
		return new ModelAndView( "1_setup/WarehouseLocationAdd", "model", m);
	}

	/**
	 * Method 'save'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

      boolean isCreate = true;
      String strError = "";
      String mode = request.getParameter("mode");
      WhLocation dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }
          
          String locationId = request.getParameter("locationId");
          WhLocationDao dao = DaoFactory.createWhLocationDao();
          if (isCreate) {
              dto = new WhLocation();
          } else {
              dto = dao.findByPrimaryKey(locationId);
          }
          
          String locationCode = request.getParameter("locationCode");
          if (locationCode.trim().isEmpty()) {
              strError += "Location Code Cannot be Empty!" + AppConstant.EOL;
          }
          /*
          List<WhLocation> tmpLocation = dao.findWhereLocationCodeEquals(locationCode);
  	      if ((isCreate && tmpLocation != null && tmpLocation.size() > 0) || (!isCreate && tmpLocation != null && tmpLocation.size() > 0 && !tmpLocation.get(0).getUserId().equals(locationCode))) {
  	    	  strError += "Location Code Already Existed!" + AppConstant.EOL;
  	      }
		  */
          String locationName = request.getParameter("locationName");
          if (locationName.trim().isEmpty()) {
              strError += "Location Name Cannot be Empty!" + AppConstant.EOL;
          }
          
          String locationType = request.getParameter("locationType");
          
          String productCode = request.getParameter("productcode");
          if (productCode.trim().isEmpty()) {
              strError += "Product Code Cannot be Empty!" + AppConstant.EOL;
          }
          request.getSession().setAttribute("productcode", productCode);
          
          String productName = request.getParameter("productname");
          if (productName.trim().isEmpty()) {
              strError += "Product Name Cannot be Empty!" + AppConstant.EOL;
          }
          request.getSession().setAttribute("productname", productName);
          
          String minProduct = request.getParameter("min");
          int min = Integer.parseInt(minProduct);
          if (minProduct.trim().isEmpty()) {
              strError += "Minimum Product Cannot be Empty!" + AppConstant.EOL;
          }
          
          String maxProduct = request.getParameter("max");
          int max = Integer.parseInt(maxProduct);
          if (maxProduct.trim().isEmpty()) {
              strError += "Maximum Product Cannot be Empty!" + AppConstant.EOL;
          }

          String locatingArea = request.getParameter("locatingArea");
          String zone = request.getParameter("zone");
          String bay = request.getParameter("bay");
          String level = request.getParameter("level");
          
          String locatingPosition = request.getParameter("locatingPosition");
          String allocationZone = request.getParameter("allocationZone");
          String workZone = request.getParameter("workZone");
          
          String pisActive = request.getParameter("isActive");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = lu.getUserId();
          String corpId = lu.getCorpId();
          String whCode = lu.getWhCode();

          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(new java.util.Date());
          }

          dto.setLocationCode(locationCode);
          dto.setLocationName(locationName);
          dto.setLocationType(locationType);
          dto.setMinProduct(min);
          dto.setMaxProduct(max);
          dto.setLocatingArea(locatingArea);
          dto.setLocatingZone(zone);
          dto.setLocationBay(bay);
          dto.setLocationLevel(level);
          dto.setLocationPosition(locatingPosition);
          dto.setAllocationZone(allocationZone);
          dto.setWorkZone(workZone);
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(new java.util.Date());
          dto.setIsActive(pisActive);
          
          dto.setCorpId(corpId);
          dto.setUserId(userId);
          dto.setWhCode(whCode);
          
          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
              dao.insert(dto);
          } 
          
          else {
              dto.setUpdatedBy(userId);
              dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
          }
          
          WhLocationDetailDao daodtl = DaoFactory.createWhLocationDetailDao();
          WhLocationDetail dso = null;
         // boolean detail = dso == null;
          
          String locationId1 = "";
          List<WhLocation> listLocation = dao.findWhereLocationCodeEquals(locationCode);
          for(WhLocation wLoc : listLocation){
        	  locationId1 =((WhLocation)wLoc).getLocationId();
          }
          
          ProductDao pd = DaoFactory.createProductDao();
          List<Product> listProduct = pd.findWhereProductCodeEquals(productCode);
          String productId = ""; 
          String productCategory = "";
          Integer piece = 0;
          Integer box = 0;
          Integer carton = 0;
          Integer palette = 0;
          for(Product pr : listProduct){
        	  productId = ((Product)pr).getProductId();
        	  productCategory =((Product)pr).getProductCategory();
        	  piece   = ((Product)pr).getUnitPiece();
        	  box     = ((Product)pr).getUnitBox();
        	  carton  = ((Product)pr).getUnitCartoon();
        	  palette = ((Product)pr).getUnitPallete();
          }
          
          String unitCode = "";
          if(piece > 0){
        	  unitCode = "PIECES";
          }
          if(box > 0){
        	  unitCode = "BOX";
          }
          if(carton > 0){
        	  unitCode = "CARTON";
          }
          if(palette > 0){
        	  unitCode = "PALETTE";
          }
          
          dso = new WhLocationDetail();
          if(isCreate){
//          	dso = new WhLocationDetail();
          	dso.setLocationId(locationId1);
          	dso.setProductId(productId);
          	dso.setProductCode(productCode);
          	dso.setProductName(productName);
          	dso.setUnitCode(unitCode);
          	dso.setProductCategory(productCategory);
          	dso.setUserId(userId);
          	dso.setCorpId(corpId);
          	dso.setWhCode(whCode);
          	if(dso.getProductCode()!=null){
          		daodtl.insert(dso);
          	}
          	
          }else{
        	  
        	  	dso.setLocationId(locationId1);
	        	dso.setProductId(productId);
	        	dso.setProductCode(productCode);
	        	dso.setProductName(productName);
	        	dso.setUnitCode(unitCode);
	        	dso.setProductCategory(productCategory);
	        	dso.setUserId(userId);
	        	dso.setCorpId(corpId);
	        	dso.setWhCode(whCode);
	        	daodtl.update(dso.createPk(), dso);
          }
          
          //bug untuk view
          dto.setProductCode(productCode);
          dto.setProductName(productName);
          return new ModelAndView("1_setup/WarehouseLocationView", "dto", dto);
      
      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println("errorMsg2 ="+errorMsg);
          if (isCreate) {
              return new ModelAndView("1_setup/WarehouseLocationAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/WarehouseLocationEdit", "model", m);
          }
      }

  }

}
