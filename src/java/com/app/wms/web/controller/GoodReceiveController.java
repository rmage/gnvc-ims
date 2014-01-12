package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.GoodReceiveSearch;
import com.app.web.engine.search.WarehouseSearch;

import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.VgrdetailproductcategoryDao;
import com.app.wms.engine.db.dao.WarehouseDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dao.spring.GoodReceiveDao;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Goodreceive;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.Warehouse;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Vgrdetailproduct;
import com.app.wms.engine.db.dto.Vgrdetailproductcategory;
import com.app.wms.web.util.AppConstant;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class GoodReceiveController extends ReportManagerController 
{
    
    @Autowired
    private GoodReceiveDao goodReceiveDao;
    
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
                LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
	            HashMap m = null;

	            final String mode = request.getParameter("mode");
	            if (mode != null && mode.equals("edit")) {
	                m = this.getModelByPrimaryKey(request);
	                m.put("mode", "edit");
	                return new ModelAndView("2_receive/GoodReceiveEdit", "model", m);
	            } else {
	                
	                String grcode = request.getParameter("grcode");
	                String grdate = request.getParameter("grdate");
	                
	                m = this.searchAndPaging(request, response);
	                
	                if(grcode == null && grdate == null) {
	                    m.put("grs", goodReceiveDao.getGR());                                    
	                } else {
	                    m.put("grs", goodReceiveDao.getGR(grcode, grdate));
	                     m.put("grcode", grcode == null? "" : grcode);
	                    m.put("grdate", grdate == null? "":grdate);
	                }
	                
	                return new ModelAndView("2_receive/GoodReceiveList", "model", m);
	                
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

//            WhDao dao = DaoFactory.createWhDao();
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
            java.lang.String whCode = request.getParameter("whCode");
            WhDao dao = DaoFactory.createWhDao();
            Wh dto = null;

            String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                dto = dao.findByPrimaryKey(whCode);
            }

            if (dto == null) {
                dto = new Wh();
                dto.setWhCode("");
                dto.setName("");
                dto.setIsActive("Y");
            }

            HashMap m = new HashMap();
            DepartmentDao daoDep = DaoFactory.createDepartmentDao();
            WarehouseDao daoWh = DaoFactory.createWarehouseDao();
            List<Department> dropListDepartment = daoDep.findAll();
    		List<Warehouse> dropListWarehouse = daoWh.findAll();
    		 
   		    m.put("dropListDepartment", dropListDepartment);
   		    m.put("dropListWarehouse", dropListWarehouse);
            
            m.put("dto", dto);

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

        Wh dto = dao.findByPrimaryKey(whCode);

        if (dto != null) {
            dto.setIsActive(AppConstant.STATUS_FALSE);
            dto.setUpdatedBy(pcreatedBy+"");
            dto.setUpdatedDate(new java.util.Date());
            dao.update(dto.createPk(), dto);
        }

        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("2_receive/GoodReceiveList", "model", m);
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
		
			return new ModelAndView( "2_receive/GoodReceiveList", "result", dto );
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
		
			return new ModelAndView( "2_receive/GoodReceiveList", "result", dto );
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
		
			return new ModelAndView( "2_receive/GoodReceiveList", "result", dto );
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
        String whCode = "";
        String corpId = "";
        
        if (lu == null) {
            String msg = "You haven't login or your session has been expired! Please do login again";
            m.put("msg", msg);
            return new ModelAndView("login", "model", m);
        }else{
        	corpId = lu.getCorpId();
        	whCode = lu.getWhCode();
        }
        
        int size = goodReceiveDao.getGR(corpId, whCode).size()+1;
                
		m.put("lotid", size);
//      m.put("grNumber", generateGRNumber(size, request));
		return new ModelAndView( "2_receive/GoodReceiveAdd", "model", m);
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
              dto = dao.findByPrimaryKey(whCode);
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
              dao.insert(dto);
          } else {
              dto.setUpdatedBy(userId+"");
              dto.setUpdatedDate(new java.util.Date());
              dao.update(dto.createPk(), dto);
          }

          return new ModelAndView("2_receive/GoodReceiveView", "dto", dto);

      } catch (org.springframework.dao.DataIntegrityViolationException e) {
          String errorMsg = "Unique Key Constraint [Code, Name]!" + AppConstant.EOL;
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println(errorMsg);
          if (isCreate) {
              return new ModelAndView("2_receive/GoodReceiveAdd", "model", m);
          } else {
              return new ModelAndView("2_receive/GoodReceiveEdit", "model", m);
          }
      } catch (Exception e) {
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);
          System.out.println(errorMsg);
          if (isCreate) {
              return new ModelAndView("2_receive/GoodReceiveAdd", "model", m);
          } else {
              return new ModelAndView("2_receive/GoodReceiveEdit", "model", m);
          }
      }

  }
        
        
        @Transactional
	    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        /*
	         *  GET LOGIN USER
	         */
	        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
	        String grNo = request.getParameter("grNo");
	        HashMap model = new HashMap();
            
	        VgrdetailproductcategoryDao dao = DaoFactory.createVgrdetailproductcategoryDao();
	        
	        List<Vgrdetailproductcategory> listSearch	= dao.getGRDetail(grNo);
	        
	        //List<Vgrdetailproductcategory> listSearch	= goodReceiveDao.getGRDetail(grNo);
            System.out.println("grNo "+grNo);
	        
	        if(listSearch != null){
	            System.out.println("[grNo][Ajax Document] grNo : " + grNo + " is valid");
	           
	            Map tableMap = new HashMap();
	            for (Vgrdetailproductcategory searchDetail : listSearch){
	            	
	            	Map returnMap = new HashMap();
                        String gr_number    = (searchDetail).getGrnumber();
                        String productCode  = (searchDetail).getProductcode();
                        String productName  = (searchDetail).getProductName();
                        String status  = (searchDetail).getQtydmg() == 0? "Good" : "Damage";
                        String remark  = (searchDetail).getRemark();
                        Date expdate  = (searchDetail).getExpirydate();
                        Integer quantity    	= (searchDetail).getQtydmg() == 0?(searchDetail).getQtygood():(searchDetail).getQtydmg() ;
                        Integer quantityPo    	= (searchDetail).getQtyreal();
                                               
                        returnMap.put("gr_number",grNo);
                        returnMap.put("productCode",productCode);
                        returnMap.put("productName",productName);
                        returnMap.put("quantity",quantity);
                        returnMap.put("quantityPo",quantityPo);
                        returnMap.put("status",status);
                        returnMap.put("remark",remark);
                        returnMap.put("expdate",expdate);
                        
                         logger.debug(returnMap);


                        tableMap.put(returnMap, returnMap);
                    }
	            
	            model.put("master", listSearch);
	            model.put("tableMap", tableMap);
	            
	        } else{
	            System.out.println("[grNo][Ajax Document] purchase order no : " + grNo + " is not valid");
	        }
         
	        return new ModelAndView("2_receive/util/GoodReceiveDetail", "model", model);
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
         
         private String generateGRNumber(int size, HttpServletRequest request) {
             // GRWHYYYYXXXXXXX
             LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
             String head = "GR";             
             String wh = lu.getWhCode().replace("WH-", "");             
             String year =  new SimpleDateFormat("yyyy").format(new Date());
             String tail = ("0000000"+size).substring(("0000000"+size).length()-7);
             
             return head+wh+year+tail;
         }
        

}
