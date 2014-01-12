package com.app.wms.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.app.web.engine.search.PurchaseSearch;
import com.app.web.engine.search.WarehouseSearch;
import com.app.wms.engine.db.dao.ApprovalRangeDao;
import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.PoDetailDao;
import com.app.wms.engine.db.dao.ProductCategoryDao;
import com.app.wms.engine.db.dao.PurchaseDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dao.WhDao;
import com.app.wms.engine.db.dao.spring.PODao;
import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.PoDetail;
import com.app.wms.engine.db.dto.ProductCategory;
import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.Uom;
import com.app.wms.engine.db.dto.Wh;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.hbm.bean.Po;
import com.app.wms.hbm.bean.Vgrdetailproduct;
import com.app.wms.web.util.AppConstant;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

public class PurchaseController extends ReportManagerController {

    
    @Autowired
    private PODao poDao;
    
    /**
     * Method 'findByPrimaryKey'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    
    public ModelAndView findByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            
            HashMap m = null;
            final String mode = request.getParameter("mode");
            if (mode != null && mode.equals("edit")) {
                m = this.getModelByPrimaryKey(request);
                m.put("mode", "edit");
                return new ModelAndView("2_receive/PurchaseOrderEdit", "model", m);
            } else {

                String purchaseNo = request.getParameter("purchaseNo");
                String estimationDeliveryDate = request.getParameter("estimationDeliveryDate");
                
                m = this.searchAndPaging(request, response);
                return new ModelAndView("2_receive/PurchaseOrderList", "model", m);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
        }

    }
    private HashMap searchAndPaging(HttpServletRequest request, HttpServletResponse response) throws Exception {
       try {
        	LoginUser lu =  (LoginUser) request.getSession().getAttribute("user");
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

            String roleCode = lu.getRoleCode();
            
            PoDetail pd = new PoDetail();
            pd.setPonumber(request.getParameter("purchaseNo"));
            
            PoDetailDao dao = DaoFactory.createPoDetailDao();
            List<PoDetail> listSearchPage = dao.findPoDetailPaging(pd,page);
            
            ApprovalRangeDao daoAppRange = DaoFactory.createApprovalRangeDao();
            List<ApprovalRange> listAppRange = daoAppRange.findAll();

            int total = 2000;
            
            m.put("app", listAppRange);
            m.put("roleCode", roleCode);
            m.put("pos", listSearchPage);
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

            HashMap m = new HashMap();
            DepartmentDao daoDep = DaoFactory.createDepartmentDao();
            SupplierDao daoSupp = DaoFactory.createSupplierDao();
            CurrencyDao daoCurr = DaoFactory.createCurrencyDao();
            ApprovalRangeDao daoApp = DaoFactory.createApprovalRangeDao();
            
            List<Department> dropListDepartment = daoDep.findAll();
    		List<Supplier> dropListSupplier = daoSupp.findAll();
    		List<Currency> dropListCurrency = daoCurr.findAll();
    		List<ApprovalRange> dropListAppRange = daoApp.findAll();
    		 
   		    m.put("dropListDepartment", dropListDepartment);
   		    m.put("dropListSupplier", dropListSupplier);
   		    m.put("dropListCurrency", dropListCurrency);
   		    m.put("dropListAppRange", dropListAppRange);

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
        return new ModelAndView("2_receive/PurchaseOrderList", "model", m);
    }
    
    public ModelAndView approvedPO(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ponumber = request.getParameter("purchaseNo");
        
        PoDetail pd = new PoDetail();
        Po poh = new Po();
        poh.setStatus("Y");
        poh.setStatusdate(new Date());
        pd.setPonumber(ponumber);
        pd.setPoh(poh);
        PoDetailDao dao = DaoFactory.createPoDetailDao();
        dao.update(pd);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("2_receive/PurchaseOrderList", "model", m);
    }
    
    public ModelAndView cancelPO(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ponumber = request.getParameter("purchaseNo");
        
        PoDetail pd = new PoDetail();
        Po poh = new Po();
        poh.setStatus("X");
        poh.setStatusdate(new Date());
        pd.setPonumber(ponumber);
        pd.setPoh(poh);
        PoDetailDao dao = DaoFactory.createPoDetailDao();
        dao.update(pd);
        
        HashMap m = this.searchAndPaging(request, response);
        return new ModelAndView("2_receive/PurchaseOrderList", "model", m);
    }

    /**
     * Method 'findAll'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            WhDao dao = DaoFactory.createWhDao();

            List<Wh> dto = dao.findAll();

            return new ModelAndView("2_receive/PurchaseOrderList", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
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
    public ModelAndView findWhereWhCodeEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String pwhCode = request.getParameter("whCode");

            // create the DAO class
            WhDao dao = DaoFactory.createWhDao();

            // execute the finder
            List<Wh> dto = dao.findWhereWhCodeEquals(pwhCode);

            return new ModelAndView("2_receive/PurchaseOrderList", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
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
    public ModelAndView findWhereNameEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // parse parameters
            java.lang.String pname = request.getParameter("name");

            // create the DAO class
            WhDao dao = DaoFactory.createWhDao();

            // execute the finder
            List<Wh> dto = dao.findWhereNameEquals(pname);

            return new ModelAndView("2_receive/PurchaseOrderList", "result", dto);
        } catch (Throwable e) {
            e.printStackTrace();
            return new ModelAndView("Error", "th", e);
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
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = this.getModelByPrimaryKey(request);
        m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");
        m.put("hash", new Date().getTime());
        return new ModelAndView("2_receive/PurchaseOrderAdd", "model", m);
    }

    /**
     * Method 'save'
     *
     * @param request
     * @param response
     * @throws Exception
     * @return ModelAndView
     */
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

