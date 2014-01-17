package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.BeritaAcaraDao;
import com.app.wms.engine.db.dto.BeritaAcara;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class BeritaAcaraController extends MultiActionController {
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
                return new ModelAndView("1_setup/BAPEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("1_setup/BAPList", "model", m);
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

            BeritaAcara b = new BeritaAcara();
            b.setBeritaNo(request.getParameter("beritano"));
            b.setPonumber(request.getParameter("ponumber"));
            
            BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
            List<BeritaAcara> listSearchPage = dao.findBeritaAcaraPaging(b,page);
            int total = 2000; 
            m.put("beritaacara", listSearchPage);
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
		 BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();	
		 BeritaAcara dto = new BeritaAcara();

         String mode = request.getParameter("mode");
         if (mode != null && mode.equals("edit")) {
        	 Integer id = Integer.parseInt(request.getParameter("id"));
             dto = dao.findByPrimaryKey(id);
            
         }
         if (dto.getBeritaNo() == null) {
        	 
        	 dto.setBeritaNo("");
        	 dto.setPonumber("");
             dto.setIsActive("Y");
             dto.setIsDelete("N");
         }
         
         if (dto.getBeritaNo() != null || dto.getPonumber() != null) {
        	 dto.setPonumber(dto.getPonumber());
        	 dto.setPonumber(dto.getPonumber());
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
			
			BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
            List<BeritaAcara> dto = dao.findAll();

            return new ModelAndView( "1_setup/BAPList", "model", dto);
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
       
        BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
        Integer id = Integer.parseInt(request.getParameter("id"));
        BeritaAcara dto = dao.findByPrimaryKey(id);
        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(createdBy);
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("1_setup/BAPList", "model", m);
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
		
		BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
		
		return new ModelAndView( "1_setup/BAPAdd", "model", map);
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
      BeritaAcara dto = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          BeritaAcaraDao dao = DaoFactory.createBeritaAcaraDao();
          if (isCreate) {
        	  dto = new BeritaAcara();
          } else {
        	  Integer id = Integer.parseInt(request.getParameter("id"));
              dto = dao.findByPrimaryKey(id);
          }
          
          String beritano = request.getParameter("beritano");
          Date beritadate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("beritadate") + "");
          String ponumber = request.getParameter("purchaseNo");
          String podate = request.getParameter("podate");
          Date podates = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(podate);
          BigDecimal total = new BigDecimal(request.getParameter("total"));
          String contractorname = request.getParameter("contractor");
          BigDecimal resultwork = new BigDecimal(request.getParameter("resultwork"));
          String enduser = request.getParameter("enduser");
          //BigDecimal pertialcompletion = new BigDecimal(request.getParameter("pertialcompletion"));
          String pertialcompletion = request.getParameter("pertialcompletion");
          String plantengineer = request.getParameter("plantengineer");
          //BigDecimal retention = new BigDecimal(request.getParameter("retention"));
          String retention = request.getParameter("retention");
          String operationengineer = request.getParameter("operationengineer");
          //BigDecimal totalprogress = new BigDecimal(request.getParameter("totalprogress"));
          String totalprogress = request.getParameter("totalprogress");
          String descriptionwork = request.getParameter("descriptionwork");
          
          System.out.println("------------------------------------------");
          System.out.println("beritano ="+beritano);
          System.out.println("beritadate ="+beritadate);
          System.out.println("ponumber ="+ponumber);
          System.out.println("podate ="+podate);
          System.out.println("podates ="+podates);
          System.out.println("total ="+total);
          System.out.println("contractorname ="+contractorname);
          System.out.println("resultwork ="+resultwork);
          System.out.println("enduser ="+enduser);
          System.out.println("pertialcompletion ="+pertialcompletion);
          System.out.println("plantengineer ="+plantengineer);
          System.out.println("retention ="+retention);
          System.out.println("operationengineer ="+operationengineer);
          System.out.println("totalprogress ="+totalprogress);
          System.out.println("descriptionwork ="+descriptionwork);
          System.out.println("------------------------------------------");
          
          String[] productcode1s = request.getParameterValues("productCode1");
          String[] qtys = request.getParameterValues("qty1");

          for(int i = 0; i < productcode1s.length; i++){
	        	BeritaAcara beritaAcara = new BeritaAcara();
	        	beritaAcara.setProductcode(productcode1s[i]);
	        	beritaAcara.setQty(new BigDecimal(qtys[i]));
	        	
	        	System.out.println("------------------------------------------");
	        	System.out.println("productcode ="+productcode1s[i]);
	        	System.out.println("qty ="+new BigDecimal(qtys[i]));
	        	System.out.println("------------------------------------------");
	        	//daod.insert(prsDetail);
	        	
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
          
          dto.setBeritaNo(beritano);
          dto.setPonumber(ponumber);
          dto.setBeritaDate(new Date());
          //
          //......
          //
          dto.setIsActive(request.getParameter("isActive"));
          dto.setIsDelete("N");
          dto.setUpdatedBy(userId);
          dto.setUpdatedDate(new java.util.Date());

          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
//#              dao.insert(dto);
          } 
          
          else {
              dto.setUpdatedBy(userId);
              dto.setUpdatedDate(new java.util.Date());
//#              dao.update(dto.createPk(), dto);
          }
		 
          return new ModelAndView("1_setup/BAPView", "dto", dto);

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);

          if (isCreate) {
              return new ModelAndView("1_setup/BAPAdd", "model", m);
          } else {
              return new ModelAndView("1_setup/BAPEdit", "model", m);
          }
      }
      
  }
	


}
