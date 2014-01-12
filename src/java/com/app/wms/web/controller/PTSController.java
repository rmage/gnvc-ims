package com.app.wms.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.wms.engine.db.dao.PtsDao;
import com.app.wms.engine.db.dto.Pts;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class PTSController extends MultiActionController {
	


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
                return new ModelAndView("2_receive/PTSEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("2_receive/PTSList", "model", m);
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

            Pts p = new Pts();
            
            String ptsCode = request.getParameter("");
            String ptsDate = request.getParameter("");
            
            p.setPtsCode(ptsCode);
            p.setPtsDate(ptsDate);
            /*
            p.setPackStyleSize(packStyleSize);
            p.setCanCode(canCode);
            p.setForBrand(forBrand);
            p.setReff(reff);
            p.setNsDs(nsDs);
            p.setProductBatch(productBatch);
            p.setBasket(basket);
            p.setQuantity(quantity);
            p.setFlkPercent(flkPercent);
            p.setNw(nw);
            p.setDw(dw);
            p.setPw(pw);
            p.setReleaseTo(releaseTo);
            p.setRemarks(remarks);
            p.setIssuedBy(issuedBy);
            p.setCheckedBy(checkedBy);
            p.setReceivedBy(receivedBy);
            p.setIsActive(isActive);
            */
            PtsDao dao = DaoFactory.createPtsDao();
            List<Pts> listSearchPage = dao.findPtsPaging(p,page);
            int total = 2000; 
            m.put("pts", listSearchPage);
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
		 PtsDao dao = DaoFactory.createPtsDao();
         Pts dto = new Pts();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getPtsCode() == null) {
        	 
        	 dto.setPtsCode("");
        	 dto.setPtsDate("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
         }
         
         if (dto.getPtsCode() != null || dto.getPtsDate() != null) {
        	 dto.setPtsCode(dto.getPtsCode());
        	 dto.setPtsDate(dto.getPtsDate());
             dto.setIsActive(dto.getIsActive());
             dto.setIsDelete(dto.getIsDelete());
         }
         //edit
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
			
			PtsDao dao = DaoFactory.createPtsDao();
			List<Pts> dto = dao.findAll();
			
			return new ModelAndView( "2_receive/PTSList", "model", dto);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        String createdBy = "";
        if (lu == null) {
			HashMap m = new HashMap();
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	createdBy = (String)lu.getUserId();
        }
       
        PtsDao dao = DaoFactory.createPtsDao();
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        Pts dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("2_receive/PTSList", "model", m);
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
		
		PtsDao dao = DaoFactory.createPtsDao();
		
		return new ModelAndView( "2_receive/PTSAdd", "model", map);
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
      Pts dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          PtsDao dao = DaoFactory.createPtsDao();
          if (isCreate) {
        	  dto = new Pts();
          } else {
        	  Integer id = Integer.parseInt(request.getParameter("id"));
              dto = dao.findByPrimaryKey(id);
          }
          
          String ptsCode = request.getParameter("ptsCode");
          if (ptsCode.trim().isEmpty()) {
              strError += "PTS Code Cannot be Empty!" + AppConstant.EOL;
          }

          String ptsDate = request.getParameter("ptsDate");
          if (ptsDate.trim().isEmpty()) {
              strError += "PTS Date Cannot be Empty!" + AppConstant.EOL;
          }
          
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          String userId = "";
          if (lu == null) {
  			HashMap m = new HashMap();
              String msg = "You haven't login or your session has been expired! Please do login again";
              m.put("msg", msg);
              return new ModelAndView("login", "model", m);
          }else{
        	  userId = (String)lu.getUserId();
          }

          if (isCreate) {
              dto.setCreatedBy(userId);
              dto.setCreatedDate(now);
          }
          
          dto.setPtsCode(ptsCode);
          dto.setPtsDate(ptsDate);
          dto.setIsActive("Y");
          dto.setIsDelete("N");
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(new java.util.Date());

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
		 
          return new ModelAndView("2_receive/PTSView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);

          if (isCreate) {
              return new ModelAndView("2_receive/PTSAdd", "model", m);
          } else {
              return new ModelAndView("2_receive/PTSEdit", "model", m);
          }
      }
      
  }



}
