package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.web.engine.search.CorporateSearch;
import com.app.wms.engine.db.dao.CityDao;
import com.app.wms.engine.db.dao.CorpDao;
import com.app.wms.engine.db.dao.CorporateDao;
import com.app.wms.engine.db.dao.ProvinceDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dto.City;
import com.app.wms.engine.db.dto.Corp;
import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Province;
import com.app.wms.engine.db.dto.User;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.engine.util.ctrlIDGenerator;
import com.app.wms.web.util.AppConstant;

public class CorporateController extends MultiActionController {
	
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
                return new ModelAndView("1_setup/CorporateEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/CorporateList", "model", m);
            }

        }
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{

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
            CorporateSearch c = null;
            if (request.getParameter("btnViewAll") != null) {
                request.getSession().setAttribute("CorporateSearch", null);
            } else if (request.getParameter("btnSearch") != null) {
                System.out.println("create new search session");
                c = new CorporateSearch();
                c.setId(request.getParameter("corpId"));
                c.setName(request.getParameter("name"));
                request.getSession().setAttribute("CorporateSearch", c);
            } else if (request.getSession().getAttribute("CorporateSearch") != null) {
                System.out.println("use previous search session");
                c = (CorporateSearch) request.getSession().getAttribute("CorporateSearch");
            } else {
                // no criteria
            }
			*/
            CorporateSearch c = new CorporateSearch();
            c.setId(request.getParameter("corpId"));
            c.setName(request.getParameter("name"));
            request.getSession().setAttribute("CorporateSearch", c);
            
            CorpDao dao = DaoFactory.createCorpDao();
            List<Corp> listSearchPage = dao.findCorporatePaging(c, page);

            int total = 2000; 
            m.put("corp", listSearchPage);
            m.put("totalRows", total);
            m.put("page", page);
            m.put("paging", paging);

            return m;

		}catch (Exception e){
			throw e;
		}
		
	}

	private HashMap getModelByPrimaryKey(HttpServletRequest request) throws Exception {
        try {
            java.lang.String corpId = request.getParameter("corpId");
            CorpDao dao = DaoFactory.createCorpDao();
            Corp dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(corpId);
                dto.setIsActive("Y");
            }

            if (dto == null) {
                dto = new Corp();
                dto.setCorpId("");
                dto.setCorpName("");
//                dto.setWhCode("");
                dto.setAddress1("");
                dto.setAddress2("");
                dto.setAddress3("");
                dto.setEmail("");
                dto.setCityCode("");
                dto.setZipcode("");
                dto.setPhone1("");
                dto.setPhone2("");
                dto.setFax("");
                dto.setProvinceCode("");
                dto.setIsActive("Y");
            }
            //edit
            HashMap m = new HashMap();
            
//            WhDao daoWarehouse = DaoFactory.createWhDao();
//    		List<Wh> dropListWarehouse = daoWarehouse.findAll();
//    		
    		CityDao daoCity = DaoFactory.createCityDao();
    		List<City> dropListCity = daoCity.findAll();
    		
    		ProvinceDao daoProvince = DaoFactory.createProvinceDao();
    		List<Province> dropListProvince = daoProvince.findAll();
//			m.put("dropListWarehouse", dropListWarehouse);
			m.put("dropListCity", dropListCity);
			m.put("dropListProvince", dropListProvince);
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
			
			CorpDao dao = DaoFactory.createCorpDao();
			List<Corp> dto = dao.findAll();
			
			return new ModelAndView( "1_setup/CorporateList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String corpId = request.getParameter("corpId");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = (String)lu.getUserId();
       
        CorpDao dao = DaoFactory.createCorpDao();

        Corp dto = dao.findByPrimaryKey(corpId);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/CorporateList", "model", m);
    }
	
	
	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereIdEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			String corpId = request.getParameter("corpId");

			CorpDao dao = DaoFactory.createCorpDao();
		
			List<Corp> dto = dao.findWhereCorpIdEquals(corpId);
		
			return new ModelAndView( "1_setup/CorporateList", "model", dto );
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
			String name = request.getParameter("name");
		
			CorpDao dao = DaoFactory.createCorpDao();
		
			List<Corp> dto = dao.findWhereCorpNameEquals(name);
		
			return new ModelAndView( "1_setup/CorporateList", "model", dto );
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
		Map map = new HashMap();
		map = this.getModelByPrimaryKey(request);
		map.put("mode", "create");		
		
//		WhDao daoWarehouse = DaoFactory.createWhDao();
//		List<Wh> dropListWarehouse = daoWarehouse.findAll();
		
		CityDao daoCity = DaoFactory.createCityDao();
		List<City> dropListCity = daoCity.findAll();
		
		ProvinceDao daoProvince = DaoFactory.createProvinceDao();
		List<Province> dropListProvince = daoProvince.findAll();
		
		String genCorpId = ""+new ctrlIDGenerator().getIDCorporate();
		map.put("corpId", genCorpId);
		
		if(dropListCity.size()>0 && dropListProvince.size()>0 ){
//			map.put("dropListWarehouse", dropListWarehouse);
			map.put("dropListCity", dropListCity);
			map.put("dropListProvince", dropListProvince);			
			return new ModelAndView( "1_setup/CorporateAdd", "model", map);
		}
		
		return new ModelAndView( "1_setup/CorporateAdd", "model", map);
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
      Date now = new Date();
      java.lang.String mode = request.getParameter("mode");
      Corp dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          String corpId = request.getParameter("corpId");
          
          CorpDao dao = DaoFactory.createCorpDao();
          if (isCreate) {
        	  dto = new Corp();
          } else {
              dto = dao.findByPrimaryKey(corpId);
          }
          
//          String corpCd = request.getParameter("code");
//          if (corpCd.trim().isEmpty()) {
//              strError += "Corporate Code Cannot be Empty!" + AppConstant.EOL;
//          }
//          List<Corp> tmpCorp = dao.findWhereCorpIdEquals(corpCd);
//	  	  if ((isCreate && tmpCorp != null && tmpCorp.size() > 0) || (!isCreate && tmpCorp != null && tmpCorp.size() > 0 && !tmpCorp.get(0).getCorpId().equals(corpId))) {
//	  		  strError += "Corporate Code Already Existed!" + AppConstant.EOL;
//	  	  }

          String name = request.getParameter("name");
          if (name.trim().isEmpty()) {
              strError += "Corporate Name Cannot be Empty!" + AppConstant.EOL;
          }
          
//          String warehouse = request.getParameter("warehouse");
          
          String address1 = request.getParameter("address1");
          if (address1.trim().isEmpty()) {
              strError += "Address1 Cannot be Empty!" + AppConstant.EOL;
          }
          
          String address2 = request.getParameter("address2");
          if (address2.trim().isEmpty()) {
              strError += "Address2 Cannot be Empty!" + AppConstant.EOL;
          }

          String address3 = request.getParameter("address3");
          
          String email = request.getParameter("email");
          if (email.trim().isEmpty()) {
              strError += "Email Cannot be Empty!" + AppConstant.EOL;
          }
          
          String city = request.getParameter("city");
          
          String zip = request.getParameter("zipcode");
          if (zip.trim().isEmpty()) {
              strError += "Zip Code Cannot be Empty!" + AppConstant.EOL;
          }
          
          String phone1 = request.getParameter("phone1");
          if (phone1.trim().isEmpty()) {
              strError += "Phone1 Cannot be Empty!" + AppConstant.EOL;
          }
          
          String phone2 = request.getParameter("phone2");
          String fax = request.getParameter("fax");
          String province = request.getParameter("province");
          
          java.lang.String pisActive = request.getParameter("isActive");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = (String)lu.getUserId();

          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }
          dto.setCorpId(corpId);
          dto.setCorpName(name);