            return new ModelAndView("2_receive/PurchaseOrderView", "dto", dto);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String errorMsg = "Unique Key Constraint [Code, Name]!" + AppConstant.EOL;
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            if (isCreate) {
                return new ModelAndView("2_receive/PurchaseOrderAdd", "model", m);
            } else {
                return new ModelAndView("2_receive/PurchaseOrderEdit", "model", m);
            }
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            HashMap m = this.getModelByPrimaryKey(request);
            m.put("mode", mode);
            m.put("msg", errorMsg);
            System.out.println(errorMsg);
            if (isCreate) {
                return new ModelAndView("2_receive/PurchaseOrderAdd", "model", m);
            } else {
                return new ModelAndView("2_receive/PurchaseOrderEdit", "model", m);
            }
        }

    }
    
    public ModelAndView listProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap m = this.getModelByPrimaryKey(request);
        m = this.getModelByPrimaryKey(request);
        m.put("mode", "create");        
        return new ModelAndView("10_component/PickListProductPO", "model", m);                
    }
    
    @Transactional
    public ModelAndView ajaxDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
         *  GET LOGIN USER
         */
        LoginUser loginUser = (LoginUser) request.getSession().getAttribute("user");
        String poNo = request.getParameter("poNo");
        HashMap model = new HashMap();

        
        List<Vgrdetailproduct> listSearch	= poDao.getDetail(poNo);
            System.out.println("poNo "+poNo);
        
        if(listSearch != null){
            System.out.println("[poNo][Ajax Document] sales order no : " + poNo + " is valid");
           
            Map tableMap = new HashMap();
            for (Vgrdetailproduct searchDetail : listSearch){
            	
            	Map returnMap = new HashMap();
                    String po_number    = (searchDetail).getPonumber();
                    String productCode  = (searchDetail).getProductCode();
                    String productName  = (searchDetail).getProductName();
                    BigDecimal quantity    	= (searchDetail).getQty();

                    returnMap.put("po_number",po_number);
                    returnMap.put("productCode",productCode);
                    returnMap.put("productName",productName);
                    returnMap.put("quantity",quantity);

                    tableMap.put(returnMap, returnMap);
                }
            
            model.put("master", listSearch);
            model.put("tableMap", tableMap);
            
        } else{
            System.out.println("[PurchaseOrderDetail][Ajax Document] purchase order no : " + poNo + " is not valid");
        }

        return new ModelAndView("2_receive/util/PurchaseOrderDetail", "model", model);
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
    
}
