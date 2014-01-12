package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dao.spring.PutawayDao;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Putaway;
import com.app.wms.hbm.bean.PutawayDetail;
import com.app.wms.hbm.bean.Vputawaydetailproduct;
import com.app.wms.web.util.AppConstant;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PutawayController  extends ReportManagerController 
{

    @Autowired
    private PutawayDao putawayDao;
    
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
                return new ModelAndView("3_putaway/PutawayEdit", "model", m);
            } else {
                m = this.searchAndPaging(request, response);
                
                 String putawaycode = request.getParameter("putawaycode");
                String grcode = request.getParameter("grcode");
                String condition = request.getParameter("condition");
                
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
                if(putawaycode == null && grcode == null && condition == null) {
                     m.put("putaways", putawayDao.getPutaways(lu.getCorpId(), lu.getWhCode())); 
                } else {
                    m.put("putaways", putawayDao.getPutaways(lu.getCorpId(), lu.getWhCode(), putawaycode, grcode, condition));
                     m.put("putawaycode", putawaycode == null? "" : putawaycode);
                    m.put("grcode", grcode == null? "":grcode);
                }
                
                
                
                 
                return new ModelAndView("3_putaway/PutawayList", "model", m);
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

            WarehouseSearch c = null;
            if (request.getParameter("btnViewAll") != null) {
                request.getSession().setAttribute("WarehouseSearch", null);
            } else if (request.getParameter("btnSearch") != null) {
                System.out.println("create new search session");
                c = new WarehouseSearch();
                c.setWhCode(request.getParameter("whCode"));
                c.setName(request.getParameter("name"));
                request.getSession().setAttribute("WarehouseSearch", c);
            } else if (request.getSession().getAttribute("WarehouseSearch") != null) {
                System.out.println("use previous search session");
                c = (WarehouseSearch) request.getSession().getAttribute("WarehouseSearch");
            } else {
                // no criteria
            }

            WhDao dao = DaoFactory.createWhDao();
//            List<Wh> list = dao.findByCriteriaLimit(c, start, end);

            int total = 2000; 
//            m.put("wh", list);
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
            java.lang.String putawayNo = request.getParameter("putawayNo");
                        
            Putaway dto = new Putaway();           
            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = putawayDao.getPutaway(putawayNo);
            }
            
            //Update rendra 24/04/2012, double list location and product
            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
            String corpId = (String)lu.getCorpId();
            String whCode = (String)lu.getWhCode();
            
            List<Vputawaydetailproduct> vputawaydetailproducts = new ArrayList<Vputawaydetailproduct>();
            //vputawaydetailproducts = putawayDao.getPutawayDetails(putawayNo);//source before
            //Update rendra 24/04/2012, double list location and product
            vputawaydetailproducts = putawayDao.getPutawayDetails(putawayNo, corpId, whCode);
            
            HashMap m = new HashMap();
            m.put("dto", dto);
            m.put("vputawaydetailproducts", vputawaydetailproducts);

            return m;

        } catch (Exception e) {
            throw e;
        }
    }
	
	public ModelAndView inactivate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String whCode = request.getParameter("whCode");

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        java.math.BigDecimal pcreatedBy = new BigDecimal(lu.getUserId());
       
        WhDao dao = DaoFactory.createWhDao();

