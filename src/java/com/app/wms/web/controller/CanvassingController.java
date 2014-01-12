package com.app.wms.web.controller;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.app.wms.engine.db.dao.CanvassingDao;
import com.app.wms.engine.db.dao.CanvassingDetailDao;
import com.app.wms.engine.db.dao.CurrencyDao;
import com.app.wms.engine.db.dao.DepartmentDao;
import com.app.wms.engine.db.dao.SupplierDao;
import com.app.wms.engine.db.dto.Canvassing;
import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.Currency;
import com.app.wms.engine.db.dto.Department;
import com.app.wms.engine.db.dto.Supplier;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.AppConstant;

public class CanvassingController extends ReportManagerController {
	
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
                return new ModelAndView("2_receive/CanvassEdit", "model", m);
            } else {

                m = this.searchAndPaging(request, response);
                return new ModelAndView("2_receive/CanvassList", "model", m);
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

            Canvassing c = new Canvassing();
            c.setPrsnumber(request.getParameter("prsnumber"));
          
            CanvassingDetailDao dao = DaoFactory.createCanvassingDetailDao();
            //List<Canvassing> listSearchPage = dao.findCanvassingPaging(c,page);
            List<CanvassingDetail> listSearchPage = dao.findAllList();
            
            int total = 2000; 
            m.put("canvassing", listSearchPage);
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
		 CanvassingDao dao = DaoFactory.createCanvassingDao();
         Canvassing dto = new Canvassing();
         CanvassingDetailDao daod = DaoFactory.createCanvassingDetailDao();
         CanvassingDetail dtod = new CanvassingDetail();

         String mode = request.getParameter("mode");
         String prsnumber = "";
         String productcode = "";
         
         if (mode != null && mode.equals("edit")) {
        	 if(request.getParameter("id") != null){
        	   Integer id = Integer.parseInt(request.getParameter("id"));
        	   dtod = daod.findByPrimaryKey(id);
        	 }
        	 prsnumber = request.getParameter("prsnumber");
             dto = dao.findWherePrsnumber(prsnumber);
             dtod = daod.findWherePrsnumber(prsnumber);
            
         }

         List<CanvassingDetail> cd = new ArrayList <CanvassingDetail>();
         if(request.getParameter("id")!=null){
        	 productcode = request.getParameter("productcode");
        	 cd = daod.findWhereCanvassingDetail(Integer.parseInt(request.getParameter("id")), prsnumber, productcode);
         }
         
         SupplierDao daoSupp = DaoFactory.createSupplierDao();
 		 List<Supplier> dropListSupplier = daoSupp.findAll();
		    
 		 System.out.println("dto ="+dto);
 		 System.out.println("dtod ="+dtod);
 		 
         HashMap m = new HashMap();
         m.put("dropListSupplier", dropListSupplier);
         m.put("dto", dto);
         m.put("dtod", dtod);
         m.put("cd", cd);
         
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
			
			CanvassingDao dao = DaoFactory.createCanvassingDao();
			List<Canvassing> dto = dao.findAll();
			
			return new ModelAndView( "2_receive/CanvassList", "model", dto);
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
		
		CanvassingDao dao = DaoFactory.createCanvassingDao();
		
		return new ModelAndView( "2_receive/CanvassAdd", "model", map);
	}
	
	public ModelAndView canvassingForm(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return new ModelAndView( "2_receive/CanvassForm");
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
      Canvassing dto = null;
      CanvassingDetail dtod = null;
      try {
          if (mode.equalsIgnoreCase("create")) {
              isCreate = true;
          } else {
              isCreate = false;
          }

          CanvassingDao dao = DaoFactory.createCanvassingDao();
          CanvassingDetailDao daod = DaoFactory.createCanvassingDetailDao();
          if (isCreate) {
        	  dto = new Canvassing();
          } else {
        	 
        		  Integer id = Integer.parseInt(request.getParameter("id"));
        		  dto = dao.findByPrimaryKey(id);
        		  dtod = daod.findByPrimaryKey(id);
        	 
          }
          
          String prsnumber = request.getParameter("prsnumber");
          String canvassername = request.getParameter("canvassername");
          String canvassingdate = request.getParameter("canvassingdate");
          String remarks = request.getParameter("remarks");
          
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
          
          String[] productcode1s = request.getParameterValues("productCode1");
          String[] productname = request.getParameterValues("productName1");
          String[] uom = request.getParameterValues("uom");
          String[] qtys = request.getParameterValues("qty");
          String[] unitprice = request.getParameterValues("unitprice1");
          
          String[] warranty = request.getParameterValues("warranty1");
          String[] termpayment = request.getParameterValues("termpayment1");
          String[] termdelivery = request.getParameterValues("termdelivery1");
          String[] discount = request.getParameterValues("discount1");
          String[] pph = request.getParameterValues("pph1");
          String[] ppn = request.getParameterValues("ppn1");
          
          String[] suppliercode = request.getParameterValues("supplier1");
         
          if (isCreate) {
        	  dto.setCreatedby(userId);
        	  
        	  for (int i = 0; i < productcode1s.length; i++) {
            	  CanvassingDetail cd = new CanvassingDetail();
            	  cd.setPrsnumber(prsnumber);
            	  cd.setSupplierCode(suppliercode[i]);
            	  cd.setProductcode(productcode1s[i]);
            	  cd.setProductname(productname[i]);
            	  cd.setPriceunit(BigDecimal.ZERO);
            	  /*
            	  cd.setWarranty(new SimpleDateFormat("dd/MM/yyyy").parse(warranty[i] + ""));
            	  cd.setTermpayment(termpayment[i]);
            	  cd.setTermdelivery(termdelivery[i]);
            	  cd.setDiscount(new BigDecimal(discount[i].replaceAll(",", "")));
            	  cd.setPph(new BigDecimal(pph[i].replaceAll(",", "")));
            	  cd.setPpn(new BigDecimal(ppn[i].replaceAll(",", "")));
            	  */
            	  cd.setIsSelected("N");
            	  daod.insert(cd);
              }
             
          }

          dto = new Canvassing();
          dto.setPrsnumber(prsnumber);
          dto.setCanvassername(canvassername);
          dto.setCanvassingdate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(canvassingdate + ""));
          dto.setRemarks(remarks);
          dto.setCreatedby(userId);
          
          if (strError.length() > 0) {
              throw new Exception(strError);
          }

          if (isCreate) {
        	  
        	  List<Canvassing> tmp =  dao.findWherePrsnumberEquals(prsnumber);
        	  if ((isCreate && tmp != null && tmp.size() > 0) || (!isCreate && tmp != null && tmp.size() > 0 && !tmp.get(0).getPrsnumber().equals(prsnumber))) {
    	  		 
    	  	  }else{
    	  		dao.insert(dto);
    	  	  }
              
          } 
          
          else {

              dto.setPrsnumber(prsnumber);
              dto.setCanvassername(canvassername);
              dto.setCanvassingdate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(canvassingdate + ""));
              dto.setRemarks(remarks);
              dto.setCreatedby(userId);
              dao.updateDto(dto);
              
              for (int i = 0; i < productcode1s.length; i++) {
            	 
            	  dtod.setPrsnumber(prsnumber);
            	  dtod.setSupplierCode(suppliercode[i]);
            	  dtod.setProductcode(productcode1s[i]);
            	  dtod.setProductname(productname[i]);
            	  dtod.setPriceunit(new BigDecimal(unitprice[i].replaceAll(",", "")));
            	  
            	  dtod.setWarranty(new SimpleDateFormat("dd/MM/yyyy").parse(warranty[i] + ""));
            	  dtod.setTermpayment(termpayment[i]);
            	  dtod.setTermdelivery(termdelivery[i]);
            	  dtod.setDiscount(new BigDecimal(discount[i].replaceAll(",", "")));
            	  dtod.setPph(new BigDecimal(pph[i].replaceAll(",", "")));
            	  dtod.setPpn(new BigDecimal(ppn[i].replaceAll(",", "")));
            	  
            	  dtod.setIsSelected(request.getParameter("selected"));
            	  
            	  daod.updateDto(dtod);
              }  
             
             
          }
          
          
          Map m = new HashMap();
          List<CanvassingDetail> listSearchPage = daod.findAll();
      	  m.put("canvassing", listSearchPage);
	      return new ModelAndView("2_receive/CanvassList","model",m);
		 

      } catch (Exception e) {
    	  e.printStackTrace();
          String errorMsg = e.getMessage();
          HashMap m = this.getModelByPrimaryKey(request);
          m.put("mode", mode);
          m.put("msg", errorMsg);

          if (isCreate) {
              return new ModelAndView("2_receive/CanvassAdd", "model", m);
          } else {
              return new ModelAndView("2_receive/CanvassEdit", "model", m);
          }
      }
      
  }
	
	public void doSelected(HttpServletRequest request, HttpServletResponse response)throws Exception {
		System.out.println("selected");
	}
	
    public void doPrintResult(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
		String prsnumber = request.getParameter("prsnumber");
		System.out.println("prsnumber ="+prsnumber);
		
		templateName = request.getParameter("templateName");
		System.out.println("templateName ="+templateName);
		
		parametersKey = request.getParameter("parametersKey");
		System.out.println("parameterKey ="+parametersKey);
		
		ArrayList resultList = new ArrayList();
		resultList.add(prsnumber);
		setParameterValues(resultList);
		
		List paramKey = new ArrayList();
		paramKey.add(parametersKey);
		setParameterKeys((ArrayList<String>) paramKey);
		outputFormat = "pdf";
		createOnlineReport();
		
		try{
			printToStream(response);
			
		}catch(FileNotFoundException ex){
               ex.printStackTrace();
                    
		}catch(Exception ex){
               ex.printStackTrace();
		}
		
	}
    
    public void doPrintForm(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
		String suppliercode = request.getParameter("suppliercode");
		String prsnumber = request.getParameter("prsnumber");

		templateName = request.getParameter("templateName");
		System.out.println("templateName ="+templateName);
		
		parametersKey = request.getParameter("parametersKey");
		System.out.println("parameterKey ="+parametersKey);
		
		ArrayList resultList = new ArrayList();
		resultList.add(suppliercode+"-"+prsnumber);
		setParameterValues(resultList);
		
		List paramKey = new ArrayList();
		paramKey.add(parametersKey);
		setParameterKeys((ArrayList<String>) paramKey);
		
		String myOutputFormat = request.getParameter("outputFormat");
		
		if(myOutputFormat == null)
			outputFormat = "pdf";
		else if(myOutputFormat.equals("xls"))
			outputFormat = "xls";
		createOnlineReport();
		
		try{
			printToStream(response);
			
		}catch(FileNotFoundException ex){
            ex.printStackTrace();
                    
		}catch(Exception ex){
            ex.printStackTrace();
		}
		
	}
	
}