//          dto.setWhCode(warehouse);
          dto.setAddress1(address1);
          dto.setAddress2(address2);
          dto.setAddress3(address3);
          dto.setEmail(email);
          dto.setCityCode(city);
          dto.setZipcode(zip);
          dto.setPhone1(phone1);
          dto.setPhone2(phone2);
          dto.setFax(fax);
          dto.setProvinceCode(province);
          dto.setIsActive(pisActive);
          dto.setIsDelete("N");
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(new java.util.Date());

          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
              dao.insert(dto);
          } else {
              dto.setUpdatedBy(userId);
              dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
          }

          return new ModelAndView("1_setup/CorporateView", "dto", dto);
/*
      } catch (org.springframework.dao.DataIntegrityViolationException e) {
          String errorMsg = "Unique Key Constraint [CorpId]!" + AppConstant.EOL;
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println("error1="+errorMsg);
          if (isCreate) {
              return new ModelAndView("1_setup/CorporateAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/CorporateEdit", "model", m);
          }
*/
      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println("error2="+errorMsg);
          if (isCreate) {
        	  String genCorpId = ""+new ctrlIDGenerator().getIDCorporate();
      		  m.put("corpId", genCorpId);
              return new ModelAndView("1_setup/CorporateAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/CorporateEdit", "model", m);
          }
      }

  }

}