//        Wh dto = dao.findByPrimaryKey(whCode);
//
//        if (dto != null) {
//            dto.setIsActive(AppConstant.STATUS_FALSE);
//            dto.setUpdatedBy(pcreatedBy);
//            dto.setUpdatedDate(new java.util.Date());
//            dao.update(dto.createPk(), dto);
//        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("3_putaway/PutawayList", "model", m);
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
			
			WhDao dao = DaoFactory.createWhDao();
		
			List<Wh> dto = dao.findAll();
		
			return new ModelAndView( "3_putaway/PutawayList", "result", dto );
		}
		catch (Throwable e) {
			e.printStackTrace();
			return new ModelAndView( "Error", "th", e );
		}
		
	}

	/**
	 * Method 'findWhereWhCodeEquals'
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return ModelAndView
	 */
	public ModelAndView findWhereWhCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {
			// parse parameters
			java.lang.String pwhCode = request.getParameter("whCode");
		
			// create the DAO class
			WhDao dao = DaoFactory.createWhDao();
		
			// execute the finder
			List<Wh> dto = dao.findWhereWhCodeEquals(pwhCode);
		
			return new ModelAndView( "3_putaway/PutawayList", "result", dto );
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
			// parse parameters
			java.lang.String pname = request.getParameter("name");
		
			// create the DAO class
			WhDao dao = DaoFactory.createWhDao();
		
			// execute the finder
			List<Wh> dto = dao.findWhereNameEquals(pname);
		
			return new ModelAndView( "3_putaway/PutawayList", "result", dto );
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
		HashMap m = this.getModelByPrimaryKey(request);
		m = this.getModelByPrimaryKey(request);
		m.put("mode", "create");
                
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                
		m.put("wh", lu.getWhCode());
		m.put("punumber", generatePUNumber(putawayDao.getPutaways(lu.getCorpId(), lu.getWhCode()).size()+1, request));
		return new ModelAndView( "3_putaway/PutawayAdd", "model", m);
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
      java.lang.String mode = request.getParameter("mode");
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          java.lang.String whCode = request.getParameter("whCode");
          if (whCode.trim().isEmpty()) {
              strError += "Warehouse Code Cannot be Empty!" + AppConstant.EOL;
          }

          WhDao dao = DaoFactory.createWhDao();
          Wh dto = null;
          if (isCreate) {
              dto = new Wh();
          } else {
//              dto = dao.findByPrimaryKey(whCode);
          }

          java.lang.String pname = request.getParameter("name");
          if (pname.trim().isEmpty()) {
              strError += "Name Cannot be Empty!" + AppConstant.EOL;
          }

          java.lang.String pisActive = request.getParameter("isActive");
          LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
          java.math.BigDecimal userId = new BigDecimal(lu.getUserId());

          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
              dto.setCreatedBy(userId+"");
              dto.setCreatedDate(new java.util.Date());
          }

          dto.setWhCode(whCode);
          dto.setName(pname);
          dto.setIsActive(pisActive);

          if (isCreate) {
             // dao.insert(dto);
          } else {
//              dto.setUpdatedBy(userId);
              dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
          }

          return new ModelAndView("3_putaway/PutawayView", "dto", dto);

      } catch (org.springframework.dao.DataIntegrityViolationException e) {
          String errorMsg = "Unique Key Constraint [Code, Name]!" + AppConstant.EOL;
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println(errorMsg);
          if (isCreate) {
              return new ModelAndView("3_putaway/PutawayAdd", "model", m);
          } else {
              return new ModelAndView("3_putaway/PutawayEdit", "model", m);
          }
      } catch (Exception e) {
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println(errorMsg);
          if (isCreate) {
              return new ModelAndView("3_putaway/PutawayAdd", "model", m);
          } else {
              return new ModelAndView("3_putaway/PutawayEdit", "model", m);
          }
      }

  }

        @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        /*
	         *  GET LOGIN USER
	         */
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String ponumber = request.getParameter("ponumber");
	        HashMap model = new HashMap();

	        
	        List<PutawayDetail> listSearch	= putawayDao.getPutawayDetails2(ponumber);
                System.out.println("ponumber "+ponumber);
	        
	        if(listSearch != null){
	            System.out.println("[ponumber][Ajax Document] grNo : " + ponumber + " is valid");
	           
	            Map tableMap = new HashMap();
	            for (PutawayDetail searchDetail : listSearch){
	            	
                        System.out.println(">>>>>> "+searchDetail);
                        
	            	Map returnMap = new HashMap();
                        String putawayId    = (searchDetail).getPutawayId();
                        String productCode  = (searchDetail).getProductCode();
                        
                        String locationCode  = (searchDetail).getLocationCode();
                        String whCode  = (searchDetail).getWhCode();
                        int quantity    	= (searchDetail).getQtyPut();

                        returnMap.put("id",(searchDetail).getId());
                        returnMap.put("putawayId",putawayId);
                        returnMap.put("productCode",productCode);
                        
                        returnMap.put("productId",(searchDetail).getProductId());
                        returnMap.put("quantity",quantity);
                        returnMap.put("locationCode",locationCode);
                        returnMap.put("whCode",whCode);                        

                        tableMap.put(returnMap, returnMap);
                    }
	            
	            model.put("master", listSearch);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[ponumber][Ajax Document] purchase order no : " + ponumber + " is not valid");
	        }

	        return new ModelAndView("3_putaway/util/PutawayDetail", "model", model);
	    }


            public void doPrint(HttpServletRequest request, HttpServletResponse response) throws Exception
           {


                           String salesOrderNo = request.getParameter("sonumber");
                           System.out.println("salesOrderNo ="+salesOrderNo);

                           templateName = request.getParameter("templateName");
                           System.out.println("templateName ="+templateName);

                           parametersKey = request.getParameter("parametersKey");
                           System.out.println("parameterKey ="+parametersKey);

                           ArrayList resultList = new ArrayList();
                           resultList.add(salesOrderNo);
                           setParameterValues(resultList);

                           List paramKey = new ArrayList();
                           paramKey.add(parametersKey);
                           setParameterKeys((ArrayList<String>) paramKey);
                           outputFormat = "pdf";
                           createOnlineReport();

                           try{
                                   printToStream(response);

                           }catch(FileNotFoundException ex){
           //			Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                               ex.printStackTrace();

                           }catch(Exception ex){
           //			Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                               ex.printStackTrace();
                           }

                   }
            
            private String generatePUNumber(int size, HttpServletRequest request) {
             // PUWHYYYYXXXXXXX
             LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
             String head = "PU";             
             String wh = lu.getWhCode();             
             String year =  new SimpleDateFormat("yyyy").format(new Date());
             String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
             
             return head+wh+year+tail;
         }

}
